package com.wit.wafon.designmodelstudy.m_g_template.a;


import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/11 9:31
 * @describe
 *
 *      需求：
 *          测试 ArrayList 和 LinkedList 的增加、查询效率
 *          示例中，計算耗時的代碼是固定的，而只有鏈表在變化。
 *          缺点：
 *              测试耗时代码没有重用性
 */
public class AppTest {
    private List<Integer> integers;

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> integers = new ArrayList<>();
        for (int i =0;i<=100000;i++) {
            integers.add(0,1);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime());
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        List<Integer> integerls = new LinkedList<>();
        for (int i =0;i<=100000;i++) {
            integerls.add(0,1);
        }
        stopWatch2.stop();
        System.out.println(stopWatch2.getTime());

    }
}
