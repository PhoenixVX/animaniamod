package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.item.AnimaniaItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.BiConsumer;

public class AnimaniaItemModelProvider extends ItemModelGenerators {
    public AnimaniaItemModelProvider(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        for (DeferredHolder<Item, ? extends Item> holder : AnimaniaItems.ITEMS.getEntries()) {
            Item item = holder.get();
            if (item instanceof BlockItem blockItem) {
                if (holder.equals(AnimaniaItems.SALT_LICK_BLOCK_ITEM)) {
                    // withExistingParent("salt_lick_block", "item/generated").texture("layer0", AnimaniaMod.getId("item/salt_lick"));
                } else if (holder.equals(AnimaniaItems.TROUGH_BLOCK_ITEM)) {
                    // withExistingParent("trough_block", "item/generated").texture("layer0", AnimaniaMod.getId("block/trough_block"));
                } else if (holder.equals(AnimaniaItems.NEST_BLOCK_ITEM)) {
                    // withExistingParent("nest_block", "item/generated").texture("layer0", AnimaniaMod.getId("block/nest_block"));
                } else {
                    // simpleBlockItem(blockItem.getBlock());
                    generateFlatItem(blockItem, ModelTemplates.FLAT_ITEM);
                }
            } else {
                generateFlatItem(item, ModelTemplates.FLAT_ITEM);
            }
        }
        generateFlatItem(BuiltInRegistries.ITEM.getValue(AnimaniaMod.getId("item/manual")), ModelTemplates.FLAT_ITEM);
    }
}
