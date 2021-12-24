package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.compat.top.providers.TOPInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlockInvisiblock extends BaseEntityBlock implements TOPInfoProvider, IFoodProviderBlock
{

	protected static final AABB Invisiblock_AABB = new AABB(0.1D, 0.0D, 0.1D, 0.8D, 0.3D, 0.8D);

	private String name = "invisiblock";

	public BlockInvisiblock()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setBlockUnbreakable();
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(null);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, BlockState state, Entity entityIn)
	{
		if (entityIn != null && entityIn instanceof EntityItem)
		{

			BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

			Block chk1 = worldIn.getBlockState(pos1).getBlock();
			Block chk2 = worldIn.getBlockState(pos2).getBlock();
			Block chk3 = worldIn.getBlockState(pos3).getBlock();
			Block chk4 = worldIn.getBlockState(pos4).getBlock();

			if (chk1 != null && chk1 == BlockHandler.blockTrough)
			{
				String state1 = worldIn.getBlockState(pos1).toString().substring(29);
				state1 = state1.substring(0, state1.length() - 1);
				if (state1.contains("south"))
				{
					worldIn.updateComparatorOutputLevel(pos, chk1);
					pos = pos1;
					state = worldIn.getBlockState(pos1);
					chk1.onEntityCollidedWithBlock(worldIn, pos1, state, entityIn);
				}
			}

			if (chk2 != null && chk2 == BlockHandler.blockTrough)
			{
				String state2 = worldIn.getBlockState(pos2).toString().substring(29);
				state2 = state2.substring(0, state2.length() - 1);
				if (state2.contains("north"))
				{
					worldIn.updateComparatorOutputLevel(pos, chk2);
					pos = pos2;
					state = worldIn.getBlockState(pos2);
					chk2.onEntityCollidedWithBlock(worldIn, pos2, state, entityIn);

				}
			}

			if (chk3 != null && chk3 == BlockHandler.blockTrough)
			{
				String state3 = worldIn.getBlockState(pos3).toString().substring(29);
				state3 = state3.substring(0, state3.length() - 1);
				if (state3.contains("west"))
				{
					worldIn.updateComparatorOutputLevel(pos, chk3);
					pos = pos3;
					state = worldIn.getBlockState(pos3);
					chk3.onEntityCollidedWithBlock(worldIn, pos3, state, entityIn);

				}
			}

			if (chk4 != null && chk4 == BlockHandler.blockTrough)
			{
				String state4 = worldIn.getBlockState(pos4).toString().substring(29);
				state4 = state4.substring(0, state4.length() - 1);
				if (state4.contains("east"))
				{
					worldIn.updateComparatorOutputLevel(pos, chk4);
					pos = pos4;
					state = worldIn.getBlockState(pos4);
					chk4.onEntityCollidedWithBlock(worldIn, pos4, state, entityIn);
				}
			}

		}

	}

	@Override
	@SideOnly(Dist.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isTopSolid(BlockState state)
	{
		return false;
	}
	
	@Deprecated
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(BlockState state)
	{
		return false;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return BlockInvisiblock.Invisiblock_AABB;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean shouldSideBeRendered(BlockState blockState, IBlockAccess blockAccess, BlockPos pos, Direction side)
	{
		BlockState BlockState = blockAccess.getBlockState(pos.offset(side));
		Block block = BlockState.getBlock();

		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
	

	@Override
	public void breakBlock(World worldIn, BlockPos pos, BlockState state)
	{

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block block1 = worldIn.getBlockState(pos1).getBlock();
		Block block2 = worldIn.getBlockState(pos2).getBlock();
		Block block3 = worldIn.getBlockState(pos3).getBlock();
		Block block4 = worldIn.getBlockState(pos4).getBlock();

		if (block1 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos1);
			if (te != null && te.getTroughRotation() == 1 && !worldIn.isRemote)
			{
				worldIn.setBlockToAir(pos1);
				super.breakBlock(worldIn, pos1, state);
			}
		}

		if (block2 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos2);
			if (te != null && te.getTroughRotation() == 0 && !worldIn.isRemote)
			{
				worldIn.setBlockToAir(pos2);
				super.breakBlock(worldIn, pos2, state);
			}
		}

		if (block3 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos3);
			if (te != null && te.getTroughRotation() == 2 && !worldIn.isRemote)
			{
				worldIn.setBlockToAir(pos3);
				super.breakBlock(worldIn, pos3, state);
			}

		}

		if (block4 == BlockHandler.blockTrough)
		{
			TileEntityTrough te = (TileEntityTrough) worldIn.getTileEntity(pos4);
			if (te != null && te.getTroughRotation() == 3 && !worldIn.isRemote)
			{
				worldIn.setBlockToAir(pos4);
				super.breakBlock(worldIn, pos4, state);
			}

		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block chk1 = worldIn.getBlockState(pos1).getBlock();
		Block chk2 = worldIn.getBlockState(pos2).getBlock();
		Block chk3 = worldIn.getBlockState(pos3).getBlock();
		Block chk4 = worldIn.getBlockState(pos4).getBlock();

		if (chk1 != null && chk1 == BlockHandler.blockTrough)
		{
			String state1 = worldIn.getBlockState(pos1).toString().substring(29);
			state1 = state1.substring(0, state1.length() - 1);
			if (state1.contains("south"))
			{
				worldIn.updateComparatorOutputLevel(pos, chk1);
				pos = pos1;
				state = worldIn.getBlockState(pos1);
				return chk1.onBlockActivated(worldIn, pos1, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk2 != null && chk2 == BlockHandler.blockTrough)
		{
			String state2 = worldIn.getBlockState(pos2).toString().substring(29);
			state2 = state2.substring(0, state2.length() - 1);
			if (state2.contains("north"))
			{
				worldIn.updateComparatorOutputLevel(pos, chk2);
				pos = pos2;
				state = worldIn.getBlockState(pos2);
				return chk2.onBlockActivated(worldIn, pos2, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk3 != null && chk3 == BlockHandler.blockTrough)
		{
			String state3 = worldIn.getBlockState(pos3).toString().substring(29);
			state3 = state3.substring(0, state3.length() - 1);
			if (state3.contains("west"))
			{
				worldIn.updateComparatorOutputLevel(pos, chk3);
				pos = pos3;
				state = worldIn.getBlockState(pos3);
				return chk3.onBlockActivated(worldIn, pos3, state, playerIn, hand, facing, hitX, hitY, hitZ);

			}
		}

		if (chk4 != null && chk4 == BlockHandler.blockTrough)
		{
			String state4 = worldIn.getBlockState(pos4).toString().substring(29);
			state4 = state4.substring(0, state4.length() - 1);
			if (state4.contains("east"))
			{
				worldIn.updateComparatorOutputLevel(pos, chk4);
				pos = pos4;
				state = worldIn.getBlockState(pos4);
				return chk4.onBlockActivated(worldIn, pos4, state, playerIn, hand, facing, hitX, hitY, hitZ);
			}
		}

		return false;
	}

	@Override
	@Nullable
	public Item getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockTrough);
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(BlockHandler.blockTrough);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityInvisiblock();
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{

		TileEntityInvisiblock te = (TileEntityInvisiblock) worldIn.getTileEntity(pos);
		if (te.getTrough() != null)
		{
			if (!te.getTrough().itemHandler.getStackInSlot(0).isEmpty())
				return ItemHandlerHelper.calcRedstoneFromInventory(te.getTrough().itemHandler);

			if (te.getTrough().fluidHandler.getFluid() != null)
			{
				int fluid = te.getTrough().fluidHandler.getFluidAmount();
				return fluid / 66;
			}
		}
		return 0;
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityInvisiblock)
		{
			TileEntityInvisiblock invis = (TileEntityInvisiblock) te;
			if (invis.getTrough() != null)
			{
				TileEntityTrough trough = invis.getTrough();
				ItemStack stack = trough.itemHandler.getStackInSlot(0);
				FluidStack fluid = trough.fluidHandler.getFluid();

				if (mode == ProbeMode.NORMAL)
				{

					if (!stack.isEmpty())
					{
						probeInfo.horizontal();
						probeInfo.item(stack);
					}
					if (fluid != null)
					{
						ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, fluid.getFluid());
						probeInfo.horizontal().item(bucket).text(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

					}
				}
			}

		}

	}

}
