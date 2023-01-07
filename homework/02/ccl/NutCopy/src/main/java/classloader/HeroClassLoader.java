package classloader;

import java.io.*;

public class HeroClassLoader extends ClassLoader {
    private String classPath;

    public HeroClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * Load class file
     * input: class full name to locate the class file
     * output Class object
     *
     * @param name The <a href="#binary-name">binary name</a> of the class
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = getData(name);
            if (classData != null) {
                return defineClass(name, classData, 0, classData.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getData(String className) throws IOException {
        //load bytecode data of class
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try (InputStream in = new FileInputStream(path);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[2048];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
