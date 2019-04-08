package com.github.tianbingj.asm.neww;

import com.github.tianbingj.asm.ClassUtils;
import com.github.tianbingj.asm.data.Exercises;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class NewTest {

    @Test
    public void testNew() throws Exception {
        byte[] bytes = NewStringGenerator.dump();
//        ClassUtils.toClassFile(bytes, Exercises.childClassNameFor(NewStringGenerator.FOR_CASE)+ ".class");
        Class subClass = ClassUtils.loadClass(Exercises.childQualifiedClassName(NewStringGenerator.FOR_CASE), bytes);
        Object object = subClass.newInstance();
        @SuppressWarnings("unchecked")
        Method method = subClass.getMethod("genString");
        String result = (String)method.invoke(object);
        Assert.assertEquals("HelloWorld", result);
    }
}
