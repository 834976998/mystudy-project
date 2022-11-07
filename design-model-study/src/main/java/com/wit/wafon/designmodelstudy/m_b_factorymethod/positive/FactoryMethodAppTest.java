package com.wit.wafon.designmodelstudy.m_b_factorymethod.positive;

/**
 * @author fengwang26
 * @date 2022/10/18 10:18
 * @describe 针对反例。进行简单工厂设计模式的改造
 *
 *      工厂方法
 *
 *
 */

//============================== 客户端 =====================================
public class FactoryMethodAppTest {

    public static void main(String[] args) {
        FoodFactory foodFactory = new RiceNoodleFactory();
        Food food = foodFactory.getFood();
        food.eat();
        /**
         * ColdRiceNoodle 用户新增的食物，很好的通过工厂方法获取而不用修改作者源代码
         * 扩展产品，随之扩展工厂。不修改源代码，符合开闭原则
         */
        FoodFactory foodFactory2 = new ColdRiceNoodleFactory();
        Food food2 = foodFactory2.getFood();
        food2.eat();

        /**
         * 调用作者的方法业务
         */
        Business.taste(foodFactory);
        Business.taste(foodFactory2);


    }
}

/*
    用户新增食物产品
 */
class ColdRiceNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("凉皮");
    }
}

class ColdRiceNoodleFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new ColdRiceNoodle();
    }
}

//=============================== 服务端 ======================================
//抽象产品
interface Food {
    void eat();
}

//产品1
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("汉堡包");
    }
}

//产品2
class RiceNoodle implements Food {

    @Override
    public void eat() {
        System.out.println("过桥米线");
    }
}

/*
    抽象工厂
 */
interface FoodFactory {
    public Food getFood() ;
}

class HamburgerFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new Hamburger();
    }
}

class RiceNoodleFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new RiceNoodle();
    }
}

/**
 * 作者封装的业务逻辑
 */
class Business {

    /**
     * 工厂中作者封装的业务逻辑
     */
    public static void taste (FoodFactory foodFactory){

        Food f = foodFactory.getFood();
        System.out.println("评委1：品尝(色)：");
        f.eat();
        Food f2 = foodFactory.getFood();
        System.out.println("评委2：品尝(香)");
        f2.eat();
        Food f3 = foodFactory.getFood();
        System.out.println("评委3：品尝(味)");
        f3.eat();
    }



}
