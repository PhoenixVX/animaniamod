package com.animania.addons.farm.common.entity.cows;

import net.minecraft.world.World;

public class CowHereford
{

	public static class EntityBullHereford extends EntityBullBase
	{

		public EntityBullHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{

			return 15987699;
		}

	}

	public static class CowEntityHereford extends CowEntityBase
	{

		public CowEntityHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 15987699;
		}

	}

	public static class EntityCalfHereford extends EntityCalfBase
	{

		public EntityCalfHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{

			return 15987699;
		}

	}

}