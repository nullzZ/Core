package game.core.cach;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import game.core.db.dao.OrderRecordMapper;
import game.core.redis.RedisUtil;

/**
 * @author nullzZ
 *
 */
@Service
public class RedisManager {

	private static final Logger logger = Logger.getLogger(RedisManager.class);
	@SuppressWarnings("rawtypes")
	public Map<String, AbsDao> keys = new HashMap<>();
	public Map<String, String> keyRecords = new HashMap<>();
	@Resource
	private RedisUtil redisUtil;
	@Resource
	private OrderRecordMapper orderRecordMapper;
	@SuppressWarnings("rawtypes")
	@Resource
	private List<AbsDao> daos;

	@SuppressWarnings("rawtypes")
	private void init() {
		for (AbsDao ad : daos) {
			String name = ad.getClass().getSimpleName().replace("Dao", "");
			Type genType = ad.getClass().getGenericSuperclass();
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			Class<?> entityClass = (Class) params[0];
			keys.put(name, ad);
			keyRecords.put(name, entityClass.getName());
		}
	}

	public void start() {
		init();
		new Thread(new Runnable() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void run() {
				while (true) {
					for (String daoName : keys.keySet()) {
						String k = Keys.getUdateKey(daoName);
						AbsDao dao = keys.get(daoName);
						Object or = null;
						try {
							or = redisUtil.setPop(k, Class.forName(keyRecords.get(daoName)));
							if (or != null && dao != null) {
								if (dao.updateDB(or) <= 0) {
									dao.insertDB(or);
								}
							} else {
								logger.error("[异步存储异常]key:" + k + "|" + JSON.toJSONString(or));
							}
						} catch (Exception e) {
							if (or == null) {
								logger.error("[异步存储异常]key:" + k + "|" + JSON.toJSONString(or));
							} else {
								logger.error("[异步存储异常]key:" + k + "|" + JSON.toJSONString(or));
							}

						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

		logger.info("[异步mysql存储]启动成功");
	}
}
