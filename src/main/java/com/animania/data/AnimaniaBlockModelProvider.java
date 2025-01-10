package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("SameParameterValue")
public class AnimaniaBlockModelProvider extends BlockModelGenerators {
    public AnimaniaBlockModelProvider(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        createTrivialCube(AnimaniaBlocks.MUD_BLOCK.get());
        createStrawBlock(AnimaniaBlocks.STRAW_BLOCK.get());
        createTrivialCube(AnimaniaBlocks.SALT_LICK_BLOCK.get());
        createTrivialCube(AnimaniaBlocks.NEST_BLOCK.get());
        createDirectionalBlock(AnimaniaBlocks.TROUGH_BLOCK.get(), AnimaniaMod.getId("block/mud_block"), AnimaniaMod.getId("block/oak_planks"));
    }

    private void createStrawBlock(@NotNull Block block) {
        ResourceLocation modelLocation = TexturedModel.CARPET.get(block).create(block, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(block, modelLocation));
    }

    private void createDirectionalBlock(@NotNull Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture) {
        /*ModelFile model = models().withExistingParent(name, mcLoc("block/cube_column"))
                .texture("side", sideTexture)
                .texture("end", bottomTexture);
        horizontalBlock(block, model);*/
        ResourceLocation textureLocation = ModelLocationUtils.getModelLocation(block);

        TextureMapping textureMapping = new TextureMapping();
        textureMapping.put(TextureSlot.SIDE, sideTexture);
        textureMapping.put(TextureSlot.END, bottomTexture);
        ResourceLocation templateLocation = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, textureMapping, this.modelOutput);
        this.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, textureLocation))
                        .with(createHorizontalFacingDispatch())
        );
    }
}
