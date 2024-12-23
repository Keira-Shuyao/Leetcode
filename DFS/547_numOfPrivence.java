class Solution {
    //这题是无向图
    //和第200题不一样，这里遍历所有nodes，只有m（代表m个city）个，因为这题只有m个city，
    //第200题遍历所有边，有n*n个node
    // m nodes are used instead of m*m because we are interested in visiting each node in the graph 
    //to identify the connected components, not in examining every possible connection between nodes.


///注意无向图中DFS和有向图不同，
//有向图中只探索上下左右4个方向，或者八个方向
//无向图DFS looks through entire row for connections instead of just adjacent cells
    
int m = 0;
    int circleNum = 0;
    public int findCircleNum(int[][] isConnected) {
        
        if(isConnected == null || isConnected.length == 0 || isConnected[0].length == 0){
            return 0;
        }
        m = isConnected.length;

        //注意这个hasVisited 是一个一维列表，每一个元素代表一个城市是否被visited，默认全部为false，是的话就变成true
        boolean [] hasVisited = new boolean[m]; //track visited city
        for(int i = 0; i<m; i++){
            if(!hasVisited[i]){// if city i hasn't been visited
                dfs(isConnected, i, hasVisited);//explore all city circle to count this city circle
                circleNum ++;//each time dfs, the circleNum +1, since this node isnot visited
            }
            
        }
        return circleNum;

    }



    private void dfs(int[][] isConnected, int i, boolean[] hasVisited){
        hasVisited[i] = true;//marked current city as visited
        for(int k = 0; k<m; k++){//check all possible friends city
            //当前node相连 并且还没有被visited
            if(isConnected[i][k] == 1 && !hasVisited[k]){ //if k is a friend and not visited
                dfs(isConnected, k, hasVisited); //check k's friend
            }
            
        }
    }

    /**
    这题🤯： 看一下下面的例子
    Let's see an example:
M = [
    [1,1,0],
    [1,1,0],
    [0,0,1]
]
Process:

Start with student 0:

hasVisited = [false, false, false]
i = 0:
- Mark 0 as visited: [true, false, false]
- Check row 0: finds friend 1
  - Mark 1 as visited: [true, true, false]
  - Check row 1: no new friends
- circleNum = 1

Check student 1:

- Already visited, skip

Check student 2:

i = 2:
- Not visited, do DFS
- Mark 2 as visited: [true, true, true]
- Check row 2: no new friends
- circleNum = 2
Key differences from previous island problems:

Uses a separate boolean array to track visited nodes instead of modifying input
Matrix represents relationships (M[i][j] = M[j][i] = 1 means i and j are friends)
DFS looks through entire row for connections instead of just adjacent cells

Time complexity: O(n²) - we might need to explore entire matrix
Space complexity: O(n) - for visited array and recursion stack
The reason circleNum is incremented in main function (similar to numIslands):

Each DFS call explores an entire friend circle
When we find an unvisited student, they must be part of a new circle
Otherwise, they would have been visited during exploration of previous circles
     */
}