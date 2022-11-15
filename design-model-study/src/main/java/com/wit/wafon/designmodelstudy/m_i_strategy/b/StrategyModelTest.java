package com.wit.wafon.designmodelstudy.m_i_strategy.b;

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
 *          变化：
 *              游戏公司需要提高游戏竞争力，要求游戏中的鸭子能够飞翔
 *
 *              调整方式一：
 *                 在父类 Duck 中添加一个 fly() 方法，那么所有的子类都会继承飞翔
 *                 问题：
 *                      并不是所有的鸭子都会飞翔（橡皮鸭不会飞）
 *
 *              调整方式二：
 *                  让不会飞的鸭子重写父类飞行方法，抛出异常
 *
 *         变化：
 *              在角色不断的增多时候，变化越累越剧烈，每次添加一个角色，都要考虑父类中的方法是不是满足，不满足需要重写，非常麻烦。
 *
 *
 */
public class StrategyModelTest {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.display();
        mallardDuck.fly();

        System.out.println("================");

        Duck rubberDuck = new RubberDuck();
        rubberDuck.quack();
        rubberDuck.swim();
        rubberDuck.display();
        rubberDuck.fly();
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

    public void fly(){
        System.out.println("自由飞翔");
    }

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

/**
 * 橡皮鸭
 */
class RubberDuck extends Duck{

    /**
     * 橡皮鸭有自己的叫声
     */
    @Override
    public void quack (){
        System.out.println("吱吱叫");
    }

    @Override
    public void display() {
        System.out.println("黄色橡皮鸭");
    }

    @Override
    public void fly(){
        throw new RuntimeException("救命，我是橡皮鸭，我不会飞呀");
    }
}


