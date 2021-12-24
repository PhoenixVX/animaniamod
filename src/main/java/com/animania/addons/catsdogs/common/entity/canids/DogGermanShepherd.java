package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogGermanShepherd
{

	public static class EntityPuppyGermanShepherd extends EntityPuppyBase
	{
	
		public EntityPuppyGermanShepherd(Level world)
		{
			super(world);
			this.type = DogType.GERMAN_SHEPHERD;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14478321;
		}
	}

	public static class EntityFemaleGermanShepherd extends EntityFemaleDogBase
	{
	
		public EntityFemaleGermanShepherd(Level world)
		{
			super(world);
			this.type = DogType.GERMAN_SHEPHERD;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14478321;
		}
	}

	public static class EntityMaleGermanShepherd extends EntityMaleDogBase
	{
	
		public EntityMaleGermanShepherd(Level world)
		{
			super(world);
			this.type = DogType.GERMAN_SHEPHERD;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -8300224;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -14478321;
		}
	}

}
