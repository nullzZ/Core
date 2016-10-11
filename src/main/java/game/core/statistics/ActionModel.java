package game.core.statistics;

public class ActionModel {

	private int cmd;
	/**
	 * 调用总次数
	 */
	private int countSum;
	/**
	 * 每秒执行次数
	 */
	private int currCount;
	/**
	 * 处理最高时间(ms)
	 */
	private float prossMaxTime;

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getCountSum() {
		return countSum;
	}

	public void setCountSum(int countSum) {
		this.countSum = countSum;
	}

	public int getCurrCount() {
		return currCount;
	}

	public void setCurrCount(int currCount) {
		this.currCount = currCount;
	}

	public float getProssMaxTime() {
		return prossMaxTime;
	}

	public void setProssMaxTime(float prossMaxTime) {
		this.prossMaxTime = prossMaxTime;
	}

}
