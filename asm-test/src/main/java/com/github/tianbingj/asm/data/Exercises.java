package com.github.tianbingj.asm.data;

import com.github.tianbingj.asm.Constants;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Exercises {

    public static String ClASS_NAME = "Exercise";

    public static String CHILD_ClASS_NAME = "ExerciseChild";

    public static String INTERNAL_CLASS_NAME = "com/github/tianbingj/asm/data/Exercise";

    public static String CHILD_INTERNAL_CLASS_NAME = "com/github/tianbingj/asm/data/ExerciseChild";

    public static String CHILD_QUALIFIED_CLASS_NAME = "com.github.tianbingj.asm.data.ExerciseChild";

    /**
     * 由于不能重复load同一个class， 所以构造Exercise子类的时候根据场景创建不同的子类定义
     * @param forCase
     * @return
     */
    public static String childClassNameFor(String forCase) {
        return  CHILD_ClASS_NAME + forCase;
    }

    /**
     * 同上
     * @param forCase
     * @return
     */
    public static String childInternalClassName(String forCase) {
        return  CHILD_INTERNAL_CLASS_NAME + forCase;
    }

    /**
     * 同上
     * @param forCase
     * @return
     */
    public static String childQualifiedClassName(String forCase) {
        return CHILD_QUALIFIED_CLASS_NAME + forCase;
    }

    static protected void addConstructor(ClassWriter classWriter) {
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, INTERNAL_CLASS_NAME, "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        mv.visitEnd();
    }
}
