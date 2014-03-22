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

package dk.philiphansen.banhammer.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.banhammer.reference.ItemInfo;

public class ModItems {
	public static ItemBanHammer itemBanHammer;

	public static void init() {
		itemBanHammer = new ItemBanHammer();

		GameRegistry.registerItem(itemBanHammer, ItemInfo.BAN_HAMMER_NAME);
	}
}
