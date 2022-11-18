package com.wit.wafon.designmodelstudy.m_l_composite.d;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/18 11:10
 * @describe
 *
 *      树形结构
 *          <<陕菜>>
 *              * 蚂蚁上树
 *              <<胡辣汤>>
 *                  * 肉丸胡辣汤
 *                  * 海带胡辣汤
 *              <<羊肉泡>>
 *                  * 优质
 *                  * 普通
 *                  * 双份优质
 *              <<三秦套餐>>
 *           << 川菜>>
 *              * 火锅
 *              * 伤心凉粉
 *              * 老妈兔头
 *           << 粤菜>>
 *              * 鱼丸
 *              * 牛丸
 *              * 虾丸
 *
 *      但凡需要制作树形结构，就可以使用组合模式
 *
 *      为了解决 a 包中
 *      缺点：
 *          1.菜单只能添加菜单项，不能添加其他菜单；
 *          2.菜单项不能添加其他菜单项；
 *      需要重构代码：
 *          1.菜单可以添加子菜单、菜单项
 *          2.菜单项不能再添加菜单项（菜单项是树的末端节点）
 *
 *      设计成嵌套菜单
 *
 *      优点：
 *          1.能够实现多级嵌套菜单了
 *          2.代码有重用性
 *
 *      变化：
 *          1.现在素食主义者比较多，餐厅为了适应变化，需要添加了一键打印素食菜单项功能
 *          2.需要将每道菜的价格打印出来
 */
public class AppTest {

    public static void main(String[] args) {

        testMenu2();
    }

    private static void testMenu2(){
        Menu menu = new Menu("老江湖", "");
        Menu smenu1 = new Menu("陕菜", "");
        Menu smenu2 = new Menu("川菜", "");
        Menu smenu3 = new Menu("粤菜", "");
        menu.add(smenu1);
        menu.add(smenu2);
        menu.add(smenu3);

        /**
         * 陕菜
         */
        Menu smenu1_m1 = new Menu("胡辣汤", "可以分为很多种胡辣汤，是一个大项即子菜单");
        smenu1.add(smenu1_m1);
        //胡辣汤子菜单项
        MenuItem smenu1_m1_1 = new MenuItem("肉丸胡辣汤", "加肉丸。是一个具体的菜");
        MenuItem smenu1_m1_2 = new MenuItem("海带胡辣汤", "不加肉");
        smenu1_m1.add(smenu1_m1_1);
        smenu1_m1.add(smenu1_m1_2);

        Menu smenu1_m2 = new Menu("羊肉泡馍", "大秦发明");
        smenu1.add(smenu1_m2);
        Menu smenu1_m2_1 = new Menu("优质", "配料丰富");
        smenu1_m2.add(smenu1_m2_1);
        MenuItem smenu1_m2_1_i1 = new MenuItem("鸡蛋羊肉", "加1块钱");
        MenuItem smenu1_m2_1_i2 = new MenuItem("肉末羊肉", "加5块钱");
        smenu1_m2_1.add(smenu1_m2_1_i1);
        smenu1_m2_1.add(smenu1_m2_1_i2);

        /**
         * 川菜
         */

        MenuItem smenu2_im1 = new MenuItem("老妈兔头", "没有牙齿");
        MenuItem smenu2_im2 = new MenuItem("凉拌贡菜", "素食主义者偏爱");
        smenu2.add(smenu2_im1);
        smenu2.add(smenu2_im2);

        Menu smenu2_m1 = new Menu("牛油火锅", "地沟油");
        smenu2.add(smenu2_m1);
        Menu smenu2_m1_1 = new Menu("九宫格","九个格子，其实是一锅汤");
        smenu2_m1.add(smenu2_m1_1);
        MenuItem smenu2_m1_1_1 = new MenuItem("特辣", "致命");
        MenuItem smenu2_m1_1_2 = new MenuItem("中辣", "重伤");
        MenuItem smenu2_m1_1_3 = new MenuItem("微辣", "轻伤");
        MenuItem smenu2_m1_1_4 = new MenuItem("咪咪辣", "友好");
        smenu2_m1_1.add(smenu2_m1_1_1);
        smenu2_m1_1.add(smenu2_m1_1_2);
        smenu2_m1_1.add(smenu2_m1_1_3);
        smenu2_m1_1.add(smenu2_m1_1_4);

        menu.print("");

    }

    private static void testMenu1() {
    /*
        菜单示意图
            <<陕菜>>
                * 鱼香肉丝
                <<胡辣汤>>
                    * 肉丸胡辣汤
                    * 海带胡辣汤
     */

        //菜单
        Menu menu = new Menu("陕菜", "老陕西爱吃的菜");
        //子菜单
        Menu smenu1 = new Menu("胡辣汤", "可以分为很多种胡辣汤，是一个大项即子菜单");
        //胡辣汤子菜单项
        MenuItem smenu1_1 = new MenuItem("肉丸胡辣汤", "加肉丸。是一个具体的菜");
        MenuItem smenu1_2 = new MenuItem("海带胡辣汤", "不加肉");
        smenu1.add(smenu1_1);
        smenu1.add(smenu1_2);
        menu.add(smenu1);

        //菜单项
        MenuItem menuItem1 = new MenuItem("鱼香肉丝", "没有鱼。是一个具体的菜");
        menu.add(menuItem1);

        //3级
        Menu smenu2 = new Menu("羊肉泡馍", "大秦发明");
        menu.add(smenu2);
        Menu smenu2_1 = new Menu("优质", "配料丰富");
        smenu2.add(smenu2_1);
        MenuItem menu1_1_i1 = new MenuItem("鸡蛋羊肉", "加1块钱");
        MenuItem menu1_1_i2 = new MenuItem("肉末羊肉", "加5块钱");
        smenu2_1.add(menu1_1_i1);
        smenu2_1.add(menu1_1_i2);

        menu.print("");
    }


}

/**
 * 菜单项与菜单子项共性上提
 */
@Data
abstract class MenuComponent {
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    public MenuComponent ( String name,String description){
        this.name = name;
        this.description = description;
    }

    /**
     * 打印方法
     *      菜单与菜单项的具体实现过程不一致
     *      保障向上转型时执行子类的具体实现过程
     * @param prefix
     */
    public abstract void print(String prefix);

}

/**
 * 菜单
 */
class Menu extends MenuComponent{

    /**
     * 子项
     */
    private List<MenuComponent> menuItems = new ArrayList<>();


    /**
     * 通过子类适配父类有参构造器
     */
    public Menu (String name,String description){
        super(name,description);
    }

    /**
     * 打印菜单
     * @Param prefix 打印前缀
     */
    public void print(String prefix){
        System.out.println(prefix+"<<"+getName()+">> - "+getDescription());
        Iterator<MenuComponent> it = menuItems.iterator();
        while (it.hasNext()){
            //将菜单与菜单项分别打印
            MenuComponent mc = it.next();
            mc.print(prefix+"\t");
        }
    }

    /**
     * 添加菜单、菜单项
     *      添加菜单、菜单项通用的方法，因此通过父类来实现兼容
     */
    public void add (MenuComponent mc){
        menuItems.add(mc);
    }

}

/**
 * 菜单项
 */
class MenuItem extends MenuComponent{

    public MenuItem (String name,String description){
        super(name,description);
    }
    /**
     * 打印菜单项
     */
    public void print (String prefix){
        System.out.println(prefix+"* "+getName()+" : "+getDescription());
    }

}
