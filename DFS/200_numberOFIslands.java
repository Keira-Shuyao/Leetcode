class Solution {
    private int m, n;
    //图形搜索题都需要设置边界 这题和695非常类似 
    //看题目要求：只能搜索上下左右四个方向就是下面这四个方向！
    //假设当前格子为00
    //如果是八个方向的话，就是八个（-1，-1）； （-1， 0）；1，-1； 0，-1； 0，1； 1，-1， 1，0； 1，1
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }

        m = grid.length; //row number
        n = grid[0].length; //column number
        int islandsNum = 0;

        // Visit each cell in the grid
        for (int i = 0; i < m; i++){
            for(int j = 0; j <n; j++){
                if(grid[i][j] != '0'){
                    dfs(grid, i, j);
                    islandsNum++; //因为当前格子不为0才会DFS，所以一定会islandsNum➕1
                }
            }
        }
        return islandsNum;

    }

    //这题和695不一样的地方在于
    //We don't need to track area, just count number of islands so DFS is void isntead of int because it just marks visited cells
    //Each time we find a '1', we increment islandsNum because it must be the start of a new island (all connected '1's from previous islands were changed to '0')
    private void dfs(char[][]grid, int i, int j){
        //set up the range
        if(i<0 || i>= m || j<0 ||j>= n||grid[i][j]== '0'){
            return;
        }
        grid[i][j] = '0';
        for(int[]d: direction ){
            dfs(grid, i+d[0], j+d[1]);
        }
    }
}

/*
 * 和695不一样的地方在于
 * The differences are because:

For Number of Islands:

We just need to count how many separate islands exist
Each DFS call marks one complete island, so we increment counter in main function
We don't care about size, just that it's a separate island
DFS is void because it just marks visited cells


For Maximum Area:

We need to calculate the area of each island and find the maximum
DFS must return an integer (the area) so we can compare sizes
Area calculation happens during DFS by adding 1 for each cell and summing results from all directions
Main function uses Math.max to keep track of largest area found
 */