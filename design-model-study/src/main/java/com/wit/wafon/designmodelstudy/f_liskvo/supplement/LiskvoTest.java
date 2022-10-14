package com.wit.wafon.designmodelstudy.f_liskvo.supplement;

/**
 * @author fengwang26
 * @date 2022/10/14 11:48
 * @describe
 *
 *      正方形不是长方形
 */
public class LiskvoTest {
    public static void main(String[] args) {
        Fu fu = new Fu();
        fu.f1();
    }
}


class Fu {
    public void f1(){
        System.out.println("父类方法");
    }
}

/**
 * 方法重写：
 *      在子类和父类中，出现了返回类型、方法名、参数相同的方法，构成重写。
 * 重写限制：
 *      1.子类重写父类方法时，子类方法的访问修饰符不能比父类更严格；
 *      2.子类重写父类方法时，子类不能抛出比父类更多的异常；
 */
class Zi extends Fu{
    public void f1(){
        System.out.println("子类方法");
    }
}
