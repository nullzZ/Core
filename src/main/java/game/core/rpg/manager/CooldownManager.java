package game.core.rpg.manager;

import game.core.rpg.role.Person;

/**
 * @author nullzZ
 *
 */
public class CooldownManager {

	private static final CooldownManager instance = new CooldownManager();

	private CooldownManager() {

	}

	public static CooldownManager getInstance() {
		return instance;
	}

	public void addCooldown(Person role, CooldownType cooldownType) {
		Cooldown cooldown = new Cooldown();
		cooldown.setType(cooldownType);
		cooldown.setStart(System.currentTimeMillis());
		role.getCooldowns().put(cooldownType.getValue(), cooldown);
	}

	/**
	 * 是否在冷却中
	 * 
	 * @param role
	 * @param cooldownType
	 * @return
	 */
	public boolean isCooldowning(Person role, CooldownType cooldownType) {
		Cooldown cooldown = role.getCooldowns().get(cooldownType.getValue());
		if (cooldown == null) {
			return false;
		}
		if (cooldown.getRemainTime() <= 0) {
			remove(role, cooldownType);
			return false;
		} else {
			return true;
		}
	}

	public void remove(Person role, CooldownType cooldownType) {
		role.getCooldowns().get(cooldownType.getValue());
	}
}
