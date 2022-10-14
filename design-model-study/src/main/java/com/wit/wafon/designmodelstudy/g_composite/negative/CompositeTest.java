package com.wit.wafon.designmodelstudy.g_composite.negative;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author fengwang26
 * @date 2022/10/14 15:26
 * @describe
 *
 *      需求：
 *          制作一个集合，要求集合记录曾经加过多少个元素。（不是统计当前集合的元素的个数）
 */
public class CompositeTest {

    public static void main(String[] args) {
        MySet mySet = new MySet();
        mySet.add("a");
        mySet.add("b");
        mySet.add("c");
        mySet.remove("c");
        mySet.remove("b");
        mySet.add("d");
        System.out.println(mySet.size() +"  --  "+mySet.getCount());
        mySet.addAll(Arrays.asList("e","f","g"));
        System.out.println(mySet.size() +"  --  "+mySet.getCount());

    }

}

/**
 * 方式一：
 *      继承 HashSet ，重写添加元素的方法。
 *      隐患1：目前 addAll 会调用add()，在以后可能会修改，那么当前逻辑会被修改
 *
 * 方法2：重写 addAll()，不在调用父类的 addAll()
 *      隐患2：如果在新的jdk版本中，新增了添加元素的方法，则统计失效；
 *      隐患3：重写了 add() 与 addAll()，原生的 HashSet 中其他的方法会依赖该方法，重写后可能会影响别的方法。
 *
 * 方法3：
 *      不再重写 add() 或者 addAll() 等方法，自己定义 add2() 和 addAll2() 替换原来的 add() addAll()
 *      隐患1：无法严格要求用户行为，调用错误后会导致统计错误。
 *      隐患2：明明可能与 jdk 未来版本重名导致方法重写。
 */
@Getter
class MySet extends HashSet {

    private int count = 0 ;

    @Override
    public boolean add(Object o) {
        count++;
        return super.add(o);
    }

    /**
     * 方法1
     */
    // @Override
    // public boolean addAll(Collection c) {
    //     count+=c.size();
    //     //父类的 addAll() 会调用 add() ，导致记录不准确
    //     return super.addAll(c);
    //
    // }

    /**
     * 方法2
     */
    @Override
    public boolean addAll(Collection c) {

        boolean result = false;
        for (Object o : c) {
            if(add(c)){
                //只要由一个添加成功了，即添加成功
                result = true;
            }
        }
        return result;
    }

    /**
     * 方法3
     */



}
