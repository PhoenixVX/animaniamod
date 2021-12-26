package com.animania.compat.top;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.compat.top.providers.TOPInfoEntityProvider;
import com.animania.compat.top.providers.TOPInfoProvider;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class TOPCompat implements Function<ITheOneProbe, Void>
{

	public static ITheOneProbe probe;

	@Nullable
	@Override
	public Void apply(ITheOneProbe input)
	{
		probe = input;
		probe.registerProvider(new IProbeInfoProvider() {
			@Override
			public String getID()
			{
				return Animania.MODID + ":blocks";
			}

			@Override
			public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData data)
			{
				if (blockState.getBlock() instanceof TOPInfoProvider)
				{
					TOPInfoProvider provider = (TOPInfoProvider) blockState.getBlock();
					provider.addProbeInfo(mode, probeInfo, player, level, blockState, data);
				}

			}

		});

		probe.registerEntityProvider(new IProbeInfoEntityProvider() {

			@Override
			public String getID()
			{
				return Animania.MODID + ":entities";
			}

			@Override
			public void addProbeEntityInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
			{

				if (entity instanceof TOPInfoEntityProvider provider)
				{
					provider.addProbeInfo(mode, probeInfo, player, level, entity, data);
				}

			}

		});

		return null;
	}

}
