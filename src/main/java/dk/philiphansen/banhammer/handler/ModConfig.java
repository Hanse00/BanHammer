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

import dk.philiphansen.banhammer.reference.ItemInfo;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class ModConfig {
	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		ItemInfo.BAN_HAMMER_ITEM_ID = config.get("Item id", "Ban Hammer id", ItemInfo.BAN_HAMMER_ITEM_ID_DEFAULT).getInt();

		config.save();
	}
}
