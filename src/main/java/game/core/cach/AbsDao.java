package game.core.cach;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import game.core.redis.RedisUtil;

/**
 * @author nullzZ
 *
 */
public abstract class AbsDao<T> implements IDao<T> {

	private static final Logger logger = Logger.getLogger(AbsDao.class);

	private long seconds = 100;

	@Resource
	private RedisUtil redisUtil;

	private String table = this.getClass().getSimpleName().replace("Dao", "");

	public boolean insertHEx(String primaryKey, long uid, T t) {
		if (redisUtil.hContainsKey(Keys.getDataKey(table, primaryKey), Keys.getDataFieldKey(table, primaryKey, uid))) {
			return false;
		}
		this.asynUpdateQ(t);
		return redisUtil.hset(Keys.getDataKey(table, primaryKey), Keys.getDataFieldKey(table, primaryKey, uid), t,
				seconds);
	}

	public boolean updateH(String primaryKey, long uid, T t) {
		return this.insertHEx(primaryKey, uid, t);
	}

	public boolean deleteH(String primaryKey, long uid) {
		this.asynDelete(uid);
		return redisUtil.hremove(Keys.getDataKey(table, primaryKey), String.valueOf(uid));
	}

	public T selectOne(String primaryKey, long uid, Class<T> clazz) {
		T t = redisUtil.hget(Keys.getDataKey(table, primaryKey), Keys.getDataFieldKey(table, primaryKey, uid), clazz);
		if (t == null) {
			t = this.selectOneByDB(uid);
			if (t != null) {
				this.insertHEx(primaryKey, uid, t);
			}
		}
		return t;
	}

	public List<T> selectAll(String primaryKey, Class<T> clazz) {
		List<T> list = redisUtil.hgetAll(Keys.getDataKey(table, primaryKey), clazz);
		if (list == null) {
			list = selectAllByDB(primaryKey);
			if (list != null && list.size() > 0) {
				Map<String, T> values = new HashMap<>();
				for (T vt : list) {
					try {
						Method method = vt.getClass().getDeclaredMethod("getUid");
						long uid = (long) method.invoke(vt);
						values.put(Keys.getDataFieldKey(table, primaryKey, uid), vt);
					} catch (Exception e) {
						logger.error("selectAll", e);
					}
				}
				redisUtil.hsetAll(Keys.getDataKey(table, primaryKey), values, seconds);
			}
		}
		return list;
	}

	/**
	 * 加入异步更新列表
	 * 
	 * @param roleId
	 * @param t
	 * @return
	 */
	private boolean asynUpdateQ(T t) {
		return redisUtil.setPush(Keys.getUdateKey(table), t) > 0;
	}

	/**
	 * 如果没有数据就是删除,删除时一定要验证一下redis set里是否删除了
	 * 
	 * @param table
	 * @param roleId
	 * @return
	 */
	private boolean asynDelete(long uid) {
		return redisUtil.setPush(Keys.getUdateKey(table), uid) > 0;
	}

}
