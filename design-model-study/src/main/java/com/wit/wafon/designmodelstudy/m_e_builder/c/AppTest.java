package com.wit.wafon.designmodelstudy.m_e_builder.c;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/8 18:42
 * @describe
 *      目前这种还不是建造者模式
 *      优点：
 *          电脑已经被封装，隐藏了建造过程
 *          可以根据客户端不同的需求来采用不同的配置电脑
 *      缺点：
 *          1.不同的配置电脑中的代码在重复；
 *          2.建造过程不稳定。在某个建造者创建产品过程中，漏掉了某一流程，编译器不会报错。
 *            例如在低端电脑建造者中，没有装Cpu。
 *
 */
// ============================== 客户端 ===================================
public class AppTest {
    public static void main(String[] args) {
        AdvancedComputerBuilder advancedComputerBuilder = new AdvancedComputerBuilder();
        MiddleComputerBuilder middleComputerBuilder = new MiddleComputerBuilder();
        LowComputerBuilder lowComputerBuilder = new LowComputerBuilder();
        //开发
        Computer a = advancedComputerBuilder.build();
        Computer m = middleComputerBuilder.build();
        Computer l = lowComputerBuilder.build();
        System.out.println("a = " + a);
        System.out.println("m = " + m);
        System.out.println("l = " + l);
    }
}


// ============================== 作者 ===================================
//高端电脑
class AdvancedComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 8750HK");
        computer.setGpu("rtx2080ti");
        computer.setMemery("32G");
        computer.setHard("500G固态+2T机械");
        return computer;
    }
}
//中端电脑
class MiddleComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 7700hq");
        computer.setGpu("gtx1060");
        computer.setMemery("16G");
        computer.setHard("256G固态+1T机械");
        return computer;
    }
}
//低配
class LowComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 7700u");
        computer.setGpu("gtx940mx");
        computer.setMemery("8G");
        computer.setHard("1T机械");
        return computer;
    }
}

@Data
class Computer {
    private String cpu;
    private String gpu;
    private String memery;
    private String hard;
}
