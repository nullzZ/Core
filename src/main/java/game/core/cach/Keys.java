package game.core.cach;

/**
 * @author nullzZ
 *
 */
public class Keys {
	public static final String SIGN = "_";// 俩个key之间的分隔符
	public static final String DATA_KEY = "data";
	public static final String FIELD_KEY = "field";
	public static final String UPDATE_KEY = "asyn_update";

	public static String getUdateKey(String table) {
		StringBuilder key = new StringBuilder();
		key.append(Keys.UPDATE_KEY);
		key.append(Keys.SIGN);
		key.append(table);
		return key.toString();
	}

	public static String getDataKey(String table, String userKey) {
		StringBuilder key = new StringBuilder();
		key.append(Keys.DATA_KEY);
		key.append(Keys.SIGN);
		key.append(table);
		key.append(Keys.SIGN);
		key.append(userKey);
		return key.toString();
	}

	public static String getDataFieldKey(String table, String userKey, long uid) {
		StringBuilder key = new StringBuilder();
		key.append(getDataKey(table, userKey));
		key.append(Keys.SIGN);
		key.append(Keys.FIELD_KEY);
		key.append(Keys.SIGN);
		key.append(uid);
		return key.toString();
	}
}
