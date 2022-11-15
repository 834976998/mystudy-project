package com.wit.wafon.designmodelstudy.m_i_strategy.a;

/**
 * @author fengwang26
 * @date 2022/11/14 15:19
 * @describe
 *      策略模式
 *
 *      场景
 *          有一家游戏公司，制作了一款鸭子游戏。
 *          游戏中：角色都是鸭子，不同的鸭子之间有共性，所以为了提高代码的重用性，开发人员制作了鸭子的父类：Duck
 *          把这些鸭子的共性提到父类中。
 *
 *          实现如下：
 *
 *
 */
public class StrategyModelTest {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.display();

        Duck redHeadDuck = new RedHeadDuck();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.display();
    }
}

abstract class Duck {
    /**
     * 嘎嘎叫
     *      所有的鸭子都会嘎嘎叫
     */
    public void quack (){
        System.out.println("嘎嘎叫");
    }

    /**
     * 游泳
     *      所有的鸭子都会游泳
     */
    public void swim(){
        System.out.println("游泳...");
    }

    /**
     * 鸭子的外观
     *      不同种类的鸭子，外观不同，因此做成抽象类，子类实现自己的外观
     *       抽象方法必须在抽象类中
     */
    public abstract void display ();

}

/**
 * 野鸭
 */
class MallardDuck extends Duck {

    @Override
    public void display() {
        System.out.println("灰色的野鸭");
    }
}

/**
 * 红头鸭
 */
class RedHeadDuck extends Duck {

    @Override
    public void display() {
        System.out.println("头是红黑色的");
    }
}


