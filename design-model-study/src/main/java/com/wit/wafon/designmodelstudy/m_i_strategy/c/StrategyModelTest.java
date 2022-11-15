package com.wit.wafon.designmodelstudy.m_i_strategy.c;

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
 *          针对在角色不断的增多时候，变化越累越剧烈，每次添加一个角色，都要考虑父类中的方法是不是满足，不满足需要重写，非常麻烦。
 *          我们希望，不会飞的鸭子，没有 fly() 方法，不会叫的鸭子没有 quack()
 *
 *          修改方法：
 *              将容易变化的方法做成接口，需要的就去实现，不需要的就不实现
 *
 *          缺点1：
 *              以前是每加入一个鸭子角色，需要判断是否会飞，会叫，不会的重写方法
 *              现在的是每加入一个鸭子角色，需要判断是否会飞，会叫，会的实现接口
 *              没有本质区别。
 *
 *              而且，每实现一个接口，都需要重写该技能，且大部分是可以重用的代码。
 *              但是，JDK1.8 中，接口会有默认的实现
 *          缺点2：
 *              游戏中有 48 种鸭子，其中 12 种有自己独特的飞翔方式。默认实现只能写一种，其余的 11 种需要自定义写。
 *              飞翔方法没有重用性
 *
 *
 */
public class StrategyModelTest {
    public static void main(String[] args) {

        /**
         * !!! 向上转型时，子类独有的方法无法调用
         */
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.display();
        mallardDuck.fly();

        System.out.println("================");

        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.quack();
        rubberDuck.swim();
        rubberDuck.display();
    }
}

abstract class Duck {

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

interface Flyable {
    //接口中省略作用域关键字默认为 public ，类中省略为 dufault （只能同包访问）
    default void fly() {
        System.out.println("默认自由飞翔");
    }
}

interface Quackable{
    default void quack() {
        System.out.println(
                "默认嘎嘎叫"
        );
    }
}

/**
 * 野鸭
 */
class MallardDuck extends Duck  implements Flyable,Quackable{

    @Override
    public void display() {
        System.out.println("灰色的野鸭");
    }
}


/**
 * 红头鸭
 */
class RedHeadDuck extends Duck implements Flyable,Quackable{

    @Override
    public void display() {
        System.out.println("头是红黑色的");
    }

    @Override
    public void fly() {
        System.out.println("红头鸭飞");
    }

    @Override
    public void quack() {
        System.out.println("红头鸭嘎嘎叫");
    }
}

/**
 * 橡皮鸭
 */
class RubberDuck extends Duck implements Quackable{


    @Override
    public void display() {
        System.out.println("黄色橡皮鸭");
    }

    @Override
    public void quack() {
        System.out.println("橡皮鸭吱吱叫");
    }
}

/**
 * 诱饵鸭
 */
class DecoyDuck extends Duck {

    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }
}


