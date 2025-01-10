package com.animania.data;

import com.animania.AnimaniaMod;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class AnimaniaModelProvider extends ModelProvider {
    public AnimaniaModelProvider(PackOutput output) {
        super(output, AnimaniaMod.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        super.registerModels(
                new AnimaniaBlockModelProvider(blockModels.blockStateOutput, blockModels.itemModelOutput, blockModels.modelOutput),
                new AnimaniaItemModelProvider(itemModels.itemModelOutput, itemModels.modelOutput)
        );
    }
}
