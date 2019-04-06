package com.github.tianbingj.invokedynamic;

import java.lang.invoke.*;

public class AsmInvokeDynamicTest {

    public static int plus(int a, int b) {  // method I want to dynamically invoke
        System.out.println(":" + a + "b:" + b);
        return a + b;
    }

    public static CallSite bootstrap(MethodHandles.Lookup caller, String name, MethodType type) throws Exception {
        System.out.println("bootstrap method");
        MethodHandle mh = MethodHandles.lookup().findStatic(AsmInvokeDynamicTest.class,
                "plus", MethodType.methodType(int.class, int.class, int.class));
        return new ConstantCallSite(mh.asType(type));
    }
}
