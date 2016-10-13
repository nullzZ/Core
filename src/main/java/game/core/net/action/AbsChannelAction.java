package game.core.net.action;

import javax.annotation.Resource;

import com.google.protobuf.GeneratedMessage;

import game.core.net.manager.ActionManager;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 * @param <Channel>
 * @param <M>
 */
public abstract class AbsChannelAction<C extends Channel, M extends GeneratedMessage> implements IAction<C, M> {
	@Resource
	private ActionManager actionManager;

	@Override
	public void sendMsg(C channel, GeneratedMessage msg) {
		channel.writeAndFlush(msg);
	}
}
