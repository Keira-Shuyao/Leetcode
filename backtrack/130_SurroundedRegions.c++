class Solution {
public:
    void DFS(vector<vector<char>>& board, int i, int j, int m, int n){
        if(i<0 || i>=m || j < 0 ||j>=n || board[i][j] != 'O') return;
         //将所有DFS中遇到的与boundary相连的/有路径的格子，先保存成#，方便后续替换
         board[i][j] = '#';
        //  check all four directions
        DFS(board, i-1, j,m,n);
        DFS(board, i+1, j, m, n);
        DFS(board, i, j-1, m, n);
        DFS(board, i, j+1, m, n);
    }

    void solve(vector<vector<char>>& board) {
        int m = board.size();
        if(m == 0){ return;
        }

        int n = board[0].size();

        //check whether the boundary contains 'O'
        //first check the first column and last column
        for(int i = 0; i< m; i++){
            if(board[i][0]=='O'){
                DFS(board, i, 0, m, n);
            }
            if(board[i][n-1]=='O'){
                DFS(board, i, n-1, m, n);
            }
        }

        //second check the first row and last row
        for(int j = 0; j<n; j++){
            if(board[0][j]=='O'){
                DFS(board, 0,j,m,n);
            }
            if(board[m-1][j]== 'O'){
                DFS(board,m-1, j, m, n);
            }
        }

        //Finally, 将剩下的‘O’，即被包围的需要替换的目标格子替换成‘X’，将之前保存的不能替换的‘#’变为‘O’
        for(int i = 0; i< m; i++){
            for(int j = 0; j< n; j++){
                if(board[i][j]=='O'){
                    board[i][j] = 'X';
                }
                 if(board[i][j]=='#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
};
/*
目标：找到所有被包围的O，变成X（如果边界上有O，那么和这个边界O有相同path的所有O都不能改变）
思路：
1.先找到boundary上的O，进行DFS，把path上所有的O变成别的，比如#
2.遍历整个matrix，让所有其他还剩下的O变成X
3.让所有之前变成#的cell变成O
DFS：对当前cell的四个方向进行DFS recursion。两种写法，1:写出direction，然后在direction上循环；2:直接在DFS内部写4个DFS recursion
*/