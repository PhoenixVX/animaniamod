package com.animania.common.events;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Animania.MODID)
public class LoginEventHandler
{

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (UpdateHandler.show && AnimaniaConfig.gameRules.showModUpdateNotification == true)
		{
			event.getPlayer().sendMessage(new TextComponentString(UpdateHandler.updateStatus));

			ITextComponent download = new TextComponentString(I18n.translateToLocal("animania.update.download"));
			download.getStyle().setColor(TextFormatting.GOLD).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(I18n.translateToLocal("animania.update.show_latest_files")))).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://minecraft.curseforge.com/projects/animania/files"));
			ITextComponent component = new TextComponentString(I18n.translateToLocal("animania.update.click_to")).appendSibling(download);
			event.getPlayer().sendMessage(component);
		}
	}
}
