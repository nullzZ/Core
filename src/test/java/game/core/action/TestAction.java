package game.core.action;

import org.springframework.stereotype.Service;

import game.core.net.action.AbsChannelAction;
import game.core.net.action.IActionAnnotation;
import game.core.protobuf.AnyProto.AnyRequest;
import io.netty.channel.Channel;

/**
 * @author nullzZ
 *
 */
@IActionAnnotation(cmd = 1)
@Service
public class TestAction extends AbsChannelAction<Channel, AnyRequest> {

	@Override
	public void execute(Channel t, AnyRequest m) {
		// TODO Auto-generated method stub

	}

}
