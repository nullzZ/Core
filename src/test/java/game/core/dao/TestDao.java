package game.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import game.core.cach.AbsDao;
import game.core.db.dao.OrderRecordMapper;
import game.core.db.dto.OrderRecord;
import game.core.db.dto.OrderRecordExample;

/**
 * @author nullzZ
 *
 */
@Service
public class TestDao extends AbsDao<OrderRecord> {

	@Resource
	private OrderRecordMapper orderRecordMapper;

	public boolean insert(OrderRecord t) {
		return super.insertHEx(String.valueOf(t.getRoleId()), t.getUid(), t);
	}

	public List<OrderRecord> selectAll(long roleId) {
		return super.selectAll(String.valueOf(roleId), OrderRecord.class);
	}

	public OrderRecord selectOne(long roleId, long uid) {
		return super.selectOne(String.valueOf(roleId), uid, OrderRecord.class);
	}

	@Override
	public OrderRecord selectOneByDB(long uid) {
		return orderRecordMapper.selectByPrimaryKey(uid);
	}

	@Override
	public List<OrderRecord> selectAllByDB(String roleId) {
		OrderRecordExample ex = new OrderRecordExample();
		ex.createCriteria().andRoleIdEqualTo(Long.parseLong(roleId));
		return orderRecordMapper.selectByExample(ex);
	}

	@Override
	public int updateDB(OrderRecord t) {
		return orderRecordMapper.updateByPrimaryKey(t);
	}

	@Override
	public int insertDB(OrderRecord t) {
		return orderRecordMapper.insertSelective(t);
	}

}
