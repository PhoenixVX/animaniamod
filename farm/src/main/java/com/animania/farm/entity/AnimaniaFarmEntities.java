package com.animania.farm.entity;

import com.animania.AnimaniaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AnimaniaFarmEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AnimaniaMod.MOD_ID);

    public static <E extends Entity> Supplier<EntityType<E>> registerEntityType(String name, Supplier<EntityType<E>> entityType) {
        return ENTITIES.register(name, entityType);
    }
}
