package com.github.tianbingj.asm.timer;

import jdk.internal.org.objectweb.asm.*;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by tianbing on 2019/3/23.
 * <p>
 * 统计一个类花费的总时间
 * 给类添加一个静态的成员long timer:
 * 每个方法调用前执行timer -= System.currentTimeMillis();
 * 每个方法调用后执行timer += System.currentTimeMillis();
 */
public class AddTimerAdapter extends ClassVisitor {

    private String ownerInternalName;

    private boolean isInterface;

    public AddTimerAdapter(int i) {
        super(i);
    }

    public AddTimerAdapter(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        ownerInternalName = name;
        isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
    }

    @Override
    public final MethodVisitor visitMethod(int access, java.lang.String name, java.lang.String descriptor,
                                           java.lang.String signature, java.lang.String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (!isInterface && cv != null && !name.equals("<init>")) {
            mv = new AddTimeMethodAdapter(mv);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer", "J", null, 0L);
            if (fv != null) {
                //为什么调用visit end ？
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }


    class AddTimeMethodAdapter extends MethodVisitor {

        public AddTimeMethodAdapter(int i) {
            super(i);
        }

        public AddTimeMethodAdapter(MethodVisitor mv) {
            super(ASM4, mv);
        }

        /**
         * 每个方法调用前实现timer -= System.currentTimeMillis();
         */
        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitFieldInsn(Opcodes.GETSTATIC, ownerInternalName, "timer", "J");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", isInterface);
            mv.visitInsn(Opcodes.LSUB);
            mv.visitFieldInsn(Opcodes.PUTSTATIC, ownerInternalName, "timer", "J");
        }

        /**
         * 在任何return、athrow语句之前实现timer += System.currentTimeMillis();
         * <p>
         * 为什么不能用visitEnd？
         */
        @Override
        public void visitInsn(int opcode) {
            if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN || opcode == Opcodes.ATHROW) {
                mv.visitFieldInsn(Opcodes.GETSTATIC, ownerInternalName, "timer", "J");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                mv.visitInsn(Opcodes.LADD);
                mv.visitFieldInsn(Opcodes.PUTSTATIC, ownerInternalName, "timer", "J");
            }
            mv.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStacks, int maxLocals) {
            mv.visitMaxs(maxStacks + 2, maxLocals);
        }
    }

}
