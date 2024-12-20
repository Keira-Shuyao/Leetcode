#include <vector>
class Solution {
public:
//2 solution , one is dp , another is BFS.
    int numSquares(int n) {
        //base case;
        if(n<=0 ){
            return 0;
        }
        //Using static here is an optimization - it caches previous results between calls
        static vector<int> cntPerfectSquares({0}); //这个代表取index 为 n时，当前的element就是n需要几个数构建perfect square
        //This line declares a static vector cntPerfectSquares and initializes it with a single element 0. The static keyword means that the vector is shared among all instances of Solution and is not recreated each time numSquares is called. 

        while(cntPerfectSquares.size()<=n){
            int m = cntPerfectSquares.size();
            int cntSquares = INT_MAX;
            for(int i = 1; i*i <= m; i++){
                //Look up how many squares we need for that difference 
                //Uses previously calculated results to build up the answer
                ////Add 1 (because we're using one more square, i²) 因为当前这步就是又加了一个新的数
                cntSquares = min(cntSquares, cntPerfectSquares[m-i*i]+1);
            }
            //After trying all possible perfect squares, we add the minimum solution to our vector
            cntPerfectSquares.push_back(cntSquares);
        }  
        return cntPerfectSquares[n];     
    }
};

// 这题如何找到n的最少数量的perfect square？
// 主要是用了 optimization，cntSquares = min(cntSquares, cntPerfectSquares[m-i*i]+1);
/*
Let me break this down with a concrete example. Let's say n = 5.
We start with cntPerfectSquares = [0]. The code will build up solutions for all numbers from 1 to 5.
Iteration 1 (m = 1):
cppCopym = 1 (current size of vector)
cntSquares = INT_MAX

for i = 1:
    i*i = 1, which ≤ m, so we calculate:
    cntSquares = min(INT_MAX, cntPerfectSquares[1-1] + 1)
    cntSquares = min(INT_MAX, 0 + 1) = 1

vector becomes [0, 1]  // 1 needs 1 perfect square (1²)
Iteration 2 (m = 2):
cppCopym = 2
cntSquares = INT_MAX

for i = 1:
    i*i = 1, which ≤ m, so:
    cntSquares = min(INT_MAX, cntPerfectSquares[2-1] + 1)
    cntSquares = min(INT_MAX, 1 + 1) = 2

vector becomes [0, 1, 2]  // 2 needs 2 perfect squares (1² + 1²)
Iteration 3 (m = 3):
cppCopym = 3
cntSquares = INT_MAX

for i = 1:
    i*i = 1, which ≤ m, so:
    cntSquares = min(INT_MAX, cntPerfectSquares[3-1] + 1)
    cntSquares = min(INT_MAX, 2 + 1) = 3

vector becomes [0, 1, 2, 3]  // 3 needs 3 perfect squares (1² + 1² + 1²)
Iteration 4 (m = 4):
cppCopym = 4
cntSquares = INT_MAX

for i = 1:
    i*i = 1, which ≤ m, so first:
    cntSquares = min(INT_MAX, cntPerfectSquares[4-1] + 1)
    cntSquares = min(INT_MAX, 3 + 1) = 4

for i = 2:
    i*i = 4, which ≤ m, so next:
    cntSquares = min(4, cntPerfectSquares[4-4] + 1)
    cntSquares = min(4, 0 + 1) = 1

vector becomes [0, 1, 2, 3, 1]  // 4 needs 1 perfect square (2²)
Iteration 5 (m = 5):
cppCopym = 5
cntSquares = INT_MAX

for i = 1:
    i*i = 1, which ≤ m, so:
    cntSquares = min(INT_MAX, cntPerfectSquares[5-1] + 1)
    cntSquares = min(INT_MAX, 1 + 1) = 2

for i = 2:
    i*i = 4, which ≤ m, so:
    cntSquares = min(2, cntPerfectSquares[5-4] + 1)
    cntSquares = min(2, 1 + 1) = 2

vector becomes [0, 1, 2, 3, 1, 2]  // 5 needs 2 perfect squares (2² + 1²)
The key points:

For each number m, we try to find its solution using all possible perfect squares less than or equal to m
For each perfect square i², we:

Subtract it from m (that's m-i*i)
Look up how many squares we need for that difference (cntPerfectSquares[m-i*i])
Add 1 (because we're using one more square, i²)


We keep track of the minimum number of squares needed
After trying all possible perfect squares, we add the minimum solution to our vector

So for n = 5, the answer is 2 because 5 = 4 + 1 = 2² + 1².
*/