package com.wit.wafon.designmodelstudy.a_singleresponsibility;

import java.io.*;

/**
 * @author fengwang26
 * @date 2022/7/31 16:25
 * @describe
 *      统计文本种的字符个数
 */
public class AppTest {

    private static final String path1 = "design-model-study/src/main/resources/singleresponsibility/1.text";
    private static final String path2 = "design-model-study/src/main/resources/singleresponsibility/2.text";

    public static void main(String[] args) throws Exception {
        test2();
    }

    /**
     * 通过字节流读文件 - 字节流不查询码表
     */
    public static void test2() throws Exception{
        InputStream is = new FileInputStream(path2);
        int read = is.read();
        System.out.println("read = " + read);
        read = is.read();
        System.out.println("read = " + read);


        is.close();
    }

    /**
     * 通过字符流读取文件
     */
    public static void test1(){
        Reader in = null;
        try {
            /*
                FileReader字符流 - 查询码表与操作系统一致（中文操作系统默认GBK码表）
                    - GBK 码表一个汉字占两个字节，且都是以1开头。通过字符流读取会判断为1开头，一次读取两个字节
                    - utf-8 码表中一个中文字符占3个字节，一次读取一个字节。
             */
            in = new FileReader(path1);
            int n ;
            int count = 0;
            while((n=in.read())!=-1){
                count++;
            }
            System.out.println("文本有 " + count +" 个字符");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
