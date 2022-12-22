package com.wit.wafon.designmodelstudy.m_m_observer.a;


/**
 * @author fengwang26
 * @date 2022/12/21 15:21
 * @describe
 *      观察者模式
 *
 *      场景：(如图 业务场景.png)
 *          游戏中，英雄角色存在血条hp和蓝条mp，有些面板中需要展示角色的血量和蓝量；
 *          当角色的血量和蓝量改变时，三个地方都需要同时变化；
 *
 *      解决：
 *          当怪兽攻击角色时候，血量降低，在角色的 setHp()中处理角色的血量
 *
 *      缺点：
 *          1.若要增加一个显示血量的面板，将血量与蓝量显示为柱状，需要在以前的代码中添加逻辑。-- 违反了开闭原则
 *          2.在 setHp() 中，处理了多种逻辑。-- 违反了单一职责原则
 *
 */
public class Test {
    public static void main(String[] args) {
        Role role = new Role();
        role.setName("鲁班");
        role.setHp(100);
        role.setMp(100);

        System.out.println("======受伤======");

        Monster monster = new Monster();
        monster.attack(role);
    }
}

/**
 * 角色
 */
class Role {
    private String name;
    private Integer hp;
    private Integer mp;

    /**
     * 设置血量
     * @param hp
     */
    public void setHp(Integer hp) {
        this.hp = hp;
        //修改血量后，需要变换3个地方的显示
        System.out.println("血条血量更新："+hp);
        System.out.println("球型血量更新："+hp);
        System.out.println("面板血量更新："+hp);
    }

    /**
     * 设置蓝量
     * @param mp
     */
    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getMp() {
        return mp;
    }
}

/**
 * 怪物
 */
class Monster {

    /**
     * 攻击力
     */
    public Integer power = 10;

    public void attack(Role role){
        role.setHp(role.getHp()-power);
    }
}


