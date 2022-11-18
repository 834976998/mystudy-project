package com.wit.wafon.designmodelstudy.m_k_iterator.b;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/17 14:46
 * @describe
 *      制作 ArrayList,LinkList,HashSet
 */
public class AppTest {
    public static void main(String[] args){
        testMyLinkedList();
    }

    public static void testMyLinkedList () {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("aa");
        myLinkedList.add("bb");
        myLinkedList.add("cc");
        myLinkedList.add("dd");
        System.out.println(myLinkedList.size());
        myLinkedList.remove("ff");
        System.out.println(myLinkedList.size());
        System.out.println(myLinkedList);

        try {
            for (int i = 0; i < myLinkedList.size(); i++) {
                System.out.println(myLinkedList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println(myLinkedList.get(9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test3 (){
        MyArrayList myArrayList = new MyArrayList(10);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        myArrayList.add(10);
        myArrayList.add(11);
        System.out.println("size = " + myArrayList.size());
    }

    public static void test2(){
        List<Object> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);

        System.out.println("size = " + arrayList.size());

        System.out.println("==================================");

        MyArrayList myArrayList = new MyArrayList(10);
        myArrayList.add1(1);
        myArrayList.add1(2);
        myArrayList.add1(3);
        myArrayList.add1(4);
        myArrayList.add1(5);
        myArrayList.add1(6);
        myArrayList.add1(7);
        myArrayList.add1(8);
        myArrayList.add1(9);
        myArrayList.add1(10);
        myArrayList.add1(11);

        /*
            add1()方法：
                数组默认长度为 10 ，不会自动扩容，超过10个元素后，会下标越界
         */
    }

    public static void test1(){

        List<Object> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        System.out.println("arrayList = " + arrayList);
        arrayList.remove(2);
        System.out.println("remove = " + arrayList);
        System.out.println("size = " + arrayList.size());

        System.out.println("==================================");

        MyArrayList myArrayList = new MyArrayList(10);
        myArrayList.add1(1);
        myArrayList.add1(2);
        myArrayList.add1(3);
        myArrayList.add1(4);
        myArrayList.add1(5);
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.remove(2);
        System.out.println("myRemove = " + myArrayList);
        System.out.println("mySize = " + myArrayList.size());
    }
}

