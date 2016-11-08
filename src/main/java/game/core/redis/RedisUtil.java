package game.core.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author nullzZ
 *
 */
@Service
public class RedisUtil implements IRedisUtil {
	private static final Logger logger = Logger.getLogger(RedisUtil.class);
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean set(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			logger.error("set异常", e);
			return false;
		}

	}

	@Override
	public <T> boolean set(String key, T value) {
		String v = JSON.toJSONString(value);
		return this.set(key, v);
	}

	@Override
	public boolean set(String key, String value, long seconds) {
		try {
			redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			logger.error("set异常", e);
			return false;
		}
	}

	@Override
	public <T> boolean set(String key, T value, long seconds) {
		String v = JSON.toJSONString(value);
		return this.set(key, v, seconds);
	}

	@Override
	public boolean hset(String key, String field, String value) {
		try {
			redisTemplate.opsForHash().put(key, field, value);
			return true;
		} catch (Exception e) {
			logger.error("hset异常", e);
			return false;
		}
	}

	@Override
	public <T> boolean hsetAll(String key, Map<String, T> values) {
		try {
			if (values == null) {
				return false;
			}
			Map<String, String> vm = new HashMap<>();
			for (String k : values.keySet()) {
				vm.put(k, JSON.toJSONString(values.get(k)));
			}
			redisTemplate.opsForHash().putAll(key, vm);
			return true;
		} catch (Exception e) {
			logger.error("hset异常", e);
			return false;
		}
	}

	public <T> boolean hsetAll(String key, Map<String, T> values, long seconds) {
		try {
			if (values == null) {
				return false;
			}
			Map<String, String> vm = new HashMap<>();
			for (String k : values.keySet()) {
				vm.put(k, JSON.toJSONString(values.get(k)));
			}
			redisTemplate.opsForHash().putAll(key, vm);
			redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			logger.error("hset异常", e);
			return false;
		}
	}

	@Override
	public <T> boolean hset(String key, String field, T value) {
		String v = JSON.toJSONString(value);
		return this.hset(key, field, v);
	}

	@Override
	public boolean hset(String key, String field, String value, long seconds) {
		try {
			if (this.hset(key, field, value)) {
				return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
			} else {
				logger.error("hset异常,设置时间");
				return false;
			}
		} catch (Exception e) {
			logger.error("hset异常", e);
			return false;
		}
	}

	@Override
	public <T> boolean hset(String key, String field, T value, long seconds) {
		String v = JSON.toJSONString(value);
		return this.hset(key, field, v, seconds);
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		T resultStr = null;
		try {
			String v = redisTemplate.opsForValue().get(key);
			if (v != null) {
				resultStr = JSON.parseObject(v, clazz);
			}
		} catch (Exception e) {
			logger.error("get异常", e);
		}
		return resultStr;
	}

	@Override
	public <T> T hget(String key, String field, Class<T> clazz) {
		T resultStr = null;
		try {
			String v = (String) redisTemplate.opsForHash().get(key, field);
			if (v != null) {
				resultStr = JSON.parseObject(v, clazz);
			}
		} catch (Exception e) {
			logger.error("hget异常", e);
		}
		return resultStr;
	}

	@Override
	public <T> List<T> hgetAll(String key, Class<T> clazz) {
		try {
			List<Object> v = redisTemplate.opsForHash().values(key);
			if (v != null) {
				List<T> resultStr = new ArrayList<>();
				for (Object o : v) {
					resultStr.add((T) JSON.parseObject((String) o, clazz));
				}
			}
		} catch (Exception e) {
			logger.error("hgetAll异常", e);
		}
		return null;
	}

	@Override
	public boolean containsKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			logger.error("containsKey异常", e);
		}
		return false;
	}

	@Override
	public boolean hContainsKey(String key, String field) {
		try {
			return redisTemplate.opsForHash().hasKey(key, field);
		} catch (Exception e) {
			logger.error("hContainsKey异常", e);
		}
		return false;
	}

	@Override
	public boolean remove(String key) {
		try {
			redisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			logger.error("remove异常", e);
		}
		return false;
	}

	@Override
	public boolean hremove(String key, String field) {
		try {
			return redisTemplate.opsForHash().delete(key, field) > 0;
		} catch (Exception e) {
			logger.error("hremove异常", e);
		}
		return false;
	}

	@Override
	public boolean hremove(String key, List<String> fields) {
		try {
			return redisTemplate.opsForHash().delete(key, fields) > 0;
		} catch (Exception e) {
			logger.error("hremoves异常", e);
		}
		return false;
	}

	@Override
	public long listLPush(String key, String value) {
		try {
			return redisTemplate.opsForList().leftPush(key, value);
		} catch (Exception e) {
			logger.error("listPush异常", e);
		}
		return 0;
	}

	@Override
	public String listRPop(String key) {
		try {
			return redisTemplate.opsForList().rightPop(key);
		} catch (Exception e) {
			logger.error("listRPop异常", e);
		}
		return null;
	}

	@Override
	public <T> long listLPush(String key, T value) {
		try {
			String v = JSON.toJSONString(value);
			return this.listLPush(key, v);
		} catch (Exception e) {
			logger.error("listPush异常", e);
		}
		return 0;
	}

	@Override
	public <T> T listRPop(String key, Class<T> clazz) {
		try {
			String v = redisTemplate.opsForList().rightPop(key);
			return JSON.parseObject(v, clazz);
		} catch (Exception e) {
			logger.error("listRPop异常", e);
		}
		return null;
	}

	@Override
	public long setPush(String key, String value) {
		try {
			return redisTemplate.opsForSet().add(key, value);
		} catch (Exception e) {
			logger.error("listPush异常", e);
		}
		return 0;
	}

	@Override
	public <T> long setPush(String key, T value) {
		try {
			String v = JSON.toJSONString(value);
			return this.setPush(key, v);
		} catch (Exception e) {
			logger.error("setPush异常", e);
		}
		return 0;
	}

	@Override
	public <T> T setPop(String key, Class<T> clazz) {
		try {
			String v = redisTemplate.opsForSet().pop(key);
			if (v != null) {
				T t = JSON.parseObject(v, clazz);
				return t;
			}
		} catch (Exception e) {
			logger.error("setPop异常", e);
		}
		return null;
	}

}
