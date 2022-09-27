package com.wit.wafon.designmodelstudy.b_openclose.positive;

/**
 * @author fengwang26
 * @date 2022/9/18 16:58
 * @describe
 *  打折车
 */
public class DiscountCar extends Car{
    /*
        重写父类的 getPrice 实现打折
        通过扩展实现功能增强，而不修改原来定义的 car
     */
    @Override
    public Double getPrice(){
        return super.getPrice()*0.8;
    }

}
