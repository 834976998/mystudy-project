package com.wit.wafon.designmodelstudy.e_demeter.positive;

/**
 * @author fengwang26
 * @date 2022/9/28 15:29
 * @describe
 *
 *      迪米特法则
 *
 *
 */

public class AppTest {
    public static void main(String[] args) {
        new Person().shutdownComputer();
    }
}

class Person {

    private Computer computer = new Computer();

    /**
     * 正例
     * 不要求 Person 对于 Computer 的细节非常清楚，具体的细节由电脑内部封装
     */
    public void shutdownComputer(){
        computer.shutdown();
    }

}


/**
 * 模拟电脑关机
 */
class Computer {

    public void shutdown(){
        this.saveData();
        this.killProcess();
        this.closeScrean();
        this.poweroff();
    }

    private void saveData(){
        System.out.println("保存数据");
    }

    private void killProcess(){
        System.out.println("关闭程序");
    }

    private void closeScrean(){
        System.out.println("关闭屏幕");
    }

    private void poweroff(){
        System.out.println("切断电源");
    }

}

