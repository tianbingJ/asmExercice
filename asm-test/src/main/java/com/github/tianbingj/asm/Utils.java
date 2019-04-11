package com.github.tianbingj.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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

    public static String internalClassName(String name) {
        return "com/github/tianbingj/asm/" + name;
    }

    public static String fullyQualifiedClassName(String name) {
        return "com.github.tianbingj.asm." + name;
    }

    static public void addConstructor(ClassWriter classWriter, String superInternalClassName) {
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superInternalClassName, "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        mv.visitEnd();
    }
}
