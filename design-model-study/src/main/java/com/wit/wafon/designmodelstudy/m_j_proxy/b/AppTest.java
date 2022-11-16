package com.wit.wafon.designmodelstudy.m_j_proxy.b;

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
 *      修改：
 *         为了适配修改的需求，在每个方法中添加日志输出
 *      缺点：
 *          1.工作量太大；
 *          2.如果CalcImpl不是我们自己创建的，手头没有源代码，不能直接修改；
 *          3.需求再次改变，例如使用英文打印，需要再次改动；
 *
 *
 */
public class AppTest {

    public static void main(String[] args) {
        ICalc iCalc = new CalcImpl();
        int add = iCalc.add(1, 3);

        int sub = iCalc.sub(1, 3);

        int mul = iCalc.mul(1, 3);

        int div = iCalc.div(6, 3);
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
        System.out.println("add 方法开始，参数为："+a+","+b);
        int c = a+b;
        System.out.println("add 方法结束，结果为："+c);
        return c;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("sub 方法开始，参数为："+a+","+b);
        int c = a-b;
        System.out.println("sub 方法结束，结果为："+c);
        return c;
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("mul 方法开始，参数为："+a+","+b);
        int c = a*b;
        System.out.println("mul 方法结束，结果为："+c);
        return c;
    }

    @Override
    public int div(int a, int b) {
        System.out.println("div 方法开始，参数为："+a+","+b);
        int c = a/b;
        System.out.println("div 方法结束，结果为："+c);
        return c;
    }
}
