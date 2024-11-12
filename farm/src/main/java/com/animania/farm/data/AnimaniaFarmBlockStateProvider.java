package com.animania.farm.data;

import com.animania.AnimaniaMod;
import com.animania.farm.block.AnimaniaFarmBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

public class AnimaniaFarmBlockStateProvider extends BlockStateProvider {
    public AnimaniaFarmBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AnimaniaMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredHolder<Block, ? extends Block> entry : AnimaniaFarmBlocks.BLOCKS.getEntries()) {
            Block block = entry.get();
            String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
            createCube(name, block);
        }
    }

    // Utility methods
    private void createCube(String name, @NotNull Block block) {
        ResourceLocation textureLocation = modLoc("block/" + name);
        ModelFile cube = models().cubeAll(name, textureLocation);
        simpleBlock(block, cube);
    }
}
