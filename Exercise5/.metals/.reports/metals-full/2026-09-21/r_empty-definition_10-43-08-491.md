error id: file://<WORKSPACE>/ConcurrentIntegerSet.java:java/util/Set#add().
file://<WORKSPACE>/ConcurrentIntegerSet.java
empty definition using pc, found symbol in pc: java/util/Set#add().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1518
uri: file://<WORKSPACE>/ConcurrentIntegerSet.java
text:
```scala
// NOTE: In this file, you should only modify the class ConcurrentIntegerSetSync
package exercises05;

import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;

public interface ConcurrentIntegerSet {
    public boolean add(Integer element);
    public boolean remove(Integer element);
    public int size();
}

class ConcurrentIntegerSetBuggy implements ConcurrentIntegerSet {
    final private Set<Integer> set;

    public ConcurrentIntegerSetBuggy() {
        this.set = new HashSet<Integer>();
    }

    public boolean add(Integer element) {
        return set.add(element);
    }

    public boolean remove(Integer element) {
        return set.remove(element);
    }

    public int size() {
        return set.size();
    }
}

// TODO: Fix this class to pass your tests
class ConcurrentIntegerSetSync implements ConcurrentIntegerSet {
    final private Set<Integer> set;

    public ConcurrentIntegerSetSync() {
        this.set = new HashSet<Integer>();
    }

    public boolean add(Integer element) {
        return set.add(element);
    }

    public boolean remove(Integer element) {
        return set.remove(element);
    }

    public int size() {
        return set.size();
    }
}

class ConcurrentIntegerSetLibrary implements ConcurrentIntegerSet {
    final private Set<Integer> set;

    public ConcurrentIntegerSetLibrary() {
        this.set = new ConcurrentSkipListSet<Integer>();
    }

    public boolean add(Integer element) {
        return set.ad@@d(element);
    }

    public boolean remove(Integer element) {
        return set.remove(element);
    }

    public int size() {
        return set.size();
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/Set#add().