package com.wit.wafon.designmodelstudy.m_l_composite.b;

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
 *      为了解决 a 包中无法 3 级的菜单.
 *      进行如下修改
 *          在菜单子项中添加菜单子项集合。
 *          那么菜单子项与菜单项的结构完全一致
 *      缺点：
 *          菜单项与菜单子项代码两套结构完全一致。但是没有复用
 *      改造：
 *          将菜单项与菜单子项相同部分上提。
 *
 *          改造后：
 *              优点：
 *                  1.成功实现了多级菜单
 *              缺点：
 *                  1.破坏了菜单项结构。
 *                  2.菜单项应该是末端节点，而不应该还能添加。
 */
public class AppTest {

    public static void main(String[] args) {
        Menu menu = new Menu("陕菜", "老陕西爱吃的菜");
        MenuItem mi = new MenuItem("胡辣汤", "陕西、河南都爱吃，麻辣");
        MenuItem mi2 = new MenuItem("羊肉泡", "将羊肉泡一下哎");
        //第三级菜单 - 当前设计无法添加
        MenuItem mi2_1 = new MenuItem("优质羊肉泡", "加肉");
        MenuItem mi2_2 = new MenuItem("普通羊肉泡", "不加肉");
        mi2.add(mi2_1);
        mi2.add(mi2_2);

        MenuItem mi3 = new MenuItem("肉夹馍", "其实是馍夹肉，大口吃馍");
        menu.add(mi);
        menu.add(mi2);
        menu.add(mi3);
        menu.print("");
    }


}


/**
 * 菜单
 */
@Data
class Menu {
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 菜单项
     */
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu (String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * 添加菜单项
     */
    public void add(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    /**
     * 打印菜单
     * @Param prefix 打印前缀
     */
    public void print(String prefix){
        System.out.println(prefix+"<<"+name+">> - "+description);
        Iterator<MenuItem> it = menuItems.iterator();
        while (it.hasNext()){
            MenuItem next = it.next();
            next.print("\t"+prefix);
        }
    }

}

/**
 * 菜单项
 */
@Data
class MenuItem {
    /**
     * 菜单项名称
     */
    private String name;

    /**
     * 菜单项描述
     */
    private String description;

    public MenuItem (String name,String description){
        this.name = name;
        this.description = description;
    }

    /**
     * 菜单项集合
     */
    private List<MenuItem> menuItems = new ArrayList<>();

    /**
     * 打印菜单项
     */
    public void print (String prefix){

        System.out.println(prefix+"<<"+name+">> - "+description);
        Iterator<MenuItem> it = menuItems.iterator();
        while (it.hasNext()){
            MenuItem next = it.next();
            next.print("\t"+prefix);
        }
    }

    /**
     * 添加菜单项的子项
     * @param menuItem
     */
    public void add(MenuItem menuItem){
        menuItems.add(menuItem);
    }

}
