package game.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import game.core.cach.AbsDao;
import game.core.db.dao.RoleMapper;
import game.core.db.dto.Role;

/**
 * @author nullzZ
 *
 */
@Service
public class RoleDao extends AbsDao<Role> {
	@Resource
	private RoleMapper roleMapper;

	public boolean insert(Role t) {
		return super.insertHEx(String.valueOf(t.getUid()), t);
	}

	@Override
	public Role selectOneByDB(long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> selectAllByDB(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDB(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDB(Role t) {
		return roleMapper.insert(t);
	}

	@Override
	public int deleteDB(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
