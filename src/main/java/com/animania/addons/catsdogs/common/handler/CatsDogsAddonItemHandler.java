package com.animania.addons.catsdogs.common.handler;

import com.animania.addons.catsdogs.common.entity.canids.DogType;
import com.animania.addons.catsdogs.common.entity.felids.CatType;
import com.animania.api.data.EntityGender;
import com.animania.common.items.ItemEntityEgg;

public class CatsDogsAddonItemHandler
{

	public static RItem entityeggrandomdog;
	public static RItem entityeggrandomcat;

	/**
	 * Register Items
	 */
	public static void preInit()
	{
		entityeggrandomdog = new ItemEntityEgg("dog_random", DogType.BLOOD_HOUND, EntityGender.RANDOM);
		entityeggrandomcat = new ItemEntityEgg("cat_random", CatType.AMERICAN_SHORTHAIR, EntityGender.RANDOM);
	}

}
