package com.github.tianbingj.asm;

import java.io.File;
import java.io.FileOutputStream;

public final class ClassUtils {

    private ClassUtils() {}

    private static final MyClassLoader DEFAULT_CLASS_LOADER = new MyClassLoader();

    private static class MyClassLoader extends ClassLoader {

        public Class<?> defineClass(String name, byte[] b) {
            Class c = defineClass(name, b, 0, b.length);
            System.out.println("class name:" + c);
            return c;
        }
    }

    public static Class<?> loadClass(String name, byte[] bytes) {
        return DEFAULT_CLASS_LOADER.defineClass(name, bytes);
    }


    public static void toClassFile(byte[] bytes, String fileName) throws Exception {
        File file = new File(fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
    }
}
