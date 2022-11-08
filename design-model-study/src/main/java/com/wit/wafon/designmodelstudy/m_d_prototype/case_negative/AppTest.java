package com.wit.wafon.designmodelstudy.m_d_prototype.case_negative;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author fengwang26
 * @date 2022/11/7 18:18
 * @describe
 *
 *      OA周报的问题以及解决方案
 *         需   求：周报中很多重复信息，每周填写一样，如果每次都重新输入，导致用户体验比较差。
 *         解决方案：支持用户保存模板，每次进来加载一些预定义的内容；
 */
public class AppTest {

    public static void main(String[] args) {

        //第一周周报
        WeekReport wr1 = new WeekReport();
        wr1.setId(1);
        wr1.setEmp("王锋");
        wr1.setTitle("11月第1周个人周报");
        wr1.setSummary("学习了7大设计原则");
        wr1.setPlan("无");
        wr1.setSuggestion("无");
        wr1.setCreate_time(LocalDateTime.now());
        System.out.println(wr1.toString());

        //第二周周报
        WeekReport wr2 = new WeekReport();
        wr2.setId(2);
        wr2.setTitle("11月第2周个人周报");
        wr2.setEmp("王锋");
        wr2.setSummary("学习了工厂设计模式");
        wr2.setPlan("无");
        wr2.setSuggestion("无");
        wr2.setCreate_time(LocalDateTime.now());
        System.out.println(wr2.toString());

        /*
            问题说明：
                第二周周报表单与第一周周报表单内容修改的字段比较少。
            目的：
                只修改变动的字段，而不是每次新建一个对象来设置值
         */

    }

}

@Data
@ToString
class WeekReport{

    private int id;
    //主题
    private String title;
    //员工姓名
    private String emp;
    //总结
    private String summary;
    //计划
    private String plan;
    //建议
    private String suggestion;
    //创建时间
    private LocalDateTime create_time;

}
