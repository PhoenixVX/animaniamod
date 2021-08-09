package com.animania.common.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animania.api.interfaces.IConvertable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AnimaniaCommand extends CommandBase
{
	private static Map<MinecraftServer, Long> servers = new HashMap<MinecraftServer, Long>();

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getName()
	{
		return "animania";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "/animania <tovanilla>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (args.length > 0)
		{
			if (args[0].toLowerCase().equals("tovanilla"))
			{
				if (sender instanceof PlayerEntity)
				{
					if (servers.containsKey(server))
					{
						long time = servers.get(server);
						long curr = System.currentTimeMillis();
						if (time >= curr)
						{
							for (World world : server.worlds)
							{
								List<Entity> convertedEntities = new ArrayList<Entity>();

								for (Entity entity : world.loadedEntityList)
								{
									if (entity instanceof IConvertable)
									{
										Entity converted = ((IConvertable) entity).convertToVanilla();
										entity.setPosition(entity.getX(), -200, entity.getZ());
										entity.setDead();
										convertedEntities.add(converted);
									}
								}

								convertedEntities.forEach(conv -> world.spawnEntity(conv));
							}

							servers.remove(server);
							return;
						}
					}

					servers.put(server, System.currentTimeMillis() + 20_000);
					throw new SyntaxErrorException("WARNING!!!! THIS IS A DESTRUCTIVE ACTION THAT CANNOT BE UNDONE! ALL YOUR ANIMANIA ANIMALS (IN LOADED CHUNKS) WILL BE CONVERTED TO VANILLA ANIMALS! TO CONFIRM, EXECUTE THIS COMMAND AGAIN.");

				} else
					throw new SyntaxErrorException("Only a player can execute this command");
			}
		}

		throw new WrongUsageException(this.getUsage(sender));
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		if (args.length > 0)
		{
			if (args.length == 1)
			{
				return CommandBase.getListOfStringsMatchingLastWord(args, "tovanilla");
			}
		}

		return Collections.EMPTY_LIST;
	}

}
