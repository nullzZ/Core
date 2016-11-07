package game.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import game.core.cach.AbsDao;
import game.core.db.dao.OrderMapper;
import game.core.db.dto.Order;
import game.core.db.dto.OrderExample;

/**
 * @author nullzZ
 *
 */
@Service
public class TestDao extends AbsDao<Order> implements ITestDao {

	@Resource
	private OrderMapper orderMapper;

	@Override
	public boolean insert(Order t) {
		return super.insertHEx(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public boolean update(Order t) {
		return super.updateH(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public boolean delete(Order t) {
		return super.deleteH(String.valueOf(t.getRoleId()), t);
	}

	@Override
	public List<Order> selectAll(long roleId) {
		return super.selectAll(String.valueOf(roleId), Order.class);
	}

	@Override
	public Order selectOne(long roleId, long uid) {
		return super.selectOne(String.valueOf(roleId), uid, Order.class);
	}

	@Override
	public Order selectOneByDB(long uid) {
		return orderMapper.selectByPrimaryKey(uid);
	}

	@Override
	public List<Order> selectAllByDB(String roleId) {
		OrderExample ex = new OrderExample();
		ex.createCriteria().andRoleIdEqualTo(Long.parseLong(roleId));
		return orderMapper.selectByExample(ex);
	}

	@Override
	public int updateDB(Order t) {
		return orderMapper.updateByPrimaryKey(t);
	}

	@Override
	public int insertDB(Order t) {
		return orderMapper.insertSelective(t);
	}

	@Override
	public int deleteDB(Order t) {
		return orderMapper.deleteByPrimaryKey(t.getUid());
	}

}
