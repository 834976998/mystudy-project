package com.wit.wafon.designmodelstudy.m_h_adaptor.b;

/**
 * @author fengwang26
 * @date 2022/11/14 14:39
 * @describe
 */
public class WavefromTest {
    public static void main(String[] args) {

        /*
            首次 new 对象，引起类对象加载，执行静态代码块，给 Wavefrom.counter 赋值为0
         */
        Wavefrom wavefrom = new Wavefrom();
        System.out.println(wavefrom.toString());
        /*
            再次 new 对象后，不执行静态代码块，仅执行 id=counter,counter++
         */
        Wavefrom wavefrom1 = new Wavefrom();
        System.out.println(wavefrom1.toString());

        Wavefrom wavefrom2 = new Wavefrom();
        System.out.println(wavefrom2.toString());
    }
}

/**
 * 实现代码 ID 自增长
 */
class Wavefrom {

    private static long counter;

    private final long id = counter++;

    @Override
    public String toString() {
        return "Wavefrom{" +
                "id=" + id +
                '}';
    }
}


class Filter {

    public String name (){
        return getClass().getSimpleName();
    }

    public Wavefrom process (Wavefrom input){
        return input;
    }

}

/**
 * 低通滤波
 */
class LowPass {
    double cutoff;

    public LowPass (double cutoff){
        this.cutoff = cutoff;
    }

    public Wavefrom process (Wavefrom input){
        return input;
    }
}

/**
 * 高通滤波
 */
class HighPass {
    double cutoff;

    public HighPass (double cutoff){
        this.cutoff = cutoff;
    }

    public Wavefrom process (Wavefrom input){
        return input;
    }
}

/**
 * 带通滤波
 */
class BandPass {
    double cutoff;

    public BandPass (double cutoff){
        this.cutoff = cutoff;
    }

    public Wavefrom process (Wavefrom input){
        return input;
    }
}

