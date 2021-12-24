package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockInvisiblockProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return new ItemStack(BlockHandler.blockTrough);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{

		String stack = accessor.getNBTData().getString("stack");
		String fluid = accessor.getNBTData().getString("fluid");

		if (!stack.isEmpty())
			currenttip.add(stack);

		if (fluid != null)
			currenttip.add(fluid);

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayerEntity player, TileEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		TileEntityInvisiblock tile = (TileEntityInvisiblock) te;
		TileEntityTrough trough = tile.getTrough();

		try
		{
			ItemStack stack = trough.itemHandler.getStackInSlot(0);
			FluidStack fluid = trough.fluidHandler.getFluid();

			if (!stack.isEmpty())
				tag.setString("stack", stack.getCount() + " " + stack.getDisplayName());

			if (fluid != null)
				tag.setString("fluid", fluid.getLocalizedName() + ", " + fluid.amount + "mB");

			return tag;
		}
		catch (Exception e)
		{
			return new CompoundTag();
		}
	}

}
