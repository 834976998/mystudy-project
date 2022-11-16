package com.wit.wafon.designmodelstudy.m_j_proxy.h;

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
 *       针对 g 包中的问题
 *
 *              新需求：
 *                  针对 ICalc 这个接口的日志功能，不同的方法打印不同的日志。
 *                  add方法使用英文日志，sub方法使用中文日志，mul/div方法不要日志
 *              实现：
 *                  该需求只能在日志功能实现的时候，对方法进行判断，对不同的方法实现不同语言的日志
 *              问题：
 *                  1.if-else 过多
 *                  2.在同一个方法中，同时处理 add、sub、mul和 div 的日志。违反了单一职责原则
 *
 *       新建 4 个拦截器，分别针对 add、sub、mul和 div 方法来处理日志
 *       对拦截器进行层层代理
 *
 *              问题：
 *                  代理包装是逆向的，例如：
 *                      AddLogInterceptor 是里层
 *                      SubLogInterceptor 在中层
 *                      MulLogInterceptor 在外层
 *
 *
 */
public class ProxyTest {
    public static void main(String[] args) {
        // icalc 是目标对象
        ICalc icalc = new CalcImpl();
        //根据目标对象，动态生成一个代理对象
        ICalc proxy =(ICalc) MyProxy.getProxy(icalc,new AddLogInterceptor());
        //我们把 proxy 这个代理对象，再当成一个新的目标对象，实现嵌套代理
        ICalc proxy2 = (ICalc) MyProxy.getProxy(proxy, new SubLogInterceptor());

        ICalc proxy3 = (ICalc) MyProxy.getProxy(proxy2, new MulLogInterceptor());

        proxy3.add(1,2);
        proxy3.sub(3,1);
        proxy3.mul(3,1);

    }
}

/**
 * 加法自定义处理器
 */
class AddLogInterceptor implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if("add".equals(method.getName())){
            System.out.println(method.getName()+"方法开始，参数是：" +  (ObjectUtils.isEmpty(args)?null: Arrays.asList(args)));
        }
    }

    @Override
    public void after(Method method, Object result) {

        if("add".equals(method.getName())){
            System.out.println("方法结束，结果为："+result);
        }
    }
}

/**
 * 减法
 */
class SubLogInterceptor implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if("sub".equals(method.getName())){
            System.out.println(method.getName()+" menthod start，params is ：" +  (ObjectUtils.isEmpty(args)?null: Arrays.asList(args)));
        }
    }

    @Override
    public void after(Method method, Object result) {
        if("sub".equals(method.getName())){
            System.out.println(method.getName()+" method end，result is："+result);
        }
    }
}

/**
 * 乘法
 */
class MulLogInterceptor implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if("mul".equals(method.getName())){
        }
    }

    @Override
    public void after(Method method, Object result) {
        if("mul".equals(method.getName())){
        }
    }
}

/**
 * 减法
 */
class DivLogInterceptor implements Interceptor {

    @Override
    public void before(Method method, Object[] args) {
        if("div".equals(method.getName())){
        }
    }

    @Override
    public void after(Method method, Object result) {
        if("div".equals(method.getName())){
        }
    }
}

/** ======================================= 作者 ==========================================
 /**
 * 自定义拦截器
 */
interface Interceptor {
    //前置处理
    void before(Method method, Object[] args);
    //后置处理
    void after(Method method, Object result);
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
    public static Object getProxy (Object target,Interceptor interceptor){
        //获取当前类的类加载器 - 加载器随意选择就行
        ClassLoader classLoader = MyProxy.class.getClassLoader();
        // 获取 target 所属的类所实现的接口
        Class[] interfaces = target.getClass().getInterfaces();
        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler(target,interceptor));
        return proxy;
    }

}


class MyInvocationHandler implements InvocationHandler {

    /**
     * 要处理的真实对象
     */
    private Object target;

    /**
     * 预留处理器
     * @param target
     */
    private Interceptor interceptor;

    public MyInvocationHandler(Object target,Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
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
        //前置通知 - 提出由用户实现
        //System.out.println(method.getName()+"方法开始，参数是：" +  (ObjectUtils.isEmpty(args)?null:Arrays.asList(args)));
        interceptor.before(method,args);
        //利用反射机制，调用真实方法
        //把 method 方法当作真实对象 target 调用，参数是 args
        Object r = method.invoke(target,args);
        //后置通知 - 提出由用户实现
        interceptor.after(method,r);
        return r;
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
