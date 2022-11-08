package com.wit.wafon.designmodelstudy.m_d_prototype.case_prototype;

import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.Date;

/**
 * @author fengwang26
 * @date 2022/11/7 18:32
 * @describe
 *
 *      实现克隆
 *          通过序列化实现深度拷贝
 */
@Data
@ToString
public class WeekReport implements Cloneable, Serializable {

    private int id;
    //主题
    private String title;
    //员工姓名
    private String emp;
    //总结
    private String summary;
    //计划
    private String plan;
    //建议
    private String suggestion;
    //创建时间
    private Date create_time;

    /**
     * 实现克隆接口
     *      通过序列化和反序列化实现深拷贝
     *      文件写到了具体的盘，不适合部署在 linux 中
     * @return
     * @throws CloneNotSupportedException
     */
    /*@Override
    public Object clone() throws CloneNotSupportedException {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            OutputStream out = new FileOutputStream("E://a.txt");
            //通过对象流将对象存储
            oos = new ObjectOutputStream(out);
            *//**
             * 序列化时，对象的所有属性层级关系会被序列化自动处理
             *//*
            oos.writeObject(this);
            //读取文件，实现对象复制
            FileInputStream in = new FileInputStream("E://a.txt");
            ois = new ObjectInputStream(in);
            WeekReport ep = (WeekReport) ois.readObject();
            System.out.println("克隆对象地址是否相等："+(this==ep));
            return ep;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
           if(ois!=null){
               try {
                   ois.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if(oos!=null){
               try {
                   oos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
            try {
                boolean b = Files.deleteIfExists(Paths.get("E://a.txt"));
                System.out.println("临时文件删除 = " + b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 将文件序列化后卸载内存中
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //卸载内存中
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //通过对象流将对象存储
            oos = new ObjectOutputStream(out);
            /**
             * 序列化时，对象的所有属性层级关系会被序列化自动处理
             */
            oos.writeObject(this);
            //从内存中读取数据 - 序列化数据
            byte[] bb = out.toByteArray();
            //读取字节，实现对象复制
            ByteArrayInputStream in = new ByteArrayInputStream(bb);
            ois = new ObjectInputStream(in);
            WeekReport ep = (WeekReport) ois.readObject();
            System.out.println("克隆对象地址是否相等："+(this==ep));
            return ep;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
