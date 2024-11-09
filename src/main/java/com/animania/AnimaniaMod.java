package com.animania;

import com.animania.block.AnimaniaBlocks;
import com.animania.block.entity.AnimaniaBlockEntityTypes;
import com.animania.data.AnimaniaDataGenerators;
import com.animania.fluid.AnimaniaFluids;
import com.animania.item.AnimaniaItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.util.Random;

@Mod(AnimaniaMod.MOD_ID)
public class AnimaniaMod {
    public static final String MOD_ID = "animania";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Random RANDOM = new Random();

    public AnimaniaMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Initialize all registries
        AnimaniaBlocks.BLOCKS.register(modEventBus);
        AnimaniaItems.ITEMS.register(modEventBus);
        AnimaniaItems.CREATIVE_MODE_TABS.register(modEventBus);
        AnimaniaBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        AnimaniaFluids.FLUIDS.register(modEventBus);
        AnimaniaFluids.FLUID_TYPES.register(modEventBus);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(AnimaniaClientMod::registerClientExtensions);
            modEventBus.addListener(AnimaniaClientMod::registerRenderers);
            modEventBus.addListener(AnimaniaClientMod::registerLayerDefinitions);
        }

        modEventBus.addListener(AnimaniaDataGenerators::gatherData);

        modContainer.registerConfig(ModConfig.Type.COMMON, AnimaniaConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Utility methods
    public static ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static Random getRandom() {
        return RANDOM;
    }
}
