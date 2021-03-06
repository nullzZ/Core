package game.core;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import game.core.cach.RedisManager;
import game.core.dao.TestDao;
import game.core.db.dto.Orders;

/**
 * Hello world!
 *
 */
@Service
public class App {

	// @Resource
	// private MyServerHandler myServerHandler;
	// @Resource
	// private MyEncoder myEncoder;

	// public void start() {
	// Server s = new Server(20000, myServerHandler, myEncoder);
	// s.start(false, false);
	// }

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("D:\\git\\Core\\src\\test\\resources\\log4j.xml");
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			RedisManager redisManager = (RedisManager) ac.getBean("redisManager");
			redisManager.start();

			for (int i = 0; i < 10; i++) {
				Orders r = new Orders();
				r.setUid((long) i);
				r.setRoleId((long) i);
				r.setName("test:" + i);
				TestDao testDao = (TestDao) ac.getBean("testDao");
				testDao.update(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * new Thread(new Runnable() { public void run() { try {
		 * Thread.sleep(5000); } catch (InterruptedException e) {
		 * e.printStackTrace(); } s.stop(); } }).start();
		 */
	}
}
