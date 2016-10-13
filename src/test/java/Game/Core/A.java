package Game.Core;

import org.springframework.stereotype.Service;

import Game.Core.protobuf.AnyProto.AnyRequest;
import Game.Core.protobuf.AnyProto.AnyResponse;
import game.core.net.action.IActionAnnotation;
import game.core.net.action.AbsChannelAction;
import io.netty.channel.Channel;

@IActionAnnotation(cmd = 1)
@Service
public class A extends AbsChannelAction<Channel, AnyRequest> {

	@Override
	public void execute(Channel t, AnyRequest m) {
		// TODO Auto-generated method stub
		System.out.println("测试：" + m.getTypeUrl());
		AnyResponse.Builder msg = AnyResponse.newBuilder();
		msg.setState(1);
		this.sendMsg(t, msg.build());
	}

}
