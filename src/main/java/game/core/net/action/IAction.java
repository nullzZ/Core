package game.core.net.action;

/**
 * 
 * @author nullzZ
 *
 * @param <T>
 * @param <M>
 */
public interface IAction<T, M> {
	public void execute(T t, M m);

}
