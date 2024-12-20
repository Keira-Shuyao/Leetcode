class Solution {
    // public int numSquares(int n) {
    //     // Solution1: BFS 
    //     //将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边。
    //     //求解最小的平方数数量，就是求解从节点 n 到节点 0 的最短路径。
    //     List<Integer> squares = generateSquare(n);
    //     Queue<Integer> queue = new LinkedList<>();
    //     boolean[] marked = new boolean[n+1];
    //     int level = 0;
    //     marked[n] = true;
    //     queue.add(n);
    //     while(!queue.isEmpty()){
    //         int size = queue.size();
    //         level++;
    //         while(size-- >0){
    //             int cur = queue.poll();
    //             for(int s: squares){
    //                 //checks each perfect square s to calculate the next number 
    //                 int next = cur - s;

    //                 //下面是几个edge cases
    //                 //it breaks the loop since we don't want negative numbers
    //                 if(next < 0){
    //                     break;
    //                 }
    //                 // it returns level because it means we've found a way to sum perfect squares to get to 0.
    //                 if(next == 0){
    //                     //！！！这个level 才是我们需要返回的 最短路径！
    //                     return level;
    //                 }
    //                 if(marked[next]){
    //                     continue;
    //                 }
    //                 marked[next] = true;
    //                 queue.add(next);
    //             }
    //         }
    //     }
    //     return n;
    // }
    // //这里不是用n^2来计算平方和，而使用等差数列
    // //连续平方数之间的差是逐渐增加的。具体地说，从n^2到(n+1)^2的差是2n+1
    // //把1^2 and 2^2之间的difference设置成3已经包含（2n+1）里面的1了
    // //每次更新差值 这次的平方数加上与下个数之间的差值就是下个数的平方数
    // private List<Integer> generateSquare (int n){
    //     List<Integer> squares = new ArrayList<>();
    //     int square = 1;
    //     int diff = 3;
    //     while(square<=n){
    //         squares.add(square);
    //         square += diff;
    //         diff+= 2;
    //     }
    //     return squares;
    // }
    //     //Solution2: DP 

public int numSquares(int n) {
    Queue<Integer> q = new LinkedList<>();
    Set <Integer> visited = new HashSet<>();
    q.offer(0);
    visited.add(0);
    int depth = 0;
    while(!q.isEmpty()){
        int size = q.size();
        depth++;
        while(size-- >0){
            int u = q.poll();
            for(int i = 1; i*i <= n; i++){
                int v = u+i*i;
                if(v == n){
                    return depth;
                }
                if(v>n){
                    break;
                }
                if(!visited.contains(v)){
                    q.offer(v);
                    visited.add(v);
                }
            }
        }
    }
    return depth;
    
}
}