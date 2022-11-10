package com.wit.wafon.designmodelstudy.m_f_decorator.b;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/9 19:59
 * @describe
 *      业务场景
 *          星巴克卖咖啡。
 *              咖啡种类：Decaf,Espresso,DrakRoast,HouseBlend
 *          因为所有的咖啡都有共性，所以开发人员将他们的共性提到一个父类中：Beverage
 *
 *      为了满足 a 包中的变化：咖啡可以添加调料：牛奶、豆浆、摩卡、奶泡
 *
 *      尝试这样解决问题：
 *          为加牛奶、豆浆、摩卡、奶泡的 Decaf 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 Espresso 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 DrakRoast 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 HouseBlend 分别创建一个类；
 *      问题：会引起类爆炸，不能这样设计类。
 *
 */
public class AppTest {
    public static void main(String[] args) {

        Beverage decaf = new Decaf();
        System.out.println("decaf : " + decaf.getDesctiption() + decaf.cost());

        Beverage espresso = new Espresso();
        System.out.println("espresso : " + espresso.getDesctiption() + espresso.cost());

        Beverage darkRoast = new DarkRoast();
        System.out.println("darkRoast : " + darkRoast.getDesctiption() + darkRoast.cost());

        Beverage houseBlend = new HouseBlend();
        System.out.println("houseBlend : " + houseBlend.getDesctiption() + houseBlend.cost());

    }
}

/**
 * 四种咖啡的共性：饮料
 */
@Data
@AllArgsConstructor
abstract class Beverage {
    //描述
    private String desctiption;

    public abstract double cost ();

}

/**
 * 无咖啡因咖啡
 */
class Decaf extends Beverage {
    public Decaf(){
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        return 1;
    }
}

class Espresso extends Beverage {

    public Espresso (){
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        return 2;
    }
}

class DarkRoast extends Beverage {

    public DarkRoast (){
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5;
    }
}

class HouseBlend extends Beverage {

    public HouseBlend (){
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3;
    }
}


