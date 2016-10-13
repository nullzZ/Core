package game.core.net.action;

import com.google.protobuf.GeneratedMessage;

/**
 * 
 * @author nullzZ
 *
 * @param <T>
 * @param <M>
 */
public interface IAction<T, M> {

	public void execute(T t, M m);

	public void sendMsg(T t, GeneratedMessage m);
}
