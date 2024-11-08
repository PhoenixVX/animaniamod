package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class AnimaniaBlockStateProvider extends BlockStateProvider {
    public AnimaniaBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AnimaniaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createCube("mud_block", AnimaniaBlocks.MUD_BLOCK.get());

        Block strawBlock = AnimaniaBlocks.STRAW_BLOCK.get();
        ModelFile strawBlockModel = models()
                .withExistingParent("straw_block","minecraft:block/carpet")
                .texture("wool", "animania:block/straw_block")
                .texture("particle", "animania:block/straw_block")
                .renderType("minecraft:cutout");
        simpleBlock(strawBlock, strawBlockModel);
    }

    private void createCube(String name, @NotNull Block block) {
        ResourceLocation textureLocation = AnimaniaMod.getId("block/" + name);
        ModelFile cube = models().cubeAll(name, textureLocation);
        simpleBlock(block, cube);
    }

    private String getNormalizedPath(String path) {
        return path.replace(":block." + AnimaniaMod.MOD_ID, "");
    }
}
