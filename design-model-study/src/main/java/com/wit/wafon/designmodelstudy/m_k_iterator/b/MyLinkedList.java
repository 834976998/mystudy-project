package com.wit.wafon.designmodelstudy.m_k_iterator.b;

import lombok.Data;

/**
 * @author fengwang26
 * @date 2022/11/17 16:58
 * @describe
 *      自定义 LinkedList
 */
public class MyLinkedList {

    @Data
    class Node {
        Object obj;
        //自关联
        Node next;
    }

    /**
     * 头节点
     */
    private Node head;

    /**
     * 末端节点
     */
    private Node tail;

    public MyLinkedList (){

    }

    /**
     * 添加
     * @param obj
     */
    public void add (Object obj){
        Node node = new Node();
        node.obj = obj;
        //只有添加第一个元素时，头节点赋值
        if(head == null){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            tail = node;
        }

    }

    /**
     * 查询链表大小
     * @return
     */
    public int size(){
        int size = 0;
        Node temp = head;
        if(temp != null){
            size = 1;
        }
        while (temp.next!=null) {
            size ++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 删除元素
     */
    public void remove (Object ojb) {

        //删除的是第一个元素
        if(ojb.equals(head.obj)){
            head.obj = null;
            //第二个元素变为第一个
            head = head.next;
            return ;
        }
        //删除的元素不是第一个
        Node temp = head.next;
        //记录当前位置指针
        Node prepointer = head;
        while(temp!=null && !temp.obj.equals(ojb)){
            prepointer = temp;
            temp = temp.next;
            //删除一个不存在的元素，整个函数结束
            if(temp==null){
                return ;
            }
        }
        prepointer.next = temp.next;
        //将删除项末端置空
        temp.next = null;
    }

    /**
     * 获取元素
     * @return
     */
    public Object get(int index) throws Exception {
        if(index<0){
            throw new RuntimeException("非法参数");
        }
        if(index>size()-1){
            throw new RuntimeException("数组下标越界");
        }
        Node temp = head;
        int i=0;
        while(i < index ){
            temp = temp.next;
            i++;
        }
        return temp.obj;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node temp = head;
        while(temp!=null){
            sb.append(temp.obj).append(", ");
            temp = temp.next;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }



}
