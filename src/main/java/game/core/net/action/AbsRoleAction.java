package game.core.net.action;

import com.google.protobuf.GeneratedMessage;

import game.core.net.model.AbsRole;

public abstract class AbsRoleAction<R extends AbsRole, M extends GeneratedMessage> implements IAction<R, M> {

	@Override
	public void sendMsg(R r, GeneratedMessage msg) {
		r.getChannel().writeAndFlush(msg);
	}
}
