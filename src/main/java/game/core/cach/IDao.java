package game.core.cach;

import java.util.List;

/**
 * @author nullzZ
 *
 */
public interface IDao<T> {

//	public boolean insertH(long roleId, long uid, T t);

	public boolean insertHEx(long roleId, long uid, T t);

	public boolean updateH(long roleId, long uid, T t);

	public boolean deleteH(long roleId, long uid);

	public T selectOne(long roleId, long uid, Class<T> clazz);

	public List<T> selectAll(long roleId, Class<T> clazz);

	public T selectOneByDB(long uid);

	public List<T> selectAllByDB(long roleId);

}
