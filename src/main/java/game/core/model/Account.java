package game.core.model;

import io.netty.channel.Channel;

/**
 * 帐号
 * 
 * @author nullzZ
 *
 */
public class Account {

	private Channel channel;

	public Account(Channel channel) {
		this.channel = channel;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
