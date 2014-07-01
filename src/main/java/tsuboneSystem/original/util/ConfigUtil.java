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
