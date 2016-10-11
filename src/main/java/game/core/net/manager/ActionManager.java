package game.core.net.manager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageLite;

import game.core.net.action.IAction;
import game.core.net.action.IActionAnnotation;
import game.core.net.action.IChannelAction;
import game.core.net.action.IRoleAction;
import game.core.net.handle.MyDispatcher;
import game.core.net.model.AbsRole;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 */
@Service
public class ActionManager implements IActionManager {
	private static final Logger logger = Logger.getLogger(ActionManager.class);
	@SuppressWarnings("rawtypes")
	@Resource
	private List<IAction> allActions;
	@SuppressWarnings("rawtypes")
	private Map<Integer, IAction> actions = new HashMap<>();
	private Map<Integer, Method> methods = new HashMap<>();
	private Map<Integer, Class<?>> requestMessageMap = new HashMap<>();
	/** key：message类名，value ：对应的消息id **/
	private Map<String, Integer> responseMessageMap = new HashMap<>();
	@Resource
	private MyDispatcher myDispatcher;

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
	public void handle(Channel channel, int cmd, byte[] bb) throws Exception {
		if (!this.isHasAction(cmd)) {
			logger.error("[没有指定action]:" + cmd);
			channel.close();
			return;
		}

		Object msg = this.getRequestMessage(cmd, bb);

		@SuppressWarnings("rawtypes")
		IAction action = this.actions.get(cmd);
		if (IChannelAction.class.isInstance(action)) {
			myDispatcher.execute(channel, cmd, action, msg);
		} else if (IRoleAction.class.isInstance(action)) {
			AbsRole role = HandleManager.getRole(channel);
			if (role == null) {
				channel.close();
				logger.error("[handle]role is null");
			} else {
				myDispatcher.execute(role, cmd, action, msg);
			}

		} else {

		}

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

	private boolean isHasAction(int cmd) {
		return this.actions.containsKey(cmd);
	}

	private String getResponseMessage(Class<?> clz) {
		String className = clz.getCanonicalName();
		int index = className.lastIndexOf(".");
		String headStr = className.substring(0, index);
		String tailStr = className.substring(index);

		return headStr.concat(tailStr.replace("Request", "Response"));
	}

	private Object getRequestMessage(int cmdId, byte[] bytes) throws Exception {
		Class<?> messageClz = requestMessageMap.get(cmdId);
		Method method = methods.get(cmdId);
		if (messageClz == null || method == null || bytes == null)
			return null;

		return method.invoke(messageClz, bytes);
	}

}
