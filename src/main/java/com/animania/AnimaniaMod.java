package com.animania;

import com.animania.block.AnimaniaBlocks;
import com.animania.data.AnimaniaDataGenerators;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(AnimaniaMod.MOD_ID)
public class AnimaniaMod {
    public static final String MOD_ID = "animania";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AnimaniaMod(IEventBus modEventBus, ModContainer modContainer) {
        // Initialize all registries
        modEventBus.addListener(this::commonSetup);

        AnimaniaBlocks.BLOCKS.register(modEventBus);

        modEventBus.addListener(AnimaniaDataGenerators::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
