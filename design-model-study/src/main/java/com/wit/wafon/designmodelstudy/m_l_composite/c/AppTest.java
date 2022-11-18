package com.wit.wafon.designmodelstudy.m_l_composite.c;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/11/18 11:10
 * @describe
 *
 *      树形结构
 *          *陕菜
 *              - 胡辣汤
 *                  -- 肉丸胡辣汤
 *                  -- 海带胡辣汤
 *              - 羊肉泡
 *                  -- 优质
 *                  -- 普通
 *                  -- 双份优质
 *              - 三秦套餐
 *           * 川菜
 *              -- 火锅
 *              -- 伤心凉粉
 *              -- 老妈兔头
 *           * 粤菜
 *              -- 鱼丸
 *              -- 牛丸
 *              -- 虾丸
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
 */
public class AppTest {

    public static void main(String[] args) {

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

        //菜单项
        MenuItem menuItem1 = new MenuItem("鱼香肉丝", "没有鱼。是一个具体的菜");

        //菜单添加菜单项
        menu.add(menuItem1);
        /*
            菜单添加子菜单
                目前无法通过菜单添加菜单，也就无法实现菜单的嵌套

         */
        // menu.add(smenu1);

        menu.print("");
    }


}

/**
 * 菜单项与菜单子项共性上提
 */
@Data
class MenuComponent {
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



}

/**
 * 菜单
 */
class Menu extends MenuComponent{

    /**
     * 子项
     */
    private List<MenuItem> menuItems = new ArrayList<>();


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
        Iterator<MenuItem> it = menuItems.iterator();
        while (it.hasNext()){
            MenuItem next = it.next();
            next.print("\t"+prefix);
        }
    }

    /**
     * 添加菜单项
     *      菜单与菜单项通用的方法
     */
    public void add (MenuItem menuItem){
        menuItems.add(menuItem);
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
        System.out.println(prefix+"<<"+getName()+">> - "+getDescription());
    }

}
