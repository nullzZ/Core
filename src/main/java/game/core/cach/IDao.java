package game.core.cach;

import java.util.List;

/**
 * @author nullzZ
 *
 *         实现接口,命名格式：XXDao
 */
public interface IDao<T> {

	// public boolean insertH(long roleId, long uid, T t);

	public boolean insertHEx(String primaryKey, long uid, T t);

	public boolean updateH(String primaryKey, long uid, T t);

	public boolean deleteH(String primaryKey, long uid);

	public T selectOne(String primaryKey, long uid, Class<T> clazz);

	public List<T> selectAll(String primaryKey, Class<T> clazz);

	public T selectOneByDB(long uid);

	public List<T> selectAllByDB(String primaryKey);

	public int updateDB(T t);

	public int insertDB(T t);
}
