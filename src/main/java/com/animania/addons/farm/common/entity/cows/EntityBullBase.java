package com.animania.addons.farm.common.entity.cows;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBullBase extends EntityAnimaniaCow implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean>defineId(EntityBullBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean>defineId(EntityBullBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>defineId(EntityBullBase.class, DataSerializers.OPTIONAL_UUID);

	public EntityBullBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 1.8F);
		this.width = 1.6F;
		this.height = 1.8F;
		this.gender = EntityGender.MALE;
		this.stepHeight = 1.1F;
		this.mateable = true;

		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !getSterilized())
		{
			this.tasks.addTask(0, new EntityAIAttackMeleeBulls(this, 1.8D, false));
		}
		// this.tasks.addTask(1, new EntityAIFollowMateCows(this, 1.1D));
		if (!getSterilized())
			this.tasks.addTask(3, new GenericAIMate<EntityBullBase, CowEntityBase>(this, 1.0D, CowEntityBase.class, EntityCalfBase.class, EntityAnimaniaCow.class));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityBullBase.FIGHTING, false);
		this.dataManager.register(EntityBullBase.STERILIZED, false);
		this.dataManager.register(EntityBullBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void setInLove(PlayerEntity player)
	{
		if (!this.getFighting() && !this.getSleeping())
		{
			this.world.setEntityState(this, (byte) 18);
		}
	}

	public boolean getFighting()
	{
		return this.getBoolFromDataManager(FIGHTING);
	}

	public void setFighting(boolean fighting)
	{
		this.dataManager.set(EntityBullBase.FIGHTING, fighting);
	}

	@Override
	public void setAttackTarget(@Nullable LivingEntity LivingEntityIn)
	{
		super.setAttackTarget(LivingEntityIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.getSleeping())
		{
			this.setSleeping(false);
		}

		if (this.isEntityInvulnerable(source))
			return false;
		else
			return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = false;
		if (this.canEntityBeSeen(entityIn) && this.getDistance(entityIn) <= 2.0F)
		{
			flag = entityIn.attackEntityFrom(new EntityDamageSource("bull", this), 5.0F);

			if (flag)
				this.applyEnchantments(this, entityIn);

			// Custom Knockback
			if (entityIn instanceof PlayerEntity)
				((LivingEntity) entityIn).knockBack(this, 1, (this.getX() - entityIn.getX()) / 2, (this.getZ() - entityIn.getZ()) / 2);
		}

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.bullMoo1, FarmAddonSoundHandler.bullMoo2, FarmAddonSoundHandler.bullMoo3, FarmAddonSoundHandler.bullMoo4, FarmAddonSoundHandler.bullMoo5, FarmAddonSoundHandler.bullMoo6, FarmAddonSoundHandler.bullMoo7, FarmAddonSoundHandler.bullMoo8, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo8, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo8);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.angryBull1, FarmAddonSoundHandler.angryBull2, FarmAddonSoundHandler.angryBull3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.cowDeath1 : FarmAddonSoundHandler.cowDeath2;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, CowEntityBase.class);

		super.onLivingUpdate();
	}

	@SideOnly(Dist.CLIENT)
	public float getHeadAnchorPointY(float p_70894_1_)
	{
		if (this.getFighting())
			return 0;
		else
			return super.getHeadAnchorPointY(p_70894_1_);
	}

	@SideOnly(Dist.CLIENT)
	public float getHeadAngleX(float p_70890_1_)
	{
		if (this.getFighting())
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
		else
			return super.getHeadAngleX(p_70890_1_);
	}

	@Override
	public EntityBullBase createChild(AgeableEntity p_90011_1_)
	{
		return null;
	}

	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		super.writeEntityToNBT(compound);

		compound.putBoolean("Fighting", this.getFighting());
	}

	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		super.readEntityFromNBT(compound);

		this.setFighting(compound.getBoolean("Fighting"));
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null)
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public DataParameter<Boolean> getSterilizedParam()
	{
		return STERILIZED;
	}

	@Override
	public void sterilize()
	{
		Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
		while (it.hasNext())
		{
			EntityAITaskEntry entry = it.next();
			EntityAIBase ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof EntityAIAttackMeleeBulls)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
