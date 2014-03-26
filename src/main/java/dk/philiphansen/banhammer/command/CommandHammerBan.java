/*
 * Copyright (C) 2014 Philip Hansen.
 *
 * This file is part of BanHammer.
 *
 * BanHammer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BanHammer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with BanHammer.  If not, see <http://www.gnu.org/licenses/>.
 */

package dk.philiphansen.banhammer.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.BanEntry;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class CommandHammerBan extends CommandBase {
	public String getCommandName() {
		return "hammerBan";
	}

	public int getRequiredPermissionLevel() {
		return 3;
	}

	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "commands.hammerBan.usage";
	}

	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
		return MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isListActive() && super
				.canCommandSenderUseCommand(par1ICommandSender);
	}

	public void processCommand(ICommandSender commandSender, String[] commandArgs) {

		if (commandArgs.length >= 1 && commandArgs[0].length() > 0) {
			EntityPlayerMP entityPlayerMP = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername
					(commandArgs[0]);
			BanEntry banEntry = new BanEntry(commandArgs[0]);
			banEntry.setBannedBy(commandSender.getCommandSenderName());

			MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().put(banEntry);

			if (entityPlayerMP != null) {
				World world = entityPlayerMP.getEntityWorld();
				Double strikeX = entityPlayerMP.posX;
				Double strikeY = entityPlayerMP.posY;
				Double strikeZ = entityPlayerMP.posZ;

				world.addWeatherEffect(new EntityLightningBolt(world, strikeX, strikeY, strikeZ));

				entityPlayerMP.playerNetServerHandler.kickPlayerFromServer(StatCollector.translateToLocal("commands" +
						".hammerBan.message"));
			}

			notifyAdmins(commandSender, "commands.hammerBan.success", new Object[]{commandArgs[0]});
		} else {
			throw new WrongUsageException("commands.hammerBan.usage", new Object[0]);
		}
	}

	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
		return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr,
				MinecraftServer.getServer().getAllUsernames()) : null;
	}
}
