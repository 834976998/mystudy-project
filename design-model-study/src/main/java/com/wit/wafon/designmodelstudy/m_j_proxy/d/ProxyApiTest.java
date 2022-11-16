package com.wit.wafon.designmodelstudy.m_j_proxy.d;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author fengwang26
 * @date 2022/11/15 11:43
 * @describe
 *
 *      动态代理
 *
 *      需求场景：
 *          自定义计算器，实现加减乘除功能
 *
 *       针对 c 包中的问题
 *          优点：
 *              1.符合开闭原则；
 *          缺点：
 *              1.改动太大；
 *              2.需求经常变化后，较难改动；
 *       我们做如下修改:
 *
 *
 *
 */
public class ProxyApiTest {
    public static void main(String[] args) {

        /*
            创建静态代理对象
                参数
                ClassLoader：类加载器(加载第二个参数动态生成的字节码)
                    要实例化一个对象，接必须调用类的构造器！
                    在构造器调用之前，Jvm 会加载该类的字节码！
                    Jvm 就是使用“类加载器”来加载类的字节码。

                Class<?>[]：接口字节码数组
                    要创建一个类的对象，必须要先加载类的字节码。那么类需要加载哪个类的字节码，通过该参数确定
                    动态代理会生成实现了目标接口类的字节码。
                    本例中就是生成一个实现了 ICalc 接口的类的字节码！

                InvocationHandler：调用处理器
                    动态代理会加载自己动态生成的字节码，而这个字节码是根据某几个接口生成的。
                    在本例中就是根据 ICalc 接口生成的
                    生成的是实现了 ICalc 接口的字节码。
                    问题是，实现了一个接口，就需要实现其中的抽象方法，那么动态代理生成的字节码，实现了 ICalc 接口，那么就需要实现 add()、sub()、mul()、div() 方法
                    那么实现了方法，就应该有方法体，方法体由第三个参数决定。

                    class 动态生成类 implements ICalc {
                        add() {
                            new MyInvocationHandler().invoke();
                        }
                        sub() {
                            new MyInvocationHandler().invoke();
                        }
                        mul() {
                            new MyInvocationHandler().invoke();
                        }
                        div() {
                            new MyInvocationHandler().invoke();
                        }
                    }


            以下代码，是动态创建一个代理对象的代码。

            优点：
                1.较少了工作量，如果需求变化，只需要修改一个地方就行；
                2.不用修改作者代码，符合开闭原则

            目前还不是代理模式。只是 jdk 中自带的代理API

         */

        //获取当前类的类加载器 - 加载器随意选择就行
        ClassLoader classLoader = ProxyApiTest.class.getClassLoader();
        System.out.println(ProxyApiTest.class.getClassLoader() == MyInvocationHandler.class.getClassLoader());
        //字节码数组
        Class[] interfaces = {ICalc.class};
        //真实对象
        ICalc calc = new CalcImpl();
        ICalc iCalc = (ICalc) Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler(calc));
        int add = iCalc.add(1, 3);
        int sub = iCalc.sub(1, 3);
        int mul = iCalc.mul(1, 3);
        int div = iCalc.div(6, 3);


    }
}

class MyInvocationHandler implements InvocationHandler {

    /**
     * 要处理的真实对象
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 方法的返回值需要与 ICalc 接口定义的一致
     *    参数与方法调用一一对应
     *    iCalc.add(1, 3);
     *    proxy.method(args);
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"开始，参数是：" + "("+ Arrays.asList(args)+")");
        //利用反射机制，调用真实方法
        //把 method 方法当作真实对象 target 调用，参数是 args
        Object r = method.invoke(target,args);
        System.out.println(method.getName()+"结束，结果是："+r);
        return r;
    }
}

/** ======================================= 作者 ==========================================
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
        int c = a+b;
        return c;
    }

    @Override
    public int sub(int a, int b) {
        int c = a-b;
        return c;
    }

    @Override
    public int mul(int a, int b) {
        int c = a*b;
        return c;
    }

    @Override
    public int div(int a, int b) {
        int c = a/b;
        return c;
    }
}
