package com.wit.wafon.designmodelstudy.m_m_observer.d;

import java.util.ArrayList;
import java.util.List;

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
 *      为了解决 a 包中的问题，引入观察者模式
 *
 *      1.将面板抽象出来；
 *      2.将抽象出来的面板，去订阅角色的血量变化；
 *        在角色中添加存储观察者的容器，在血量变化时候通知相应的观察者；
 *        所有面板都会有一个方法，就是接收 hp 方法，因此将该方法上提为一个抽象类；
 *
 *     优点：
 *          1.新添加面板时，只需要新建面板，实现抽象接口即可。满足开闭原则；
 *     缺点：
 *          1.目前只是广播了 hp，如果需要广播 mp，需要修改源代码，违反了开闭原则；
 *          2.游戏业务变动较大，每次都会改动，比如添加了速度 sp 属性后，需要广播该属性，需要修改源代码；
 *
 *
 *     正对 b 包的缺点2问题，将接收参数改为 role 对象
 *
 *     优化：
 *          1.将抽线的 Observer 中的参数修改为具体的 Role ，使得每次传递的是整个 Role，所有的参数都会被通知；
 *     缺点：
 *          1.Observer是一个抽象的接口，参数却是一个具体的对象。这样 Observer 就只能观察 Role这一个特定的对象
 *            抽象依赖具体，违反了依赖倒置原则。
 *           上层不能依赖于下层，都应该依赖于抽象
 *
 *     针对以上（c包）问题，进行如下修改
 *
 *          1.将接口中的参数剔除，使得接口中的方法不依赖于任何对象；
 *          2.在每个面板中添加关联的对象，在初始化面板时赋值
 *
 */
public class ObserverTest {
    public static void main(String[] args) {
        Role role = new Role();
        role.setName("鲁班");
        role.setHp(100);
        role.setMp(100);


        Panel panel = new Panel(role);
        BallPanel ballPanel = new BallPanel(role);
        HeadPanel headPanel = new HeadPanel(role);

        //添加观察者
        role.addObserver(panel);
        role.addObserver(ballPanel);
        role.addObserver(headPanel);

        System.out.println("======受伤======");

        Monster monster = new Monster();
        monster.attack(role);
        monster.attack(role);

    }
}

// ==================================== 作者 ============================================

/**
 * 角色
 */
class Role {
    private String name;
    private Integer hp;
    private Integer mp;

    /**
     * 存放观察者集合
     */
    private List<Observer> observers = new ArrayList<>();

    /**
     * 添加观察者
     * @param object
     */
    public void addObserver(Observer object){
        observers.add(object);
    }

    /**
     * 删除观察者
     */
    public void removeObserver(Object object){
        observers.remove(object);
    }
    /**
     * 通知消息
     */
    public void notifyObserver(){
        observers.forEach(v->v.update());
    }

    /**
     * 设置血量
     * @param hp
     */
    public void setHp(Integer hp) {
        this.hp = hp;
        //通知观察者
        notifyObserver();
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

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mp=" + mp +
                ", observers=" + observers +
                '}';
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

/**
 * 上提共性方法
 */
interface Observer{
    public void update();
}

/**
 * 文字面板
 */
class Panel implements Observer{

    private Role role;

    public Panel (Role role){
        this.role = role;
    }

    @Override
    public void update() {
        System.out.println("Panel role = " + role);
    }
}

/**
 * 球形面板
 */
class BallPanel implements Observer{


    private Role role;

    public BallPanel (Role role){
        this.role = role;
    }

    @Override
    public void update() {
        System.out.println("BallPanel role = " + role);
    }
}

/**
 * 头顶血条
 */
class HeadPanel implements Observer{
    private Role role;

    public HeadPanel (Role role){
        this.role = role;
    }
    @Override
    public void update() {
        System.out.println("HeadPanel role = " + role);
    }
}