package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.item.AnimaniaItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AnimaniaItemModelProvider extends ItemModelProvider {
    public AnimaniaItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AnimaniaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> holder : AnimaniaItems.ITEMS.getEntries()) {
            Item item = holder.get();
            if (item instanceof BlockItem blockItem) {
                if (holder.equals(AnimaniaItems.SALT_LICK_BLOCK_ITEM)) {
                    withExistingParent("salt_lick_block", "item/generated").texture("layer0", AnimaniaMod.getId("item/salt_lick"));
                } else if (holder.equals(AnimaniaItems.TROUGH_BLOCK_ITEM)) {
                    withExistingParent("trough_block", "item/generated").texture("layer0", AnimaniaMod.getId("block/trough_block"));
                } else {
                    simpleBlockItem(blockItem.getBlock());
                }
            } else {
                createSimpleItem(BuiltInRegistries.ITEM.getKey(item).getPath());
            }
        }
    }

    private void createSimpleItem(String name) {
        createSimpleItem(name, AnimaniaMod.getId("item/" + name));
    }

    private void createSimpleItem(String name, ResourceLocation texture) {
        withExistingParent(name, "item/generated").texture("layer0", texture);
    }
}
