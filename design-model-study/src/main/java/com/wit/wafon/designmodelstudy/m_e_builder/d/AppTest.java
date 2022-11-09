package com.wit.wafon.designmodelstudy.m_e_builder.d;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/8 18:42
 * @describe
 *      针对C包中的问题 - 进行优化
 *      1.建造接口不稳定，没有规范的建造流程
 *          创建一个建造接口，把制作产品的具体步骤稳定下来。让建造者实现接口
 *
 *      优点：
 *          建造者类中的建造过程比较稳定
 *      缺点：
 *          1.代码有重复
 *          2.流程变为客户端需要知道细节，违反了迪米特法则
 *
 *
 */
// ============================== 客户端 ===================================
public class AppTest {
    public static void main(String[] args) {
        AdvancedComputerBuilder advancedComputerBuilder = new AdvancedComputerBuilder();
        advancedComputerBuilder.setCpu();
        advancedComputerBuilder.setGpu();
        advancedComputerBuilder.setMemery();
        advancedComputerBuilder.setHardDisk();
        MiddleComputerBuilder middleComputerBuilder = new MiddleComputerBuilder();
        middleComputerBuilder.setCpu();
        middleComputerBuilder.setGpu();
        middleComputerBuilder.setMemery();
        middleComputerBuilder.setHardDisk();
        LowComputerBuilder lowComputerBuilder = new LowComputerBuilder();
        lowComputerBuilder.setCpu();
        lowComputerBuilder.setGpu();
        lowComputerBuilder.setMemery();
        lowComputerBuilder.setHardDisk();
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
