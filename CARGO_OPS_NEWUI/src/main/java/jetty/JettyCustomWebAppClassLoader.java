package jetty;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyCustomWebAppClassLoader extends org.mortbay.jetty.webapp.WebAppClassLoader {

	public JettyCustomWebAppClassLoader(WebAppContext context, String[] srcDirs, String destDir) throws IOException {
		super(null, context);
		if (srcDirs != null && destDir != null) {
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					}
					return file.getName().endsWith(".class");
				}
			};
			File dDir = new File(destDir);
			System.out.println("DestDir = " + dDir.getCanonicalPath());
			File sDir = null;
			for (int i = 0; i < srcDirs.length; i++) {
				sDir = new File(srcDirs[i]);
				System.out.println("SrcDir = " + sDir.getCanonicalPath());
				FileUtils.copyDirectory(sDir, dDir, fileFilter);
			}
		}
		System.out.println("JettyCustomWebAppClassLoader");
	}

	public JettyCustomWebAppClassLoader(ClassLoader parent, WebAppContext context) throws IOException {
		super(parent, context);
		System.out.println("JettyCustomWebAppClassLoader");
	}
}
