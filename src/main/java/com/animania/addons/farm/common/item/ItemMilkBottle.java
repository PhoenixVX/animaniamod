package com.animania.addons.farm.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemMilkBottle extends ItemAnimaniaFood
{
	public ItemMilkBottle()
	{
		super(4, 1f, "milk_bottle");
		this.setMaxStackSize(4);
		this.setContainerItem(Items.GLASS_BOTTLE);
		this.setAlwaysEdible();
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity LivingEntity)
	{

		if (LivingEntity instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer) LivingEntity;
			entityPlayer.clearActivePotions();

			if (!entityPlayer.capabilities.isCreativeMode)
			{

				if (!worldIn.isRemote)
				{
					EntityItem entityitem = new EntityItem(worldIn, LivingEntity.posX + 0.5D, LivingEntity.posY + 0.5D, LivingEntity.posZ + 0.5D, new ItemStack(Items.GLASS_BOTTLE));
					AnimaniaHelper.spawnEntity(worldIn, entityitem);
				}

				if (entityPlayer.getFoodStats() != null)
				{
					entityPlayer.getFoodStats().addStats(this, stack);
				}
				worldIn.playSound((EntityPlayer) null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
				this.onFoodEaten(stack, worldIn, entityPlayer);
				entityPlayer.addStat(StatList.getObjectUseStats(this));
				stack.shrink(1);
			}
		}

		return stack;
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		playerIn.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer)
	{

	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;

	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.removeall"));

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}
}
