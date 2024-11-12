package com.animania.farm;

import com.animania.AnimaniaMod;
import com.animania.farm.block.AnimaniaFarmBlocks;
import com.animania.farm.data.AnimaniaFarmDataGenerators;
import com.animania.farm.entity.AnimaniaFarmEntities;
import com.animania.farm.item.AnimaniaFarmItems;
import com.animania.item.AnimaniaItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(AnimaniaMod.MOD_ID + "_farm")
public class AnimaniaFarmMod {
    public AnimaniaFarmMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Initialize all registries
        AnimaniaFarmBlocks.BLOCKS.register(modEventBus);
        AnimaniaFarmItems.ITEMS.register(modEventBus);
        AnimaniaFarmEntities.ENTITIES.register(modEventBus);

        modEventBus.addListener(this::buildContents);

        modEventBus.addListener(AnimaniaFarmDataGenerators::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        AnimaniaMod.getLogger().debug("Animania Farm addon loaded!");
    }

    private void buildContents(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(AnimaniaItems.ANIMANIA_RESOURCES.getKey())) {
            for (DeferredHolder<Block, ? extends Block> entry : AnimaniaFarmBlocks.BLOCKS.getEntries()) {
                event.accept(new ItemStack(entry.get(), 1));
            }
        }
    }

    public static ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(AnimaniaMod.MOD_ID, path);
    }
}
