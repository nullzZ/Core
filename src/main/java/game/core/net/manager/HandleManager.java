package game.core.net.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import game.core.model.AbsRole;
import game.core.model.Account;
import io.netty.channel.Channel;

/**
 * 游戏句柄
 * 
 * @author nullzZ
 *
 */
public class HandleManager {

	private static Map<Channel, Account> accounts = new ConcurrentHashMap<>();
	private static Map<Channel, AbsRole> roles = new ConcurrentHashMap<>();

	public static void putAccount(Channel c, Account account) {
		accounts.put(c, account);
	}

	public static Account getAccount(Channel c) {
		return accounts.get(c);
	}

	public static void putRole(Channel c, AbsRole r) {
		roles.put(c, r);
	}

	public static AbsRole getRole(Channel c) {
		return roles.get(c);
	}

}
