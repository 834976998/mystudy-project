package com.wit.wafon.designmodelstudy.c_interfaceisolation.negative;

/**
 * @author fengwang26
 * @date 2022/9/18 17:16
 * @describe
 */
public class Bird implements Animal {

    @Override
    public void eat() {
        System.out.println("捉虫吃");
    }

    @Override
    public void swim() {
        System.out.println("我不会呀");
        throw new RuntimeException();
    }

    @Override
    public void fly() {
        System.out.println("翱翔天际");
    }
}
