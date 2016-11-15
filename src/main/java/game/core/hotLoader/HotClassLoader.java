package game.core.hotLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author nullzZ
 *
 */
public class HotClassLoader extends ClassLoader {
	private String classpath;

	public HotClassLoader(String classpath) {
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}

	@Override
	public Class<?> findClass(String name) {
		byte[] data = loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	public byte[] loadClassData(String name) {
		try {
			name = name.replace(".", "//");
			FileInputStream is = new FileInputStream(new File(classpath + name + ".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = is.read()) != -1) {
				baos.write(b);
			}
			is.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
