package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.entity.cows.CowEntityBase;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.Entity;

public class WailaCowEntityProvider extends WailaAnimalEntityProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		if (accessor.getPlayer().isSneaking())
		{

			CowEntityBase thisEntity = (CowEntityBase)entity;
			
			if (thisEntity.getHasKids())
				currenttip.add(I18n.translateToLocal("text.waila.milkable"));

			if (thisEntity.getFertile() && !thisEntity.getPregnant())
			{
				currenttip.add(I18n.translateToLocal("text.waila.fertile1"));
			} 

			if (thisEntity.getPregnant())
			{
				if (thisEntity.getGestation() > 1) {
					int bob = thisEntity.getGestation();
					currenttip.add(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")" );
				} else {
					currenttip.add(I18n.translateToLocal("text.waila.pregnant1"));
				}
			} 
			
		}
		return currenttip;
	}


}
