/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.original.util;

import org.seasar.framework.util.ResourceUtil;

public class ConfigUtil {
	/**
	 * tsuboneConfig.propertiesで設定したデータを取得する。tsuboneConfig.propertiesに書かれていない場合はエラーにします。
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		String value = ResourceUtil.getProperties("tsuboneConfig.properties").getProperty(key);
		if (value == null) {
			throw new RuntimeException("not set key(key:" + key + ") at tsuboneConfig.properties");
		}
		return value;
	}
}
