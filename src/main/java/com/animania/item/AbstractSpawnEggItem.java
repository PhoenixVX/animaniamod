package com.animania.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractSpawnEggItem extends Item {
    public AbstractSpawnEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemStack = context.getItemInHand();
            BlockPos clickedPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState clickedState = level.getBlockState(clickedPos);
            if (level.getBlockEntity(clickedPos) instanceof Spawner spawner) {
                EntityType<?> entityType = this.getType();
                if (entityType != null) {
                    spawner.setEntityId(entityType, level.getRandom());
                    level.sendBlockUpdated(clickedPos, clickedState, clickedState, 3);
                    level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, clickedPos);
                    itemStack.shrink(1);
                }
            } else {
                BlockPos clickedPos1;
                if (clickedState.getCollisionShape(level, clickedPos).isEmpty()) {
                    clickedPos1 = clickedPos;
                } else {
                    clickedPos1 = clickedPos.relative(direction);
                }

                EntityType<?> entityType = this.getType();
                if (entityType != null) {
                    Entity entity = entityType.spawn((ServerLevel) level, itemStack, context.getPlayer(), clickedPos1, EntitySpawnReason.SPAWN_ITEM_USE, true, !Objects.equals(clickedPos, clickedPos1) && direction == Direction.UP);
                    if (entity != null) {
                        itemStack.shrink(1);
                        level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, clickedPos);
                    }
                }
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        BlockHitResult clickedBlock = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (clickedBlock.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            BlockPos clickedPos = clickedBlock.getBlockPos();
            if (!(level.getBlockState(clickedPos).getBlock() instanceof LiquidBlock)) {
                return InteractionResult.PASS;
            } else if (level.mayInteract(player, clickedPos) && player.mayUseItemAt(clickedPos, clickedBlock.getDirection(), itemStack)) {
                EntityType<?> entityType = this.getType();
                if (entityType != null) {
                    Entity entity = entityType.spawn((ServerLevel) level, itemStack, player, clickedPos, EntitySpawnReason.SPAWN_ITEM_USE, false, false);
                    if (entity == null) {
                        return InteractionResult.PASS;
                    } else {
                        itemStack.consume(1, player);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
                        return InteractionResult.CONSUME;
                    }
                }
                return InteractionResult.PASS;
            } else {
                return InteractionResult.FAIL;
            }
        }
    }

    public @Nullable EntityType<?> getType() {
        return null;
    }
}
