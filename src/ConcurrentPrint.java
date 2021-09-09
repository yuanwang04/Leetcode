import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentPrint extends Thread {

    static Counter counter;
    static int total;
    static int round;

    private char content;
    private int offset;

    public static void main(String[] args) {
        counter = new Counter();
        total = 3;
        round = 100;

        new ConcurrentPrint('a', 0).start();
        new ConcurrentPrint('b', 1).start();
        new ConcurrentPrint('c', 2).start();

    }

    public ConcurrentPrint(char content, int offset) {
        this.content = content;
        this.offset = offset;
    }


    public static class Counter {
        public int n;
        public Lock lock;

        public Counter() {
            n = 0;
            lock = new ReentrantLock();
        }
    }


    public void run() {
        while (true) {
            try {
                counter.lock.lock();
                if (counter.n >= round * total) {
                    break;
                }
                if (counter.n % total == offset) {
                    System.out.print(content);
                    counter.n++;
                }
            } finally {
                counter.lock.unlock();
            }
        }
    }
}
