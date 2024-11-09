package com.animania.item;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import com.animania.fluid.AnimaniaFluids;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AnimaniaItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnimaniaMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, AnimaniaMod.MOD_ID);

    // Animania base block items
    public static final Supplier<Item> MUD_BLOCK_ITEM = registerBlockItem("mud_block", AnimaniaBlocks.MUD_BLOCK, new Item.Properties());
    public static final Supplier<Item> STRAW_BLOCK_ITEM = registerBlockItem("straw_block", AnimaniaBlocks.STRAW_BLOCK, new Item.Properties());
    public static final Supplier<Item> SALT_LICK_BLOCK_ITEM = registerBlockItem("salt_lick_block", AnimaniaBlocks.SALT_LICK_BLOCK, new Item.Properties());
    public static final Supplier<Item> TROUGH_BLOCK_ITEM = registerBlockItem("trough_block", AnimaniaBlocks.TROUGH_BLOCK, new Item.Properties());
    public static final Supplier<Item> NEST_BLOCK_ITEM = registerBlockItem("nest_block", AnimaniaBlocks.NEST_BLOCK, new Item.Properties());

    // Animania base items
    public static final Supplier<Item> SLOP_BUCKET = registerItem("slop_bucket", () -> new BucketItem(AnimaniaFluids.SLOP_FLUID.value(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final Supplier<Item> MANUAL = registerItem("manual", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> RANDOM_SPAWN_EGG = registerItem("random_spawn_egg", () -> new RandomAnimalSpawnEggItem(new Item.Properties().stacksTo(1)));

    // Animania item groups
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANIMANIA_ENTITIES = CREATIVE_MODE_TABS.register("animania_entities", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.animania_entities"))
                    .icon(() -> new ItemStack(Items.EGG))
                    .displayItems(((parameters, output) -> {
                        output.accept(new ItemStack(RANDOM_SPAWN_EGG.get()));
                    }))
                    .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANIMANIA_RESOURCES = CREATIVE_MODE_TABS.register("animania_resources", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.animania_resources"))
                    .icon(() -> new ItemStack(SLOP_BUCKET.get()))
                    .displayItems(((parameters, output) -> {
                        output.accept(new ItemStack(SLOP_BUCKET.get()));
                        output.accept(new ItemStack(MANUAL.get()));
                        output.accept(new ItemStack(STRAW_BLOCK_ITEM.get()));
                        output.accept(new ItemStack(SALT_LICK_BLOCK_ITEM.get()));
                        output.accept(new ItemStack(MUD_BLOCK_ITEM.get()));
                        output.accept(new ItemStack(TROUGH_BLOCK_ITEM.get()));
                        output.accept(new ItemStack(NEST_BLOCK_ITEM.get()));
                    }))
                    .build()
    );

    private static Supplier<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties) {
        return registerItem(name, () -> new BlockItem(block.get(), properties));
    }

    private static Supplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }
}
