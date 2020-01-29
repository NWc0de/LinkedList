/*
 * Implements a basic linked list.
 * Author: Spencer Little
 */

import java.util.Stack;

/**
 * A generic LinkedList and associated sorting methods.
 * @param <T> the type of element to be stored by the list, must be comparable for sorting
 */
public class LinkedList<T extends Comparable> {

    private Node root;
    private Node tail;
    private int size;

    LinkedList() { size = 0; }
    LinkedList(T[] elems) {addAll(elems);}
    LinkedList(T elem) {
        root = new Node(elem);
        tail = root;
        size = 1;
    }

    /**************************************************
     *           Data Access/Manipulation             *
     **************************************************/

    public int size() {return size;}
    public T getFirst() {return root.getElemement();}
    public T getLast() {return tail.getElemement();}
    public T get(int ind) { return getNode(ind).getElemement(); }

    public void addFirst(T elem) {
        Node in = new Node(elem);
        size++;
        in.setNext(root);
        root = in;
    }

    public void addLast(T elem) {
        Node in = new Node(elem);
        size++;
        if (tail == null) root = in;
        else tail.setNext(in);
        tail = in;
    }

    public void addAll(T[] elems) {
        for (T el : elems) {
            addLast(el);
        }
    }

    /**
     * Sorts the linked list with bubble sort. - O(n^2)
     */
    public void bubbleSort() {
        if (size <= 1) return;
        for (int i = 0; i < size - 1; i++) {
            Node prev = null, curr = root;
            boolean swap = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (curr.compareTo(curr.getNext()) > 0) {
                    Node tmp = curr.getNext(); // will become new previous
                    swapNodes(curr, curr.getNext(), prev, curr); // current is implicitly updated
                    prev = tmp;
                    swap = true;
                } else {
                    prev = curr;
                    curr = curr.getNext();
                }
            }
            if (!swap) return;
        }
    }

    /**
     * Sorts the linked list with shell sort. - O(n^2)
     */
    public void shellShort() {
        Stack<Integer> intervals = genKnuthSequence();
        while (!intervals.empty()) { // for each interval
            int intrv = intervals.pop();
            int itr = intrv == 1 ? 1 : size() % intrv;
            Node prev1, curr1, prev2, curr2;

            for (int i = 0; i < itr; i++) {
                System.out.println(intrv);
                prev1 = i == 0 ? null : getNode(i - 1);
                curr1 = getNode(i);
                curr2 = curr1;
                prev2 = prev1;
                int ind = i;
                boolean swap;
                System.out.println("--------------------------------");
                do {
                    swap = false;
                    while (curr2 != null) {

                        if (ind + intrv < size()) {
                            prev2 = getNode(ind + intrv - 1);
                            curr2 = getNode(ind + intrv);
                            ind += intrv;
                        } else {
                            break;
                        }

                        System.out.println("Before swap");
                        if (prev1 != null) System.out.println("prev1: " + prev1.getElemement());
                        System.out.println("curr1: " + curr1.getElemement());
                        System.out.println("prev2: " + prev2.getElemement());
                        System.out.println("curr2: " + curr2.getElemement());
                        System.out.println(toString());
                        if (curr1.compareTo(curr2) > 0) {
                            swapNodes(prev1, curr1, prev2, curr2); // curr1 is implicitly updated
                            if (prev2 == curr1) prev1 = curr2; // handle case of adjacent nodes
                            else prev1 = prev2;
                            swap = true;
                        } else {
                            prev1 = prev2;
                            curr1 = curr2;
                        }
                        System.out.println("After swap");
                        if (prev1 != null) System.out.println("prev1: " + prev1.getElemement());
                        System.out.println("curr1: " + curr1.getElemement());
                        System.out.println("prev2: " + prev2.getElemement());
                        System.out.println("curr2: " + curr2.getElemement());
                        System.out.println(toString());
                        System.out.println("---------------------");
                    }
                } while (swap);
            }
        }
    }

    /**
     * Generates a sequence of intervals based on Knuth's sequence for shellshort.
     */
    private Stack<Integer> genKnuthSequence() { // is this proper way to calculate intervals?
        Stack<Integer> stk = new Stack<>();
        int rnd = 1, interval = 1;
        while (interval < size()) {
            stk.push(interval);
            interval = (int) (Math.pow(3, rnd++) - 1) / 2;
        }
        return stk;
    }

    private Node getNode(int ind) {
        if (ind >= size()) throw new IllegalArgumentException("Index " + ind + " out of bounds for length " + size());
        Node n = root;
        for (int i = 0; i < ind; i++) {
            n = n.getNext();
        }
        return n;
    }


    /**************************************************
     *                Auxiliary Methods               *
     **************************************************/

    /**
     * Returns a string representation of the linked list.
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();
        for (Node x = root; x != null; x = x.next) {
            asString.append("[ " + x.getElemement() + " ] -> ");
        }
        return asString.toString();
    }

    /**
     * Swaps nodes swap1 and swap2, requires the nodes "behind" (pointing to)
     * swap1, and swap2: prev1, and prev2 respectively.
     */
    private void swapNodes(Node prev1, Node swap1, Node prev2, Node swap2) {
        boolean invalidNodes =
                (prev1 != null && prev1.getNext() != swap1)
                || (prev2 != null && prev2.getNext() != swap2)
                || (swap1 == null) || (swap2 == null);

        if (invalidNodes) throw new IllegalArgumentException("Invalid nodes: previous nodes do not point to the swap nodes. Or provided nodes were null.");

        Node next1 = swap1.getNext(), next2 = swap2.getNext();

        if (swap1 == next2) swap1.setNext(swap2); // assure swap node doesn't end up pointing to itself
        else swap1.setNext(next2);
        if (swap2 == next1) swap2.setNext(swap1);
        else swap2.setNext(next1);

        if (prev1 != null && prev1 != swap2) prev1.setNext(swap2); // adjacent nodes are handled by first swap
        else if (prev1 == null) root = swap2;
        if (prev2 != null && prev2 != swap1) prev2.setNext(swap1);
        else if (prev2 == null) root = swap1;
    }

    /*
     * A simple class representing a linked node.
     */
    private class Node {
        private T elem;
        private Node next;

        Node() {}
        Node(T elem) {this.elem = elem;}

        public Node getNext() {return next;}
        public void setNext(Node next) {this.next = next;}

        public T getElemement() {return elem;}
        public void setElem(T elem) {this.elem = elem;}

        /*
         * Compares this node with n based on the each nodes element.
         * Returns a negative int if this < n, 0 if they are equal, or a positive int if this > n
         */
        public int compareTo(Node n) {
            return getElemement().compareTo(n.getElemement());
        }
    }
}
