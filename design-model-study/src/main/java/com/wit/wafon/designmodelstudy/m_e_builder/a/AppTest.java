package com.wit.wafon.designmodelstudy.m_e_builder.a;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/8 18:42
 * @describe
 *
 *      建造者模式：
 *          关注对象的创建过程。例如做一个凉皮，是如何做的（创建对象后需要赋值）。
 *      工厂模式：
 *          关注创建后的产品（直接实例化一个类的对象）
 *
 *      直接创建对象，赋值
 *      缺点：
 *          1.客户端在实例化好产品的对象后，必须对该对象的每一个属性赋值，这样对于客户端程序员来说，非常麻烦。
 *            且违反了迪米特法则（最少知道原则）
 *          2.
 *
 */
// ============================== 客户端 ===================================
public class AppTest {
    public static void main(String[] args) {
        Computer c = new Computer();
        c.setCpu("i7 7500u");
        c.setGpu("gt940mx");
        c.setMemery("16G");
        c.setHard("1T固态");
        System.out.println("c = " + c);
    }
}


// ============================== 作者 ===================================
@Data
class Computer {
    private String cpu;
    private String gpu;
    private String memery;
    private String hard;
}
