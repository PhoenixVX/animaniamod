package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockWildHive extends BlockHive
{
	private String name = "block_wild_hive";

	public BlockWildHive()
	{
		super(Material.SPONGE, MaterialColor.YELLOW);
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.3f);
		this.setResistance(0.3f);
		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "wild_hive"));
		ForgeRegistries.ITEMS.register(item);
	}
}
