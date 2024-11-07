package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class AnimaniaBlockStateProvider extends BlockStateProvider {
    public AnimaniaBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AnimaniaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Block strawBlock = AnimaniaBlocks.STRAW_BLOCK.get();
        ModelFile strawBlockModel = models()
                .withExistingParent("straw_block","minecraft:block/carpet")
                .texture("wool", "animania:block/straw_block")
                .texture("particle", "animania:block/straw_block");
        simpleBlock(strawBlock, strawBlockModel);
    }
}
