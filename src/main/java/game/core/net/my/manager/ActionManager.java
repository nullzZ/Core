package game.core.net.my.manager;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import game.core.model.AbsRole;
import game.core.net.action.AbsChannelAction;
import game.core.net.action.AbsRoleAction;
import game.core.net.action.IAction;
import game.core.net.manager.AbsActionManager;
import game.core.net.manager.HandleManager;
import game.core.net.my.MyDispatcher;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 */
@Service
public class ActionManager extends AbsActionManager {
	private static final Logger logger = Logger.getLogger(ActionManager.class);
	@Resource
	private MyDispatcher myDispatcher;

	/**
	 * 消息处理
	 * 
	 * @param channel
	 * @param cmd
	 *            消息指令
	 * @param bb
	 *            消息体
	 * @throws Exception
	 */
	@Override
	public void handle(Channel channel, int cmd, byte[] bb) throws Exception {
		if (!this.isHasAction(cmd)) {
			logger.error("[没有指定action]:" + cmd);
			channel.close();
			return;
		}

		Object msg = this.getRequestMessage(cmd, bb);

		@SuppressWarnings("rawtypes")
		IAction action = this.actions.get(cmd);
		if (AbsChannelAction.class.isInstance(action)) {
			myDispatcher.execute(channel, cmd, action, msg);
		} else if (AbsRoleAction.class.isInstance(action)) {
			AbsRole role = HandleManager.getRole(channel);
			if (role == null) {
				channel.close();
				logger.error("[handle]role is null");
			} else {
				myDispatcher.execute(role, cmd, action, msg);
			}

		} else {

		}
	}
}
