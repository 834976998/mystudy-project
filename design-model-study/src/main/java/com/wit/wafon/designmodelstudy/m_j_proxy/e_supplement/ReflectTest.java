package com.wit.wafon.designmodelstudy.m_j_proxy.e_supplement;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

/**
 * @author fengwang26
 * @date 2022/11/16 14:45
 * @describe
 *
 *      反射机制
 */
public class ReflectTest {
    @SneakyThrows
    public static void main(String[] args) {

        /*
            1.正常创建对象
         */
        Person person = new Person();
        person.eat();

        System.out.println("=====================");

        /*
            2.通过反射调用
         */

        //2.1 获取类的字节码 - 字节码是根据源代码生成的，所以源代码中的信息，字节码中也会有
        Class<?> aClass = Class.forName("com.wit.wafon.designmodelstudy.m_j_proxy.e_supplement.Person");

        //2.2 利用反射机制，创建对象 - 利用反射机制，调用类的无参构造器来实例化对象的（克隆和反序列化是不调用构造器的）
        Object obj = aClass.newInstance();

        //2.3 反射出字节码中的某个方法
        //无参方法
        Method eatMethod = aClass.getDeclaredMethod("eat");
        Method eatMethod1 = aClass.getDeclaredMethod("run");
        //有参方法，方法类型为 String
        Method eatMethod2 = aClass.getDeclaredMethod("eat",String.class);

        //2.4 调用 - 把 eatMethod 所代表的方法当作 obj 对象的方法来调用
        eatMethod.invoke(obj);
        eatMethod1.invoke(obj);
        eatMethod2.invoke(obj,"砂锅");

    }

}

class Person {
    public void eat(){
        System.out.println("我吃饭！");
    }

    public void eat(String food){
        System.out.println("我吃"+food+"！");
    }


    public void run(){
        System.out.println("我跑！");
    }
}
