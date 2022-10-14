package com.wit.wafon.designmodelstudy.e_demeter.supplement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/9/28 15:58
 * @describe
 */
public class AppTest {

    private List list = new ArrayList<>();

    /**
     * 朋友：
     * String：返回值
     * Integer：参数
     * date : 方法内参数
     * list：类里面的参数
     *
     * 非朋友：（朋友的朋友不是朋友）
     *      Bar ：由朋友 Foo 简介创造
     */
    public String f1(Integer i){
        Date date = new Date();
        Foo foo = new Foo();
        Bar bar = foo.get();
        return "";
    }

}

class Foo {
    public Bar get(){
        return new Bar();
    }
}

class Bar{

}
