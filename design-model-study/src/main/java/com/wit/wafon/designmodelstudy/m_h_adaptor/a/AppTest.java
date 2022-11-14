package com.wit.wafon.designmodelstudy.m_h_adaptor.a;

/**
 * @author fengwang26
 * @date 2022/11/14 10:34
 * @describe
 * 定义：
 *     将一个类的接口转换成客户端希望的另一个接口，让那些接口不兼容的类可以在一起工作。
 * 通俗解释：
 *      通过已有功能，适配新的功能。
 *
 */

// ========================================= 客户端 ============================================
public class AppTest {
    public static void main(String[] args) {
        //计算两个数和
        int add2 = Calc.add(1, 2);
        System.out.println(add2);

        //需求变换，计算 3 个数的和

        //方法1
        int add3 = Calc.add(1, Calc.add(2, 3));
        System.out.println("add3 = " + add3);

        //方法2 - 适配器
        Calc calc = new Calc();
        int addAdapter = new CalcAdapter(calc).add(1, 2, 3);
        System.out.println("addAdapter = " + addAdapter);

    }
}

/**
 * 适配器
 *      满足：
 *          开闭原则
 *          组合优于继承
 */
class CalcAdapter {
    //组合优于继承
    private  Calc calc;

    public CalcAdapter (Calc c){
        this.calc= c;
    }
    /**
        适配计算 3 个数的和
     */
    public int add (int a,int b ,int c) {
        return calc.add(a,calc.add(b,c));
    }
}


// ============================================ 作者 ===============================================
class Calc {
    //计算两个数的和
    public static int add (int a,int b){
        return a+b;
    }
}
