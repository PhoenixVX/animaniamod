package com.animania.common.handler;

import com.animania.common.loottables.AddMoreFunction;
import com.animania.common.loottables.EntityFedProperty;
import com.animania.common.loottables.EntityGenderProperty;
import com.animania.common.loottables.EntityWateredProperty;
import com.animania.common.loottables.WoolColorFunction;

import net.minecraft.loot.functions.LootFunctionManager;
import net.minecraft.world.storage.loot.properties.EntityPropertyManager;

public class LootTableHandler
{
	public static void preInit()
	{
		EntityPropertyManager.registerProperty(new EntityFedProperty.Serializer());
		EntityPropertyManager.registerProperty(new EntityWateredProperty.Serializer());
		EntityPropertyManager.registerProperty(new EntityGenderProperty.Serializer());
		LootFunctionManager.registerFunction(new AddMoreFunction.Serializer());
		LootFunctionManager.registerFunction(new WoolColorFunction.Serializer());
	}
}
