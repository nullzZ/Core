package game.core.net.action;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 * @param <Channel>
 * @param <M>
 */
public abstract class AbsChannelAction<C extends Channel, M extends GeneratedMessage> implements IAction<C, M> {

	@Override
	public void sendMsg(C channel, GeneratedMessage msg) {
		channel.writeAndFlush(msg);
	}
}
