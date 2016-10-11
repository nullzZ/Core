package game.core.net.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import game.core.net.action.IAction;
import game.core.net.model.MsgModel;
import game.core.statistics.StatisticsUtil;

public class MsgProssThread implements Runnable {

	private static final Logger logger = Logger.getLogger(MsgProssThread.class);
	private LinkedBlockingQueue<MsgModel> queue = new LinkedBlockingQueue<>();

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			while (true) {
				MsgModel msg = queue.take();
				long now = System.nanoTime();

				msg.getAction().execute(msg.getU(), msg.getMsg());

				StringBuilder sb = new StringBuilder();
				sb.append("[处理时间]cmd:");
				sb.append(msg.getCmd());
				sb.append("|");
				sb.append((System.nanoTime() - now) * 0.000001f);
				sb.append("ms");
				logger.info(sb);

				StatisticsUtil.getInstatnce().STaction(msg.getCmd(), (System.nanoTime() - now) * 0.000001f);
			}
		} catch (Exception e) {
			logger.error("[处理异常]", e);
		}
	}

	public void add(Object u, int cmd, @SuppressWarnings("rawtypes") IAction action, Object msg) {
		try {
			queue.put(new MsgModel(u, cmd, action, msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
