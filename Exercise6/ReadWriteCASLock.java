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
        
        while(true){
            Holders curr = holder.get();
            if(curr instanceof Writer){
                return false;
            }
            ReaderList rl = (ReaderList) curr;
            Holders next = new ReaderList(t,rl);
            
            if(holder.compareAndSet(curr, next)){
                return true;
            }

        }
    }

    public void readerUnlock() {
        final Thread t = Thread.currentThread();
        Holders next;
        Holders curr;
        do { 
            curr = holder.get();
        
            if(curr == null || curr instanceof Writer || !((ReaderList) curr).contains(t)){
                throw new RuntimeException("This thread does not hold lock/is writer.");
            }
            ReaderList rl = (ReaderList) curr;
            next = rl.remove(t);
            
        } while (!holder.compareAndSet(curr, next));
        
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

        
        public boolean contains(Thread t){
            return thread == t || (next != null && next.contains(t));
        }

        public ReaderList remove(Thread t){
            if(thread == t) return next; // current element

            if(next == null){// tail
                return new ReaderList(thread,null);
            }

            return new ReaderList(thread,next.remove(t));
        }
    }

    private static class Writer extends Holders {
        public final Thread thread;

        Writer(Thread t){
            this.thread = t;
        }

    }
}

