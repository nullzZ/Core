package game.core.net.handle;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import game.core.net.IDispatcher;
import game.core.net.manager.ActionManager;

/**
 * 
 * @author nullzZ
 *
 */
@Service
public class MyDispatcher implements IDispatcher {

	private static final Logger logger = Logger.getLogger(MyDispatcher.class);
	@Resource
	private ActionManager actionManager;

	private LinkedBlockingQueue<MsgModel> queue = new LinkedBlockingQueue<>();

	private MyDispatcher() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						MsgModel msg = queue.take();
						long now = System.nanoTime();
						actionManager.handle(msg.getChannel(), msg.getCmd(), msg.getBb());
						
						StringBuilder sb = new StringBuilder();
						sb.append("[处理时间]cmd:");
						sb.append(msg.getCmd());
						sb.append("|");
						sb.append((System.nanoTime() - now) * 0.000001f);
						logger.info(sb);
					}
				} catch (Exception e) {
					logger.error("[处理异常]", e);
				}
			}
		}).start();
	}

	public void add(MsgModel bb) {
		try {
			queue.put(bb);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
