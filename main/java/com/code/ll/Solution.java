package com.code.ll;

class Solution {
    // Function to pairwise swap elements of a linked list.
    // It should returns head of the modified list


    static class  Node {
        int data;
        Node next;

        Node(int key) {
            data = key;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public Node pairwiseSwap1(Node head) {
        // Create a dummy node to simplify the code
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // Nodes to be swapped
            Node first = prev.next;
            Node second = prev.next.next;

            // Swapping
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // Move prev to the next pair
            prev = first;
        }

        return dummy.next;
    }


    public static Node pairwiseSwap(Node head) {
        // Create a dummy node to simplify the code
        Node dummy = new Node(0);
        dummy.next = head;
        Node current = head;

        Node prev1 = null;
        Node node1 = null;

        Node prev2 = head;
        Node node2 = head;

        while (current != null && current.next != null) {
            node1 = current;
            node2 = current.next;
            swapNodes(head, node1, node2);
            current = node1;
        }

        return dummy.next;
    }


    static void swap(Node head, Node prev1, Node node1, Node prev2, Node node2) {
        // change prev pointer of both node.
        if (prev1 != null) {
            prev1.next = node2;
        } else {
            head = node2;
        }

        if (prev2 != null) {
            prev2.next = node1;
        } else {
            head = node1;
        }
        // swap the next pointer of both node
        Node temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;
        printList(head);
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

    public static  void swapNodes( Node head, Node node1, Node node2) {
        if (node1 == null || node2 == null || node1 == node2) {
            System.out.println("Invalid nodes. Swap not possible.");
            return;
        }
        // Find the previous nodes of node1 and node2
        Node prev1 = null, curr1 = head;
        while (curr1 != null && curr1 != node1) {
            prev1 = curr1;
            curr1 = curr1.next;
        }

        Node prev2 = null, curr2 = head;
        while (curr2 != null && curr2 != node2) {
            prev2 = curr2;
            curr2 = curr2.next;
        }

        // If either node1 or node2 is not found, return
        if (curr1 == null || curr2 == null) {
            System.out.println("One or both nodes not found. Swap not possible.");
            return;
        }
        // If node1 is not the head, update prev1.next
        if (prev1 != null) {
            prev1.next = node2;
        } else {
            head = node2;
        }
        // If node2 is not the head, update prev2.next
        if (prev2 != null) {
            prev2.next = node1;
        } else {
            head = node1;
        }
        // Swap the next pointers of node1 and node2
        Node temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;
    }




}