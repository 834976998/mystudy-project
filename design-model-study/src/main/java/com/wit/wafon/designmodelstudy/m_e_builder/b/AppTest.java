package com.wit.wafon.designmodelstudy.m_e_builder.b;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/8 18:42
 * @describe
 *      目前这种还不是建造者模式
 *      优点：
 *          电脑已经被封装，隐藏了建造过程
 *      缺点：
 *          无论是什么需求，电脑的属性是固定的
 */
// ============================== 客户端 ===================================
public class AppTest {
    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder();
        System.out.println("c = " + computerBuilder.build());
    }
}


// ============================== 作者 ===================================

class ComputerBuilder {
    private Computer computer = new Computer();

    public Computer build() {
        computer.setCpu("i7 8750HK");
        computer.setGpu("rtx2080ti");
        computer.setMemery("32G");
        computer.setHard("500G固态+2T硬盘");
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
