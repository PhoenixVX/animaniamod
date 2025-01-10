package com.animania.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class AnimaniaDataGenerators {
    public static void gatherData(final GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Register all data generators
        generator.addProvider(true, new AnimaniaModelProvider(output));
        generator.addProvider(true, new AnimaniaRecipesProvider.Runner(output, lookupProvider));
    }
}
