package game.core.statistics;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class StatisticTimerTask extends TimerTask {
	private static final Logger logger = Logger.getLogger("STRecordLogger");

	@Override
	public void run() {
		if (StatisticsUtil.getInstatnce().isOpen()) {
			this.privateRev();
			this.printAction();
			this.privateSend();

		}
	}

	private void privateRev() {
		StringBuilder sb = new StringBuilder();
		sb.append("[流量统计]");
		sb.append("[统计时间:");
		sb.append(StatisticsUtil.getInstatnce().getTime());
		sb.append("s]");
		sb.append("[每秒流量]rev:");
		sb.append(StatisticsUtil.getInstatnce().getReceive());
		sb.append("b");
		sb.append("|revSum:");
		sb.append(StatisticsUtil.getInstatnce().getReceiveSum());
		sb.append("b");
		sb.append("|revMax:");
		sb.append(StatisticsUtil.getInstatnce().getReceiveMax());
		sb.append("b");
		sb.append("|revAVG:");
		sb.append(StatisticsUtil.getInstatnce().getReceiveAvg());
		sb.append("b");
		logger.info(sb);
	}

	private void privateSend() {
		StringBuilder sb = new StringBuilder();
		sb.append("[流量统计]");
		sb.append("[统计时间:");
		sb.append(StatisticsUtil.getInstatnce().getTime());
		sb.append("s]");
		sb.append("[每秒输出流量]send:");
		sb.append(StatisticsUtil.getInstatnce().getSend());
		sb.append("b");
		sb.append("|sendSum:");
		sb.append(StatisticsUtil.getInstatnce().getSendSum());
		sb.append("b");
		sb.append("|sendMax:");
		sb.append(StatisticsUtil.getInstatnce().getSendMax());
		sb.append("b");
		sb.append("|sendAVG:");
		sb.append(StatisticsUtil.getInstatnce().getSendAvg());
		sb.append("b");
		logger.info(sb);
	}

	private void printAction() {
		if (StatisticsAction.getInstance().getActionMap().size() <= 0) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[流量统计][action执行]");
		for (ActionModel c : StatisticsAction.getInstance().getActionMap().values()) {
			sb.append("cmd:" + c.getCmd());
			sb.append("|count:");
			sb.append(c.getCurrCount());
			sb.append("|countSum:");
			sb.append(c.getCountSum());
			sb.append("|prossMaxTime:");
			sb.append(c.getProssMaxTime());
			sb.append("ms");
			sb.append("\n");
		}
		logger.info(sb);
	}

}
