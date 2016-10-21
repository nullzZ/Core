package game.core.rpg.manager;

/**
 * 冷却信息类
 * 
 * @author heyang
 *
 */
public class Cooldown {

	// 冷却类型
	private CooldownType type;
	// 关键字
	private String key;
	// 开始时间
	private long start;
	// 持续时间
	private long delay;

	public CooldownType getType() {
		return type;
	}

	public void setType(CooldownType type) {
		this.type = type;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return
	 */
	public long getEndTime() {
		return start + delay;
	}

	/**
	 * 获取剩余时间
	 * 
	 * @return
	 */
	public long getRemainTime() {
		return getEndTime() - System.currentTimeMillis();
	}

	public void release() {
		this.setKey(null);
		this.setType(null);
		this.setStart(0);
		this.setDelay(0);
	}

}
