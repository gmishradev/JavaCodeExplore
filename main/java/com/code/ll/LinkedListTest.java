package com.code.ll;

public class LinkedListTest {

    // Node class for the linked list
    static class Node {
        int data;
        Node next;

        // Constructor to create a new node
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to pairwise swap elements in the linked list
    public static Node pairwiseSwap(Node head) {
        Node first = head;
        Node second = head.next;
        head = second == null ? first : second;
        Node prev = null;
        
        while (second != null) {
            first.next = second.next;
            second.next = first;
            if (prev != null) {
                prev.next = second;
            }
            prev = first;
            first = first.next;
            
            if (first == null) {
                second = null;
            } else {
                second = first.next;
            }
        }
        return head;
    }

    // Helper method to print the linked list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method to test the pairwiseSwap
    public static void main(String[] args) {
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original Linked List:");
        printList(head);

        // Perform the pairwise swap
        head = pairwiseSwap(head);

        System.out.println("Linked List after pairwise swap:");
        printList(head);
    }
}
