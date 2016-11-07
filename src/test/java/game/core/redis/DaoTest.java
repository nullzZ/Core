package game.core.redis;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import game.core.dao.TestDao;
import game.core.db.dto.Order;

/**
 * @author nullzZ
 *
 */
public class DaoTest {
	TestDao testDao;

	@Before
	public void init() {
		PropertyConfigurator.configure("D:\\git\\Core\\src\\test\\resources\\log4j.xml");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		testDao = (TestDao) ac.getBean("testDao");

	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			Order r = new Order();
			r.setUid((long) i);
			r.setRoleId((long) i);
			r.setName("test:" + i);
			testDao.insert(r);

			r.setName("test-u:" + i);
			testDao.update(r);

			testDao.delete(r);
		}

		// List<OrderRecord> list = testDao.selectAll(r.getRoleId());
		// for (OrderRecord or : list) {
		// System.out.println("1@@@uid:" + or.getUid() + "|name:" + or.getName()
		// + "|roleId:" + or.getRoleId());
		// }

		// OrderRecord or2 = testDao.selectOne(9999L, 1000L);
		// System.out.println("2@@@uid:" + or2.getUid() + "|name:" +
		// or2.getName() + "|roleId:" + or2.getRoleId());

	}
}
