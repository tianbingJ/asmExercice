package com.github.tianbingj.asm.ifcmp;

import com.github.tianbingj.asm.ClassUtils;
import com.github.tianbingj.asm.data.Exercises;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class IfcmpTest {

    @Test
    public void test() throws Exception {
        byte[] bytes = ExerciseIfGenerator.dump();
//        ClassUtils.toClassFile(bytes, Exercises.childClassNameFor("If")+ ".class");
        Class subClass = ClassUtils.loadClass(Exercises.childQualifiedClassName("If"), bytes);

        @SuppressWarnings("unchecked")
        Method method = subClass.getMethod("judge", int.class);
        int result1 = (int)method.invoke(null, 10);
        int result2 = (int)method.invoke(null, 0);
        int result3 = (int)method.invoke(null, -10);
        Assert.assertEquals(1, result1);
        Assert.assertEquals(0, result2);
        Assert.assertEquals(-1, result3);
    }
}
