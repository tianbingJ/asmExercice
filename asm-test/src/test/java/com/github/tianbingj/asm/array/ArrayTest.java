package com.github.tianbingj.asm.array;

import com.github.tianbingj.asm.ClassUtils;
import com.github.tianbingj.asm.data.Exercises;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class ArrayTest {


    @Test
    public void testArray() throws Exception {
        byte[] bytes = ArrayGenerator.dump();
//        ClassUtils.toClassFile(bytes, Exercises.CHILD_ClASS_NAME + ".class");
        Class subClass = ClassUtils.loadClass(Exercises.childQualifiedClassName("Array"), bytes);
        @SuppressWarnings("unchecked")
        Object object = subClass.getDeclaredConstructor(int.class, int.class).newInstance(10, 10);
        @SuppressWarnings("unchecked")
        Method getMethod = subClass.getMethod("get", int.class, int.class);
        @SuppressWarnings("unchecked")
        Method setMethod = subClass.getMethod("set", int.class, int.class, int.class);
        setMethod.invoke(object, 6, 7, 1000);
        int result = (int)getMethod.invoke(object, 6, 7);
        Assert.assertEquals(1000, result);
    }
}
