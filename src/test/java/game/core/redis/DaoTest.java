package game.core.redis;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import game.core.dao.TestDao;
import game.core.db.dto.OrderRecord;

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
		OrderRecord r = new OrderRecord();
		r.setUid(1004L);
		r.setRoleId(9999L);
		r.setName("test4");
		testDao.insert(r);

		List<OrderRecord> list = testDao.selectAll(r.getRoleId());
		for (OrderRecord or : list) {
			System.out.println("1@@@uid:" + or.getUid() + "|name:" + or.getName() + "|roleId:" + or.getRoleId());
		}
//		OrderRecord or2 = testDao.selectOne(9999L, 1000L);
//		System.out.println("2@@@uid:" + or2.getUid() + "|name:" + or2.getName() + "|roleId:" + or2.getRoleId());

	}
}
