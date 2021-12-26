package com.animania.common.helper;

import java.util.Arrays;
import java.util.List;

import com.animania.common.helper.RegistryHelper.RItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemHelper
{

	public static void spawnItem(Level level, BlockPos pos, RItem item, int count, int meta)
	{
		spawnItem(level, pos, new ItemStack(item, count, meta));
	}

	public static void spawnItem(Level level, BlockPos pos, RItem item, int count)
	{
		spawnItem(level, pos, new ItemStack(item, count, 0));
	}

	public static void spawnItem(Level level, BlockPos pos, RItem item)
	{
		spawnItem(level, pos, new ItemStack(item, 1, 0));
	}

	public static void spawnItem(Level level, BlockPos pos, Block block, int count, int meta)
	{
		spawnItem(level, pos, new ItemStack(block, count, meta));
	}

	public static void spawnItem(Level level, BlockPos pos, Block block, int count)
	{
		spawnItem(level, pos, new ItemStack(block, count, 0));
	}

	public static void spawnItem(Level level, BlockPos pos, Block block)
	{
		spawnItem(level, pos, new ItemStack(block, 1, 0));
	}

	public static void spawnItem(Level level, BlockPos pos, ItemStack itemStack)
	{
		EntityItem item = new EntityItem(level);
		item.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		item.setItem(itemStack);
		AnimaniaHelper.spawnEntity(level, item);
	}

	public static int getSlotForItem(RItem item, Player player)
	{

		final List<NonNullList<ItemStack>> allInventories = Arrays.<NonNullList<ItemStack>> asList(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory);

		for (NonNullList<ItemStack> list : allInventories)
		{
			for (int i = 0; i < list.size(); ++i)
			{
				if (!list.get(i).isEmpty() && list.get(i).getItem() == item)
				{
					if (list.size() == 4)
						return 36 + i;
					if (list.size() == 1)
						return 40;
					return i;

				}
			}
		}

		return -1;
	}

}
