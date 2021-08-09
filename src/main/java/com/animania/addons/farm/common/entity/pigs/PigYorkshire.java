package com.animania.addons.farm.common.entity.pigs;

import net.minecraft.world.World;

public class PigYorkshire
{

	public static class EntityHogYorkshire extends EntityHogBase
	{
	
		public EntityHogYorkshire(World world)
		{
			super(world);
			this.pigType = PigType.YORKSHIRE;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	
	}

	public static class PigEntityletYorkshire extends PigEntityletBase
	{
	
		public PigEntityletYorkshire(World world)
		{
			super(world);
			this.pigType = PigType.YORKSHIRE;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	}

	public static class EntitySowYorkshire extends EntitySowBase
	{
	
		public EntitySowYorkshire(World world)
		{
			super(world);
			this.pigType = PigType.YORKSHIRE;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 15845576;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 15117998;
		}
	}

}
