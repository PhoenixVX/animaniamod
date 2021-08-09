package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAIPanic;

public class GenericAIPanic<T extends CreatureEntity> extends EntityAIPanic
{
	public GenericAIPanic(T creature, double speedIn)
	{
		super(creature, speedIn);
	}


	public boolean shouldExecute()
	{

		if (creature.getRevengeTarget() != null && AddonInjectionHandler.runInjection("farm", "isCow", Boolean.class, creature)) {
			return false;
		}

		if (creature.isBurning())
		{
			ISleeping s = (ISleeping)creature;
			if(s.getSleeping())
				s.setSleeping(false);
		}

		return super.shouldExecute();
	}
}