package game.core.net.model;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.Channel;

/**
 * 角色抽象类
 * 
 * @author nullzZ
 *
 */
public abstract class AbsRole {

	/**
	 * 线,用来调用对应的逻辑线程
	 */
	protected short line = 0;
	private Channel channel;

	public short getLine() {
		return line;
	}

	public void setLine(short line) {
		this.line = line;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * 发送消息
	 */
	public void sendMsg(GeneratedMessage msg) {
		channel.writeAndFlush(msg.toByteArray());
	}
}
