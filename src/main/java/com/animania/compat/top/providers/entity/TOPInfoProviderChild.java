package com.animania.compat.top.providers.entity;

import java.util.UUID;

import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public interface TOPInfoProviderChild extends TOPInfoProviderBase
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
	{
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, world, entity, data);

		if (mode == ProbeMode.EXTENDED)

		{
			CompoundNBT tag = new CompoundNBT();
			entity.writeToNBT(tag);

			String parent = tag.getString("ParentUUID");

			if (!parent.equals(""))
			{
				for (Entity e : AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 20, world, entity))
				{
					UUID id = e.getPersistentID();
					if (id.toString().equals(parent))
					{
						String name = e.getCustomNameTag();
						if (!name.equals(""))
							probeInfo.entity(e).text(I18n.translateToLocal("text.waila.parent") + " (" + name + ")");
						else
							probeInfo.entity(e).text(I18n.translateToLocal("text.waila.parent"));
						return;
					}
				}

				probeInfo.text(I18n.translateToLocal("text.waila.parentmissing"));

			}
		}

	}

}
