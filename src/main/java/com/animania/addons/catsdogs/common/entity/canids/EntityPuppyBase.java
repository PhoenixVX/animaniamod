package com.animania.addons.catsdogs.common.entity.canids;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.api.interfaces.IPlaying;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.common.entities.generic.ai.GenericAIPlay;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityPuppyBase extends EntityAnimaniaDog implements TOPInfoProviderChild, IChild, IPlaying
{

	protected static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(EntityPuppyBase.class, DataSerializers.OPTIONAL_UUID);
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float> defineId(EntityPuppyBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	protected GenericAIPlay playAI;

	public EntityPuppyBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1f, 1f);
		this.width = 1f;
		this.height = 1f;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.tasks.addTask(1, new GenericAIFollowParents<EntityPuppyBase, EntityFemaleDogBase>(this, 1.1D, EntityFemaleDogBase.class));
		this.tasks.addTask(8, playAI = new GenericAIPlay(this, EntityPuppyBase.class));
	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.315D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID> absent());

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
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, EntityFemaleDogBase.class);

		super.onLivingUpdate();
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
		return 1f;
	}

	@Override
	public GenericAIPlay getPlayAI()
	{
		return this.playAI;
	}

}
