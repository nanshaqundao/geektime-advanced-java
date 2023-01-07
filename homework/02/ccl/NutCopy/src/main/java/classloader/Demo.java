package classloader;

import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws Exception {
        HeroClassLoader heroClassLoader = new HeroClassLoader("D:\\training\\geektime\\advancedJava\\lib");
        Class c = heroClassLoader.loadClass("classloader.Test");
        if(c!=null){
            Object obj = c.getDeclaredConstructor().newInstance();
            Method method = c.getMethod("say",null);
            method.invoke(obj,null);
            System.out.println(c.getClassLoader().toString());
        }
    }
}
