package com.animania.addons.extra.compat.waila;

import java.util.List;

import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class WailaEntityPeafowlProvider extends WailaAnimalEntityProviderBase
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		EntityPeafowlBase ehb = (EntityPeafowlBase) entity;
		int timer = ehb.getLaidTimer();

		if (accessor.getPlayer().isSneaking())

			if (timer >= 0)
			{
				currenttip.add(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			}
			else
			{
				currenttip.add(I18n.translateToLocal("text.waila.egglay2"));
			}
		return currenttip;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayer player, Entity ent, CompoundTag tag, Level level)
	{
		int laytime = ent.getEntityData().getInteger("EggLayTime");
		tag.putInteger("EggLayTime", laytime);

		return super.getNBTData(player, ent, tag, level);
	}

}
