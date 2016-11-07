package game.core.dao;

import java.util.List;

import game.core.db.dto.Order;

/**
 * @author nullzZ
 *
 */
public interface ITestDao {
	public boolean insert(Order t);

	public boolean update(Order t);

	public boolean delete(Order t);

	public List<Order> selectAll(long roleId);

	public Order selectOne(long roleId, long uid);
}
