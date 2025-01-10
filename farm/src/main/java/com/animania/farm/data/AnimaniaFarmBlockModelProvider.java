package com.animania.farm.data;

import com.animania.farm.block.AnimaniaFarmBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AnimaniaFarmBlockModelProvider extends BlockModelGenerators {
    public AnimaniaFarmBlockModelProvider(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        for (DeferredHolder<Block, ? extends Block> entry : AnimaniaFarmBlocks.BLOCKS.getEntries()) {
            Block block = entry.get();
            createTrivialCube(block);
        }
    }
}
