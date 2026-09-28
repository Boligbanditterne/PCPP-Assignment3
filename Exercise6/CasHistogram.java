package exercises06;

import java.util.concurrent.atomic.AtomicInteger;

class CasHistogram implements Histogram {
    private final AtomicInteger[] counts;

    public CasHistogram(int span) {
        counts = new AtomicInteger[span];
        for (int i = 0; i < span; i++) { //populate and initialize
            counts[i] = new AtomicInteger(0);
        }
    }

    public void increment(int bin) {
        int oldValue;
        do {
            oldValue = counts[bin].get();
        } while (!counts[bin].compareAndSet(oldValue, oldValue + 1));
    }

    public int getCount(int bin) {
        return counts[bin].get();
    }

    public int getSpan() {
        return counts.length; // array length is final. We assume .length is fine to use.
    }

    public int getAndClear(int bin) {
        int oldValue;
        do {
            oldValue = counts[bin].get();
        } while (!counts[bin].compareAndSet(oldValue, 0));
        return oldValue;
    }
}
