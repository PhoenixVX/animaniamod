package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.UUID;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISleeping;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class GenericAIMate<T extends PathfinderMob & IMateable & IFoodEating & ISleeping, O extends PathfinderMob & IMateable & IFoodEating & ISleeping & IImpregnable> extends Goal
{
	private final T entity;
	Level theLevel;
	private O targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;

	private Class female;
	private Class child;
	private Class base;

	public GenericAIMate(T animal, double speedIn, Class other, Class child, Class base)
	{
		this.entity = animal;
		this.theLevel = animal.level;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;
		this.female = other;
		this.child = child;
		this.base = base;
	}

	@Override
	public boolean shouldExecute()
	{

		this.delayCounter++;

		// System.out.println(delayCounter);

		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			if (this.entity instanceof ISterilizable && ((ISterilizable) this.entity).getSterilized() || this.entity.getSleeping())
			{
				this.delayCounter = 0;
				return false;
			}

			if (this.child.isInstance(this.entity) || this.female.isInstance(this.entity) || this.entity.isInWater())
			{
				this.delayCounter = 0;
				return false;
			}

			List similarAnimalsInRange = AnimaniaHelper.getEntitiesInRange(this.base, AnimaniaConfig.gameRules.animalCapSearchRange, this.theLevel, this.entity);
			if ((similarAnimalsInRange.size() + 1 >= AnimaniaConfig.careAndFeeding.entityBreedingLimit) || (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? !this.entity.getInteracted() : false) || (AnimaniaConfig.careAndFeeding.feedToBreed && !this.entity.getHandFed()))
			{
				this.delayCounter = 0;
				return false;
			}

			if (!this.entity.getFed() || !this.entity.getWatered())
			{
				this.delayCounter = 0;
				return false;
			}

			this.targetMate = this.getNearbyMate();

			if (this.targetMate != null && Animania.RANDOM.nextInt(20) == 0)
			{
				this.delayCounter = 0;
				this.resetTask();
				return false;
			}

			return this.targetMate != null;

		}
		else
			return false;

	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (this.targetMate != null)
		{
			return this.targetMate.isAlive();
		}
		else
		{
			return false;
		}
	}

	@Override
	public void resetTask()
	{
		this.targetMate = null;
	}

	@Override
	public void updateTask()
	{

		if (this.targetMate != null)
		{
			UUID targetMateUUID = this.targetMate.getMateUniqueId();
			boolean preg = this.targetMate.getPregnant();
			boolean fertile = this.targetMate.getFertile();
			boolean uuidbool = targetMateUUID == null ? false : !targetMateUUID.equals(this.entity.getUniqueID());

			if (uuidbool || preg || !fertile)
			{
				this.targetMate.getNavigation().stop();
				this.courtshipTimer = 200;
				this.resetTask();
				this.entity.getNavigation().stop();
				return;
			}

			this.courtshipTimer--;

			if (this.courtshipTimer >= 0)
			{

				if (this.courtshipTimer % 20 == 0)
				{
					this.entity.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.entity.getVerticalFaceSpeed());
					this.entity.getNavigation().tryMoveToLivingEntity(this.targetMate, this.moveSpeed);
					this.targetMate.getLookHelper().setLookPositionWithEntity(this.entity, 10.0F, this.targetMate.getVerticalFaceSpeed());
					this.targetMate.getNavigation().tryMoveToLivingEntity(this.entity, this.moveSpeed);
				}

				double distance = this.entity.getDistance(this.targetMate);

				if (distance <= 1.8)
				{
					this.entity.setInLove(null);
					this.entity.setMateUniqueId(this.targetMate.getUUID());
					this.targetMate.setInLove(null);

					this.targetMate.setPregnant(true);
					this.targetMate.setFertile(false);
					this.targetMate.setHandFed(false);
					this.targetMate.setInteracted(this.entity.getInteracted());
					this.targetMate.setMateUniqueId(this.entity.getUUID());

					this.targetMate.getNavigation().stop();
					this.courtshipTimer = 200;
					this.resetTask();
					this.entity.getNavigation().stop();

				}
			}
			else
			{
				this.targetMate.getNavigation().stop();
				this.courtshipTimer = 200;
				this.resetTask();
				this.entity.getNavigation().stop();

				// If mating fails, we give a 2000 tick cooldown
				this.delayCounter = -2000;
			}

		}
	}

	private O getNearbyMate()
	{

		T male = this.entity;
		UUID mateID = null;

		if (male.getMateUniqueId() != null)
		{
			mateID = male.getMateUniqueId();
		}

		if (AnimaniaConfig.careAndFeeding.malesMateMultipleFemales)
			mateID = null;

		if (mateID != null)
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 5, this.entity.level, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				if (female == null)
					continue;

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.feedToBreed && female instanceof IFoodEating && !((IFoodEating) female).getHandFed())
				{
					allowBreeding = false;
				}

				if (female.getUUID().equals(mateID) && female.getFertile() && female instanceof ISleeping && !((ISleeping) female).getSleeping() && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male) && female.getWatered() && female.getFed())
				{
					this.courtshipTimer = 200;
					return female;
				}
			}
		}
		else
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 8, this.entity.level, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.feedToBreed && !female.getHandFed())
				{
					allowBreeding = false;
				}

				this.courtshipTimer--;
				if ((AnimaniaConfig.careAndFeeding.malesMateMultipleFemales ? female.getMateUniqueId() == null ? true : female.getMateUniqueId().equals(this.entity.getUUID()) : female.getMateUniqueId() == null) && female.getFertile() && !female.getSleeping() && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male) && female.getWatered() && female.getFed())
				{
					this.courtshipTimer = 200;
					return female;
				}
			}
		}

		this.delayCounter = 0;
		return null;
	}
}
