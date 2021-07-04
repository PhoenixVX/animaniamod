package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitKitRex;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

import StatBase;

public enum RabbitType implements AnimaniaType
{

	LOP(EntityRabbitBuckLop.class, EntityRabbitDoeLop.class, EntityRabbitKitLop.class, null, false),
	REX(EntityRabbitBuckRex.class, EntityRabbitDoeRex.class, EntityRabbitKitRex.class, null, true),
	DUTCH(EntityRabbitBuckDutch.class, EntityRabbitDoeDutch.class, EntityRabbitKitDutch.class, null, false),
	HAVANA(EntityRabbitBuckHavana.class, EntityRabbitDoeHavana.class, EntityRabbitKitHavana.class, null, false),
	NEW_ZEALAND(EntityRabbitBuckNewZealand.class, EntityRabbitDoeNewZealand.class, EntityRabbitKitNewZealand.class, null, true),
	JACK(EntityRabbitBuckJack.class, EntityRabbitDoeJack.class, EntityRabbitKitJack.class, null, false),
	COTTONTAIL(EntityRabbitBuckCottontail.class, EntityRabbitDoeCottontail.class, EntityRabbitKitCottontail.class, null, false),
	CHINCHILLA(EntityRabbitBuckChinchilla.class, EntityRabbitDoeChinchilla.class, EntityRabbitKitChinchilla.class, null, true);
	
	
	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	public boolean isPrime;
	
	private RabbitType(Class male, Class female, Class child, StatBase achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}
	
	@Override
	public EntityRabbitBuckBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.male.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityRabbitBuckBase male = null;
		try
		{
			male = (EntityRabbitBuckBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityRabbitDoeBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.female.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityRabbitDoeBase female = null;
		try
		{
			female = (EntityRabbitDoeBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityRabbitKitBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.child.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityRabbitKitBase child = null;
		try
		{
			child = (EntityRabbitKitBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static RabbitType breed(RabbitType male, RabbitType female)
	{
		return Animania.RANDOM.nextBoolean() ?  male : female;
	}

	public StatBase getAchievement()
	{
		return achievement;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":rabbit";
	}

	
}
