package com.wit.wafon.designmodelstudy.b_openclose.positive;

/**
 * @author fengwang26
 * @date 2022/9/18 16:47
 * @describe
 *      定义一个汽车类，有商标合价格属性
 *      需求：
 *          汽车需要做活动打折。
 *              方法一：用户使用侧自己写打折逻辑。
 *                     活动可能很复杂，用户自定义实现困难
 *              方法二：定义实体类中实现，直接处理价格属性
 *                    修改了原价格属性的含义，违反了开闭原则
 *
 *      符合开闭原则的做法是始终保持定义的 Car 的源代码不改变。
 *      创建一个car的子类，重写 getPrice() 方法
 */
public class AppTest {
    public static void main(String[] args) {
        //使用 car 的子类
        //向上转型时，调用的方法只跟 new 的对象有关
        Car car = new DiscountCar();
        car.setBrand("奔驰");
        car.setPrice(200000.00);
        System.out.println("car = " + car.toString());
    }
}
