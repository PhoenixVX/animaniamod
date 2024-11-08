package com.animania.item;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
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

    // Animania base items
    public static final Supplier<Item> MUD_BLOCK_ITEM = registerBlockItem("mud_block", AnimaniaBlocks.MUD_BLOCK, new Item.Properties());
    public static final Supplier<Item> STRAW_BLOCK_ITEM = registerBlockItem("straw_block", AnimaniaBlocks.STRAW_BLOCK, new Item.Properties());
    public static final Supplier<Item> SALT_LICK_BLOCK_ITEM = registerBlockItem("salt_lick_block", AnimaniaBlocks.SALT_LICK_BLOCK, new Item.Properties());

    // Animania item groups
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANIMANIA_ENTITIES = CREATIVE_MODE_TABS.register("animania_entities", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.animania_entities"))
                    .icon(() -> new ItemStack(Items.EGG))
                    .displayItems(((parameters, output) -> {
                        output.accept(new ItemStack(MUD_BLOCK_ITEM.get()));
                    }))
                    .build()
    );
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANIMANIA_RESOURCES = CREATIVE_MODE_TABS.register("animania_resources", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.animania_resources"))
                    .displayItems(((parameters, output) -> {
                        output.accept(new ItemStack(MUD_BLOCK_ITEM.get()));
                    }))
                    .build()
    );

    private static Supplier<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
}
