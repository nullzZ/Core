package game.core.cach;

import java.util.List;

/**
 * @author nullzZ
 *
 */
public interface IDao<T> {
	public void insert(String key, T t);

	public List<T> selectAll(String key, Class<T> clazz);
}
