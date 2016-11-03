package game.core.redis;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import game.core.redis.RedisUtil;

public class RedisTest {

	RedisUtil redisUtil;

	@Before
	public void init() {
		try {
			PropertyConfigurator.configure("D:\\ARPGProject\\DataServer\\src\\main\\resources\\log4j.xml");
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			redisUtil = (RedisUtil) ac.getBean("redisUtil");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void set() {
		A a = new A();
		a.setId(1);
		a.setName("test_1");
		boolean ret = redisUtil.set("test_1", a);
		Assert.assertEquals(ret, true);

		A ra = redisUtil.get("test_1", A.class);
		System.out.println("get:" + ra.getId() + "|" + ra.getName());

		// redisUtil.remove("test_1");
	}

	@Test
	public void hset() {
		A a = new A();
		a.setId(1);
		a.setName("htest1");
		boolean ret = redisUtil.hset("htest_1", "htest_file_1", a);

		A a2 = new A();
		a2.setId(2);
		a2.setName("htest2");
		redisUtil.hset("htest_1", "htest_file_2", a2);
		Assert.assertEquals(ret, true);

		A ra = redisUtil.hget("htest_1", "htest_file_1", A.class);
		System.out.println("hget:" + ra.getId() + "|" + ra.getName());

		List<A> rr = redisUtil.hgetAll("htest_1", A.class);
		for (A r : rr) {
			System.out.println("hgetall:" + ((A) r).getId() + "|" + ((A) r).getName());
		}
	}

}
