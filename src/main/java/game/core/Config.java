package game.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class Config {

	private static final Logger logger = Logger.getLogger(Config.class);
	public static int msgThreadSize = 1;
	public static int msgQueueSize = 10000;

	public static int loginThreadSize = Runtime.getRuntime().availableProcessors() * 2;
	public static int loginProssSize = 10000;

	public static void init() {
		try {
			PropertiesConfiguration pc = new PropertiesConfiguration("core_config.properties");
			msgThreadSize = pc.getInt("msgThreadSize");
			msgQueueSize = pc.getInt("msgQueueSize");
			loginThreadSize = pc.getInt("loginThreadSize");
			loginProssSize = pc.getInt("loginProssSize");

			logger.info("[加载配置]msgThreadSize:" + msgThreadSize + "|msgQueueSize:" + msgQueueSize + "|loginThreadSize:"
					+ loginThreadSize + "|loginProssSize:" + loginProssSize);
		} catch (ConfigurationException e) {
			logger.error("[加载配置异常]默认配置:" + msgThreadSize + "|msgQueueSize:" + msgQueueSize + "|loginThreadSize:"
					+ loginThreadSize + "|loginProssSize:" + loginProssSize);
		}

	}
}
