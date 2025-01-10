package com.animania.farm.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class AnimaniaFarmDataGenerators {
    public static void gatherData(final GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        // Register all data providers
        generator.addProvider(true, new AnimaniaFarmModelProvider(output));
    }
}
