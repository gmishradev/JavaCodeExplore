package com.code.sort;

public class SortLinkedList {

     static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Main function to sort the linked list using bubble sort
    public ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        boolean swapped = true;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        while (swapped) {
            swapped = false;
            ListNode prev = dummyHead;
            ListNode current = dummyHead.next;

            while (current != null && current.next != null) {
                if (current.val > current.next.val) {
                    // Swap nodes
                    prev.next = swap(prev, current, current.next);
                    swapped = true;
                }
                prev = current;
                current = current.next;
            }
        }

        return dummyHead.next;
    }

    // Function to swap nodes node1 and node2
    private ListNode swap(ListNode prev, ListNode node1, ListNode node2) {
        ListNode temp = node2.next;
        node2.next = node1;
        node1.next = temp;
        prev.next = node2;
        return node2;
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
        SortLinkedList sorter = new SortLinkedList();

        // Creating a linked list: 4 -> 2 -> 1 -> 3
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.println("Original list:");
        sorter.printList(head);

        head = sorter.bubbleSort(head);

        System.out.println("Sorted list:");
        sorter.printList(head);
    }
}
