package game.core.net.handle;

import org.springframework.stereotype.Service;

import game.core.Config;
import game.core.net.IDispatcher;
import game.core.net.action.IAction;
import game.core.net.thread.MsgProssThread;
import game.core.role.AbsRole;
import io.netty.channel.Channel;

/**
 * 分配器
 * 
 * @author nullzZ
 *
 */
@Service
public class MyDispatcher implements IDispatcher {

	// private static final Logger logger =
	// Logger.getLogger(MyDispatcher.class);

	/**
	 * role角色逻辑处理线程
	 */
	private MsgProssThread[] msgProssThreads;

	private MsgProssThread[] loginProssThreads;

	/**
	 * 
	 * @param threadSize
	 *            逻辑线程数量
	 */
	private MyDispatcher() {
		msgProssThreads = new MsgProssThread[Config.msgThreadSize];
		loginProssThreads = new MsgProssThread[Config.loginThreadSize];
		for (int i = 0; i < Config.msgThreadSize; i++) {
			msgProssThreads[i] = new MsgProssThread();
			new Thread(msgProssThreads[i], "msgThread-" + i).start();

			loginProssThreads[i] = new MsgProssThread();
			new Thread(loginProssThreads[i], "loginThread-" + i).start();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Channel channel, int cmd, IAction action, Object msg) throws Exception {
		try {
			int treadIndex = 0;
			msgProssThreads[treadIndex].add(channel, cmd, action, msg);
		} catch (Exception e) {
			throw e;
		}

	}

	@SuppressWarnings("rawtypes")
	public void execute(AbsRole role, int cmd, IAction action, Object msg) throws Exception {
		try {
			int treadIndex = role.getLine();
			msgProssThreads[treadIndex].add(role, cmd, action, msg);
		} catch (Exception e) {
			throw e;
		}

	}

}
