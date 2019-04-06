package com.github.tianbingj.invokedynamic;


import com.github.tianbingj.asm.ClassUtils;

import java.io.FileWriter;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        //generates mainClass
        byte[] bytes = MainClassGenerator.generate();
        String string = new String(bytes);
        FileWriter fileWriter = new FileWriter("Main.class");
        fileWriter.write(string);
        fileWriter.close();
        Class c = ClassUtils.loadClass(null, bytes);
        Object object = c.newInstance();
        Method method = c.getMethod("main", String[].class);
        method.invoke(null, (Object) new String[0]);
    }
}
