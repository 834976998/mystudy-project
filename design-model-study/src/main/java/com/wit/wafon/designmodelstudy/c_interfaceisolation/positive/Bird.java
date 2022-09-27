package com.wit.wafon.designmodelstudy.c_interfaceisolation.positive;

/**
 * @author fengwang26
 * @date 2022/9/18 17:21
 * @describe
 *      按照需要实现接口，只实现需求的接口
 */
public class Bird implements EatAble,FlyAble{
    @Override
    public void eat() {
        System.out.println("吃虫子");
    }

    @Override
    public void fly() {
        System.out.println("翱翔天际");
    }
}
