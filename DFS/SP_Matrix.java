//1091 shortest path

import java.util.LinkedList;
import java.util.Queue;

public class SP_Matrix {
    public static void main(String[] args) {
        int [][] grid1 = new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        // int [][] grid2 = new int[][]{{1,0,0},{1,1,0},{1,1,0}};
        int result1 = shortestPathBinaryMatrix(grid1);
        // int result2 = shortestPathBinaryMatrix(grid2);
        System.out.println(result1);
        // System.out.println(result2);
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        if(grid == null || grid.length == 0 || grid[0][0]!= 0){
            return -1;
        }

        Queue <int[]>queue = new LinkedList<>();
        //the first element, x = 0,y = 0,value =0;
        queue.add(new int[]{0,0,0});

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            //如果X坐标= n-1 ;Y 坐标= n-1;那就返回当前值
            if(curr[0] == grid.length-1 && curr[1] == grid[0].length-1){
                //在这里再把开头的元素算上。
                return curr[2]+1;
            }
            //当queue不为0时，把它周围的每一个可能的box都加上
            for(int i = 0; i<dirs.length; i++){
                int x = curr[0]+dirs[i][0];
                int y = curr[1]+dirs[i][1];

                if(x<0 || y<0 || x>=grid.length|| y>= grid.length || grid[x][y] != 0){
                    //碰到超出边界的或者值不为0的格子 跳过
                    continue;
                }

                grid[x][y] = -1;
                queue.add(new int[]{x,y,curr[2]+1});
            }
        }
    return -1;
    }    
}