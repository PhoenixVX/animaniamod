package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.ReflectionUtil;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraftforge.fml.ModList;

public class GenericAIEatGrass<T extends PathfinderMob & ISleeping & IFoodEating> extends GenericAISearchBlock
{

	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.TALL_GRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	protected final T grassEaterEntity;
	protected final Level entityLevel;
	public int eatingGrassTimer;
	public boolean eatsGrass;
	private int timer;
	private boolean isEating = false;

	public GenericAIEatGrass(T grassEaterEntityIn, boolean eatsGrass)
	{
		super(grassEaterEntityIn, 1.0, 8, Direction.UP);
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityLevel = grassEaterEntityIn.level;
		this.eatsGrass = eatsGrass;
		this.setMutexBits(7);
	}

	public GenericAIEatGrass(T grassEaterEntityIn)
	{
		this(grassEaterEntityIn, true);
	}

	@Override
	public boolean shouldExecute()
	{
		if (++this.timer <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;
		if (this.grassEaterEntity.getSleeping() || this.grassEaterEntity.getFed())
		{
			this.timer = 0;
			return false;
		}

		if (this.grassEaterEntity.getRandom().nextInt(120) == 0)
		{
			this.timer = 0;
			return super.shouldExecute();
		}

		return false;
	}

	@Override
	public void startExecuting()
	{
		if (!this.destinationBlock.equals(GenericAISearchBlock.NO_POS) && this.eatsGrass)
			super.startExecuting();
		else
		{
			this.eatingGrassTimer = 160;
			this.entityLevel.broadcastEntityEvent(this.grassEaterEntity, (byte) 10);
			this.grassEaterEntity.getNavigation().stop();
		}
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() || this.eatingGrassTimer > 0;
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
		this.eatingGrassTimer = 0;
		this.isEating = false;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		this.timer = 0;
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.isAtDestination() && this.shouldMoveTo(this.level, this.seekingBlockPos))
		{
			BlockState state = this.level.getBlockState(this.seekingBlockPos);
			Block block = state.getBlock();

			if (!this.isEating)
			{
				this.eatingGrassTimer = 160;
				this.entityLevel.broadcastEntityEvent(this.grassEaterEntity, (byte) 10);
				this.grassEaterEntity.getNavigation().stop();
				this.isEating = true;
			}

			if (this.eatsGrass && this.eatingGrassTimer == 4)
			{
				this.entityLevel.globalLevelEvent(2001, this.seekingBlockPos, Block.getId(state));

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
				{
					String name = block.getRegistryName().toString();
					boolean handled = false;

					if (ModList.get().isLoaded("desirepaths") && (name.startsWith("desirepaths:grass_worn_") && !name.endsWith("6") || block instanceof GrassBlock))
					{
						try
						{
							ReflectionUtil.findMethod(Class.forName("com.corosus.desirepaths.block.GrassBlockWorn"), "performWearTick", null, Level.class, BlockPos.class, float.class).invoke(null, this.entityLevel, this.seekingBlockPos, 20.0F);
							handled = true;
						}
						catch (Exception e)
						{
							// Do nothing as handled is still false
							e.printStackTrace();
						}
					}

					if (!handled)
						this.entityLevel.setBlock(this.seekingBlockPos, Blocks.DIRT.defaultBlockState(), 2);
				}

				if (this.grassEaterEntity instanceof IFoodEating)
				{
					this.startExecuting();
					((IFoodEating) this.grassEaterEntity).setFed(true);
				}

				this.grassEaterEntity.eatGrassBonus();
			}
		}

	}

	@Override
	protected boolean shouldMoveTo(Level levelIn, BlockPos pos)
	{
		BlockState state = this.level.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof GrassBlock || AddonInjectionHandler.runInjection("farm", "isMooshroom", Boolean.class, this.grassEaterEntity, block) || this.handleDesirePaths(block))
			return true;

		return false;
	}

	private boolean handleDesirePaths(Block block)
	{
		if (ModList.get().isLoaded("desirepaths"))
		{
			String name = block.getRegistryName().toString();
			if (name.contains("desirepaths:grass_worn_") && (name.endsWith("1") || name.endsWith("2") || name.endsWith("3")))
				return true;
		}

		return false;
	}
}