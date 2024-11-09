package com.animania;

import com.animania.block.entity.AnimaniaBlockEntityTypes;
import com.animania.block.entity.renderer.NestBlockEntityRenderer;
import com.animania.block.entity.renderer.TroughBlockEntityRenderer;
import com.animania.fluid.AnimaniaFluids;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

public class AnimaniaClientMod {
    public static void registerClientExtensions(final RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return AnimaniaFluids.SLOP_STILL_FLUID_LOCATION;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return AnimaniaFluids.SLOP_FLOWING_FLUID_LOCATION;
            }
        }, AnimaniaFluids.SLOP_FLUID_TYPE);
    }

    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AnimaniaBlockEntityTypes.TROUGH_BLOCK_ENTITY.get(), TroughBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AnimaniaBlockEntityTypes.NEST_BLOCK_ENTITY.get(), NestBlockEntityRenderer::new);
    }

    public static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TroughBlockEntityRenderer.TROUGH, TroughBlockEntityRenderer::createBodyLayer);
        event.registerLayerDefinition(NestBlockEntityRenderer.NEST, NestBlockEntityRenderer::createBodyLayer);
    }
}
