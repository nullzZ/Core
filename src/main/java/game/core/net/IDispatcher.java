package game.core.net;

import game.core.net.action.IAction;
import game.core.net.model.AbsRole;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 */
public interface IDispatcher {
	@SuppressWarnings("rawtypes")
	public void execute(Channel channel, int cmd, IAction action, Object msg) throws Exception;

	@SuppressWarnings("rawtypes")
	public void execute(AbsRole role, int cmd, IAction action, Object msg) throws Exception;
}
