package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.passive.TameableEntity;

public class GenericAISit<T extends TameableEntity & ISleeping> extends SitGoal
{

	T entity;
	
	public GenericAISit(T entityIn)
	{
		super(entityIn);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.entity.getSleeping())
			return false;
		
		return super.shouldExecute();
	}
	
}
