package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoulei
 * @version 1.0.0
 * @ClassName JoinTest
 * @Description TODO
 * @createTime 2019年09月25日 09:35:00
 */
public class JoinTest {

    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”
            t1.start();                     // 启动“线程t1”
            t1.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.printf("%s start\n", this.getName());

            // 延时操作
//            for(int i=0; i <1000000; i++)
//                ;
            try {
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("开始睡眠的时间:" + format.format(date));
                Thread.sleep(10000);
                date = new Date(System.currentTimeMillis());
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("结束睡眠的时间:" + format.format(date));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}
