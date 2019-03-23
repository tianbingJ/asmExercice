package com.github.tianbingj.asm;

/**
 * Created by tianbing on 2019/3/23.
 */
public class Utils {

    public static final MyClassLoader CLASS_LOADER = new MyClassLoader();

    public static Class getClassFromBytes(String name, byte[] bytes) {
        return CLASS_LOADER.defineClass(name, bytes);
    }

    static class MyClassLoader extends ClassLoader {

        public Class defineClass(String name, byte[] bytes) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
