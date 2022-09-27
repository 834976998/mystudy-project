package com.wit.wafon.designmodelstudy.d_dependencyreverse.negetive;

/**
 * @author fengwang26
 * @date 2022/9/18 17:29
 * @describe
 *      依赖倒置原则
 */
//=================================== 作者 =========================================
class Person{
    //依赖具体的狗，无法实现扩展
    public void feed(Dog dog){
        System.out.println("开始喂养...");
        dog.eat();
    }
    public void feed(Cat cat){
        System.out.println("开始喂养...");
        cat.eat();
    }
}

class Dog{
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
class Cat{
    public void eat(){
        System.out.println("吃鱼");
    }
}