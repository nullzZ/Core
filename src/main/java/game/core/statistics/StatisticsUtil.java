package game.core.statistics;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

/**
 * 统计工具
 * 
 * @author nullzZ
 *
 */
public class StatisticsUtil {

	private static final Logger logger = Logger.getLogger(StatisticsUtil.class);
	private static final StatisticsUtil instance = new StatisticsUtil();

	private volatile boolean isOpen = false;
	private long startTime;

	/**
	 * 接收总流量
	 */
	private AtomicLong receiveSum = new AtomicLong(0L);
	/**
	 * 每秒流量
	 */
	private AtomicInteger receive = new AtomicInteger(0);

	/**
	 * 每秒最大流量
	 */
	private AtomicInteger receiveMax = new AtomicInteger(0);
	/**
	 * 发送总流量
	 */
	private AtomicLong sendSum = new AtomicLong(0L);

	private Timer timer;

	private StatisticsUtil() {

	}

	public static StatisticsUtil getInstatnce() {
		return instance;
	}

	public void start() {
		startTime = System.currentTimeMillis();
		isOpen = true;
		timer = new Timer();
		timer.schedule(new StatisticTimerTask(), 0, 1000);
		logger.info("start serverStatistics---");
	}

	public void stop() {
		isOpen = false;
		timer.cancel();
	}

	/**
	 * action统计
	 * 
	 * @param cmd
	 */
	public void STaction(int cmd, float prossTime) {
		if (!isOpen) {
			return;
		}
		StatisticsAction.getInstance().STaction(cmd, prossTime);
	}

	public void STReceiveSum(int s) {
		if (!isOpen) {
			return;
		}
		receiveSum.addAndGet(s);
		receive.addAndGet(s);
		// logger.info("[流量统计]rev:" + s + "|revSum:" + this.getReceiveSum());
	}

	public void STSendSum(int s) {
		if (!isOpen) {
			return;
		}
		sendSum.addAndGet(s);
	}

	/**
	 * 每秒执行一次
	 * 
	 * @return
	 */
	public int getReceive() {
		int r = receive.get();
		if (r > receiveMax.get()) {
			receiveMax.set(r);
		}
		receive.set(0);
		return r;
	}

	public long getReceiveAvg() {
		long t = getTime();
		if (t <= 0) {
			return 0;
		}
		return this.getReceiveSum() / t;
	}

	public long getTime() {
		return (System.currentTimeMillis() - this.startTime) / 1000;
	}

	public long getReceiveSum() {
		return receiveSum.get();
	}

	public long getSendSum() {
		return sendSum.get();
	}

	public int getReceiveMax() {
		return receiveMax.get();
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public long getStartTime() {
		return startTime;
	}

}
