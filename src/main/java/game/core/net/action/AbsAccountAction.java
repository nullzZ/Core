package game.core.net.action;

import com.google.protobuf.GeneratedMessage;

import game.core.net.model.Account;

public abstract class AbsAccountAction<A extends Account, M extends GeneratedMessage> implements IAction<A, M> {

	@Override
	public void sendMsg(A a, GeneratedMessage msg) {
		a.getChannel().writeAndFlush(msg);
	}
}
