package com.wit.wafon.designmodelstudy.e_demeter.negative;

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
     * 反例
     * 要求 Person 对于 Computer 的细节非常清楚
     * 若顺序错误，导致程序错误！
     */
    public void shutdownComputer(){
        computer.saveData();
        computer.killProcess();
        computer.closeScrean();
        computer.poweroff();
    }

}


/**
 * 模拟电脑关机
 */
class Computer {

    public void saveData(){
        System.out.println("保存数据");
    }

    public void killProcess(){
        System.out.println("关闭程序");
    }

    public void closeScrean(){
        System.out.println("关闭屏幕");
    }

    public void poweroff(){
        System.out.println("切断电源");
    }

}

