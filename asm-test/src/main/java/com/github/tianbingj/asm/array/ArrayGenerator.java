package com.github.tianbingj.asm.array;


import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.data.Exercises;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 *     创建一个二维数组，实现get set方法
 *     private int[][] intMatrix;
 *
 *     private void set(int i, int j , int value) {
 *         this.intMatrix[i][j] = value;
 *     }
 *
 *     private int get(int i, int j) {
 *         return this.intMatrix[i][j;
 *     }
 */
public class ArrayGenerator extends Exercises {

    public static final String FOR_CASE = "Array";

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, childInternalClassName(FOR_CASE), null, INTERNAL_CLASS_NAME, null);

        // int[][] intMatrix;
        FieldVisitor fv = classWriter.visitField(Opcodes.ACC_PRIVATE, "intMatrix", "[[I", null, null);
        fv.visitEnd();

        //constructor
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(II)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, INTERNAL_CLASS_NAME, "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMultiANewArrayInsn("[[I", 2);
        mv.visitFieldInsn(Opcodes.PUTFIELD, childInternalClassName(FOR_CASE), "intMatrix", "[[I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        mv.visitEnd();

        //set
        MethodVisitor setMv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "set", "(III)V", null, null);
        setMv.visitCode();
        setMv.visitVarInsn(Opcodes.ALOAD, 0);
        setMv.visitFieldInsn(Opcodes.GETFIELD, childInternalClassName(FOR_CASE), "intMatrix", "[[I");
        setMv.visitVarInsn(Opcodes.ILOAD, 1);
        setMv.visitInsn(Opcodes.AALOAD);
        setMv.visitVarInsn(Opcodes.ILOAD, 2);
        setMv.visitVarInsn(Opcodes.ILOAD, 3);
        setMv.visitInsn(Opcodes.IASTORE);
        setMv.visitInsn(Opcodes.RETURN);
        setMv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        setMv.visitEnd();

        //get
        MethodVisitor getMv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "get", "(II)I", null, null);
        getMv.visitCode();
        getMv.visitVarInsn(Opcodes.ALOAD, 0);
        getMv.visitFieldInsn(Opcodes.GETFIELD, childInternalClassName(FOR_CASE), "intMatrix", "[[I");
        getMv.visitVarInsn(Opcodes.ILOAD, 1);
        getMv.visitInsn(Opcodes.AALOAD);
        getMv.visitVarInsn(Opcodes.ILOAD, 2);
        getMv.visitInsn(Opcodes.IALOAD);
        getMv.visitInsn(Opcodes.IRETURN);
        getMv.visitMaxs(Constants.AUTO_COMPUTE_SIZE, Constants.AUTO_COMPUTE_SIZE);
        getMv.visitEnd();

        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}
