package game.core.hotLoader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;

/**
 * @author nullzZ
 *
 */
public class ScriptManager {
	private static final Logger logger = Logger.getLogger(ScriptManager.class);
	private URLClassLoader parentClassLoader;
	private String classpath;
	private static ConcurrentHashMap<Integer, IScript> scripts = new ConcurrentHashMap<Integer, IScript>();

	private static ScriptManager instance = new ScriptManager();

	private ScriptManager() {

	}

	public static ScriptManager getInstance() {
		return instance;
	}

	public void load() {
		this.parentClassLoader = (URLClassLoader) this.getClass().getClassLoader();
		this.buildClassPath();
	}

	private void buildClassPath() {
		this.classpath = null;
		StringBuilder sb = new StringBuilder();
		for (URL url : this.parentClassLoader.getURLs()) {
			String p = url.getFile();
			sb.append(p).append(File.pathSeparator);
		}
		this.classpath = sb.toString();
		logger.info("脚本加载需要类路径：" + this.classpath);
	}

	private Class<?> javaCodeToObject(String name, String code)
			throws IllegalAccessException, IOException, ClassNotFoundException {
		boolean reload = false;
		try {
			Class<?> c = Class.forName(name);
			if (c != null) {
				reload = true;
			}
		} catch (Exception e) {
		}

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

		ScriptClassLoader loader = new ScriptClassLoader();

		JavaFileObject jfile = new JavaSourceFromString(name, code);
		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
		jfiles.add(jfile);
		List<String> options = new ArrayList<String>();

		options.add("-encoding");
		options.add("UTF-8");
		options.add("-classpath");
		options.add(this.classpath);
		options.add("-d");
		options.add("bin");

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, jfiles);

		boolean success = task.call();

		fileManager.close();

		if (success) {
			if (reload) {
				return loader.loadScriptClass(name);
			} else {
				return Class.forName(name);
			}

		} else {
			String error = "";
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				// error = error + compilePrint(diagnostic);
			}
			logger.error(error);
		}
		return null;
	}

	private class JavaSourceFromString extends SimpleJavaFileObject {

		private String code;

		public JavaSourceFromString(String name, String code) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}

	}

}
