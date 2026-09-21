## 5.1

### 5.1.1

***See ConcurrentSetTest.java***

Assume that code add function has following lines:

1. Check if element already exists in set
2. Add element to set

**Interleaving** 
This interleaving would preduce an error.
t1(1) t2(1) t1(2) t(2)

### 5.1.2

***See ConcurrentSetTest.java***

Assume that code add function has following lines:

1. Check if element exists in set
2. Remove the element

**Interleaving** 
This interleaving would preduce an error.
t1(1) t2(1) t1(2) t(2)

### 5.1.3

***SeeConcurrentIntegerSet.java***

The sync class uses synchronized method calls which means that the method will not be called concurrently on the same set, which fixes the issue.

### 5.1.4

The tests pass on this library. It seems that this library is already thread safe and have implemented something like synchronized.

### 5.1.5

A fail on one of the tests would prove that the library is not thread safe since this would essentially proof an example where it is not thread safe.

#### 5.1.6

It does not prove it, but it does highly suggest it with high numbers. As we can see only a small percentage fail, which means that we might get a lucky run where the tests "mistakenly" pass.

### 5.2

### 5.2.1

Capacity is set to 2

t1(release) t2(acquire) t1(acquire) t3(acquire)

This would mean that 3 enter when capacity is only 2

### 5.2.2


