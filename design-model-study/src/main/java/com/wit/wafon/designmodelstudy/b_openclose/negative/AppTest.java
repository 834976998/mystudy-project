package com.wit.wafon.designmodelstudy.b_openclose.negative;

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
 */
public class AppTest {
    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("奔驰");
        car.setPrice(200000.00);
        System.out.println("car = " + car.toString());

        //十月一将车打八折
        car.setPrice(200000.00*0.8);
        System.out.println(car.toString());
    }
}
