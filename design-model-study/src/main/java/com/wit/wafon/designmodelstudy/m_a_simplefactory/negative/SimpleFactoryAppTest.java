package com.wit.wafon.designmodelstudy.m_a_simplefactory.negative;

/**
 * @author fengwang26
 * @date 2022/10/14 17:24
 * @describe
 *      简单工厂模式反例
 */
//======================================= 客户端 ===========================================
public class SimpleFactoryAppTest {

    public static void main(String[] args) {
        /*
            若是服务端的 Hamburger 改名了。客户端的代码会报错，客户端与服务端耦合
            缺点：客户端需要清楚服务端的细节，违反了迪米特法则
            问题：简单工厂设计模式
         */
        Food hamburger = new Hamburger();
        hamburger.eat();


    }

}

//======================================= 服务端 =========================================

/**
 * 抽象产品
 */
interface Food {
    void eat();
}

/**
 * 具体产品
 */
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包！");
    }
}
