package com.github.tianbingj.asm.controll;

import com.github.tianbingj.asm.ClassUtils;
import com.github.tianbingj.asm.data.Exercises;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class WhileIntTest {

    private Class subClass;
    private Object object;

    @Test
    public void testWhileInt() throws Exception {
        WhileIntGenerator whileIntGenerator = new WhileIntGenerator();
        byte[] bytes = whileIntGenerator.dump();
        ClassUtils.toClassFile(bytes, Exercises.childClassNameFor("While")+ ".class");
        subClass = ClassUtils.loadClass(Exercises.childQualifiedClassName("While"), bytes);
        object = subClass.newInstance();

        Method method = subClass.getMethod("whileInt", int.class);
        method.invoke(object, 100);
        Method getIntValueMethod = subClass.getMethod("getIntValue");
        int result = (int)getIntValueMethod.invoke(object);
        Assert.assertEquals(100, result);
    }
}
