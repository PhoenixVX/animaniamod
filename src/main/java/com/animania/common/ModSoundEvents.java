package com.animania.common;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents
{

	public static SoundEvent zap;
	public static SoundEvent combo;

	public static void registerSounds()
	{

		ModSoundEvents.zap = registerSound("zap");
		ModSoundEvents.combo = registerSound("combo");
	}

	private static SoundEvent registerSound(String soundName)
	{
		final ResourceLocation soundID = new ResourceLocation(Animania.MODID, soundName);
		SoundEvent s = new SoundEvent(soundID);
		s.setRegistryName(soundID);
		ForgeRegistries.SOUND_EVENTS.register(s);
		return s;
	}

}