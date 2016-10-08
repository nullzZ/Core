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
import game.core.role.IRole;
import io.netty.channel.Channel;

/**
 * 
 * @author nullzZ
 *
 */
@Service
public class ActionManager {
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

	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void init() {
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

	private String getResponseMessage(Class<?> clz) {
		String className = clz.getCanonicalName();
		int index = className.lastIndexOf(".");
		String headStr = className.substring(0, index);
		String tailStr = className.substring(index);

		return headStr.concat(tailStr.replace("Request", "Response"));
	}

	public Object getRequestMessage(int cmdId, byte[] bytes) throws Exception {
		Class<?> messageClz = requestMessageMap.get(cmdId);
		Method method = methods.get(cmdId);
		if (messageClz == null || method == null || bytes == null)
			return null;

		return method.invoke(messageClz, bytes);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void handle(Channel channel, int cmd, byte[] bb) throws Exception {
		Object msg = this.getRequestMessage(cmd, bb);

		IAction action = this.actions.get(cmd);
		if (action == null) {
			logger.error("[handle]Action==null |" + cmd);
			channel.close();
			return;
		} else {
			if (IChannelAction.class.isInstance(action)) {
				action.execute(channel, msg);
			} else if (IRoleAction.class.isInstance(action)) {
				IRole role = HandleManager.getRole(channel);
				if (role == null) {
					channel.close();
					logger.error("[handle]role is null");
				} else {
					action.execute(role, msg);
				}

			} else {

			}
		}
	}

}
