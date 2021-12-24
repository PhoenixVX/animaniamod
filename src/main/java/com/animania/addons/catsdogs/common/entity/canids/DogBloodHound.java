package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.level.Level;

public class DogBloodHound
{

	public static class EntityPuppyBloodHound extends EntityPuppyBase
	{
	
		public EntityPuppyBloodHound(Level world)
		{
			super(world);
			this.type = DogType.BLOOD_HOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5938636;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13689844;
		}
	}

	public static class EntityMaleBloodHound extends EntityMaleDogBase
	{
	
		public EntityMaleBloodHound(Level world)
		{
			super(world);
			this.type = DogType.BLOOD_HOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5938636;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13689844;
		}
	}

	public static class EntityFemaleBloodHound extends EntityFemaleDogBase
	{
	
		public EntityFemaleBloodHound(Level world)
		{
			super(world);
			this.type = DogType.BLOOD_HOUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -5938636;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -13689844;
		}
	}

}
