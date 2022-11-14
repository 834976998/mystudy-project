package com.wit.wafon.designmodelstudy.a_singleresponsibility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author fengwang26
 * @date 2022/11/11 14:17
 * @describe
 *
 *      统计文件中单词出现的次数
 *
 *      单一职责：
 *          代码需要在同一个抽象级别
 */
public class AppTest2 {

    public static void main(String[] args) {
        //读取文件
        String s = loadFile("design-model-study/src/main/resources/template/test.txt");
        //获取单词
        String[] words = parseWords(s);
        //统计单词出现次数
        Map<String,Integer>  countWordsMap = countWords(words);
        countWordsMap.forEach((k,v)-> System.out.println(k+":"+v));
        //获取最大单词数key集合
        List<String>  maxValueKeys = getMaxValue(countWordsMap);
        System.out.println("maxValue = " + maxValueKeys);
    }


    /**
     * 获取Map中最大Value对应的Key值
     * @return  key
     */
    public static List<String> getMaxValue(Map<String,Integer> map){
        //获取最大的 value
        Collection<Integer> values = map.values();
        Integer max = Collections.max(values);
        //通过 value 获取 key
        List<String> list = new ArrayList<>();
        map.forEach((k,v)->{
            if(max.equals(v)){
                list.add(k);
            }
        });
        return list;
    }

    public static Map<String,Integer> countWords(String[] words){
        Map<String,Integer> countWordsMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(countWordsMap.containsKey(word)){
                countWordsMap.put(word,countWordsMap.get(word)+1);
            }else{
                countWordsMap.put(word,1);
            }
        }
        return countWordsMap;
    }

    /**
     * 解析单词
     * @param str
     * @return
     */
    public static String[] parseWords(String str){
        System.out.println("解析单词");
        String[] word = str.split("[^a-zA-Z]+");
        return word;
    }

    /**
     * 将文件中的内容读取为字符串
     * @param path
     * @return
     */
    public static String loadFile (String path){
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return sb.toString();
    }

}
