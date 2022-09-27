package com.wit.wafon.designmodelstudy.c_interfaceisolation.negative;

/**
 * @author fengwang26
 * @date 2022/9/18 17:14
 * @describe
 */
public class Dog implements Animal{
    @Override
    public void eat() {
        System.out.println("吃骨头");
    }

    @Override
    public void swim() {
        System.out.println("狗刨");
    }

    /**
     * 不应该实现该方法，但是应为接口中有定义，不得不实现
     */
    @Override
    public void fly() {
        System.out.println("我怕不会呀");
        throw new RuntimeException();
    }
}
