package com.wit.wafon.designmodelstudy.m_j_proxy.i;

import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/15 11:43
 * @describe
 *      动态代理
 *
 *      需求场景：
 *          自定义计算器，实现加减乘除功能
 *
 *       针对 h 包中的问题
 *          新建 4 个拦截器，分别针对 add、sub、mul和 div 方法来处理日志
 *          对拦截器进行层层代理
 *              问题：
 *                  代理包装是逆向的，例如：
 *                      AddLogInterceptor 是里层
 *                      SubLogInterceptor 在中层
 *                      MulLogInterceptor 在外层
 *
 *      这种逆向包装，对于用户不友好
 *          优化：
 *              将拦截器装入列表，倒序便利进行层层代理封装。
 *          缺点：
 *              获取拦截器时过于复杂
 *
 *          优化：
 *              将循环代理封装，例如：MyProxy.getProxy2
 *
 *      注意：
 *          不一定非要使用 jdk 动态代理来实现代理模式，JDK动态代理不等价于代理模式
 */
public class ProxyTest {
    public static void main(String[] args) {
        // icalc 是目标对象
        ICalc icalc = new CalcImpl();

        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new AddLogInterceptor());
        interceptors.add(new SubLogInterceptor());
        interceptors.add(new MulLogInterceptor());
        interceptors.add(new DivLogInterceptor());

        ICalc proxy2 = (ICalc) MyProxy.getProxy2(icalc,interceptors);

        proxy2.add(1,2);
        proxy2.sub(3,1);
        proxy2.mul(3,1);
        proxy2.div(6,2);

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
 * 除法
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
     * @Param interceptor 拦截器
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

    /**
     *
     * @param target   要代理的目标对象
     * @param interceptors 拦截器集合
     * @return
     */
    public static Object getProxy2(Object target,List<Interceptor> interceptors){
        for (int i = interceptors.size()-1; i >=0 ; i--) {
            Interceptor interceptor = interceptors.get(i);
            target =  MyProxy.getProxy(target,interceptor);
        }
        return target;
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
        //可以通过前置方法的返回值，来控制目标对象的访问权限
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
