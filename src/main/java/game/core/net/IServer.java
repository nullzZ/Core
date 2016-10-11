package game.core.net;

/**
 * 
 * @author nullzZ
 *
 */
public interface IServer {

	/**
	 * 
	 * @param isLog
	 *            是否开启日志
	 * @param isStatistic
	 *            是否开启统计
	 */
	public void start(final boolean isLog, boolean isStatistic);

	public void stop();
}
