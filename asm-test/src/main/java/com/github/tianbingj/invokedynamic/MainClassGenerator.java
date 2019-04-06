package com.github.tianbingj.invokedynamic;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;


public class MainClassGenerator {

    /**
     * generate :
     * class Main {
     * <p>
     * public static void main(String[] args) {
     * System.out.println(AsmInvokeDynamicTest.plus(24, 42));
     * }
     * }
     *
     * @return
     */

    public static byte[] generate() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, "com/github/tianbingj/invokedynamic/Main", null, "java/lang/Object", null);
        cw.visitSource("Main.java", null);
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "sum", "I", null, null);

        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        //main 方法
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        MethodType mt = MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class);
        Handle bootstrap = new Handle(Opcodes.H_INVOKESTATIC, "com/github/tianbingj/invokedynamic/AsmInvokeDynamicTest", "bootstrap", mt.toMethodDescriptorString(), false);
        mv.visitIntInsn(Opcodes.BIPUSH, 42);
        mv.visitIntInsn(Opcodes.BIPUSH, 24);
        mv.visitInvokeDynamicInsn("plus", "(II)I", bootstrap);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 1);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }
}
