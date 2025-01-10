package com.animania.data;

import com.animania.item.AnimaniaItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AnimaniaRecipesProvider extends RecipeProvider {
    public AnimaniaRecipesProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> itemHolder = this.registries.lookupOrThrow(Registries.ITEM);
        ShapelessRecipeBuilder.shapeless(itemHolder, RecipeCategory.DECORATIONS, AnimaniaItems.MUD_BLOCK_ITEM.get(), 4)
                .requires(Items.WATER_BUCKET)
                .requires(Items.DIRT, 2)
                .unlockedBy(getHasName(Items.DIRT), has(Items.WATER_BUCKET))
                .save(this.output);

        // TODO: unlockedBy does not seem right here
        ShapelessRecipeBuilder.shapeless(itemHolder, RecipeCategory.DECORATIONS, AnimaniaItems.NEST_BLOCK_ITEM.get(), 1)
                .requires(ItemTags.WOOL)
                .requires(Items.STICK)
                .requires(ItemTags.LEAVES)
                .unlockedBy(getHasName(Items.STICK), has(ItemTags.LEAVES))
                .unlockedBy(getHasName(Items.STICK), has(ItemTags.WOOL))
                .save(this.output);

        // TODO: This recipe requires salt which is not in Animania base
        // Move to the addon in which salt originates from when we get there
        ShapelessRecipeBuilder.shapeless(itemHolder, RecipeCategory.FOOD, AnimaniaItems.SALT_LICK_BLOCK_ITEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(this.output);

        ShapelessRecipeBuilder.shapeless(itemHolder, RecipeCategory.FOOD, AnimaniaItems.STRAW_BLOCK_ITEM.get(), 1)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(this.output);

        // TODO: unlockedBy may not be correct here
        ShapedRecipeBuilder.shaped(itemHolder, RecipeCategory.DECORATIONS, AnimaniaItems.TROUGH_BLOCK_ITEM.get(), 1)
                .pattern("p p")
                .pattern("pip")
                .pattern("s s")
                .define('p', ItemTags.PLANKS)
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.PLANKS))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output);
    }

    public static final class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider registries, @NotNull RecipeOutput output) {
            return new AnimaniaRecipesProvider(registries, output);
        }

        @Override
        public @NotNull String getName() {
            return "Animania Recipes";
        }
    }
}
