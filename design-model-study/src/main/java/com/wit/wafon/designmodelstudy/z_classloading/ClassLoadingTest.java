package com.wit.wafon.designmodelstudy.z_classloading;

/**
 * @author fengwang26
 * @date 2022/9/28 9:54
 * @describe
 *      类加载机制
 *
 *     1.类的成员
 *        类中的实例成员，等价于构造代码块；
 *        类中的静态成员，等价于静态块；在类的构造时执行一次，以后的每次构造不再执行
 *     2、执行顺序
 *        静态代码块 > 静态成员变量 > 代码块 > 成员变量 > 构造器
 *        注：若为相同类型的代码块，按照代码顺序执行
 *
 *
 */
public class ClassLoadingTest {
    public static void main(String[] args) {
        new Foo();
        System.out.println("================");
        new Foo();
    }

}

class Foo{

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("代码块");
    }

    int a = f1();

    static int b = f2();

    public Foo(){
        System.out.println("类构造器");
    }

    private static int f2() {
        System.out.println("b");
        return 2;
    }

    public int f1(){
        System.out.println("a");
        return 1;
    }

}
