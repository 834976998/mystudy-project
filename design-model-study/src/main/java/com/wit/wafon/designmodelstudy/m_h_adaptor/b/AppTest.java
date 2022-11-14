package com.wit.wafon.designmodelstudy.m_h_adaptor.b;

import java.util.Arrays;

/**
 * @author fengwang26
 * @date 2022/11/14 11:22
 * @describe
 */
public class AppTest {
    public static void main(String[] args) {
        String str = "how are you";

        /*
            代码在重复
         */
        Processor upcase = new Upcase();
        System.out.println("use processor:"+ upcase.name() + " = " + upcase.process(str));
        Processor downCase = new DownCase();
        System.out.println("use processor:"+ downCase.name() + " = " + downCase.process(str));
        Processor splitter = new Splitter();
        System.out.println("use processor:"+ splitter.name() + " = " + splitter.process(str));

        /*
            通过处理器处理
         */
        Processor upcase2 = new Upcase();
        Apply.process(upcase2,str);
        Processor downCase2 = new DownCase();
        Apply.process(downCase2,str);
        Processor splitter2 = new Splitter();
        Apply.process(splitter2,str);

    }
}

/**
 * 处理器
 *      通过引用来去除重复代码
 */
class Apply {

    /**
     * process() 可以接收任何类型的 Processor 及其子类
     * @param processor
     * @param str
     */
    public static void process (Processor processor,String str){
        System.out.println("use processor:"+ processor.name() + " = " + processor.process(str));
    }
}

class Processor {
    public String name(){
        // getSimpleName() 返回不带包名的类名
        return getClass().getSimpleName();
    }

    Object process(Object input){
        //虚假处理
        return input;
    }
}

/**
 * 转大写
 */
class Upcase extends Processor {
    /**
     *
     * @param input
     * @return
     * @Descript
     *      重写父类的方法，父类的方法返回类型为 Object
     *      重写后，子类的返回的参数为 String。String 会默认向上转型（返回参数的协变）
     */
    @Override
    String process (Object input){
        /*
            . 的优先级比 () 高，所有在转换大小写时，需要先强转
         */
        return ((String)input).toUpperCase();
    }
}

/**
 * 转小写
 */
class DownCase extends Processor {
    @Override
    String process (Object input){
        /*
            . 的优先级比 () 高，所有在转换大小写时，需要先强转
         */
        return ((String)input).toLowerCase();
    }
}

/**
 * 按照空格分割
 */
class Splitter extends Processor {
    @Override
    String process (Object input){
        /*
            . 的优先级比 () 高，所有在转换大小写时，需要先强转
         */
        return Arrays.toString((((String)input)).split(" "));
    }
}
