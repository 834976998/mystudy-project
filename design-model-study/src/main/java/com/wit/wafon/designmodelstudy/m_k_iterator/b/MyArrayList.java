package com.wit.wafon.designmodelstudy.m_k_iterator.b;

import java.util.Arrays;

/**
 * 自定义 ArrayList
 */
public class MyArrayList {
    /**
     * 对象集合
     */
    private Object[] arr = null;
    /**
     * 下标
     */
    private int index;

    /**
     * 默认容量为 10
     */
    public MyArrayList() {
        this(10);
    }

    /**
     * 自定义容量
     *
     * @param capacity
     */
    public MyArrayList(int capacity) {
        arr = new Object[capacity];
    }

    /**
     * 添加元素
     * 不会自动扩容，有下标越界风险
     *
     * @param obj 待添加的元素
     */
    public void add1(Object obj) {
        arr[index++] = obj;
    }

    public void add(Object obj) {
        //判断是否越界
        ensureCapacityInternal();
        arr[index++] = obj;
    }

    /**
     * 判断是否越界
     * 扩容
     */
    private void ensureCapacityInternal() {
        if (index == arr.length) {
            //扩容 - 每次扩容为当前容量的 50%
            arr = Arrays.copyOf(arr, arr.length * 3 / 2);
        }
    }

    /**
     * 按照下标取值
     *
     * @param index 下标
     * @return
     */
    public Object get(int index) {
        if (arr.length < index) {
            return null;
        }
        return arr[index];
    }

    /**
     * 按照下标移除元素
     *
     * @param index
     */
    public void remove(int index) {
        // 删除元素
        if (arr.length < index) {
            return;
        }
        /* 需要将index后的元素向前移动覆盖 */
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = null;

    }

    public int size() {
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * 打印
     *
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                sb.append(arr[i]).append(", ");
            }
        }
        //按照下标 - 删掉后最后两位 “, ”
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
