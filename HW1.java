/*
 * Author: Alexander Ross
 * Section: 001
 * Description: This file contains implementations of a linked list, stack utilities,
 * and algorithm analysis methods. It includes methods for inserting, removing, and analyzing
 * elements in these data structures, as well as methods for checking palindromes and analyzing
 * algorithm complexity.
 */

import java.util.Stack;

public class HW1 {

    /**
     * LinkedList class represents a singly linked list data structure.
     * It supports operations like sorted insertion, removal of elements,
     * and removal of elements less than a specified value.
     */
    static class LinkedList {

        /**
         * Node class represents a single node in the linked list.
         * Each node contains an integer value and a reference to the next node.
         */
        static class Node {
            int data;       // Data stored in the node
            Node next;      // Reference to the next node

            /**
             * Constructor for Node.
             * @param d The integer value to store in the node.
             */
            Node(int d) {
                data = d;
                next = null;
            }
        }

        Node head; // Head of the linked list

        /**
         * Inserts a new node with the specified data into the linked list in sorted order.
         * @param data The integer value to insert into the linked list.
         */
        public void sortedInsert(int data) {
            Node new_node = new Node(data);
            new_node.next = null;

            // Special case: Insert at the head if the list is empty or the new data is smaller than the head.
            if (this.head == null || head.data >= new_node.data) {
                new_node.next = head;
                head = new_node;
            } else {
                // Traverse the list to find the correct position for insertion.
                Node current = this.head;
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }
        }

        /**
         * Removes all nodes from the linked list that contain values less than the specified value.
         * @param ltValue The threshold value. Nodes with values less than this will be removed.
         */
        public void removeElementsLT(int ltValue) {
            // Remove all leading nodes with values less than ltValue.
            while (head != null && head.data < ltValue) {
                head = head.next;
            }

            // Traverse the list and remove nodes with values less than ltValue.
            Node current = head;
            while (current != null && current.next != null) {
                if (current.next.data < ltValue) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
        }

        /**
         * Removes all nodes from the linked list that contain the specified value.
         * @param value The value to remove from the linked list.
         */
        public void removeElement(int value) {
            // Remove all leading nodes with the specified value.
            while (head != null && head.data == value) {
                head = head.next;
            }

            // Traverse the list and remove nodes with the specified value.
            Node current = head;
            while (current != null && current.next != null) {
                if (current.next.data == value) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
        }

        /**
         * Returns a string representation of the linked list.
         * @return A string containing the elements of the linked list in order.
         */
        public String toString() {
            StringBuilder output = new StringBuilder("[");
            Node currNode = this.head;
            while (currNode != null) {
                output.append(currNode.data).append(" ");
                currNode = currNode.next;
            }
            return output.toString().trim() + "]";
        }
    } // End of LinkedList class

    /**
     * Stacks class provides utility methods for working with stacks,
     * including checking if a string is a palindrome and finding the largest index
     * of a specific value in a stack.
     */
    static class Stacks {

        /**
         * Checks if the input string is a palindrome.
         * A palindrome is a string that reads the same backward as forward.
         * This method is case-insensitive and ignores spaces.
         * @param input The string to check.
         * @return True if the string is a palindrome, false otherwise.
         */
        public static boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", ""); // Normalize the input string

            // Push each character of the string onto the stack
            for (char c : input.toCharArray()) {
                stack.push(c);
            }

            // Compare each character of the string with the characters popped from the stack
            for (char c : input.toCharArray()) {
                if (c != stack.pop()) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Finds the largest index of the specified value `k` in the stack.
         * The stack is traversed from bottom to top, and the largest index
         * where `k` appears is returned.
         * @param stack The stack to search.
         * @param k The value to find in the stack.
         * @return The largest index of `k` in the stack, or -1 if `k` is not found.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            int largestIndex = -1;

            System.out.println("\n--- Running findLargestK for k = " + k + " ---");
            System.out.println("Initial Stack: " + stack);

            // Convert stack to array for correct indexing
            Integer[] arr = stack.toArray(new Integer[0]);

            // Traverse the array to find the largest index of `k`
            for (int i = 0; i < arr.length; i++) {
                System.out.println("Checking value: " + arr[i] + " at index " + i);
                if (arr[i] == k) {
                    largestIndex = i;
                    System.out.println("✅ Found k (" + k + ") at index " + i + ", updating largestIndex.");
                }
            }

            System.out.println("Returning largestIndex: " + largestIndex);
            return largestIndex;
        }
    } // End of Stacks class

    /**
     * Performs algorithm analysis for a method with two independent loops.
     * The time complexity is O(n + m), and the space complexity is O(1).
     * @param n The size of the first loop.
     * @param m The size of the second loop.
     * @return A constant value (3) representing the result of the analysis.
     */
    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        // First loop: O(n) time complexity
        for (int i = 0; i < n; i++)
            a += Math.random();

        // Second loop: O(m) time complexity
        for (int j = 0; j < m; j++)
            b += Math.random();

        return 3; // O(N + M) time, O(1) space
    }

    /**
     * Performs algorithm analysis for a method with nested loops.
     * The outer loop runs O(n) times, and the inner loop runs O(log n) times.
     * The overall time complexity is O(n log n).
     * @param n The size of the input.
     * @return A constant value (2) representing the result of the analysis.
     */
    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;

        // Outer loop: O(n) time complexity
        for (i = n / 2; i <= n; i++)
            // Inner loop: O(log n) time complexity
            for (j = 2; j <= n; j = j * 2)
                k += n / 2;

        return 2; // O(N log N) time
    }
}