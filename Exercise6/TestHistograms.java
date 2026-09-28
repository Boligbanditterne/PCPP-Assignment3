// For week 6
// raup@itu.dk * 2026-09-23


package exercises06;

// Very likely you will need some imports here

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;




public class TestHistograms {
    // The imports above are just for convenience, feel free add or remove imports
    CasHistogram his;
    CasHistogram correctHis;
    Histogram1 hisNo;
    @BeforeEach
    public void initialize()
    {
        his = new CasHistogram(5000);
        correctHis = new CasHistogram(5000);
        hisNo = new Histogram1(5000);
        for(int i = 0; i < 5000; i++){
            correctHis.increment(countFactors(i));
        }
    }
    
    @RepeatedTest(100)
    public void factorsTest(){
        List<Thread> threads = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(5000 + 1);


        for(int i = 0; i < 5000; i++){
            int n = i;
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    his.increment(countFactors(n));

                } catch (Exception e) {
                }
               
            });
            threads.add(t);
            t.start();
        }

        try {
            barrier.await();
        } catch (Exception e) {
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                System.err.println(e);
            }
            
        }

        for (int i = 0; i<5000; i++){
            assertEquals(his.getCount(i),correctHis.getCount(i));
        }
    }
    @RepeatedTest(100) // can fail
    public void factorsTestHis(){
        List<Thread> threads = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(5000 + 1);


        for(int i = 0; i < 5000; i++){
            int n = i;
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    hisNo.increment(countFactors(n));


                } catch (Exception e) {
                }
            });
            threads.add(t);
            t.start();
        }

        try {
            barrier.await();
        } catch (Exception e) {
        }


        for (Thread t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                System.err.println(e);
            }
            
        }

        for (int i = 0; i<5000; i++){
            assertEquals(hisNo.getCount(i),correctHis.getCount(i));
        }
    }





    // Function to count the number of prime factors of a number `p`
    private static int countFactors(int p) {
        if (p < 2) return 0;
        int factorCount = 1, k = 2;
        while (p >= k * k) {
            if (p % k == 0) {
                factorCount++;
                p= p/k;
            } else
                k= k+1;
        }
        return factorCount;
    }

}
