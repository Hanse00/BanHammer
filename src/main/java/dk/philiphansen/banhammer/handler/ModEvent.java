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

package dk.philiphansen.banhammer.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dk.philiphansen.banhammer.BanHammer;
import dk.philiphansen.banhammer.command.CommandHammerBan;
import dk.philiphansen.banhammer.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ModEvent {

	@SubscribeEvent
	public void EntityInteract(EntityInteractEvent event) {
		if (!event.entity.worldObj.isRemote) {
			Entity target = event.target;
			EntityPlayer player = event.entityPlayer;

			BanHammer.logger.info(player.toString());
			BanHammer.logger.info(target.toString());

			if ((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem() != null)) {
				if (player.getCurrentEquippedItem().getItem() == ModItems.itemBanHammer) {
					BanHammer.logger.info("Used ban hammer!");
					if ((target != null) && (target instanceof EntityPlayer)) {
						EntityPlayer targetPlayer = (EntityPlayer) target;
						BanHammer.logger.info("Hammer was used on player");

						CommandHammerBan banCommand = new CommandHammerBan();
						if (banCommand.canCommandSenderUseCommand(player)) {
							BanHammer.logger.info("User is allowed to kick");
							banCommand.processCommand(player, new String[]{targetPlayer.getDisplayName()});
						}
					}
				}
			}
		}
	}
}