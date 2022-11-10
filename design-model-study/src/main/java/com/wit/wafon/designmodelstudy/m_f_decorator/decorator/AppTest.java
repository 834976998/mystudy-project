package com.wit.wafon.designmodelstudy.m_f_decorator.decorator;

import lombok.Data;

import java.io.*;

/**
 * @author fengwang26
 * @date 2022/11/9 19:59
 * @describe
 *      业务场景
 *          星巴克卖咖啡。
 *              咖啡种类：Decaf,Espresso,DrakRoast,HouseBlend
 *          因为所有的咖啡都有共性，所以开发人员将他们的共性提到一个父类中：Beverage
 *
 *      b包的解决方案：
 *          为加牛奶、豆浆、摩卡、奶泡的 Decaf 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 Espresso 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 DrakRoast 分别创建一个类；
 *          为加牛奶、豆浆、摩卡、奶泡的 HouseBlend 分别创建一个类；
 *      那么需要添加 16 个类，会引起类爆炸，不能这样设计类。
 *      可以在父类 Beverage 中加 4 个 boolean 值，分别代表 牛奶、豆浆、摩卡、奶泡 调料。
 *
 *      此种方案能够解决添加调料的问题。但是如果客户端需要添加一种新的调料，需要修改作者的源码。
 *      无法很好的适配调料的变化。（违反了开闭原则）
 *
 *      c包需求：需求2：
 *          添加了调料，每种调料的价格不相同，调料会引起咖啡价格的变化。父类中根据添加调料情况计算价格。
 *      优点：
 *          1.没有类爆炸
 *          2.扩展符合开闭原则
 *      缺点：
 *          1.添加了新的调料枸杞。需要修改父类 Beverage 代码，破坏了开闭原则。
 *
 *      正对 c 包中的问题，使用装饰器设计模式
 *      构建调料抽象类，继承、关联饮料。
 *
 *      优点：
 *          1.添加新的饮料，不违反开闭原则;
 *          2.添加新的调料，不违反开闭原则；
 *      缺点：
 *          1.类比较多
 *
 *      定义：
 *          装饰器模式为结构性设计模式。允许像一个现有类添加新的功能，而不改变其结构。
 *          一边继承，一边关联。实现对现有类的包装。
 *
 *
 */
public class AppTest {
    public static void main(String[] args) throws IOException {

        test3();

    }

    /**
     * jdk 中的装饰器体现 FileInputStream
     */
    public static void test3() throws IOException {
        //类似于咖啡 - 读取字符
        Reader in = new FileReader("E://1.txt");
        //装饰器 - 继承、关联 Reader - 读行（只有支持读取当个字符，才能支持读多个）
        BufferedReader br = new BufferedReader(in);
        String line = "";
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }

    /**
     * 测试新添加的茶
     */
    public static void test2(){
        Beverage tea = new Tea();
        //添加牛奶
        Beverage milkTea = new Milk(tea);
        //添加枸杞
        Beverage gouqi = new Gouqi(milkTea);
        System.out.println(gouqi.getDesctiption() + "；价格：" + gouqi.cost());
    }

    /**
     * 对应装饰器示例示意图
     */
    public static void test1(){
        Beverage b = new Decaf();
        //给饮料中添加牛奶
        //向上转型时，调用的方法只和 new 的对象有关
        Beverage b1 = new Milk(b);
        //添加豆浆
        Beverage b3 = new SoybeanMilk(b1);

        System.out.println( b3.getDesctiption() +" 。价格："+ b3.cost());
    }
}

/**
 * 拓展饮料：茶
 */
class Tea extends Beverage {

    public Tea (){
        super("茶");
    }

    @Override
    public double cost() {
        return 6;
    }
}
/**
 * 拓展饮料：枸杞
 */
class Gouqi extends Condiment {

    public Gouqi (Beverage beverage){
        super(beverage);
    }
    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }

    @Override
    public String getDesctiption() {
        return beverage.getDesctiption() + " 枸杞";
    }
}


//============================================== 作者 ====================================

/**
 * 四种咖啡的共性：饮料
 */
@Data
abstract class Beverage {
    //描述
    private String desctiption;

    public Beverage (String desc){
        this.desctiption = desc;
    }

    /**
     * 调料价格
     * @return
     */
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
        return 1 ;
    }
}

class Espresso extends Beverage {

    public Espresso (){
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        return 2 ;
    }
}

class DarkRoast extends Beverage {

    public DarkRoast (){
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5 ;
    }
}

class HouseBlend extends Beverage {

    public HouseBlend (){
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3 ;
    }
}


/**
 * 调料
 *   虽然调料不是饮料，不满足 ”is a“ 关系，此处是为了实现装饰器模式。
 */
abstract class Condiment extends Beverage {

    //让调料类关联饮料类
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

class Milk extends Condiment {

    public Milk (Beverage beverage){
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.3;
    }

    @Override
    public String getDesctiption() {
        return beverage.getDesctiption() + " 牛奶";
    }
}

class SoybeanMilk extends Condiment {

    public SoybeanMilk (Beverage beverage){
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDesctiption() {
        return beverage.getDesctiption() + " 豆浆";
    }
}

class Mocha extends Condiment {

    public Mocha (Beverage beverage){
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.4;
    }

    @Override
    public String getDesctiption() {
        return beverage.getDesctiption() + " 摩卡";
    }
}

class MilkFoma extends Condiment {

    public MilkFoma (Beverage beverage){
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.1;
    }

    @Override
    public String getDesctiption() {
        return beverage.getDesctiption() + " 奶泡";
    }
}


