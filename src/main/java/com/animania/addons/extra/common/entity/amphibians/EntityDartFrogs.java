package com.animania.addons.extra.common.entity.amphibians;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDartFrogs extends EntityAmphibian
{

	private static final DataParameter<Integer> FROGS_TYPE = EntityDataManager.<Integer> defineId(EntityDartFrogs.class, DataSerializers.INT);
	public int poisonTimer;
	private int jumpTicks;
	private int jumpDuration;
	private boolean canEntityJump;

	public EntityDartFrogs(World worldIn)
	{
		super(worldIn, true);
		this.poisonTimer = 2;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(this.rand.nextInt(3)));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		super.writeEntityToNBT(compound);
		compound.putInteger("FrogsType", this.getFrogsType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		super.readEntityFromNBT(compound);
		this.setFrogsType(compound.getInteger("FrogsType"));
	}

	public int getFrogsType()
	{
		return this.dataManager.get(EntityDartFrogs.FROGS_TYPE).intValue();
	}

	public void setFrogsType(int frogsId)
	{
		this.dataManager.set(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(frogsId));
	}

	@Override
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);
		PlayerEntity PlayerEntity = player;

		if (!stack.isEmpty() && stack.getItem() == Items.ARROW && this.poisonTimer <= 1)
		{
			this.poisonTimer = 800;
			player.playSound(SoundEvents.ENTITY_MAGMACUBE_SQUISH, 0.2F, 1.8F);
			ItemStack bob = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.addPotionToItemStack(bob, PotionTypes.POISON);
			stack.shrink(1);

			if (stack.getCount() == 0)
			{
				player.setHeldItem(hand, bob);
				return true;
			} else if (!player.inventory.addItemStackToInventory(bob))
			{
				player.dropItem(bob, false);
				return true;
			} else
				return super.processInteract(player, hand);
		} else
			return super.processInteract(player, hand);

	}

	@Override
	protected void collideWithEntity(Entity entityIn)
	{

		if (entityIn instanceof PlayerEntity && entityIn != this)
		{
			PlayerEntity player = (PlayerEntity) entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1, false, false));
		}
		entityIn.applyEntityCollision(this);
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.poisonTimer > 1)
			this.poisonTimer--;

		if (this.canEntityJump)
			if (this.jumpTicks != this.jumpDuration)
				++this.jumpTicks;
			else if (this.jumpDuration != 0)
			{
				this.jumpTicks = 0;
				this.jumpDuration = 0;
				this.setJumping(false);
			}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.onLivingUpdate();

		if (this.onGround)
			this.squishAmount = -0.5F;
		else if (!this.onGround)
			this.squishAmount = 0.5F;

		this.alterSquishAmount();

	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "dart_frog");
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob
	 * spawner, natural spawning etc, but not called when entity is reloaded
	 * from nbt. Mainly used for initializing attributes and inventory
	 */
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);

		this.setFrogsType(this.rand.nextInt(3));

		return livingdata;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{

		int chooser = Animania.RANDOM.nextInt(5);

		if (chooser == 0)
			return ExtraAddonSoundHandler.dartfrogLiving1;
		else if (chooser == 1)
			return ExtraAddonSoundHandler.dartfrogLiving2;
		else if (chooser == 2)
			return ExtraAddonSoundHandler.dartfrogLiving3;
		else if (chooser == 3)
			return ExtraAddonSoundHandler.dartfrogLiving4;
		else
			return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - this.getGrowingAge() * 2);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.04F, 1.1F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(AmphibianType.DART_FROG, EntityGender.NONE));
	}

	@Override
	public boolean usesEggColor()
	{
		return false;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return AmphibianType.DART_FROG;
	}

}
