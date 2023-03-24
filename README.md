# UMT_Technical_Test

---

I chose to solve the problem in Java language. The solution can be found in UMT_Test/src/Main.java. 

---

# Description

My solution is based on a mathematical formula. Let's say A is the main array and B and C would be the subarrays, like in the requirement. Then, sum(A) represents the sum of all elements in A and len(A) represents the length of A. The formula would be : if B and C have the same average, sum(B) = len(B) * sum(A) / len(A), and the same for C. Based on this formula we can do a pruning to see if sum(A) * i / len(A) can be divided, with i = 1,len(A)/2. If this returns true we still cannot be 100% certain that there exists such arrays. If it returns false we can know for sure there are no such arrays. Then we use dynamic programming. We try to compute every sum with 0, 1, …, len(A)/2 elements. It is not necessary to go further len(A)/2, because those sums are complementary with the first ones. When we find the first to correspond to our formula, we return true. If we didn’t find any, that means there are no such arrays.

---

# Input

The array is located in a txt file with the path UMT_Test/src/input.txt. The file needs to be in this format:

A single line containing all elements of the array, separeted by a single space.

---

# Output

The output is shown to the console.

If there are not such arrays:
  false
  
If there are such arrays:
  First array with length *len(B)* total sum *sum(B)* and average *sum(B)/len(B)*
  Second array with length *len(C)* total sum *sum(C)* and average *sum(C)/len(C)*
  true
  


