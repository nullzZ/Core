package game.core.cach;

import java.util.List;

import javax.annotation.Resource;

import game.core.redis.RedisUtil;

/**
 * @author nullzZ
 *
 */
public abstract class AbsDao<T> implements IDao<T> {

	@Resource
	private RedisUtil redisUtil;
	private long seconds=5;

	@Override
	public void insert(String key, T t) {
		redisUtil.set(key, t, seconds);
	}

	@Override
	public List<T> selectAll(String key, Class<T> clazz) {
		List<T> list = redisUtil.hgetAll(key, clazz);
		if (list == null) {
			// mysql
		}

		return list;
	}
	
	
	
	
}
