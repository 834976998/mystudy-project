package com.wit.wafon.designmodelstudy.m_j_proxy.j;

/**
 * @author fengwang26
 * @date 2022/11/16 18:39
 * @describe
 */


public class ProxyTest {
    public static void main(String[] args) {
        /**
         * 测试获取书本词语
         * 获取 3次，执行了 3 次
         * 预期：
         *      只执行一次，查到结果后缓存。下次查询直接获取结果
         * 优化：
         *      使用代理
         */
        BookParser bookParser = new BookParser();
        bookParser.numberOfVerb();
        bookParser.numberOfVerb();


        System.out.println("==== 通过代理对象调用 ====");

        BookParserProxy bookParserProxy = new BookParserProxy();
        bookParserProxy.numberOfVerb();
        bookParserProxy.numberOfVerb();

    }


}

/**
 * 静态代理：自己手写的代码，写死的代理类（代理目标）
 *      代理对象必须与目标对象有相同的方法
 *      目标对象没有实现接口。因此，通过继承，使得代理对象与目标对象拥有相同的接口
 */
class BookParserProxy extends BookParser {

    /**
     * 句子数量
     */
    private Integer numberOfSentence;

    /**
     * 词语数量
     */
    private Integer numberOfVerb;

    /**
     * 字数
     */
    private Integer numberOfWords;


    @Override
    public int numberOfSentence() {
        //判断是否已经赋值，没赋值则计算，已经计算过直接返回
        if(numberOfSentence == null ){
            numberOfSentence = super.numberOfSentence();
        }
        return numberOfSentence;
    }

    @Override
    public int numberOfVerb() {
        if(numberOfVerb==null){
            numberOfVerb = super.numberOfVerb();
        }
        return numberOfVerb;
    }

    @Override
    public int numberOfWords() {
        if(numberOfWords==null){
            numberOfWords = super.numberOfWords();
        }
        return numberOfWords;
    }
}


/**
 * 图书解析器
 */
class BookParser {
    //接收一整本的内容，字符串非常大
    private String content = "《三国演义》天下大事，合久必分，分久必合...三国归晋";

    /**
     * 获取句数量
     *
     * @return
     */
    public int numberOfSentence() {
        //每次解析，都有很高的执行代价
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i= content.split("[.!?]").length;
        System.out.println("有 " + i + "个句子");
        return i;
    }

    /**
     * 获取词语数量
     *
     * @return
     */
    public int numberOfVerb() {
        //假装执行，使用分词器
        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfVerb = 10098;
        System.out.println("有 " + numberOfVerb + "个句子");
        return numberOfVerb;
    }

    /**
     * 字数
     * @return
     */
    public int numberOfWords() {
        //假装执行，使用分词器
        try {
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfWords = 200000;
        System.out.println("有 " + numberOfWords + "个句子");
        return numberOfWords;
    }

}
