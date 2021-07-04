package com.animania.addons.farm.common.item;

import javax.annotation.Nullable;

import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemTruffleSoup extends ItemAnimaniaFood
{
	public ItemTruffleSoup()
	{
		super(10, 0.6f, "truffle_soup", new PotionEffect(MobEffects.REGENERATION, 1200, 1, false, false));
		this.setMaxStackSize(1);
		this.setContainerItem(Items.BOWL);
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity LivingEntity)
	{

		if (LivingEntity instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer) LivingEntity;
			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1, false, false));

			if (entityplayer.getFoodStats() != null)
			{
				entityplayer.getFoodStats().addStats(this, stack);
			}
			worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
			}

			if (!entityplayer.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
		}

		return stack.getCount() <= 0 ? new ItemStack(Items.BOWL) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer)
	{

	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		playerIn.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
}
