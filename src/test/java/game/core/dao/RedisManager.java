package game.core.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import game.core.cach.Keys;
import game.core.db.dao.OrderRecordMapper;
import game.core.db.dto.OrderRecord;
import game.core.redis.RedisUtil;

/**
 * @author nullzZ
 *
 */
@Service
public class RedisManager {

	public Map<String, Class<?>> keys = new HashMap<>();
	@Resource
	private RedisUtil redisUtil;
	@Resource
	private OrderRecordMapper orderRecordMapper;

	public void init() {
		keys.put("test", OrderRecord.class);
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					OrderRecord or = redisUtil.setPop(Keys.getUdateKey("TestDao"), OrderRecord.class);
					if (or != null) {
						if (orderRecordMapper.updateByPrimaryKey(or) <= 0) {
							orderRecordMapper.insert(or);
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
}
