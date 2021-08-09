package com.animania.addons.farm.common.entity.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SheepFriesian
{

	public static class EntityRamFriesian extends EntityRamBase
	{
	
		public EntityRamFriesian(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.FRIESIAN;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}
	
	
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
	
			int i = 1 + this.rand.nextInt(2);
	
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
	
			switch (this.getColorNumber()) {
			case 0:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 1));
				break;
			case 1:
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 2:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 2));
				break;
			}
	
			this.setSheared(true);
	
			return woolDrops;
		}
		
		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 1:
				return true;
			default:
				return false;
			}
		}
	
	
	}

	public static class EntityLambFriesian extends EntityLambBase
	{
	
		public EntityLambFriesian(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.FRIESIAN;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}
		
	}

	public static class EntityEweFriesian extends EntityEweBase
	{
	
		public EntityEweFriesian(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.FRIESIAN;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 2039583;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 4013373;
		}
	
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
		{
	
			int i = 1 + this.rand.nextInt(2);
	
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
	
			switch (this.getColorNumber())
			{
			case 0:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 1));
				break;
			case 1:
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 2:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 2));
				break;
			}
	
			this.setSheared(true);
	
			return woolDrops;
		}
	
		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 1:
				return true;
			default:
				return false;
			}
		}
	
	}

}
