package com.animania;

import com.animania.fluid.AnimaniaFluids;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

public class AnimaniaClientMod {
    public static void initializeClient(final RegisterClientExtensionsEvent event) {
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
}
