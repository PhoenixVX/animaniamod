package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogPug
{

	public static class EntityPuppyPug extends EntityPuppyBase
	{
	
		public EntityPuppyPug(Level world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

	public static class EntityMalePug extends EntityMaleDogBase
	{
	
		public EntityMalePug(Level world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

	public static class EntityFemalePug extends EntityFemaleDogBase
	{
	
		public EntityFemalePug(Level world)
		{
			super(world);
			this.type = DogType.PUG;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -1514529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13026238;
		}
	}

}
