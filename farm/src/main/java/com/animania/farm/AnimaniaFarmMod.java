package com.animania.farm;

import com.animania.AnimaniaMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(AnimaniaMod.MOD_ID + "_farm")
public class AnimaniaFarmMod {
    public AnimaniaFarmMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Initialize all registries
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        AnimaniaMod.getLogger().debug("Animania Farm addon loaded!");
    }

    public static ResourceLocation getId(String path) {
        return ResourceLocation.fromNamespaceAndPath(AnimaniaMod.MOD_ID, path);
    }
}
