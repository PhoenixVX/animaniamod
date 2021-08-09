package com.animania.addons.farm.common.entity.cows;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCalfBase extends EntityAnimaniaCow implements TOPInfoProviderChild, IChild
{
	protected static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(EntityCalfBase.class, DataSerializers.OPTIONAL_UUID);
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float> defineId(EntityCalfBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityCalfBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 3.6F);
		this.width = 1.6F;
		this.height = 3.6F;
		this.stepHeight = 1.1F;
		this.tasks.addTask(1, new GenericAIFollowParents<EntityCalfBase, CowEntityBase>(this, 1.1, CowEntityBase.class));
		this.ageTimer = 0;
		this.cowType = CowType.FRIESIAN;
		this.gender = EntityGender.CHILD;

	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityCalfBase.AGE, Float.valueOf(0));
		this.dataManager.register(EntityCalfBase.PARENT_UNIQUE_ID, Optional.<UUID> absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26000000298023224D);
	}

	@Override
	public void setInLove(PlayerEntity player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	public int getAgeTimer()
	{
		return ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		ageTimer = i;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.mooCalf1, FarmAddonSoundHandler.mooCalf2, FarmAddonSoundHandler.mooCalf3);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.mooCalf1, FarmAddonSoundHandler.mooCalf2, FarmAddonSoundHandler.mooCalf3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.mooCalf1, FarmAddonSoundHandler.mooCalf2, FarmAddonSoundHandler.mooCalf3);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.05F, 1.1F);
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, CowEntityBase.class);

		this.setSize((0.8f + this.getEntityAge()) * 2, (1.0f + this.getEntityAge()) * 2);

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean damaged, int looting)
	{
		return;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - (this.getEntityAge() * 2));
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && this.isCowBreedingItem(stack);
	}

	private boolean isCowBreedingItem(ItemStack itemIn)
	{
		return AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, itemIn) || itemIn.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public EntityCalfBase createChild(AgeableEntity e)
	{
		return null;
	}

	@Override
	public DataParameter<Optional<UUID>> getParentUniqueIdParam()
	{
		return PARENT_UNIQUE_ID;
	}

	@Override
	public DataParameter<Float> getEntityAgeParam()
	{
		return AGE;
	}

	@Override
	public void ageUp(int growthSeconds, boolean updateForcedAge)
	{
		float entityAge = this.getEntityAge();
		entityAge += 0.05f;
		this.setEntityAge(entityAge);
	}

	@Override
	public float getSizeDividend()
	{
		return 1;
	}
}
