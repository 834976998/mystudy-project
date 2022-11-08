package com.wit.wafon.designmodelstudy.m_d_prototype.case_deepclone;

import java.util.Date;

/**
 * @author fengwang26
 * @date 2022/11/7 18:18
 * @describe
 *
 *      OA周报的问题以及解决方案
 *         需   求：周报中很多重复信息，每周填写一样，如果每次都重新输入，导致用户体验比较差。
 *         解决方案：支持用户保存模板，每次进来加载一些预定义的内容；
 */
public class AppPrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        //第一周周报
        WeekReport wr1 = new WeekReport();
        wr1.setId(1);
        wr1.setEmp("王锋");
        wr1.setTitle("11月第1周个人周报");
        wr1.setSummary("学习了7大设计原则");
        wr1.setPlan("无");
        wr1.setSuggestion("无");
        wr1.setCreate_time(new Date());
        System.out.println(wr1.toString());

        //第二周周报
        WeekReport wr2 = (WeekReport) wr1.clone();
        System.out.println("克隆后 wr2 = " + wr2);
        wr2.setId(2);
        wr2.setTitle("11月第2周个人周报");
        wr2.setEmp("王锋");
        wr2.setSummary("学习了工厂设计模式");
        wr2.setPlan("无");
        wr2.setSuggestion("无");
        wr2.getCreate_time().setTime(0);

        System.out.println( "wr1="+wr1);
        System.out.println("wr2="+wr2);


    }

}

