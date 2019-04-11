package com.github.tianbingj.asm.exception;


import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.Utils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * @author tianbing
 */
public class ExceptionTestGenerator {

    public static final String CLASS_NAME = "ExceptionTest";

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, CLASS_NAME, Utils.internalClassName(CLASS_NAME), "java/lang/Object", null);

        Utils.addConstructor(classWriter, "java/lang/Object");

        generateThrow(classWriter);

        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    private static void generateThrow(ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "throwException", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(Opcodes.NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn("HelloWorld");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false);
        methodVisitor.visitInsn(Opcodes.ATHROW);
        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }
}
