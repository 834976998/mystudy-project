package com.wit.wafon.designmodelstudy.m_i_strategy.d;



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
 *          针对以上缺点
 *
 *              引入策略模式，将飞行和叫的行为抽取为接口，固定抽象方法
 *              由特定的飞行和叫的方式去实现具体的方法
 *              在鸭子父类中关联（组合）飞行和叫的接口，调用飞行和叫的方法。
 *              在具体的鸭子子类构造器中，实例化具体的飞行和叫的方式
 *              由此实现具体飞和叫的代码的复用
 *
 *           优点:
 *              1.提高了代码的重用性
 *              2.运行时可以修改类的行为方式（游戏中角色获取技能）
 *              3.符合开闭原则，行为方式拓展开放
 *
 *           缺点：
 *              1.每一种策略都需要建成一个类
 *
 *
 */
public class StrategyModelTest {
    public static void main(String[] args) {

        /**
         * !!! 向上转型时，子类独有的方法无法调用
         */
        Duck mallardDuck = new MallardDuck();
        mallardDuck.swim();
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        System.out.println("================");

        Duck rubberDuck = new RubberDuck();
        rubberDuck.swim();
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.performQuack();

        System.out.println("================");

        Duck decoyDuck = new DecoyDuck();
        decoyDuck.swim();
        decoyDuck.display();
        decoyDuck.performFly();
        decoyDuck.performQuack();
        //运行时改变行为模式
        System.out.println("获取飞行技能：竹蜻蜓");
        decoyDuck.setFlyBehavior(new FlyWithZQT());
        decoyDuck.performFly();
    }
}

// --------------- 拓展 ------
class FlyWithZQT implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("通过竹蜻蜓飞");
    }
}


// ============================================= 服务端 ======================================

interface FlyBehavior {
    void fly();
}
interface QuackBehavior {
    void quack();
}

/**
 * 单独定义类来实现抽象方法
 *    飞行行为
 */
class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("用翅膀飞！");
    }
}

/**
 * 飞行行为2
 */
class FlyWithRocket implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("背上火箭飞");
    }
}

/**
 * 飞行方式3
 */
class FlyWithKick implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("被踢飞");
    }
}

/**
 * 飞行方式4
 */
class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}

/**
 * 叫方式1
 */
class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}

/**
 * 叫方式2
 */
class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

/**
 * 叫的方式3
 */
class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<<Slience>>");
    }
}

abstract class Duck {

    /**
     * 通过关联来重用特定方法
     * 具体策略由子类来初始化赋值
     */
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

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

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}



/**
 * 野鸭
 */
class MallardDuck extends Duck {

    /**
     * 定义野鸭飞行方式（子类确定父类具体的行为方式）
     *      翅膀飞
     */
    public MallardDuck (){
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("灰色的野鸭");
    }
}


/**
 * 红头鸭
 */
class RedHeadDuck extends Duck {

    public RedHeadDuck (){
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("头是红黑色的");
    }

}

/**
 * 橡皮鸭
 */
class RubberDuck extends Duck {

    public RubberDuck (){
        this.flyBehavior = new FlyWithRocket();
        this.quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("黄色橡皮鸭");
    }

}

/**
 * 诱饵鸭
 */
class DecoyDuck extends Duck {

    public DecoyDuck (){
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("诱饵鸭");
    }
}


