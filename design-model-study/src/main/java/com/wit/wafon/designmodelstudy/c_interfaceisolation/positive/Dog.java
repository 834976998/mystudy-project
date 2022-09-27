package com.wit.wafon.designmodelstudy.c_interfaceisolation.positive;

/**
 * @author fengwang26
 * @date 2022/9/18 17:20
 * @describe
 *      按照需要实现接口
 */
public class Dog implements EatAble,SwimAble{

    @Override
    public void eat() {
        System.out.println("吃骨头");
    }

    @Override
    public void swim() {
        System.out.println("狗刨");
    }

}
