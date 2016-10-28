package game.core.net.my;

import game.core.model.AbsRole;
import game.core.net.action.IAction;
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
