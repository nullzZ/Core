package game.core.cach;

import java.util.List;

/**
 * @author nullzZ
 *
 *         实现接口,命名格式：XXDao
 */
public interface IDao<T> {

	public T selectOneByDB(String primaryKey);

	public List<T> selectAllByDB(String primaryKey);

	public int updateDB(T t);

	public int insertDB(T t);

	public int deleteDB(T t);
}
