package com.github.tianbingj.asm.arithmetic;


import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.data.Exercises;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddGenerator {

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, Exercises.CHILD_INTERNAL_CLASS_NAME, null, Exercises.INTERNAL_CLASS_NAME, null);
        addInit(classWriter);
        doAddInt(classWriter);
        doubleAddInt(classWriter);
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    /**
     * 创建构造方法
     */
    private static void addInit(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, Exercises.INTERNAL_CLASS_NAME, "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);

        mv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        mv.visitEnd();
    }

    private static void doAddInt(ClassWriter cw) {
        MethodVisitor methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "add", "(I)I", null, null);
        methodVisitor.visitCode();
        //get intValue
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "intValue", "I");
        //add
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.IADD);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);

        //intValue = sum
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "intValue", "I");

        //get Value
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "intValue", "I");
        methodVisitor.visitInsn(Opcodes.IRETURN);

        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }

    /**
     * 添加 double add(float value) {
     *     this.doubleValue += value;
     *     return this.doubleValue;
     * }
     * @param cw
     */
    private static void doubleAddInt(ClassWriter cw) {
        MethodVisitor methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "add", "(D)D", null, null);
        methodVisitor.visitCode();

        //get double value
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "doubleValue", "D");

        //add
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 1);
        methodVisitor.visitInsn(Opcodes.DADD);
        methodVisitor.visitVarInsn(Opcodes.DSTORE, 1);

        //doubleValue = sum
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.DLOAD, 1);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "doubleValue", "D");

        //get value
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Exercises.CHILD_INTERNAL_CLASS_NAME, "doubleValue", "D");
        methodVisitor.visitInsn(Opcodes.DRETURN);

        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }
}
