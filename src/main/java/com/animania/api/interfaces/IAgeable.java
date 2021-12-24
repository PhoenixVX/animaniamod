package com.animania.api.interfaces;

import net.minecraft.network.syncher.EntityDataAccessor;

public interface IAgeable extends IAnimaniaAnimal
{
	public EntityDataAccessor<Integer> getAgeParam();
		
	default void setAge(int age)
	{
		EntityDataAccessor<Integer> param = getAgeParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, age);
	}
	
	default int getAge()
	{
		EntityDataAccessor<Integer> param = getAgeParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}
}
