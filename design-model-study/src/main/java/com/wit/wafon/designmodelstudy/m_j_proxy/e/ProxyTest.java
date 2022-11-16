package com.wit.wafon.designmodelstudy.m_j_proxy.e;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author fengwang26
 * @date 2022/11/15 11:43
 * @describe
 *      动态代理
 *
 *      需求场景：
 *          自定义计算器，实现加减乘除功能
 *
 *       针对 d 包中的问题
 *          缺点：
 *              1.利用JDK获取一个代理对象太复杂了
 *       我们做如下修改:
 *          将代理获取方式封装，简化代理对象创建成本
 *
 *       问题：
 *          1.目前我们创建的代理对象，只能在真实的方法调用前后，加日志，无法实现其他功能。
 *          比如：用户不想加日志功能，而是想加缓存功能、或者权限控制功能，或者延迟加载等...
 *
 *          需要将自定义处理的代码分出来
 *
 *
 */
public class ProxyTest {
    public static void main(String[] args) {

        CalcImpl icalc = new CalcImpl();
        ICalc proxy =(ICalc) MyProxy.getProxy(icalc);
        int add = proxy.add(1, 3);
        int sub = proxy.sub(1, 3);
        int mul = proxy.mul(1, 3);
        int div = proxy.div(6, 3);

        Vampire youngVampire = new YoungVampire();
        Vampire vampireProxy = (Vampire)MyProxy.getProxy(youngVampire);
        vampireProxy.drinkblood();
    }
}

/**
 * 吸血鬼
 */
interface Vampire {
    void drinkblood();
}

/**
 * 年轻的吸血鬼
 */
class YoungVampire implements Vampire {

    @Override
    public void drinkblood() {
        System.out.println("吸血！");
    }
}

/**
 * 包装代理接口
 */
class MyProxy {

    /**
     * 封装获取代理对象
     * @param target  需要代理的真实对象
     * @return  代理后的对象
     */
    public static Object getProxy (Object target){
        //获取当前类的类加载器 - 加载器随意选择就行
        ClassLoader classLoader = MyProxy.class.getClassLoader();
        // 获取 target 所属的类所实现的接口
        Class[] interfaces = target.getClass().getInterfaces();
        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler(target));
        return proxy;
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
        System.out.println(method.getName()+"方法开始，参数是：" +  (ObjectUtils.isEmpty(args)?null:Arrays.asList(args)));
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
