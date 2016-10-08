package game.core.net.handle;

import io.netty.channel.Channel;

public class MsgModel {
	private Channel channel;
	private int cmd;
	private byte[] bb;

	public MsgModel(Channel channel, int cmd, byte[] bb) {
		this.channel = channel;
		this.cmd = cmd;
		this.bb = bb;
	}

	public Channel getChannel() {
		return channel;
	}

	public int getCmd() {
		return cmd;
	}

	public byte[] getBb() {
		return bb;
	}

}
