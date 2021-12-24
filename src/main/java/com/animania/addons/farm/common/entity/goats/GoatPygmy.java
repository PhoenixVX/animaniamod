package com.animania.addons.farm.common.entity.goats;

import net.minecraft.world.level.Level;

public class GoatPygmy
{

	public static class EntityBuckPygmy extends EntityBuckBase
	{

		public EntityBuckPygmy(Level worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.PYGMY;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9475221;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4145731;
		}
	}

	public static class EntityDoePygmy extends EntityDoeBase
	{

		public EntityDoePygmy(Level worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.PYGMY;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9475221;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4145731;
		}
	}

	public static class EntityKidPygmy extends EntityKidBase
	{

		public EntityKidPygmy(Level worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.PYGMY;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 9475221;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 4145731;
		}
	}

}
