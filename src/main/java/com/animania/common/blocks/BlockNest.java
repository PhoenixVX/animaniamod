package com.animania.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;
import com.animania.compat.top.providers.TOPInfoProvider;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

public class BlockNest extends BaseEntityBlock implements TOPInfoProvider
{
	private String name = "block_nest";

	protected static final AABB AABB = new AABB(0.0D, 0.0D, 0.00D, 1.0D, 0.3D, 1.0D);

	public BlockNest()
	{
		super(Material.WOOD);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		BlockHandler.blocks.add(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setTickRandomly(true);

	}

	@Override
	public int tickRate(World worldIn)
	{
		return 5;
	}

	@Override
	public String getLocalizedName()
	{
		return I18n.translateToLocal("tile.animania_block_nest.name");
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
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
	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{

		return BlockNest.AABB;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, BlockState state, Random rand)
	{
		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);
		if (te != null && te.getNestContent() != NestContent.EMPTY)
		{

			AddonInjectionHandler.runInjection("farm", "nestHatchChickens", Void.class, te, worldIn, pos, state, rand);

			AddonInjectionHandler.runInjection("extra", "nestHatchPeafowl", Void.class, te, worldIn, pos, state, rand);
		}

	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{

		return this.defaultBlockState();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityNest();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, BlockState state)
	{
		return new ItemStack(BlockHandler.blockNest, 1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, BlockState state)
	{

		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);

		if (te != null)
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.itemHandler.getStackInSlot(0));

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@Nullable
	public Item getItemDropped(BlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockHandler.blockNest);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{

		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntityNest te = (TileEntityNest) worldIn.getTileEntity(pos);

		if (te != null && heldItem.isEmpty() && !playerIn.isSneaking())
		{
			ItemStack stack = te.itemHandler.extractItem(0, 1, false);
			AnimaniaHelper.addItem(playerIn, stack);
			return true;
		}

		return false;

	}

	public String getName()
	{
		return this.name;
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData data)
	{
		TileEntity te = world.getTileEntity(data.getPos());
		if (te instanceof TileEntityNest)
		{
			TileEntityNest nest = (TileEntityNest) te;
			ItemStack stack = nest.itemHandler.getStackInSlot(0);

			if (mode == ProbeMode.NORMAL)
			{
				if (!stack.isEmpty())
				{
					probeInfo.horizontal();
					probeInfo.item(stack);
					if (nest.birdType != null)
						probeInfo.text("From: " + nest.birdType.toString().substring(0, 1).toUpperCase() + nest.birdType.toString().substring(1).toLowerCase());
				}
			}

		}

	}

}