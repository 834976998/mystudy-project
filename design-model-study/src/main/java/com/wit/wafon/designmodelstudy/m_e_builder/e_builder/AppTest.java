package com.wit.wafon.designmodelstudy.m_e_builder.e_builder;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/8 18:42
 * @describe
 *      针对 d 包中的问题 - 进行优化
 *      建造者模式：
 *          添加指挥者：指挥建造过程
 *          建造者接口：稳定建造过程
 *          建造者： 实际建造步骤
 *
 *       优点：
 *          1.创建的过程稳定不变；（有ComputerBuilder接口）
 *          2.创建的过程只写一次，没有重复代码（指挥者）
 *          3.当需要扩展指挥者的时候，不用修改之前的代码，符合开闭原则
 *
 *       使用场景：（过程比较稳定）
 *          有时候，对像会面临一个复杂的创建过程，其通常由各个部分的子对象用一定的树算法构成；
 *          由于需求的变化，这个需求的各个部分经常会发生剧烈的变化，但是将他们组合在一起的算法是相对稳定的。
 *
 *
 */
// ============================== 客户端 ===================================
public class AppTest {
    public static void main(String[] args) {
        //建造者
        AdvancedComputerBuilder advancedComputerBuilder = new AdvancedComputerBuilder();
        MiddleComputerBuilder middleComputerBuilder = new MiddleComputerBuilder();
        LowComputerBuilder lowComputerBuilder = new LowComputerBuilder();
        //指挥者
        Director director = new Director();
        //建造产品
        Computer a = director.build(advancedComputerBuilder);
        Computer m = director.build(middleComputerBuilder);
        Computer l = director.build(lowComputerBuilder);
        System.out.println("a = " + a);
        System.out.println("m = " + m);
        System.out.println("l = " + l);
    }
}

/**
 * 扩展
 */




// ============================== 作者 ===================================

/**
 * 指挥者
 */
class Director {
    public Computer build (ComputerBuilder cb){
        cb.setCpu();
        cb.setGpu();
        cb.setMemery();
        cb.setHardDisk();
        return cb.build();
    }
}

/**
 * 电脑建造者接口
 *      通过抽象方法，来固定电脑的建造过程
 */
interface ComputerBuilder {
    void setCpu();
    void setGpu();
    void setMemery();
    void setHardDisk();
    Computer build();
}

//高端电脑
class AdvancedComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i7 8750HK");
    }

    @Override
    public void setGpu() {
        computer.setGpu("rtx2080ti");
    }

    @Override
    public void setMemery() {
        computer.setMemery("32G");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("500G固态+2T机械");
    }

    public Computer build() {
        return computer;
    }

}

//中端电脑
class MiddleComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i7 7700hq");
    }

    @Override
    public void setGpu() {

        computer.setGpu("gtx1060");
    }

    @Override
    public void setMemery() {

        computer.setMemery("16G");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("256G固态+1T机械");
    }

    public Computer build() {
        return computer;
    }
}

//低配
class LowComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i7 7700u");
    }

    @Override
    public void setGpu() {

        computer.setGpu("gtx940mx");
    }

    @Override
    public void setMemery() {

        computer.setMemery("8G");
    }

    @Override
    public void setHardDisk() {

        computer.setHardDisk("1T机械");
    }

    public Computer build() {

        return computer;
    }
}

@Data
class Computer {
    private String cpu;
    private String gpu;
    private String memery;
    private String hardDisk;
}
