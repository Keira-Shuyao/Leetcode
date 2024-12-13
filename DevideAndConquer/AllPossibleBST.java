import java.util.LinkedList;
import java.util.List;
//这题的问题在于，用了devide and conquer,但是生成的左右子树是单独生成的，有很多可能性，
// The left and right subtrees are independent, and we need to combine each possible left subtree with each possible right subtree for a given root i.

// This is why we need the nested loops:

// The outer loop iterates over all possible left subtrees.
// The inner loop iterates over all possible right subtrees.

public class AllPossibleBST {

    public static void main(String[] args) {
        int n = 3;
        List<TreeNode> result = generateTrees(n);
        System.out.println(result);
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new LinkedList<TreeNode>();
        }
        return generateSubtrees(1, n);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {   
            this.val = val;
            this.left = left;
            this.right = right;
        }
        // 记得要override toString method
        // When you try to print an object in Java using System.out.println(), Java
        // automatically calls the object's toString() method. If you haven't defined a
        // toString() method for your class, it will use the default one provided by the
        // Object class,

        // @Override
        // public String toString() {
        // return "TreeNode{" +
        // "val=" + val +
        // ", left=" + (left != null ? left.toString() : "null") +
        // ", right=" + (right != null ? right.toString() : "null") +
        // '}';
        // }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            buildString(this, sb);
            sb.append("]");
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val + ",");
                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }
    }

    private static List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        // it means the range is empty, so it adds a null value to the list of trees and
        // returns it.
        if (s > e) {
            res.add(null);
            return res;
        }

        // use recursion to generate each possibilities for the left subtree and right
        // subtree
        // then combine together
        // the key is to determine the range for left and right!!
        for (int i = s; i <= e; i++) {
            List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);// the range for left subtree is (s,i-1)
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);// the range for left subtree is (i+1,e)
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
