## 6.1


### 6.1.1

***Write a class CasHistogram implementing the above interface. In your implementation, ensure that: 1. class state does not escape, and 2. safe publication. Explain why 1. and 2. are guaranteed in your imple- mentation, and report any immutable variables.***

***See CasHistogram.java***

getSpan() uses safe publication. Length of the array is immutable in java and is therefore final. Therefore this is safe to publish. 

getCount() does not return a reference but an integer and is therefore also safe to publish.

### 6.1.2

***Note that the behavior for the method getAndClear(int bin) combines two operations: get obtaining the current count in the specified bin, and clear reset the bin count to 0. Does your implementation for this method behave as if the operations were executed atomically? Explain your answer.***

Yes, because the return can only happen if the clear happens first. They will always happen together atomically.

### 6.1.3

***Your task in this exercise is to write a parallel functional correctness test for CasHistogram. In this test, you must use your CasHistogram class to concurrently count the number of prime factors for the numbers in the range (0, 4999).*** 

***See TestHistograms.java***

## 6.2

### 6.2.1 - 6.2.4

***See ReadWriteCASLock.java***

### 6.2.5 - 6.26

***See TestLocks.java"***
