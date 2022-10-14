package com.wit.wafon.designmodelstudy.g_composite.positive;

import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fengwang26
 * @date 2022/10/14 16:42
 * @describe
 *       需求：
 *          制作一个集合，要求集合记录曾经加过多少个元素。（不是统计当前集合的元素的个数）
 *       解决方案：
 *          使自定义的Set与 HashSet 保持关联关系（组合），而不通过继承
 *          优点：（组合优于继承）
 *              1.组合方式完美实现需求。且没有继承关系后，无论 HashSet() 源码作何修改，都不影响自定义统计逻辑。
 *
 *          疑惑：
 *              1.难道不能继承吗？
 *                  使用多态，或者父类很少做改动可用，其余情况尽量是同组合
 *              2.难道不能重写方法吗？
 *          若父类父类的作者与子类作者不是同一个人，存在沟通问题，最好不进行继承。
 *              1.父类的作者不知道自己的哪个方法会被重写；
 *              2.子类的作者不知道父类会加哪些新的方法；
 *          当作者自己能控制父类与子类，可以重写！
 */
public class CompositePTest {
    public static void main(String[] args) {
        MySet2 mySet2 = new MySet2();
        mySet2.add("1");
        System.out.println(mySet2.getCount());

        mySet2.addAll(Arrays.asList("2","3"));
        System.out.println(mySet2.getCount());


    }
}

@Data
class MySet2 {

    private Set set = new HashSet();

    private int count = 0 ;

    public boolean add (Object e){
        count++;
        return set.add(e);
    }

    public boolean addAll(Collection c){
        count+=c.size();
        return set.addAll(c);
    }

}
