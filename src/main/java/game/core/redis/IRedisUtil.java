package game.core.redis;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author nullzZ
 *
 */
public interface IRedisUtil {

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public boolean set(String key, String value);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public <T> boolean set(String key, T value);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public boolean set(String key, String value, long seconds);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public <T> boolean set(String key, T value, long seconds);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * 
	 * @param field
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public boolean hset(String key, String field, String value);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public <T> boolean hset(String key, String field, T value);

	public <T> boolean hsetAll(String key, Map<String, T> values);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public boolean hset(String key, String field, String value, long seconds);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 * @param value
	 *            must not be {@literal null}.
	 */
	public <T> boolean hset(String key, String field, T value, long seconds);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 */
	public <T> T get(String key, Class<T> clazz);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 */
	public <T> T hget(String key, String field, Class<T> clazz);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 */
	public <T> List<T> hgetAll(String key, Class<T> clazz);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 */
	public boolean containsKey(String key);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 */
	public boolean hContainsKey(String key, String field);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 */
	public boolean remove(String key);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param field
	 *            must not be {@literal null}.
	 */
	public boolean hremove(String key, String field);

	/**
	 *
	 * @param key
	 *            must not be {@literal null}.
	 * @param fields
	 *            must not be {@literal null}.
	 */
	public boolean hremove(String key, List<String> fields);

	public long listLPush(String key, String value);

	public String listRPop(String key);

	public <T> T listRPop(String key, Class<T> clazz);

	public <T> long listLPush(String key, T value);

	public long setPush(String key, String value);

	public <T> long setPush(String key, T value);

	public <T> T setPop(String key, Class<T> clazz);
}
