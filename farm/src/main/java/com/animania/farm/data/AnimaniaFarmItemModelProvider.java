package com.animania.farm.data;

import com.animania.AnimaniaMod;
import com.animania.farm.item.AnimaniaFarmItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AnimaniaFarmItemModelProvider extends ItemModelProvider {
    public AnimaniaFarmItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AnimaniaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> entry : AnimaniaFarmItems.ITEMS.getEntries()) {
            Item item = entry.get();
            String name = BuiltInRegistries.ITEM.getKey(item).getPath();
            ResourceLocation defaultLocation = AnimaniaMod.getId(name);
            if (item instanceof BlockItem) {
                withExistingParent(name, "item/generated").texture("layer0", defaultLocation);
            }
        }
    }
}
