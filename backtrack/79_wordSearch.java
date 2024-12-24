class Solution {

    private final static int[][] direction = {{1,0}, {-1,0},{0,1},{0,-1}};
    private int m;
    private int n;

    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0){
            return true;
        }

        if(board == null || board.length == 0 || board[0].length==0){
            return false;
        }

        m = board.length;
        n=board[0].length;

        boolean[][] hasVisited = new boolean[m][n];

        for(int r=0; r<m; r++){
            for(int c=0; c<n;c++){
                if(backtrack(0,r,c,hasVisited, board, word)){
                    return true;//If any recursive call returns True, indicating that the word has been found, return True
                }
            }
        }
    // If none of the recursive calls returns True, reset the current cell to its original value and return False.
        return false;
    }

    private boolean backtrack (int curLen, int r, int c, boolean[][]visited, final char[][] board, String word){
        //base case: index curLen  equals the length of the word, return True, indicating that the word has been found.
        if(curLen == word.length()){
            return true;
        }
        //Edge cases
        //current position (i, j) is out of the grid boundaries or the character at (i, j) does not match the character at index curLen
        if(r<0 || r>=m ||c<0 ||c>=n ||board[r][c] != word.charAt(curLen) || visited[r][c]){
            return false;
        }

        //Mark the current cell as visited 
        //backtrack 标记元素：Backtracking 不是立即返回，而要继续求解
        visited[r][c] = true; //在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；

        for(int[] d: direction){
            if(backtrack(curLen+1, r+d[0], c+d[1], visited, board, word)){
                return true;
            }
        }
        //但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
        visited[r][c] = false;
        return false;
    }
}


/**
Approach
1. Implement a recursive function backtrack that takes the current position (i, j) in the grid and the current index k of the word.
2.Base cases:
If k equals the length of the word, return True, indicating that the word has been found.
If the current position (i, j) is out of the grid boundaries or the character at (i, j) does not match the character at index k of the word, return False.
3.Mark the current cell as visited by changing its value or marking it as empty.
4. Recursively explore all four directions (up, down, left, right) by calling backtrack with updated positions (i+1, j), (i-1, j), (i, j+1), and (i, j-1).
5.If any recursive call returns True, indicating that the word has been found, return True.
6. If none of the recursive calls returns True, reset the current cell to its original value and return False.
7. Iterate through all cells in the grid and call the backtrack function for each cell. If any call returns True, return True, indicating that the word exists in the grid. Otherwise, return False.

 */