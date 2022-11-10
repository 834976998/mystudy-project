package com.wit.wafon.designmodelstudy.m_f_decorator.c;

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
 *      需求2：
 *          添加了调料，每种调料的价格不相同，调料会引起咖啡价格的变化。父类中根据添加调料情况计算价格。
 *      优点：
 *          1.没有类爆炸
 *          2.扩展符合开闭原则
 *      缺点：
 *          1.添加了新的调料枸杞。需要修改父类 Beverage 代码，破坏了开闭原则。
 *          2.每种调料只能添加一份；
 *
 */
public class AppTest {
    public static void main(String[] args) {

        Beverage tea = new Tea();
        //添加牛奶
        tea.setAddMilk(true);
        tea.setAddSoybeanMilk(true);
        System.out.println("tea : " + tea.getDesctiption() + tea.cost() + "  是否添加牛奶："+tea.isAddMilk());

    }
}

//扩展一个茶
class Tea extends Beverage {

    public Tea (){
        super("茶");
    }

    public double cost (){
        return 6 + super.cost();
    }

}

//============================================== 作者 ====================================

/**
 * 四种咖啡的共性：饮料
 */
@Data
@AllArgsConstructor
abstract class Beverage {
    //描述
    private String desctiption;

    public Beverage (String desc){
        this.desctiption = desc;
    }

    private boolean addMilk ;
    private boolean addSoybeanMilk;
    private boolean addMocha;
    private boolean addMilkFoma;

    /**
     * 调料价格
     * @return
     */
    public double cost (){
        double cost = 0;
        return cost += (addMilk?0.2:0)+(addSoybeanMilk?0.3:0)+(addMocha?0.4:0)+(addMilkFoma?0.1:0);
    }

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
        return 1 + super.cost();
    }
}

class Espresso extends Beverage {

    public Espresso (){
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        return 2 + super.cost();
    }
}

class DarkRoast extends Beverage {

    public DarkRoast (){
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5 + super.cost();
    }
}

class HouseBlend extends Beverage {

    public HouseBlend (){
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3 + super.cost();
    }
}


