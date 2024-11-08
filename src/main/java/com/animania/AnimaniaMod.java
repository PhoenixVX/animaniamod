package com.animania;

import com.animania.block.AnimaniaBlocks;
import com.animania.block.entity.AnimaniaBlockEntityTypes;
import com.animania.data.AnimaniaDataGenerators;
import com.animania.item.AnimaniaItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
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
        modEventBus.addListener(this::commonSetup);

        // Initialize all registries
        AnimaniaBlocks.BLOCKS.register(modEventBus);
        AnimaniaItems.ITEMS.register(modEventBus);
        AnimaniaItems.CREATIVE_MODE_TABS.register(modEventBus);
        AnimaniaBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(AnimaniaDataGenerators::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public static ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
