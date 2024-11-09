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
        createStrawBlock("straw_block", AnimaniaBlocks.STRAW_BLOCK.get());
        createSaltLickBlock("salt_lick_block", AnimaniaBlocks.SALT_LICK_BLOCK.get());
        createCube("nest_block", AnimaniaBlocks.NEST_BLOCK.get());
        createDirectionalBlock("trough_block", AnimaniaBlocks.TROUGH_BLOCK.get(), modLoc("block/mud_block"), mcLoc("block/oak_planks"));
    }

    private void createCube(String name, @NotNull Block block) {
        ResourceLocation textureLocation = modLoc("block/" + name);
        ModelFile cube = models().cubeAll(name, textureLocation);
        simpleBlock(block, cube);
    }

    private void createStrawBlock(String name, @NotNull Block block) {
        ResourceLocation textureLocation = modLoc("block/" + name);
        ModelFile strawBlockModel = models()
                .withExistingParent(name,"minecraft:block/carpet")
                .texture("wool", textureLocation)
                .texture("particle", textureLocation)
                .renderType("minecraft:cutout");
        simpleBlock(block, strawBlockModel);
    }

    private void createSaltLickBlock(String name, @NotNull Block block) {
        ModelFile cube = models().cubeAll(name, mcLoc("block/stone"))
                .texture("particle", modLoc("item/salt_lick"));
        simpleBlock(block, cube);
    }

    private void createDirectionalBlock(String name, @NotNull Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture) {
        ModelFile model = models().withExistingParent(name, mcLoc("block/cube_column"))
                .texture("side", sideTexture)
                .texture("end", bottomTexture);
        horizontalBlock(block, model);
    }
}
