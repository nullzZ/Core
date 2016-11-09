package game.core.cach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import game.core.redis.RedisUtil;

/**
 * @author nullzZ </br>
 * 
 */
public abstract class AbsDao<T extends AbsRecord> implements IDao<T> {

	private static final Logger logger = Logger.getLogger(AbsDao.class);

	private long seconds = 100;

	@Resource
	private RedisUtil redisUtil;

	private String table = this.getClass().getSimpleName().replace("Dao", "");

	@SuppressWarnings("unchecked")
	protected boolean insertHEx(String primaryKey, T t) {
		if (!redisUtil.containsKey(Keys.getDataKey(table, primaryKey))) {
			this.selectAllDBAndSet(primaryKey, (Class<T>) t.getClass());// 优先查询一下
		}
		if (redisUtil.hContainsKey(Keys.getDataKey(table, primaryKey),
				Keys.getDataFieldKey(table, primaryKey, t.getUid()))) {
			logger.error("[insertHEx]:key重复");
			return false;
		}
		this.asynInsertQ(t);
		return redisUtil.hset(Keys.getDataKey(table, primaryKey), Keys.getDataFieldKey(table, primaryKey, t.getUid()),
				t, seconds);
	}

	@SuppressWarnings("unchecked")
	protected boolean updateHEx(String primaryKey, T t) {
		if (!redisUtil.containsKey(Keys.getDataKey(table, primaryKey))) {
			this.selectAllDBAndSet(primaryKey, (Class<T>) t.getClass());// 优先查询一下
		}
		if (!redisUtil.hContainsKey(Keys.getDataKey(table, primaryKey),
				Keys.getDataFieldKey(table, primaryKey, t.getUid()))) {
			logger.error("[updateH]:key不存在");
			return false;
		}
		this.asynUpdateQ(t);
		return redisUtil.hset(Keys.getDataKey(table, primaryKey), Keys.getDataFieldKey(table, primaryKey, t.getUid()),
				t, seconds);
	}

	@SuppressWarnings("unchecked")
	protected boolean deleteH(String primaryKey, T t) {
		if (!redisUtil.containsKey(Keys.getDataKey(table, primaryKey))) {
			this.selectAllDBAndSet(primaryKey, (Class<T>) t.getClass());// 优先查询一下
		}
		this.asynDelete(t);
		return redisUtil.hremove(Keys.getDataKey(table, primaryKey),
				Keys.getDataFieldKey(table, primaryKey, t.getUid()));
	}

	// protected T selectOne(String primaryKey, long uid, Class<T> clazz) {
	// T t = redisUtil.hget(Keys.getDataKey(table, primaryKey),
	// Keys.getDataFieldKey(table, primaryKey, uid), clazz);
	// if (t == null) {
	// t = this.selectOneByDB(uid);
	// if (t != null) {
	// this.insertHEx(primaryKey, t);
	// }
	// }
	// return t;
	// }

	protected T selectOne(String primaryKey, Class<T> clazz) {
		T t = redisUtil.get(Keys.getDataKey(table, primaryKey), clazz);
		if (t == null) {
			t = this.selectOneByDB(primaryKey);
			if (t != null) {
				this.insertHEx(primaryKey, t);
			}
		}
		return t;
	}

	protected List<T> selectAll(String primaryKey, Class<T> clazz) {
		List<T> list = redisUtil.hgetAll(Keys.getDataKey(table, primaryKey), clazz);
		if (list == null) {
			list = selectAllDBAndSet(primaryKey, clazz);
		}
		return list;
	}

	private List<T> selectAllDBAndSet(String primaryKey, Class<T> clazz) {
		List<T> list = selectAllByDB(primaryKey);
		if (list != null && list.size() > 0) {
			Map<String, T> values = new HashMap<>();
			for (T vt : list) {
				try {
					values.put(Keys.getDataFieldKey(table, primaryKey, vt.getUid()), vt);
				} catch (Exception e) {
					logger.error("[selectAll]", e);
				}
			}
			redisUtil.hsetAll(Keys.getDataKey(table, primaryKey), values, seconds);
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
		setFlag(t, CachFlag.UPDATE);
		return redisUtil.listLPush(Keys.getUdateKey(), t) > 0;
	}

	private boolean asynInsertQ(T t) {
		setFlag(t, CachFlag.INSERT);
		return redisUtil.listLPush(Keys.getUdateKey(), t) > 0;
	}

	private boolean asynDelete(T t) {
		setFlag(t, CachFlag.DELETE);
		return redisUtil.listLPush(Keys.getUdateKey(), t) > 0;
	}

	private void setFlag(T t, CachFlag cachFlag) {
		t.setFlag(cachFlag);
		t.setDaoName(t.getClass().getSimpleName());
	}

}
