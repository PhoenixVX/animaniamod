package com.animania.common.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IFoodEating;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEntityEgg extends Item
{

	private String name = "entity_egg";
	public AnimaniaType type;
	public EntityGender gender;

	public static Map<AnimalContainer, Item> ANIMAL_EGGS = new HashMap<AnimalContainer, Item>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_PRIMARY = new HashMap<AnimalContainer, Integer>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_SECONDARY = new HashMap<AnimalContainer, Integer>();
	public static Map<AnimalContainer, Boolean> ANIMAL_USES_COLOR = new HashMap<AnimalContainer, Boolean>();

	public ItemEntityEgg(String atype, AnimaniaType animal, EntityGender gender)
	{
		super();
		this.setCreativeTab(Animania.TabAnimaniaEggs);
		this.maxStackSize = 64;
		this.name = this.name + "_" + atype;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.type = animal;
		this.gender = gender;

		ANIMAL_EGGS.put(new AnimalContainer(animal, gender), this);

	}

	@Override
	public EnumActionResult onItemUse(PlayerEntity playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getHeldItem(hand);

		if (world.isRemote)
			return EnumActionResult.SUCCESS;

		LivingEntity entity = null;

		if (this.gender == EntityGender.RANDOM)
		{
			Class<? extends AnimaniaType> clazz = this.type.getClass();
			AnimaniaType[] types = clazz.getEnumConstants();

			if (type instanceof RandomAnimalType)
				entity = EntityGender.getEntity(type, gender, world);
			else
				entity = EntityGender.getEntity(types[Animania.RANDOM.nextInt(types.length)], gender, world);
		}
		else
		{
			entity = EntityGender.getEntity(type, gender, world);
		}
		if (entity != null)
		{

			entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);

			if (stack.hasDisplayName())
				((LivingEntity) entity).setCustomNameTag(stack.getDisplayName());

			if (!playerIn.isCreative())
				stack.shrink(1);

			world.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;
			
			if(entity instanceof IFoodEating)
			{
				IFoodEating foodEating = (IFoodEating) entity;
				foodEating.setInteracted(true);
			}
			
			AnimaniaHelper.spawnEntity(world, entity);
			return EnumActionResult.SUCCESS;

		}

		return EnumActionResult.FAIL;
	}

	public AnimalContainer getAnimal()
	{
		return new AnimalContainer(this.type, this.gender);
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return I18n.translateToLocal("entity.animania:" + stack.getItem().getRegistryName().getResourcePath().replace("entity_egg_", "") + ".name");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_entity_egg.desc1") + " " + TextFormatting.DARK_GRAY + I18n.translateToLocal("item.animania_entity_egg.desc2"));
	}

	@SideOnly(Dist.CLIENT)
	public static class Color implements IItemColor
	{
		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex)
		{
			World world = Minecraft.getMinecraft().world;
			if (!stack.isEmpty() && stack.getItem() != ItemHandler.entityeggrandomanimal)
			{
				AnimalContainer animal = ((ItemEntityEgg) stack.getItem()).getAnimal();

				if (animal.getGender() != EntityGender.RANDOM)
				{
					if (ANIMAL_USES_COLOR.containsKey(animal) && ANIMAL_USES_COLOR.get(animal).booleanValue())
					{
						switch (tintIndex)
						{
						case 0:
							return ANIMAL_COLOR_PRIMARY.get(animal).intValue();
						case 1:
							return ANIMAL_COLOR_SECONDARY.get(animal).intValue();

						}
					}
				}
			}
			return -1;
		}

	}
}
