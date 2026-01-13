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
     *
     * @param head
     * @return
     * @Date 01/13/2026
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

    /**
     * 2. Add Two Numbers-= Medium
     *
     * @param l1
     * @param l2
     * @return
     * @Date 01/13/2026
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // create a head node
        ListNode dummy = new ListNode(0);

        // locate current location in head node
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry == 1) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // the previous sum's carry
            sum += carry;
            carry = sum / 10;
            // store the unit value
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * 21. Merge Two Sorted Lists - Medium
     *
     * @param list1
     * @param list2
     * @return
     * @Date 01/13/2026
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode(0);
        ListNode curr = list;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    curr.next = list1;
                    list1 = list1.next;
                } else {
                    curr.next = list2;
                    list2 = list2.next;
                }
            } else if (list1 != null) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;

        }
        return list.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 138. Copy List with Random Pointer - Medium
     * @Date 01/13/2026
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Node headTrack = head;
        // new list's head node
        Node headNode = new Node(0);
        Node curr = headNode;
        // first assign value
        while (headTrack != null) {
            curr.next = new Node(headTrack.val);
            curr = curr.next;
            headTrack = headTrack.next;
        }
        headTrack = head;
        curr = headNode.next;
        while (curr != null) {
            // the original random node
            if (headTrack.random == null) {
                curr.random = null;
            } else {
                Node target = headTrack.random;
                Node targetTrack = head;
                // track the target node in replicated list
                Node repTrack = headNode.next;
                while (targetTrack != target) {
                    targetTrack = targetTrack.next;
                    repTrack = repTrack.next;
                }
                // find the target node
                curr.random = repTrack;
            }
            curr = curr.next;
            headTrack = headTrack.next;
        }
        return headNode.next;
    }
}
