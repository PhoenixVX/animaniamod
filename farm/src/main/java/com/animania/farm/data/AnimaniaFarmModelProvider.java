package com.animania.farm.data;

import com.animania.AnimaniaMod;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class AnimaniaFarmModelProvider extends ModelProvider {
    public AnimaniaFarmModelProvider(PackOutput output) {
        super(output, AnimaniaMod.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        super.registerModels(
                new AnimaniaFarmBlockModelProvider(blockModels.blockStateOutput, blockModels.itemModelOutput, blockModels.modelOutput),
                new AnimaniaFarmItemModelProvider(itemModels.itemModelOutput, itemModels.modelOutput)
        );
    }
}
