package Game.Core;

import org.springframework.stereotype.Service;

import Game.Core.AnyProto.AnyRequest;
import game.core.net.action.IActionAnnotation;
import game.core.net.action.IChannelAction;
import io.netty.channel.Channel;

@IActionAnnotation(cmd = 1)
@Service
public class A implements IChannelAction<Channel, AnyRequest> {

	@Override
	public void execute(Channel t, AnyRequest m) {
		// TODO Auto-generated method stub
		System.out.println("测试：" + m.getTypeUrl());
	}

}
