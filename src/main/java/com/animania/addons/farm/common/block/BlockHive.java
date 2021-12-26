package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.tileentity.TileEntityHive;
import com.animania.common.handler.BlockHandler;

import PropertyDirection;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHive extends BaseEntityBlock
{
	private String name = "block_hive";
	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public BlockHive()
	{
		super(Material.WOOD, MaterialColor.YELLOW);
		this.setSoundType(SoundType.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, Direction.NORTH));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.3f);
		this.setResistance(0.3f);
		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "bee_hive"));
		ForgeRegistries.ITEMS.register(item);
	}

	public BlockHive(Material mat, MaterialColor color)
	{
		super(mat, color);
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
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
	public boolean onBlockActivated(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = player.getItemInHand(hand);
		TileEntityHive hive = (TileEntityHive) level.getTileEntity(pos);

		if (hive != null)
		{
			// EMPTY LIQUIDS
			if (!heldItem.isEmpty() && FluidUtil.getFluidHandler(heldItem) != null && FluidUtil.getFluidContained(heldItem) == null && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = heldItem.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						}
						else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;

			}
			else if (!heldItem.isEmpty() && heldItem.getItem() == Items.GLASS_BOTTLE && hive.fluidHandler.getFluid() != null && hive.fluidHandler.getFluid().amount >= 1000)
			{
				FluidStack fluidStack = hive.fluidHandler.drain(1000, true);
				ItemStack honeyBottle = new ItemStack(FarmAddonItemHandler.honeyJar);

				if (!player.isCreative())
				{
					IFluidHandlerItem handler;
					if (heldItem.getCount() >= 1)
					{
						ItemStack heldItem1 = honeyBottle.copy();
						heldItem1.setCount(1);
						handler = FluidUtil.getFluidHandler(heldItem1);

						handler.fill(fluidStack, true);
						ItemStack newstack = handler.getContainer();

						if (heldItem.getCount() > 1)
						{
							heldItem.shrink(1);
							player.inventory.addItemStackToInventory(newstack);
						}
						else
							player.setHeldItem(hand, newstack);

						return true;
					}

				}

				level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 0.6F, 0.8F);
				return true;
			}

			if (!level.isClientSide && player.isSneaking() && hand == InteractionHand.MAIN_HAND)
			{
				int honey = hive.fluidHandler.getFluidAmount();

				player.sendStatusMessage(new TextComponent("Honey stored: " + honey + "mB"), true);
			}

		}

		return true;
	}

	public BlockState getStateFromMeta(int meta)
	{
		Direction enumfacing = Direction.getFront(meta);

		if (enumfacing.getAxis() == Direction.Axis.Y)
		{
			enumfacing = Direction.NORTH;
		}

		return this.defaultBlockState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(BlockState state)
	{
		return ((Direction) state.getValue(FACING)).getIndex();
	}

	@Override
	public BlockState getStateForPlacement(Level levelIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{
		return this.defaultBlockState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public TileEntity createNewTileEntity(Level levelIn, int meta)
	{
		return new TileEntityHive();
	}

}
