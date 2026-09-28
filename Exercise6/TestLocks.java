// For week 6
// raup@itu.dk * 2026-09-23

package exercises06;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

public class TestLocks {
    // The imports above are just for convenience, feel free add or remove imports

    @BeforeEach
    public void initialize()
    {

    }

        
    @RepeatedTest(1)
    public void unit1(){
        ReadWriteCASLock r = new ReadWriteCASLock();

            Thread t = new Thread(() -> {
                r.writerTryLock();

                assertEquals(false, r.readerTryLock());
            });
        
    }

    @RepeatedTest(1)
    public void unit2(){
        ReadWriteCASLock r = new ReadWriteCASLock();

            Thread t = new Thread(() -> {
                r.readerTryLock();

                assertEquals(false, r.writerTryLock());
            });
        
    }

    @RepeatedTest(1)
    public void unit3(){
        ReadWriteCASLock r = new ReadWriteCASLock();

            Thread t = new Thread(() -> {
                assertThrows(Exception.class,() -> r.readerUnlock()); 

                assertThrows(Exception.class,() -> r.writerUnlock());      
            });

        
    }

    @RepeatedTest(100)
    public void parallel(){
        ReadWriteCASLock r = new ReadWriteCASLock();
        CyclicBarrier barrier = new CyclicBarrier(1000 + 1);
        AtomicInteger totalViolations = new AtomicInteger();
        AtomicInteger activeWriters = new AtomicInteger();
        Thread[] threads = new Thread[1000];



        for(int i  = 0; i < 1000; i++){
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    if(r.writerTryLock()){
                    
                        
                        if(activeWriters.incrementAndGet() > 1){
                            totalViolations.incrementAndGet();
                        }

                        activeWriters.decrementAndGet();
                        r.writerUnlock();
                    }

                } catch (Exception e) {

                }
                
            });
            threads[i] = t;
            t.start();

        }

        try {
            barrier.await();
        } catch (Exception e) {
        }

       for(Thread t : threads){
            try {
                t.join();
            } catch (Exception e) {
            }
            
       }
        
        assertEquals(0, totalViolations.get());
        
    }



}
