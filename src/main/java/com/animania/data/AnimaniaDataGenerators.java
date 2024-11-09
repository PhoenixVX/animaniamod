package com.animania.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class AnimaniaDataGenerators {
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Register all data generators
        generator.addProvider(true, new AnimaniaBlockStateProvider(output, existingFileHelper));
        generator.addProvider(true, new AnimaniaItemModelProvider(output, existingFileHelper));
        generator.addProvider(true, new AnimaniaRecipesProvider(output, lookupProvider));
    }
}
