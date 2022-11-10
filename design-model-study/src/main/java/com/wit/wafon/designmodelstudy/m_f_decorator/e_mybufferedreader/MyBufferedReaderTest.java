package com.wit.wafon.designmodelstudy.m_f_decorator.e_mybufferedreader;

import lombok.Getter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author fengwang26
 * @date 2022/11/10 15:55
 * @describe
 *      自定义 BufferedReader
 *      通过装饰器模式自定义BufferedReader
 */
public class MyBufferedReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("E://1.txt");
        //使用
        MyBufferedReader mbr = new MyBufferedReader(fileReader);
        String s;
        while((s=mbr.readLine())!=null){
            System.out.println(s);
        }

        //带行号读取
        System.out.println("===========  带行号读取  ========== ");
        FileReader fileReader2 = new FileReader("E://1.txt");
        MyLineNumber mlr = new MyLineNumber(fileReader2);
        String line;
        while((line=mlr.readLine())!=null){
            System.out.println(mlr.getLineNumber() +":"+ line);
        }
        mlr.close();

    }

}

/**
 * 自定义读取 - 拓展装饰器
 */
class MyBufferedReader extends Reader {

    //关联 reader
    private Reader in;

    public MyBufferedReader (Reader reader){
        this.in = reader;
    }

    /**
     * 自定义读取行
     * @return
     */
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (true){
            n = in.read();
            //记事本中的换行符为 “\r\n”
            if(n=='\r'){
                continue;
            }
            //文件结尾时  n=-1
            if(n=='\n' || n == -1){
                break;
            }
            sb.append((char) n);
        }
        //文件读到末尾读不到空行，返回 null ,否则返回空串
        if(sb.toString().length()==0 && n==-1){
            return null;
        }
        return sb.toString();
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}

/**
 * 装饰器增强2
 * 统计文件行数
 */
@Getter
class MyLineNumber extends MyBufferedReader {

    private int lineNumber;

    /**
     * 继承会默认调用父类的无参构造器
     * 如果父类没有无参构造器，就需要调用父类的有参构造器
     */
    public MyLineNumber (Reader reader){
        super(reader);
    }

    @Override
    public String readLine() throws IOException {
        String s = super.readLine();
        if( s!=null ){
            lineNumber ++;
        }
        return s;
    }
}
