package com.animania.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimaniaEntity extends Animal {
    // Male - 0, female - 1
    public static final EntityDataAccessor<Byte> GENDER = SynchedEntityData.defineId(AnimaniaEntity.class, EntityDataSerializers.BYTE);

    public AnimaniaEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(GENDER, (byte) 0);
    }

    @Override
    public boolean isFood(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        if (otherParent instanceof AnimaniaEntity animaniaEntity && animaniaEntity.isSameAnimalType(this)) {
            byte parentGender = this.getEntityData().get(GENDER);
            byte otherParentGender = animaniaEntity.getEntityData().get(GENDER);

            if (parentGender != otherParentGender) {
                EntityType<?> entityType = otherParent.getType();

                // This is a safe cast as all AnimaniaEntity instances should extend from
                // Animal which extends AgeableMob in the hierarchy
                if (entityType.getBaseClass().isAssignableFrom(AgeableMob.class)) {
                    return (AgeableMob) entityType.create(level);
                }
            }
        }
        return null;
    }

    // Utility methods
    public boolean isSameAnimalType(AnimaniaEntity other) {
        return false;
    }
}
