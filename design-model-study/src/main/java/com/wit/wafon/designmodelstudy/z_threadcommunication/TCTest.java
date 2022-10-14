package com.wit.wafon.designmodelstudy.z_threadcommunication;

/**
 * @author fengwang26
 * @date 2022/9/28 11:35
 * @describe
 *      线程之间的通信
 *         两个线程之间通信必须有共享对象，以共享对象为锁
 *
 *      需求：
 *          1.先打印 1-52，再打印 A-Z
 *              定义共享对象，通过 synchronized 加锁实现
 *          2.打印 1A2B3C...
 *              每打印一次数字线程，wait 释放一次锁
 *          3.打印 12A34B...
 *              i为偶数才释放锁，每次打印两个数
 */
public class TCTest {
    public static void main(String[] args) {
        //需求 1 实现
        // n1();
        //需求2
        n2();
    }

    public static void n2(){
        //定义共享对象 - 每一个对象自带锁
        ThreadLock threadLock = new ThreadLock();

        T2 t = new T2(threadLock);
        S2 s = new S2(threadLock);
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(s);
        thread1.start();
        thread2.start();
    }

    /**
     * 1.先打印 1-52，再打印 A-Z
     *      定义共享对象，通过 synchronized 加锁实现
     */
    public static void n1(){

        //定义共享对象 - 每一个对象自带锁
        Object o = new Object();

        T1 t = new T1(o);
        S1 s = new S1(o);
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(s);
        thread1.start();
        thread2.start();
    }

}



/**
 * 线程 T 打印 1-52 数字
 */
class T1 implements Runnable {

    private Object obj;

    public T1 (Object obj){
        super();
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 1; i <=52 ; i++) {
                System.out.println(i);
            }
        }
    }
}

/**
 * 线程 S 打印 A-Z
 */
class S1 implements Runnable {

    //关联共享对象
    private Object obj;

    public S1 (Object obj){
        super();
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.println((char)i);
            }
        }
    }
}

/**
 *  定义共享对象 - 每一个对象自带锁
 */
class ThreadLock {
    private int x = 1;

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

}
/**
 * 线程 T 打印 1-52 数字
 */
class T2 implements Runnable {

    private ThreadLock threadLock;

    public T2 (ThreadLock threadLock){
        this.threadLock = threadLock;
    }

    @Override
    public void run() {
        synchronized (threadLock) {
            for (int i = 1; i <=52 ; i++) {
                //保障先打印数字 - 保障线程打印顺序先为数字
                while(threadLock.getX()!=1){
                    try {
                        //唤醒 S2 obj
                        threadLock.notifyAll();
                        //休眠当前 obj
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
                if(i%2==0){
                    threadLock.setX(2);
                }
            }
        }
    }
}

/**
 * 线程 S 打印 A-Z
 */
class S2 implements Runnable {

    private ThreadLock threadLock;

    public S2 (ThreadLock threadLock){
        this.threadLock = threadLock;
    }

    @Override
    public void run() {
        synchronized (threadLock) {
            for (int i = 'A'; i <= 'Z'; i++) {
                //保障先打印数字
                while(threadLock.getX()!=2){
                    try {
                        //唤醒 S2 obj
                        threadLock.notifyAll();
                        //休眠当前 obj
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println((char)i);
                threadLock.setX(1);
            }
        }
    }
}