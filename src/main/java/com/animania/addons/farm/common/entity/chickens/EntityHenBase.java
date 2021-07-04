package com.animania.addons.farm.common.entity.chickens;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.chickens.ai.EntityAIFindNest;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHenBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final DataParameter<Boolean> LAID = EntityDataManager.<Boolean> defineId(EntityHenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> LAID_TIMER = EntityDataManager.<Integer> defineId(EntityHenBase.class, DataSerializers.INT);

	public EntityHenBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.7F);
		this.width = 0.5F;
		this.height = 0.7F;
		this.tasks.addTask(6, new EntityAIFindNest(this, 1.0D));
		this.tasks.addTask(9, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(10, new EntityAIAttackMelee(this, 1.0D, true));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			AddonInjectionHandler.runInjection("extra", "attackFrogs", null, this);
		}
		this.gender = EntityGender.FEMALE;

	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		if (this.world.isRemote)
			return null;

		List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 64, this.world, this.getPosition());

		if (others.size() <= 4)
		{

			int chooser = this.rand.nextInt(3);

			if (chooser == 0)
			{
				EntityRoosterBase entityChicken = this.type.getMale(world);
				entityChicken.setPosition(this.posX, this.posY, this.posZ);
				AnimaniaHelper.spawnEntity(this.world, entityChicken);
			} else if (chooser == 1)
			{
				EntityChickBase entityChicken = this.type.getChild(world);
				entityChicken.setPosition(this.posX, this.posY, this.posZ);
				AnimaniaHelper.spawnEntity(this.world, entityChicken);
			}

		}

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

		if (this.rand.nextFloat() < 0.05F)
			this.setLeftHanded(true);
		else
			this.setLeftHanded(false);

		return livingdata;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{

		if (this.getSleeping())
		{
			this.setSleeping(false);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		AddonInjectionHandler.runInjection("extra", "eatFrogs", null, entityIn, this);

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}

		return flag;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityHenBase.LAID, true);
		this.dataManager.register(EntityHenBase.LAID_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100)));
		this.timeUntilNextEgg = 6000;
	}

	@Override
	public void writeEntityToNBT(CompoundNBT CompoundNBT)
	{
		super.writeEntityToNBT(CompoundNBT);
		CompoundNBT.setBoolean("Laid", this.getLaid());
		CompoundNBT.setInteger("EggLayTime", timeUntilNextEgg);
		CompoundNBT.setInteger("LaidTimer", this.getLaidTimer());
	}

	@Override
	public void readEntityFromNBT(CompoundNBT CompoundNBT)
	{
		super.readEntityFromNBT(CompoundNBT);
		this.timeUntilNextEgg = CompoundNBT.getInteger("EggLayTime");
		this.setLaid(CompoundNBT.getBoolean("Laid"));
		this.setLaidTimer(CompoundNBT.getInteger("LaidTimer"));
	}

	public int getLaidTimer()
	{
		return this.getIntFromDataManager(LAID_TIMER);
	}

	public void setLaidTimer(int laidtimer)
	{
		this.dataManager.set(EntityHenBase.LAID_TIMER, Integer.valueOf(laidtimer));
	}

	@Override
	public void onLivingUpdate()
	{

		if (!FarmConfig.settings.chickensDropEggs)
		{
			this.timeUntilNextEgg = 1000;
		}

		super.onLivingUpdate();

		int laidTimer = this.getLaidTimer();

		if (laidTimer > -1)
		{
			laidTimer--;
			this.setLaidTimer(laidTimer);
		} else
		{
			this.setLaid(false);
		}
	}

	public boolean getLaid()
	{
		return this.getBoolFromDataManager(LAID);
	}

	public void setLaid(boolean laid)
	{
		if (laid)
		{
			this.dataManager.set(EntityHenBase.LAID, true);
			this.setLaidTimer(AnimaniaConfig.careAndFeeding.laidTimer + this.rand.nextInt(100));
		} else
			this.dataManager.set(EntityHenBase.LAID, false);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			EntityHenBase ehb = (EntityHenBase) entity;
			int timer = ehb.getLaidTimer();
			if (timer >= 0)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			} else
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay2"));
			}
		}
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
