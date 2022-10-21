package com.wit.wafon.designmodelstudy.m_a_simplefactory.positive;

/**
 * @author fengwang26
 * @date 2022/10/18 10:18
 * @describe 针对反例。进行简单工厂设计模式的改造
 *
 *      简单工厂
 *
 */

//============================== 客户端 =====================================
public class SimpleFactoryPAppTest {

    public static void main(String[] args) {
        /*
            不再直接创造具体的实现类，通过工厂获取实现面向接口编程。灵活拓展
         */
        Food food = FoodFactory.getFood(1);
        food.eat();
        Food food2 = FoodFactory.getFood(2);
        food2.eat();

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
    通过工厂方式，不用关心抽象类的实现细节
 */
class FoodFactory {
    public static Food getFood(int n) {
        Food food = null;
        switch (n) {
            case 1:
                food = new Hamburger();
                break;
            case 2:
                food = new RiceNoodle();
                break;
            default:
                break;
        }
        return food;
    }
}


