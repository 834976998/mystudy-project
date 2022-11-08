package com.wit.wafon.designmodelstudy.m_d_prototype.case_clone;

import java.util.Date;

/**
 * @author fengwang26
 * @date 2022/11/7 18:36
 * @describe
 *      使用原型解决这个问题：
 *          1、必须让目标类实现 Cloneable 接口，该接口中没有任何抽象方法。这样的接口仅仅是作一个标记，作用是告诉 jvm 该类可以被克隆；
 *          2、实现了 Cloneable 接口，必须重写 Object.clone() 方法，并将接口修改为 public，不然无法调用clone()。
 *
 *
 */
public class PositiveAppTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        //第一周周报
        WeekReport wr1 = new WeekReport();
        wr1.setId(1);
        wr1.setEmp("王锋");
        wr1.setTitle("11月第1周个人周报");
        wr1.setSummary("学习了7大设计原则");
        wr1.setPlan("无");
        wr1.setSuggestion("无");
        wr1.setCreate_time(new Date());
        System.out.println(wr1);

        //第二周周报，克隆w1对象 - 不调用构造器（直接调用底层的二进制文件）
        WeekReport wr2 = (WeekReport) wr1.clone();
        System.out.println("是否为同一空间："+(wr1==wr2));
        wr2.setTitle("11月第2周个人周报");
        wr2.setSummary("学习了工厂设计模式");
        //修改 wr2 的create_time属性，会将 wr1 中的时间也修改掉，浅拷贝
        wr2.getCreate_time().setTime(0);
        System.out.println("wr2="+wr2);
        System.out.println("wr1="+wr1);
    }
}
