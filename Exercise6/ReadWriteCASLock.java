// For week 6
// raup@itu.dk * 2024-09-22

package exercises06;

// Very likely you will need some imports here

import java.io.Reader;
import java.util.concurrent.atomic.AtomicReference;


class ReadWriteCASLock implements SimpleRWTryLockInterface {

    // TODO: Add necessary field(s) for the class
    private final AtomicReference<Holders> holder = new AtomicReference<Holders>();

    public boolean readerTryLock() {
        final Thread t = Thread.currentThread();
        final Holders curr = holder.get();
        Holders next;


        do { 
            
            ReaderList rl = (ReaderList) curr;
            next = new ReaderList(t,rl);

        } while (!(curr instanceof Writer) && 
            (holder.compareAndSet(curr, next) || 
                holder.compareAndSet(null, next)));
    
       return true;
    }

    public void readerUnlock() {
        // TODO 6.2.4
    }

    public boolean writerTryLock() {
        final Holders curr = new Writer(Thread.currentThread());
        return holder.compareAndSet(null, curr);
    }
 

    public void writerUnlock() {
        final Holders curr = holder.get();
        if(!(curr instanceof Writer)){ // make sure it is writer
            throw new RuntimeException("This thread is not a writer.");
        }
        final Writer w = (Writer) curr;
        if(w.thread != Thread.currentThread()){ // make sure the one holding lock, and thread calling unlock is same
             throw new RuntimeException("This thread does not hold the lock");
        }
        holder.set(null);
    }





    private static abstract class Holders { }

    private static class ReaderList extends Holders {
        private final Thread thread;
        private final ReaderList next;

        public ReaderList(Thread t, ReaderList rl) {
            this.thread = t;
            this.next = rl;
        }

        
        // TODO: contains

        // TODO: remove
    }

    private static class Writer extends Holders {
        public final Thread thread;

        Writer(Thread t){
            this.thread = t;
        }

    }
}
