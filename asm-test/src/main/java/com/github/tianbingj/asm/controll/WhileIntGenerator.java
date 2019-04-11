package com.github.tianbingj.asm.controll;


import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.data.Exercises;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.github.tianbingj.asm.Utils.addConstructor;

public class WhileIntGenerator extends Exercises {

    private static final String FOR_CASE = "While";

    public byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, childInternalClassName(FOR_CASE), null, Exercises.INTERNAL_CLASS_NAME, null);
        addConstructor(classWriter, Exercises.INTERNAL_CLASS_NAME);
        addWhileInt(classWriter);
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }

    /**
     * 实现
     *  public void whileInt(int count) {
     *     this.intValue = 0;
     *     while (intValue < count) {
     *         intValue ++;
     *     }
     * }
     * @param classWriter
     */
    private void addWhileInt(ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "whileInt", "(I)V", null, null);

        //this.intValue = 0
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, childInternalClassName(FOR_CASE), "intValue", "I");

        Label loopLabel = new Label();
        Label judgeLabel = new Label();
        methodVisitor.visitJumpInsn(Opcodes.GOTO, judgeLabel);
        methodVisitor.visitLabel(loopLabel);

        //get intValue and innc1
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitInsn(Opcodes.DUP);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, childInternalClassName(FOR_CASE), "intValue", "I");
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.IADD);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, childInternalClassName(FOR_CASE), "intValue", "I");

        //judge
        methodVisitor.visitLabel(judgeLabel);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, childInternalClassName(FOR_CASE), "intValue", "I");
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPLT, loopLabel);
        methodVisitor.visitInsn(Opcodes.RETURN);

        methodVisitor.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        methodVisitor.visitEnd();
    }
}
