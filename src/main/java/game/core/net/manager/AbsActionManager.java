package game.core.net.manager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageLite;

import game.core.net.action.IAction;
import game.core.net.action.IActionAnnotation;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 */
public abstract class AbsActionManager implements IActionManager {
	private static final Logger logger = Logger.getLogger(AbsActionManager.class);
	@SuppressWarnings("rawtypes")
	@Resource
	private List<IAction> allActions;
	@SuppressWarnings("rawtypes")
	protected Map<Integer, IAction> actions = new HashMap<>();
	protected Map<Integer, Method> methods = new HashMap<>();
	protected Map<Integer, Class<?>> requestMessageMap = new HashMap<>();
	/** key：message类名，value ：对应的消息id **/
	protected Map<String, Integer> responseMessageMap = new HashMap<>();

	@SuppressWarnings("rawtypes")
	@PostConstruct
	private void init() {
		for (IAction ac : allActions) {
			try {
				int cmd = checkAnnotation(ac);
				checkAction(cmd, ac);
			} catch (Exception e) {
				System.exit(0);
			}
		}
	}

	/**
	 * 消息处理
	 * 
	 * @param channel
	 * @param cmd
	 *            消息指令
	 * @param bb
	 *            消息体
	 * @throws Exception
	 */
	@Override
	public abstract void handle(Channel channel, int cmd, byte[] bb) throws Exception;

	@Override
	public Integer getResponseCmd(GeneratedMessage msg) {
		String s = msg.getClass().getCanonicalName();
		Integer cmd = this.responseMessageMap.get(s);
		return cmd;
	}

	/**
	 * 检查action注解
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private int checkAnnotation(IAction ac) throws Exception {
		if (ac.getClass().isAnnotationPresent(IActionAnnotation.class)) {
			IActionAnnotation iaa = ac.getClass().getAnnotation(IActionAnnotation.class);
			actions.put(iaa.cmd(), ac);
			logger.info("[加载action注解]cmd:" + iaa.cmd() + "--" + ac.getClass().getName());
			return iaa.cmd();
		} else {
			logger.error("[加载action注解]" + ac.getClass().getName() + "|注解不存在");
			throw new Exception("actionAnnotation is null");
		}
	}

	/**
	 * 加载action
	 * 
	 * @param ac
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	private void checkAction(int cmd, IAction ac) throws NoSuchMethodException, SecurityException {
		Method[] method = ac.getClass().getDeclaredMethods();
		for (Method m : method) {
			if (m.isSynthetic())
				continue;
			if ("execute".equals(m.getName())) {
				Class<?>[] param = m.getParameterTypes();
				if (param.length == 2) {
					Class<?> paraType = param[1];
					if (GeneratedMessage.class.isAssignableFrom(paraType)
							|| GeneratedMessageLite.class.isAssignableFrom(paraType)) {
						if (!requestMessageMap.containsKey(cmd)) {
							requestMessageMap.put(cmd, paraType);
							Method method2 = paraType.getMethod("parseFrom", byte[].class);
							methods.put(cmd, method2);

							String responseMessageName = getResponseMessage(paraType);
							responseMessageMap.put(responseMessageName, cmd);
						} else {
							Class<?> oldClazzName = requestMessageMap.get(cmd);
							logger.error(
									"MessageId重复：" + cmd + ",oldMessage:" + oldClazzName + ",newMessage:" + paraType);
						}
					}
				}
			}
		}

	}

	protected boolean isHasAction(int cmd) {
		return this.actions.containsKey(cmd);
	}

	private String getResponseMessage(Class<?> clz) {
		String className = clz.getCanonicalName();
		int index = className.lastIndexOf(".");
		String headStr = className.substring(0, index);
		String tailStr = className.substring(index);

		return headStr.concat(tailStr.replace("Request", "Response"));
	}

	protected Object getRequestMessage(int cmdId, byte[] bytes) throws Exception {
		Class<?> messageClz = requestMessageMap.get(cmdId);
		Method method = methods.get(cmdId);
		if (messageClz == null || method == null || bytes == null)
			return null;

		return method.invoke(messageClz, bytes);
	}

}
