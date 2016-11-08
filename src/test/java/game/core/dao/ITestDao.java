package game.core.dao;

import java.util.List;

import game.core.db.dto.Orders;

/**
 * @author nullzZ
 *
 */
public interface ITestDao {
	public boolean insert(Orders t);

	public boolean update(Orders t);

	public boolean delete(Orders t);

	public List<Orders> selectAll(long roleId);

	public Orders selectOne(long roleId, long uid);
}
