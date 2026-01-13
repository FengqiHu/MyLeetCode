/**
 * @author louishu
 * @date 1/13/26 17:37
 * @description
 */
public class LinkedList {

    //     * Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 141. Linked List Cycle -  Easy
     * @Date 01/13/2026
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            // the next node
            slow = slow.next;
            // the next of next node
            // fast point
            fast = fast.next.next;

            // if there is a cycle, fast will meet slow
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
