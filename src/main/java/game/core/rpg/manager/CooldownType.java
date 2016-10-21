package game.core.rpg.manager;

/**
 * @author nullzZ
 *
 */
public enum CooldownType {

	RUN(1);

	private int value;

	private CooldownType(int v) {
		this.value = v;
	}

	public int getValue() {
		return value;
	}
}
