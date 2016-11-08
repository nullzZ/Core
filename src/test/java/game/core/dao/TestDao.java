package game.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import game.core.cach.AbsDao;
import game.core.db.dao.OrdersMapper;
import game.core.db.dto.Orders;
import game.core.db.dto.OrdersExample;

/**
 * @author nullzZ
 *
 */
@Service
public class TestDao extends AbsDao<Orders> implements ITestDao {

	@Resource
	private OrdersMapper ordersMapper;

	@Override
	public boolean insert(Orders t) {
		return super.insertHEx(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public boolean update(Orders t) {
		return super.updateH(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public boolean delete(Orders t) {
		return super.deleteH(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public List<Orders> selectAll(long roleId) {
		return super.selectAll(String.valueOf(roleId), Orders.class);
	}

	@Override
	public Orders selectOne(long roleId, long uid) {
		return super.selectOne(String.valueOf(roleId), uid, Orders.class);
	}

	@Override
	public Orders selectOneByDB(long uid) {
		return ordersMapper.selectByPrimaryKey(uid);
	}

	@Override
	public List<Orders> selectAllByDB(String roleId) {
		OrdersExample ex = new OrdersExample();
		ex.createCriteria().andRoleIdEqualTo(Long.parseLong(roleId));
		return ordersMapper.selectByExample(ex);
	}

	@Override
	public int updateDB(Orders t) {
		return ordersMapper.updateByPrimaryKey(t);
	}

	@Override
	public int insertDB(Orders t) {
		return ordersMapper.insertSelective(t);
	}

	@Override
	public int deleteDB(Orders t) {
		return ordersMapper.deleteByPrimaryKey(t.getUid());
	}

}
