package com.github.tianbingj.asm.timer;

import com.github.tianbingj.asm.Utils;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * Created by tianbing on 2019/3/23.
 */

public class AddTimerAdapterTest {

    public static class TimerTest {

        public void method() {
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testAddTimer() throws Throwable {
        ClassReader classReader = new ClassReader("com.github.tianbingj.asm.timer.AddTimerAdapterTest$TimerTest");
        ClassWriter classWriter = new ClassWriter(0);
        AddTimerAdapter addTimerAdapter = new AddTimerAdapter(classWriter);
        classReader.accept(addTimerAdapter, 0);
        byte[] bytes = classWriter.toByteArray();

        Class modifiedClass = Utils.getClassFromBytes("com.github.tianbingj.asm.timer.AddTimerAdapterTest$TimerTest", bytes);
        Object modifiedObj = modifiedClass.newInstance();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = lookup.findVirtual(modifiedClass, "method", methodType);
        methodHandle.invoke(modifiedObj);

        Field field = modifiedObj.getClass().getField("timer");
        Long timer = (Long)field.get(modifiedObj);
        Assert.assertTrue(timer >= 100);
    }

}
