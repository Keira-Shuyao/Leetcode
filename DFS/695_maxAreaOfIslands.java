class Solution {
    //这题和200很像，但是这题是要求求所有联通面积里面面积最大的是多少，200是要求求所有联通的数量
    //所以这题计算面积是在dfs里面，然后在main里面比较求最大面积
    //200题是在main里面计算一共有几个联通面积
    private int m;
    private int n;
    //做矩阵图形题都需要用二维数组设置方向
    private int[][] direction = {{0,1}, {0,-1}, {1,0},{-1,0}};
    public int maxAreaOfIsland(int[][] grid) {
       if(grid == null || grid.length == 0){
        return 0;
       } 
       int maxArea = 0;
       m = grid.length;
       n = grid[0].length;
       //对于每一个点都实行DFS time complexity O(m*n)
       for(int i = 0; i < m; i++){
        for(int j = 0; j<n; j++){
            maxArea = Math.max(maxArea, dfs(grid, i, j));
        }
       }
        return maxArea;
    }
    //r 代表row，c代表column
    private int dfs(int[][]grid, int r, int c){
        if(r<0 || r>=m || c<0 || c>=n || grid[r][c]==0){
            return 0;
        }
        grid[r][c] = 0;//!!重要‼️ make the current cell as visited
        int area = 1; //current cell contribute 1 to whole area
        //check all 4 direction
        for(int[]d: direction){
            //尽管这里有recursion，但是We mark each visited cell as 0 (grid[r][c] = 0)
            //所以Each cell is processed exactly once
            area += dfs(grid, r+d[0], c+d[1]);//direction是二维列表，d[0]代表row,d[1]=column
        }
        return area;
    }
}