package com.wit.wafon.designmodelstudy.m_template.b_template;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/11 9:55
 * @describe
 *
 *      模板模式。将共有的方法抽出，做成公共模板，将变化步骤作为抽象，由子类实现
 *      例如：
 *          1.泡茶的步骤
 *              tep1：烧水，水烧开
 *              tep2：茶叶放到杯子里
 *              tep3：向杯子中导入开水
 *              tep4：泡一段时间
 *              tep5：喝茶
 *          2.泡咖啡的步骤
 *              tep1：烧水，水烧开
 *              tep2：咖啡放到杯子里
 *              tep3：向杯子中导入开水
 *              tep4：泡一段时间
 *              tep5：喝咖啡
 */
public class TemplateAppTest {
    public static void main(String[] args) {

        /*
            注意：
                此处需要向上转型，调用模板的方法
         */
        Template template = new LinkListTest();
        template.template();

        Template template2 = new ArrayListTest();
        template2.template();

    }
}

/**
 * 测试类
 */
class ArrayListTest extends Template {

    @Override
    public void calcCode() {
        List<Integer> integerls = new ArrayList<>();
        for (int i =0;i<=100000;i++) {
            integerls.add(0,1);
        }
    }
}
class LinkListTest extends Template {

    @Override
    public void calcCode() {
        List<Integer> integerls = new LinkedList<>();
        for (int i =0;i<=100000;i++) {
            integerls.add(0,1);
        }
    }
}

// ================================= 作者 =================================
/**
 * 模板方法
 *      作者将不变的部分写好，变化的部分预留为 abstract 方法
 */
abstract class Template {
    public void template (){
        System.out.println("开始计时...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        calcCode();
        stopWatch.stop();
        System.out.println("执行完成，共耗时："+stopWatch.getTotalTimeSeconds()+"s");

    }

    /**
     * 留给需要测试的代码去实现
     */
    public abstract void calcCode();
}
