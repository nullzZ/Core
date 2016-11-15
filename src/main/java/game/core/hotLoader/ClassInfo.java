package game.core.hotLoader;

/**
 * @author nullzZ
 *
 */
public class ClassInfo {
	private long loadTime;
	private IScript script;

	public ClassInfo(IScript script, long loadTime) {
		this.script = script;
		this.loadTime = loadTime;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public IScript getScript() {
		return script;
	}

	public void setScript(IScript script) {
		this.script = script;
	}

}
