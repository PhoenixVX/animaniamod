package com.animania.addons.farm.common.item;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRidingCrop extends Item
{
	private String name = "riding_crop";

	public ItemRidingCrop()
	{
		super();
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{

		ItemStack itemStackIn = playerIn.getHeldItem(hand);

		if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof EntityAnimaniaHorse)
		{
			EntityAnimaniaHorse entityhorse = (EntityAnimaniaHorse) playerIn.getRidingEntity();

			if (itemStackIn.getMaxDamage() - itemStackIn.getMetadata() >= 1 && entityhorse.boost())
			{
				itemStackIn.damageItem(1, playerIn);

				if (itemStackIn.getCount() == 0)
				{
					itemStackIn.shrink(1);
				}

				return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
			}

		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(EnumActionResult.PASS, itemStackIn);

	}

	public String getName()
	{
		return name;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn)
	{
		list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.ridingcrop1"));
		list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.ridingcrop2"));

	}

}
