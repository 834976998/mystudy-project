package com.wit.wafon.designmodelstudy.m_l_composite.i;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/18 17:57
 * @describe
 *      部门 和 员工
 *          部门属性：name、description、List(用于存放员工和其他部门)
 *          员工属性：name、sal(月薪)
 *      部门可以添加子部门 和 员工
 *      而员工不可能有添加方法
 *
 *      使用组合模式刚好满足以上场景
 *
 *      1.获取月薪在 10000-1500 的员工
 *
 */
public class OaTest {

    public static void main(String[] args) {

        DepartmentComponent iflytek = new Department("科大讯飞", "");
        DepartmentComponent kxdigit = new Department("讯飞数码", "讯飞1级部门");
        iflytek.add(kxdigit);
        DepartmentComponent whyy = new Department("讯飞数码武研应用开发组", "讯飞数码1级部门");
        kxdigit.add(whyy);
        Dogsbody wf = new Dogsbody(1,"王锋", 10000);
        whyy.add(wf);
        DepartmentComponent whdata = new Department("讯飞数码武研数据组", "讯飞数码1级部门");
        kxdigit.add(whdata);
        Dogsbody zxj = new Dogsbody(2,"周新建", 17000);
        Dogsbody jfq = new Dogsbody(3,"姜发巧", 20000);
        whdata.add(zxj);
        whdata.add(jfq);

        DepartmentComponent d =  iflytek.getByNo(2);
        d.print("");

        System.out.println("===============");
        iflytek.print("");
    }

}

/**
 * 提出共性
 */
@Data
abstract class DepartmentComponent {
    /**
     * 名称
     */
    private String name;

    public DepartmentComponent (String name){
        this.name = name;
    }

    /*============================================== 部门方法 =============================================*/
    public void add(DepartmentComponent departmentComponent) {
        throw new UnsupportedOperationException("只有部门才有此权限");
    }

    public void remove(DepartmentComponent departmentComponent) {
        throw new UnsupportedOperationException("只有部门才有此权限");
    }
    public DepartmentComponent getByNo(int no) {
        throw new UnsupportedOperationException("只有部门才有此权限");
    }
    public String getDescription() {
        throw new UnsupportedOperationException("只有部门才有此权限");
    }
    public List<DepartmentComponent> getDcList() {
        throw new UnsupportedOperationException("只有部门才有此权限");
    }
    /*======================================================================================================*/

    /*============================================== 员工方法 =============================================*/
    public double getSal(){
        throw new UnsupportedOperationException("只有部门才有此方法");
    }
    public int getNo() {
        throw new UnsupportedOperationException("只有部门才有此方法");
    }
    /*======================================================================================================*/

    /**
     * 打印各自信息
     */
    public abstract void print(String prefix);

}

/**
 * 部门
 */
class Department extends DepartmentComponent {
    /**
     * 描述
     */
    private String description;
    /**
     * 用于存放员工和其他部门
     */
    private List<DepartmentComponent> dcList = new ArrayList<DepartmentComponent>();

    public Department (String name,String description){
        super(name);
        this.description = description;
    }

    /**
     * 添加方法部门或者员工
     * @return
     */
    @Override
    public void add(DepartmentComponent departmentComponent){
        dcList.add(departmentComponent);
    }

    /**
     * 删除某个部门或者员工
     * @return
     */
    @Override
    public void remove(DepartmentComponent departmentComponent){
        dcList.remove(departmentComponent);
    }

    @Override
    public DepartmentComponent getByNo(int no){
        Iterator<DepartmentComponent> iterator = dcList.iterator();
        while (iterator.hasNext()) {
            DepartmentComponent next = iterator.next();
            try {
                if(next.getNo()==no){
                    return next;
                }
            } catch (Exception e) {
                return getByNo(no);
            }
        }
        throw new RuntimeException("没有找到工号为："+no+"的员工。");
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<DepartmentComponent> getDcList() {
        return dcList;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix+"<<"+getName()+">>"+":"+description);
        Iterator<DepartmentComponent> iterator = dcList.iterator();
        while (iterator.hasNext()) {
            DepartmentComponent next = iterator.next();
            next.print(prefix+"\t");
        }
    }
}

/**
 * 打工人
 */
class Dogsbody extends DepartmentComponent {

    /**
     * 工号
     */
    private int no;

    /**
     * 员工薪资
     */
    private double sal;

    public Dogsbody(int no,String name,double sal) {
        super(name);
        this.no = no;
        this.sal = sal;
    }

    @Override
    public double getSal() {
        return sal;
    }
    @Override
    public int getNo() {
        return no;
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName()+" 的工资为："+sal);
    }

}
