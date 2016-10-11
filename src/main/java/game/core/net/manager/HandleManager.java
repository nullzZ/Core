package game.core.net.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import game.core.role.AbsRole;
import io.netty.channel.Channel;

/**
 * 游戏句柄
 * 
 * @author nullzZ
 *
 */
public class HandleManager {

	private static Map<Channel, AbsRole> roles = new ConcurrentHashMap<>();

	public static void putRole(Channel c, AbsRole r) {
		roles.put(c, r);
	}

	public static AbsRole getRole(Channel c) {
		return roles.get(c);
	}

}
