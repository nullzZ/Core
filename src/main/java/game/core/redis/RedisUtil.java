package game.core.redis;

import java.util.ArrayList;

import java.util.List;
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
    public boolean set(String key, Object value) {
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
    public boolean set(String key, Object value, long seconds) {
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
    public boolean hset(String key, String field, Object value) {
	String v = JSON.toJSONString(value);
	return this.hset(key, field, v);
    }

    @Override
    public boolean hset(String key, String field, String value, long seconds) {
	try {
	    if (this.hset(key, field, value)) {
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	    } else {
		return false;
	    }
	} catch (Exception e) {
	    logger.error("hset异常", e);
	    return false;
	}
    }

    @Override
    public boolean hset(String key, String field, Object value, long seconds) {
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
	List<T> resultStr = new ArrayList<>();
	try {
	    List<Object> v = redisTemplate.opsForHash().values(key);
	    if (v != null) {
		for (Object o : v) {
		    resultStr.add((T) JSON.parseObject((String) o, clazz));
		}
	    }
	} catch (Exception e) {
	    logger.error("hgetAll异常", e);
	}
	return resultStr;
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

}
