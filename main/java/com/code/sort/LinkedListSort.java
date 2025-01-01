package com.code.sort;

public class LinkedListSort {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    // Main function to sort the linked list
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Split the list into halves
        ListNode middle = getMiddle(head);
        ListNode nextOfMiddle = middle.next;
        middle.next = null;

        // Step 2: Recursively sort the sublists
        ListNode left = sortList(head);
        ListNode right = sortList(nextOfMiddle);

        // Step 3: Merge the sorted sublists
        return merge(left, right);
    }

    // Function to find the middle of the list
    private ListNode getMiddle(ListNode head) {
        if (head == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Function to merge two sorted lists
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }

        return dummy.next;
    }

    // Helper function to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListSort sorter = new LinkedListSort();

        // Creating a linked list: 4 -> 2 -> 1 -> 3
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.println("Original list:");
        sorter.printList(head);

        head = sorter.sortList(head);

        System.out.println("Sorted list:");
        sorter.printList(head);
    }
}


