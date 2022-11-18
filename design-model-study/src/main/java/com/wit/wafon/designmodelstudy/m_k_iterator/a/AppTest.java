package com.wit.wafon.designmodelstudy.m_k_iterator.a;

import java.util.Arrays;

/**
 * @author fengwang26
 * @date 2022/11/17 14:41
 * @describe
 *
 *      数组的扩容
 */
public class AppTest {
    public static void main(String[] args) {
        int[] a = {11,22,33,44,55};
        System.out.println(Arrays.toString(a));

        a = Arrays.copyOf(a, 7);
        System.out.println(Arrays.toString(a));
    }
}
