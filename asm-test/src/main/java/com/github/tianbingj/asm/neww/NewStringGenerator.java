package com.github.tianbingj.asm.neww;

import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.Utils;
import com.github.tianbingj.asm.data.Exercises;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author tianbing
 */
public class NewStringGenerator extends Exercises {

    public static final String FOR_CASE = "New";

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, childInternalClassName(FOR_CASE), null, Exercises.INTERNAL_CLASS_NAME, null);
        Utils.addConstructor(classWriter, Exercises.INTERNAL_CLASS_NAME);
        addGenString(classWriter);
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    /**
     * 实现genString
     * @param classWriter
     */
    private static void addGenString(ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "genString", "()Ljava/lang/String;", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(String.class));
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitLdcInsn("HelloWorld");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/String", "<init>", "(Ljava/lang/String;)V", false);
        methodVisitor.visitInsn(Opcodes.ARETURN);
        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }

    public static void main(String[] args) {
        new String("HH");
    }
}
