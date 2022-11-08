package com.wit.wafon.designmodelstudy.m_d_prototype.case_clone;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author fengwang26
 * @date 2022/11/7 18:32
 * @describe
 *
 *      实现克隆
 */
@Data
@ToString
public class WeekReport implements Cloneable{

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
    private Date create_time;

    /**
     * 实现克隆接口
     *      浅克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
