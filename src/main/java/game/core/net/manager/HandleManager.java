package game.core.net.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import game.core.role.IRole;
import io.netty.channel.Channel;

/**
 * 游戏句柄
 * 
 * @author nullzZ
 *
 */
public class HandleManager {
	private static Map<Channel, IRole> roles = new ConcurrentHashMap<>();

	public static void putRole(Channel c, IRole r) {
		roles.put(c, r);
	}

	public static IRole getRole(Channel c) {
		return roles.get(c);
	}

}
