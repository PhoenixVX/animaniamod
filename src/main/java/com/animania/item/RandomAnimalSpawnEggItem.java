package com.animania.item;

import com.animania.AnimaniaMod;
import com.animania.entity.AnimaniaEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class RandomAnimalSpawnEggItem extends AbstractSpawnEggItem {
    public RandomAnimalSpawnEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        // TODO: Tooltip should only use 1 line
        Component description1 = Component.translatable("item.animania.random_spawn_egg.desc1").withStyle(ChatFormatting.GOLD);
        Component description2 = Component.translatable("item.animania.random_spawn_egg.desc2").withStyle(ChatFormatting.DARK_GRAY);
        tooltipComponents.add(description1);
        tooltipComponents.add(description2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable EntityType<?> getType() {
        Random random = AnimaniaMod.getRandom();
        DeferredHolder<EntityType<?>, EntityType<?>>[] types = AnimaniaEntities.ENTITIES.getEntries().toArray(new DeferredHolder[0]);
        int length = types.length;
        return random.nextInt(length) == 0 ? null : types[random.nextInt(length)].get();
    }
}
