/*
Given the head of a singly linked list, reverse the list,
and return the reversed list.

https://leetcode.com/problems/reverse-linked-list/
 */
public class ReversedLinkedList {

    public static void main(String[] args) {
        ReversedLinkedList solve = new ReversedLinkedList();
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        System.out.println(solve.reverseListRecursive(head).toString());
    }

    /**
     * Uses an iterative approach to reverse the list.
     * @param head the head of the list
     * @return the new head of the reversed list
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode next = head.next;
        curr.next = null;
        while (next.next != null) {
            ListNode nextNext = next.next;
            next.next = curr;
            curr = next;
            next = nextNext;
        }

        next.next = curr;
        return next;
    }

    /**
     * Uses an iterative approach to reverse the list.
     * @param head the head of the list
     * @return the new head of the reversed list
     */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
