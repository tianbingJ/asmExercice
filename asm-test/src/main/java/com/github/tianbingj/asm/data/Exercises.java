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


}
