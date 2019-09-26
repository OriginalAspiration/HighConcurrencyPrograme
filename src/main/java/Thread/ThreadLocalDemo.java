package Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoulei
 * @version 1.0.0
 * @ClassName ThreadLocalDemo
 * @Description TODO
 * @createTime 2019年09月26日 21:08:00
 */
public class ThreadLocalDemo {
    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<List<String>>();

    public void setThreadLocal(List<String> values) {
        threadLocal.set(values);
    }
    public void getThreadLocal() {
       threadLocal.get().forEach(value-> System.out.println(value));
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        new Thread(()->{
            List<String> params = new ArrayList<>(3);
            params.add("张三");
            params.add("李四");
            params.add("王五");
            threadLocalDemo.setThreadLocal(params);
            threadLocalDemo.getThreadLocal();
        }).start();
        new Thread(()->{
            List<String> params = new ArrayList<>(2);
            params.add("Chinese");
            params.add("English");
            threadLocalDemo.setThreadLocal(params);
            threadLocalDemo.getThreadLocal();
        }).start();
    }
}
