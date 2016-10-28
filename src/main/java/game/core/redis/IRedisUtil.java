package game.core.redis;

import java.util.List;

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
	public boolean set(String key, Object value);

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
	public boolean set(String key, Object value, long seconds);

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
	public boolean hset(String key, String field, Object value);

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
	public boolean hset(String key, String field, Object value, long seconds);

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
}
