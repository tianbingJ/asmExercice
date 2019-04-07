package com.github.tianbingj.asm.ifcmp;


import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.data.Exercises;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ExerciseIfGenerator extends Exercises {

    private static final String FOR_CASE = "If";

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, childInternalClassName(FOR_CASE), null, Exercises.INTERNAL_CLASS_NAME, null);
        addJudgeMethod(classWriter);
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    /**
     * 实现:
     *     public static int judge(int args) {
     *         if (args > 0) {
     *             return 1;
     *         } else if (args == 0) {
     *             return 0;
     *         } else {
     *             return -1;
     *         }
     *     }
     * @param classWriter
     * @throws Exception
     */
    private static void addJudgeMethod(ClassWriter classWriter) throws Exception {
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "judge", "(I)I", null, null);
        methodVisitor.visitCode();
        Label zeroLabel = new Label();
        Label gtLabel = new Label();
        Label ltLabel = new Label();

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, zeroLabel);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, gtLabel);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, ltLabel);

        //return 0
        methodVisitor.visitLabel(zeroLabel);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitInsn(Opcodes.IRETURN);
        // return 1
        methodVisitor.visitLabel(gtLabel);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.IRETURN);
        // return -1
        methodVisitor.visitLabel(ltLabel);
        methodVisitor.visitInsn(Opcodes.ICONST_M1);
        methodVisitor.visitInsn(Opcodes.IRETURN);

        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }
}
