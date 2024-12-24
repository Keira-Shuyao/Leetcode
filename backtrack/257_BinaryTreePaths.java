/*
 * Key Points:

StringBuilder Length Tracking:
javaCopyint len = sb.length();
// ... operations ...
sb.setLength(len);

Saves current length before modifications
Restores to original length after recursion
More efficient than creating new strings


Arrow (->) Addition:

Only added when we know there's more nodes to come
Not added for leaf nodes
Ensures no trailing arrow at end of paths


Recursive Structure:

DFS approach to explore all paths
Backtracks using StringBuilder length
Adds paths only when reaching leaves



Time & Space Complexity:

Time: O(N), where N is number of nodes
Space: O(H), where H is height of tree
 */


 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List <String> res = new ArrayList<>(); // stores all paths
        StringBuilder sb = new StringBuilder(); //build each path
        helper(res, root,sb);
        return res;
    }


    /**
    When using StringBuilder, We can just keep track of the length of the StringBuilder before we append anything to it before recursion and afterwards set the length back. Another trick is when to append the "->", since we don't need the last arrow at the end of the string, we only append it before recurse to the next level of the tree. 
     */
    private void helper (List<String> res, TreeNode root, StringBuilder sb){
        // Base case: if node is null, return
        if(root == null){
            return;
        }
        // Save current length of StringBuilder
        int len = sb.length();
        sb.append(root.val);//append cur node
        if(root.left == null && root.right == null){//this node is leaf, then add the path to results
            res.add(sb.toString());

        }else{
            sb.append("->");//在recursion之前 先append ->，这样最后一个node之后就不会有多余的->
            helper(res,root.left,sb);
            helper(res, root.right,sb);
        }
        //// Backtrack: restore StringBuilder to original state
        // IMPORTANT!!!
        //Memory Efficiency:
        /**
            Instead of creating new StringBuilder for each path
            Reuses the same StringBuilder by tracking length
            Backtracking:
            setLength(len) effectively "undoes" the last append
            Allows us to try different paths without string copies
         */
        sb.setLength(len);
    }
}
//Time: O(N), where N is number of nodes
// Space: O(H), where H is height of tree