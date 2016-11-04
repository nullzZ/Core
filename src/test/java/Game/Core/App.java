package game.core;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import game.core.cach.RedisManager;

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
