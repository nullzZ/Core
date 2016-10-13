package game.core.net.handle;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import game.core.Config;
import game.core.net.IDispatcher;
import game.core.net.action.IAction;
import game.core.net.model.AbsRole;
import game.core.net.thread.MsgProssThread;
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

	/**
	 * 登录线程 主要登录会加载db中的一些数据比较耗时
	 */
	private MsgProssThread[] loginProssThreads;

	private AtomicInteger nextLoginProssThreadIndex = new AtomicInteger(0);

	/**
	 * 
	 * @param threadSize
	 *            逻辑线程数量
	 */
	private MyDispatcher() {
		msgProssThreads = new MsgProssThread[Config.msgThreadSize];
		loginProssThreads = new MsgProssThread[Config.loginThreadSize];
		for (int i = 0; i < Config.msgThreadSize; i++) {
			msgProssThreads[i] = new MsgProssThread(Config.msgQueueSize);
			new Thread(msgProssThreads[i], "msgThread-" + i).start();
		}
		for (int i = 0; i < Config.loginThreadSize; i++) {
			loginProssThreads[i] = new MsgProssThread(Config.loginProssSize);
			new Thread(loginProssThreads[i], "loginThread-" + i).start();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Channel channel, int cmd, IAction action, Object msg) throws Exception {
		try {
			int treadIndex = getNextLoginProssThreadIndex();
			loginProssThreads[treadIndex].add(channel, cmd, action, msg);
		} catch (Exception e) {
			throw e;
		}

	}

	private int getNextLoginProssThreadIndex() {
		if (nextLoginProssThreadIndex.get() >= loginProssThreads.length) {
			nextLoginProssThreadIndex.set(0);
		}
		return nextLoginProssThreadIndex.getAndIncrement();

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
