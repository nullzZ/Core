package Game.Core;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import game.core.net.Server;
import game.core.net.code.MyEncoder;
import game.core.net.handle.MyServerHandler;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		PropertyConfigurator.configure("D:\\git\\Core\\src\\test\\resources\\log4j.xml");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		MyServerHandler myServerHandler = (MyServerHandler) ac.getBean("myServerHandler");
		MyEncoder myEncoder = (MyEncoder) ac.getBean("myEncoder");

		Server s = new Server(20000, myServerHandler, myEncoder);
		/*
		 * new Thread(new Runnable() { public void run() { try {
		 * Thread.sleep(5000); } catch (InterruptedException e) {
		 * e.printStackTrace(); } s.stop(); } }).start();
		 */
		s.start(true, true);

	}
}
