package com.wit.wafon.designmodelstudy.m_c_abstractfactory;


/**
 * @author fengwang26
 * @date 2022/10/18 10:18
 * @describe
 *      针对工厂方法：
 *          当有多个产品等级（食物、饮料、酒水等），工厂类就会特别多，代码难以维护
 *
 *      抽象工厂模式：
 *          当工厂同时生产多个产品。例如KFC工厂，同时生产食物和饮料。
 *
 *
 */

//============================== 客户端 =====================================
public class AbstractFactoryPAppTest {

    public static void main(String[] args) {
        /**
         * 调用作者的方法业务
         */
        Business.taste(new KFCFactory());
        System.out.println("===============");
        Business.taste(new LJShaoKaoFactory());
        System.out.println("===============");
        Business.taste(new LXJFactory());


    }
}

// 客户端拓展
class CYJ implements Food{
    @Override
    public void eat() {
        System.out.println("老乡鸡葱油鸡");
    }
}
class JT implements Drink {

    @Override
    public void drink() {
        System.out.println("老乡鸡鸡汤");
    }
}
class LXJFactory implements Factory {

    @Override
    public Food getFood() {
        return new CYJ();
    }

    @Override
    public Drink getDrink() {
        return new JT();
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

/**
    工厂方法：只生产一个产品等级
    抽象工厂：生产多个产品等级
 */
interface Factory {
    public Food getFood() ;
    public Drink getDrink() ;
}

/**
 * 肯德基工厂
 */
class KFCFactory implements Factory {

    /**
     * KFC 生产汉堡包
     * @return
     */
    @Override
    public Food getFood() {
        return new Hamburger();
    }

    /**
     * KFC 生产可乐
     * @return
     */
    @Override
    public Drink getDrink() {
        return new Cola();
    }
}

/**
 * 老街烧烤工厂
 */
class LJShaoKaoFactory implements Factory {

    @Override
    public Food getFood() {
        return new RiceNoodle();
    }

    @Override
    public Drink getDrink(){
        return new Beer();
    }
}

/**
 * 作者封装的业务逻辑
 */
class Business {
    /**
     * 工厂中作者封装的业务逻辑
     */
    public static void taste (Factory abstractFactory){

        Food f = abstractFactory.getFood();
        Drink d = abstractFactory.getDrink();
        System.out.println("评委1：品尝(色)：");
        f.eat();
        d.drink();

        Food f2 = abstractFactory.getFood();
        Drink d2 = abstractFactory.getDrink();
        System.out.println("评委2：品尝(香)");
        f2.eat();
        d2.drink();

        Food f3 = abstractFactory.getFood();
        Drink d3 = abstractFactory.getDrink();
        System.out.println("评委3：品尝(味)");
        f3.eat();
        d3.drink();
    }
}

/**
 *  ==  饮料产品等级  ==
 */
interface Drink{
    public void drink();
}

class Cola implements Drink {

    @Override
    public void drink() {
        System.out.println("可口可乐");
    }
}

class Beer implements Drink {

    @Override
    public void drink() {
        System.out.println("喝啤酒");
    }
}
