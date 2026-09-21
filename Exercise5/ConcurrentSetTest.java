package exercises05;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
// TODO: Very likely you need to expand the list of imports

public class ConcurrentSetTest {

    // Variable with set under test
    private ConcurrentIntegerSet set;
    private SemaphoreImp sem;
    int nrThreads = 24;

    // TODO: Very likely you should add more variables here


    // Uncomment the appropriate line below to choose the class to
    // test
    // Remember that @BeforeEach is executed before each test
    @BeforeEach
    public void initialize() {
        // init set
        set = new ConcurrentIntegerSetBuggy();
        // set = new ConcurrentIntegerSetSync();
        // set = new ConcurrentIntegerSetLibrary();
        sem = new SemaphoreImp(2);
    }

    @RepeatedTest(1) // set to 5000
    public void addTest() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(nrThreads + 1);
        AtomicInteger trues = new AtomicInteger();

        for (int i = 0; i < nrThreads; i++) {
            new Thread(() -> {
                try {
                    barrier.await(); // waits for all
                    if (set.add(1)) {
                        trues.incrementAndGet();
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                }

            }).start();
        }

        barrier.await();
        barrier.await();
        assertEquals(1, trues.get());
    }

    @RepeatedTest(1) // set to 5000
    public void removeTest() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(nrThreads + 1);
        AtomicInteger trues = new AtomicInteger();
        set.add(1);

        for (int i = 0; i < nrThreads; i++) {
            new Thread(() -> {
                try {
                    barrier.await(); // waits for all
                    set.remove(1);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                }

            }).start();
        }

        barrier.await();
        barrier.await();
        assertEquals(0, set.size());
    }

    @RepeatedTest(100) // if this passes, then 3 locks have been aquired. 
    public void SemTest() throws Exception {
        AtomicInteger trues = new AtomicInteger();

        sem.release();

        Thread t1 = new Thread(() -> {
                try {
                    sem.acquire();
                    trues.incrementAndGet();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                    

                });

        Thread t2 = new Thread(() -> {
                try {
                    sem.acquire();
                    trues.incrementAndGet();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                    

                });

        Thread t3 = new Thread(() -> {
                try {
                    sem.acquire();
                    trues.incrementAndGet();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                    

                });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        assertEquals(3, trues.get());
    }   
}
