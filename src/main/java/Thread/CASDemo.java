package Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoulei
 * @version 1.0.0
 * @ClassName CASDemo
 * @Description TODO
 * @createTime 2019年10月14日 15:02:00
 */
public class CASDemo {
    public static class AtomicCounter {
        private AtomicInteger atomicInteger = new AtomicInteger();

        public AtomicInteger getAtomicInteger() {
            return atomicInteger;
        }

        public void setAtomicInteger(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        public void atomicIntegerIncrement() {
            atomicInteger.incrementAndGet();
        }

        public void atomicIntegerDecrement() {
            atomicInteger.decrementAndGet();
        }
    }

    public static class AtomicProducer extends Thread {

        private AtomicCounter atomicCounter;

        public AtomicProducer(AtomicCounter atomicCounter) {
            this.atomicCounter = atomicCounter;
        }

        @Override
        public void run() {
            for (int j = 0; j < AtomicTest.LOOP; j++) {
                System.out.println("producer : " + atomicCounter.getAtomicInteger());
                atomicCounter.atomicIntegerIncrement();
            }
        }
    }

    public static class AtomicConsumer extends Thread {

        private AtomicCounter atomicCounter;

        public AtomicConsumer(AtomicCounter atomicCounter) {
            this.atomicCounter = atomicCounter;
        }

        @Override
        public void run() {
            for (int j = 0; j < AtomicTest.LOOP; j++) {
                System.out.println("consumer : " + atomicCounter.getAtomicInteger());
                atomicCounter.atomicIntegerDecrement();
            }
        }
    }

    public static class AtomicTest {

        final static int LOOP = 10000;

        public static void main(String[] args) throws InterruptedException {

            AtomicCounter counter = new AtomicCounter();
            AtomicProducer producer = new AtomicProducer(counter);
            producer.setName("producer");
            AtomicConsumer consumer = new AtomicConsumer(counter);
            consumer.setName("consumer");
            producer.start();
            consumer.start();

            producer.join();
//            System.out.println(Thread.currentThread().getName());
            consumer.join();
//            System.out.println(Thread.currentThread().getName());
            System.out.println(counter.getAtomicInteger());

        }
    }
}
