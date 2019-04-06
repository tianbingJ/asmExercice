package com.github.tianbingj.asm.arithmetic;

import com.github.tianbingj.asm.ClassUtils;
import com.github.tianbingj.asm.Constants;
import com.github.tianbingj.asm.data.Exercises;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class AddTest {

    private Class subClass;
    private Object object;

    @Before
    public void init() throws Exception {
        byte[] bytes = AddGenerator.dump();
        ClassUtils.toClassFile(bytes, Exercises.CHILD_ClASS_NAME + ".class");
        subClass = ClassUtils.loadClass(Exercises.CHILD_QUALIFIED_CLASS_NAME, bytes);
        object = subClass.newInstance();
    }

    /**
     * 测试添加的add 方法,intValue默认值是0， 加10 调用get方法
     */
    @Test
    public void testIntAdd() throws Exception {
        @SuppressWarnings("unchecked")
        Method method = subClass.getMethod("add", int.class);
        //intValue add 10
        method.invoke(object, 10);
        @SuppressWarnings("unchecked")
        Method getIntValueMethod = subClass.getMethod("getIntValue");
        int result = (int)getIntValueMethod.invoke(object);
        Assert.assertEquals(result, 10);
        //intValue add 1000
        method.invoke(object, 1000);
        getIntValueMethod = subClass.getMethod("getIntValue");
        result = (int)getIntValueMethod.invoke(object);
        Assert.assertEquals(result, 1010);
    }

    /**
     * 测试添加的add 方法,doubleValue默认值是0， 加10 调用get方法
     */
    @Test
    public void testDoubleAdd() throws Exception {
        @SuppressWarnings("unchecked")
        Method method = subClass.getMethod("add", double.class);
        //intValue add 10
        method.invoke(object, 10.0);
        @SuppressWarnings("unchecked")
        Method getIntValueMethod = subClass.getMethod("getDoubleValue");
        Double result = (Double)getIntValueMethod.invoke(object);
        Assert.assertEquals(result, 10.0, Constants.ZERO);
        //intValue add 1000
        method.invoke(object, 1000.0);
        getIntValueMethod = subClass.getMethod("getDoubleValue");
        result = (Double) getIntValueMethod.invoke(object);
        Assert.assertEquals(result, 1010.0, Constants.ZERO);
    }

}
