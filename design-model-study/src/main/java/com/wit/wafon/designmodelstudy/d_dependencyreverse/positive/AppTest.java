package com.wit.wafon.designmodelstudy.d_dependencyreverse.positive;

/**
 * @author fengwang26
 * @date 2022/9/18 17:29
 * @describe
 *      依赖倒置原则
 *      我们希望，添加下层的动物时，上层人中的代码不用修改
 *      需要抽象出 eat 方法
 */
//=================================== 作者 =========================================
interface Animal{
       void eat();
}

class Person{
    //依赖具体的狗，无法实现扩展
    public void feed(Animal animal){
        System.out.println("开始喂养...");
        animal.eat();
    }
}

class Dog implements Animal{
    public void eat(){
        System.out.println("啃骨头");
    }
}

//==================================== 20年后用户 ========================================

public class AppTest {
    public static void main(String[] args) {
        //上层
        Person person = new Person();
        //下层
        Dog dog = new Dog();
        person.feed(dog);

        Cat cat = new Cat();
        /**
         * 源码中没有该实现，需要重载eat方法，违反依赖倒置原则，没加一个动物都需要重载一下源码中的方法
         */
        person.feed(cat);
    }


}

/*
        新的需求，用户需要喂猫
        用户自定义一个猫

*/
class Cat implements Animal{
    public void eat(){
        System.out.println("吃鱼");
    }
}