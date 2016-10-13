package game.core.net.manager;

import com.google.protobuf.GeneratedMessage;

import io.netty.channel.Channel;

public interface IActionManager {

	public void handle(Channel channel, int cmd, byte[] bb) throws Exception;
	
	public Integer getResponseCmd(GeneratedMessage msg);
}
