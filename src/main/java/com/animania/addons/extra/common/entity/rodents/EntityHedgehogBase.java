package com.animania.addons.extra.common.entity.rodents;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.rodents.ai.EntityAIHedgehogFindNests;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.compat.top.TOPInfoProviderRodent;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.*;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Function;

public class EntityHedgehogBase extends TameableEntity implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean> createKey(EntityHedgehogBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean> createKey(EntityHedgehogBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean> createKey(EntityHedgehogBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer> createKey(EntityHedgehogBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean> createKey(EntityHedgehogBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float> createKey(EntityHedgehogBase.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean> createKey(EntityHedgehogBase.class, DataSerializers.BOOLEAN);

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.hedgehogFood));

	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass<EntityHedgehogBase> entityAIEatGrass;
	protected int damageTimer;
	protected HedgehogType type;

	public EntityHedgehogBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.width = 0.5F;
		this.height = 0.5F;
		this.stepHeight = 1.1F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = (AnimaniaConfig.careAndFeeding.waterTimer * 2) + this.rand.nextInt(200);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.entityAIEatGrass = new GenericAIEatGrass(this, false);
		this.tasks.addTask(12, this.entityAIEatGrass);

		this.initAI();
	}

	@Override
	public float getEyeHeight()
	{
		return this.height - .4F;
	}

	protected void initAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(1, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(2, new GenericAIFindWater<EntityHedgehogBase>(this, 1.0D, entityAIEatGrass, EntityHedgehogBase.class, true));
			this.tasks.addTask(3, new EntityAIHedgehogFindNests(this, 1.0D));
			this.tasks.addTask(4, new GenericAIFindFood<EntityHedgehogBase>(this, 1.0D, entityAIEatGrass, false));
		}
		this.tasks.addTask(5, this.aiSit);
		this.tasks.addTask(6, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(8, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(9, new GenericAITempt<EntityHedgehogBase>(this, 1.2D, false, EntityHedgehogBase.TEMPTATION_ITEMS));
		this.tasks.addTask(10, new GenericAIPanic<EntityHedgehogBase>(this, 1.5D));
		this.tasks.addTask(11, new GenericAIFollowOwner<EntityHedgehogBase>(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(13, new GenericAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(14, new GenericAIWatchClosest(this, PlayerEntity.class, 6.0F));
		this.tasks.addTask(15, new GenericAILookIdle<EntityHedgehogBase>(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(16, new GenericAISleep(this, 0.8, Block.getBlockFromName(ExtraConfig.settings.hedgehogBed), Block.getBlockFromName(ExtraConfig.settings.hedgehogBed2), EntityHedgehogBase.class, new Function<Long, Boolean>() {

					@Override
					public Boolean apply(Long worldtime)
					{		
						return (worldtime < 13000);
					}
					
				}));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.targetTasks.addTask(1, new GenericAINearestAttackableTarget<SilverfishEntity>(this, SilverfishEntity.class, false));
			this.targetTasks.addTask(2, new GenericAINearestAttackableTarget<EntityFrogs>(this, EntityFrogs.class, false));
			this.targetTasks.addTask(3, new GenericAINearestAttackableTarget<EntityToad>(this, EntityToad.class, false));

			AddonInjectionHandler.runInjection("farm", "avoidRooster", Void.class, this.tasks, this);
		}
		this.targetTasks.addTask(13, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void setInLove(PlayerEntity player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "hedgehog");
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityHedgehogBase.FED, true);
		this.dataManager.register(EntityHedgehogBase.WATERED, true);
		// this.dataManager.register(EntityHedgehogBase.TAMED, false);
		// this.dataManager.register(EntityHedgehogBase.SITTING, false);
		this.dataManager.register(EntityHedgehogBase.RIDING, false);
		this.dataManager.register(EntityHedgehogBase.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityHedgehogBase.SLEEPING, false);
		this.dataManager.register(EntityHedgehogBase.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);

	}

	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		super.writeEntityToNBT(compound);
		compound.putBoolean("IsTamed", this.isTamed());
		compound.putBoolean("IsSitting", this.isSitting());
		compound.putBoolean("Riding", this.isHedgehogRiding());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		super.readEntityFromNBT(compound);
		this.setTamed(compound.getBoolean("IsTamed"));
		this.setSitting(compound.getBoolean("IsSitting"));
		this.setHedgehogRiding(compound.getBoolean("Riding"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public DataParameter<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public DataParameter<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ExtraAddonSoundHandler.hedgehogLiving1, ExtraAddonSoundHandler.hedgehogLiving2, ExtraAddonSoundHandler.hedgehogLiving3, ExtraAddonSoundHandler.hedgehogLiving4, ExtraAddonSoundHandler.hedgehogLiving5);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ExtraAddonSoundHandler.hedgehogHurt1, ExtraAddonSoundHandler.hedgehogHurt2);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ExtraAddonSoundHandler.hedgehogHurt1;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .5F, this.getSoundPitch());
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	private boolean interactRide(PlayerEntity PlayerEntity)
	{
		this.isRemoteMountEntity(PlayerEntity);
		return true;
	}

	private void isRemoteMountEntity(Entity par1Entity)
	{

		if (this.isHedgehogRiding())
		{
			this.setHedgehogRiding(true);
			this.startRiding(par1Entity, true);

		} else if (!this.isHedgehogRiding())
			this.dismountRidingEntity();

	}

	@Override
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		PlayerEntity PlayerEntity = player;

		if (stack == ItemStack.EMPTY && this.isTamed() && player.isSneaking() && !this.getSleeping())
		{
			ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (!props.isCarrying())
			{
				props.setAnimal(this.writeToNBT(new CompoundNBT()));
				props.setCarrying(true);
				props.setType(EntityList.getKey(this).getResourcePath());
				this.setDead();
				player.swingArm(EnumHand.MAIN_HAND);
				if(!player.level.isRemote)
					Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}
		}

		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof PlayerEntity)
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());

		return flag;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isSitting() || this.isSitting() || this.isRiding())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.clearPath();
			this.navigator.setSpeed(0);
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

		GenericBehavior.livingUpdateCommon(this);

		if (this.getCustomNameTag().equals("Sonic"))
			this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2, 4, false, false));
		else if (this.getCustomNameTag().equals("Sanic"))
		{
			this.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2, 3, false, false));
			this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2, 6, false, false));
		}

		if (this.tamedTimer > -1)
		{
			this.tamedTimer--;
			if (this.tamedTimer == 0)
			{
				this.tamedTimer = 120;

				if (this.isTamed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{

					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					// this.world.spawnParticle(EnumParticleTypes.HEART,
					// this.getX() + this.rand.nextFloat() * this.width -
					// this.width, this.getY() + 1D + this.rand.nextFloat() *
					// this.height, this.getZ() + this.rand.nextFloat() *
					// this.width - this.width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	public boolean isHedgehogRiding()
	{
		return this.getBoolFromDataManager(RIDING);
	}

	public void setHedgehogRiding(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityHedgehogBase.RIDING, true);
		else
			this.dataManager.set(EntityHedgehogBase.RIDING, false);
	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public DataParameter<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public EntityHedgehogBase createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, EntityGender.NONE));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return EntityGender.NONE;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BlockPos getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public int getBlinkTimer()
	{
		return blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		blinkTimer = i;
	}

	@Override
	public int getEatTimer()
	{
		return eatTimer;
	}

	@Override
	public void setEatTimer(int i)
	{
		eatTimer = i;
	}

	@Override
	public int getFedTimer()
	{
		return fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		fedTimer = i;
	}

	@Override
	public DataParameter<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return type;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return null;
	}
}
