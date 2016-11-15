package game.core.hotLorder;

import org.junit.Test;

import game.core.hotLoader.ScriptManager;

/**
 * @author nullzZ
 *
 */
public class HotLoader {

	@Test
	public void test() {
		// ScriptManagerFactory.loadScript("game.core.hotLorder.HotTest");
		ScriptManager.getInstance().load();
	}
}
