package game.core.net.model;

/**
 * 角色抽象类
 * 
 * @author nullzZ
 *
 */
public abstract class AbsRole {

	/**
	 * 线,用来调用对应的逻辑线程
	 */
	protected short line = 0;

	public short getLine() {
		return line;
	}

	public void setLine(short line) {
		this.line = line;
	}

	/**
	 * 发送消息
	 */
	public void sendMsg() {

	}
}
