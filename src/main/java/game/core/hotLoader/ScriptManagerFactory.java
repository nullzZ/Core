package game.core.hotLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nullzZ
 *
 */
public class ScriptManagerFactory {
	/**
	 * 记录热加载类的加载信息
	 */
	private static final Map<String, ClassInfo> loadTimeMap = new HashMap<>();
	public static final String CLASS_PATH = "D:/";
	public static final String MY_MANAGER = "game.core.hotLorder";

	public static IScript loadScript(String className) {
		File loadFile = new File(CLASS_PATH + className.replaceAll("\\.", "/") + ".class");
		long lastModified = loadFile.lastModified();

		// 查看是否被家再过 ,如果没有被家再过则加载
		if (loadTimeMap.get(className) == null) {
			load(className, lastModified);
		} else if (loadTimeMap.get(className).getLoadTime() != lastModified) {// 如果被加载过，查看加载时间，如果该类已经被修改，则重新加载
			load(className, lastModified);
		}

		return loadTimeMap.get(className).getScript();
	}

	private static void load(String className, long lastModified) {

		HotClassLoader myLoader = new HotClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		try {
			loadClass = myLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		IScript script = newInstance(loadClass);
		ClassInfo loadInfo2 = new ClassInfo(script, lastModified);
		loadTimeMap.put(className, loadInfo2);
	}

	private static IScript newInstance(Class<?> cls) {
		try {
			return (IScript) cls.getConstructor(new Class[] {}).newInstance(new Object[] {});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
