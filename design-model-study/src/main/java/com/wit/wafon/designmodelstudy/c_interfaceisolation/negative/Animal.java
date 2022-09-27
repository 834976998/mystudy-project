package com.wit.wafon.designmodelstudy.c_interfaceisolation.negative;

/**
 * @author fengwang26
 * @date 2022/9/18 17:12
 * @describe
 *    定义动物属性
 *      违反了接口隔离原则
 *      并不是所有的动物都会飞、都会游泳
 */
public interface Animal {

    void eat();
    void swim();
    void fly();

}
