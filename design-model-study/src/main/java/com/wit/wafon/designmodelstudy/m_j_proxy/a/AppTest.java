package com.wit.wafon.designmodelstudy.m_j_proxy.a;

/**
 * @author fengwang26
 * @date 2022/11/15 11:43
 * @describe
 *      代理模式
 *
 *      需求场景：
 *          自定义计算器，实现加减乘除功能
 *
 *      变化：
 *          要求给 ICalc 中的每个方法添加日志，记录方法的开始和结束时机
 *
 */
public class AppTest {

    public static void main(String[] args) {
        ICalc iCalc = new CalcImpl();
        int add = iCalc.add(1, 3);
        System.out.println("add = " + add);

        int sub = iCalc.sub(1, 3);
        System.out.println("sub = " + sub);

        int mul = iCalc.mul(1, 3);
        System.out.println("mul = " + mul);

        int div = iCalc.div(6, 3);
        System.out.println("div = " + div);
    }
}

/**
 * 计算器
 */
interface ICalc {
    int add (int a,int b);
    int sub (int a,int b);
    int mul (int a,int b);
    int div (int a,int b);
}

class CalcImpl implements ICalc {

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mul(int a, int b) {
        return a*b;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }
}
