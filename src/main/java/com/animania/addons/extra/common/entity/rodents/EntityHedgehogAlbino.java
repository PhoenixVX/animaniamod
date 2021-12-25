package com.animania.addons.extra.common.entity.rodents;

public class EntityHedgehogAlbino extends EntityHedgehogBase
{

	public EntityHedgehogAlbino(Level levelIn)
	{
		super(levelIn);
		this.type = HedgehogType.ALBINO;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 12369084;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 16777215;
	}

}