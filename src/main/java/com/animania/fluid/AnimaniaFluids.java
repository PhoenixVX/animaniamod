package com.animania.fluid;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import com.animania.item.AnimaniaItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AnimaniaFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AnimaniaMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, AnimaniaMod.MOD_ID);

    // Fluid types
    public static final DeferredHolder<FluidType, FluidType> SLOP_FLUID_TYPE = FLUID_TYPES.register("slop_fluid", () -> new FluidType(FluidType.Properties.create()));

    // Fluids
    public static final DeferredHolder<Fluid, FlowingFluid> SLOP_FLUID = FLUIDS.register("slop_fluid", () -> new BaseFlowingFluid.Source(makeSlopProperties()));
    public static final DeferredHolder<Fluid, FlowingFluid> SLOP_FLOWING_FLUID = FLUIDS.register("slop_flowing_fluid", () -> new BaseFlowingFluid.Flowing(makeSlopProperties()));

    // Texture locations
    public static final ResourceLocation SLOP_STILL_FLUID_LOCATION = AnimaniaMod.getId("block/slop_still");
    public static final ResourceLocation SLOP_FLOWING_FLUID_LOCATION = AnimaniaMod.getId("block/slop_flowing");

    private static BaseFlowingFluid.Properties makeSlopProperties() {
        return new BaseFlowingFluid.Properties(SLOP_FLUID_TYPE, SLOP_FLUID, SLOP_FLOWING_FLUID)
                .bucket(AnimaniaItems.SLOP_BUCKET)
                .block(AnimaniaBlocks.SLOP_BLOCK)
                .levelDecreasePerBlock(2)
                .tickRate(2);
    }

}
