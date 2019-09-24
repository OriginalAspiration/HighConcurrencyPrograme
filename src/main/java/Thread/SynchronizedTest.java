package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoulei
 * @version 1.0.0
 * @ClassName SynchronizedTest
 * @Description TODO
 * @createTime 2019年09月24日 21:29:00
 */
public class SynchronizedTest {
    public void syncCurrentObject() {
        synchronized (SynchronizedTest.class) {
            System.out.println(Thread.currentThread().getName() + "..start.." + "-----" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "..end.." + "-----" + System.currentTimeMillis());
        }

    }

    public static void syncCurrentObjectTest() {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        //  final GenerateCode gCode = new GenerateCode();

        for (int i = 0; i < 5; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    SynchronizedTest gCode = new SynchronizedTest();
                    gCode.syncCurrentObject();
                }
            });

        }
        exec.shutdown();
    }

    public static void main(String[] args) {
        syncCurrentObjectTest();
    }
}
