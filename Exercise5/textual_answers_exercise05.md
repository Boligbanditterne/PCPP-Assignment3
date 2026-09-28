## 5.1

### 5.1.1
***Implement a functional correctness test that finds concurrency errors in the add(Integer element) method in ConcurrentIntegerSetBuggy. Describe the interleaving that your test finds.***

***See ConcurrentSetTest.java***

Assume that code add function has following lines:

1. Check if element already exists in set
2. Add element to set

**Interleaving** 
This interleaving would preduce an error.
t1(1) t2(1) t1(2) t(2)

### 5.1.2
***Implement a functional correctness test that finds concurrency errors in the remove(Integer element) method in ConcurrentIntegerSetBuggy. Describe the interleaving that your test finds***

***See ConcurrentSetTest.java***

Assume that code add function has following lines:

1. Check if element exists in set
2. Remove the element

**Interleaving** 
This interleaving would preduce an error.
t1(1) t2(1) t1(2) t(2)

### 5.1.3
***In the class ConcurrentIntegerSetSync, implement fixes to the errors you found in the previous exercises. Run the tests again to increase your confidence that your updates fixed the problems. In addition, explain why your solution fixes the problems discovered by your tests***

***SeeConcurrentIntegerSet.java***

The sync class uses synchronized method calls which means that the method will not be called concurrently on the same set, which fixes the issue.

### 5.1.4
***Run your tests on the ConcurrentIntegerSetLibrary. Discuss the results.***

The tests pass on this library. It seems that this library is already thread safe and have implemented something like synchronized.

### 5.1.5
***Do a failure on your tests above prove that the tested collection is not thread-safe? Explain your answer***

A fail on one of the tests would prove that the library is not thread safe since this would essentially proof an example where it is not thread safe.

#### 5.1.6
***Does passing your tests above prove that the tested collection is thread-safe (when only using add() and remove())? Explain your answer.***

It does not prove it, but it does highly suggest it with high numbers. As we can see only a small percentage fail, which means that we might get a lucky run where the tests "mistakenly" pass.

### 5.2

### 5.2.1
***Let capacity denote the final field capacity in SemaphoreImp. Then, the property above does not hold for SemaphoreImp. Your task is to provide an interleaving showing a counterexample of the property, and explain why the interleaving violates the property.***


Capacity is set to 2

t1(release) t2(acquire) t1(acquire) t3(acquire)

This would mean that 3 enter when capacity is only 2

### 5.2.2

***Write a functional correctness test that can trigger the interleaving you describe in 1. Explain why your test
triggers the interlaving.***

***See ConcurrentSetTest last test***

