public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    static class Solution {
        ListNode left;

        public void reorderList(ListNode head) {
            if (head == null) {
                return;
            }

            left = head;
            helper(head);
        }

        private boolean helper(ListNode right) {
            if (right == null) {
                return true;
            }

            boolean res = helper(right.next);

            if (res == false) {
                return false;
            }

            if (left == right || right.next == left) {
                right.next = null;
                return false;
            }

            ListNode next = left.next;
            left.next = right;
            right.next = next;

            left = next;
            return true;
        }
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);

        Solution solution = new Solution();
        solution.reorderList(head);

        // Print the reordered list
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
