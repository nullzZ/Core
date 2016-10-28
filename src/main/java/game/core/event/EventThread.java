package game.core.event;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/**
 * @author nullzZ
 *
 */
public abstract class EventThread implements Runnable {

	private static final Logger logger = Logger.getLogger(EventThread.class);
	private LinkedBlockingQueue<IEvent> events = new LinkedBlockingQueue<>();
	public volatile boolean isRun = true;

	@Override
	public void run() {
		while (isRun) {
			try {
				IEvent event = events.take();
				event.event();
			} catch (InterruptedException e) {
				logger.error("异常", e);
			}
		}
	}

	public void addEvent(IEvent event) {
		try {
			events.put(event);
		} catch (InterruptedException e) {
			logger.error("异常", e);
		}
	}

}
