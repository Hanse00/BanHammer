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
import dk.philiphansen.banhammer.command.CommandHammerBan;
import dk.philiphansen.banhammer.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ModEvent {
	@SubscribeEvent
	public void EntityInteract(EntityInteractEvent event) {
		if (!event.entity.worldObj.isRemote) {
			Entity target = event.target;
			EntityPlayer player = event.entityPlayer;

			if ((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem() != null)) {
				if (player.getCurrentEquippedItem().getItem() == ModItems.itemBanHammer) {
					if (target != null) {
						if (target instanceof EntityPlayer) {
							EntityPlayer targetPlayer = (EntityPlayer) target;

							CommandHammerBan banCommand = new CommandHammerBan();
							if (banCommand.canCommandSenderUseCommand(player)) {
								banCommand.processCommand(player, new String[]{targetPlayer.getDisplayName()});
							}
						} else if (target instanceof EntityLiving) {
							World world = target.worldObj;
							Double strikeX = target.posX;
							Double strikeY = target.posY;
							Double strikeZ = target.posZ;

							world.addWeatherEffect(new EntityLightningBolt(world, strikeX, strikeY, strikeZ));
						}
					}
				}
			}
		}
	}
}
