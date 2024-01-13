package edu.ser222.m01_03;

import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface. Also provides
 * a main that
 * demonstrates it.
 * 
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */

public class CompletedDeque<Item> implements Deque<Item> {
    private DoubleLinearNode<Item> first;
    private DoubleLinearNode<Item> last;
    private int size;

    public CompletedDeque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void enqueueFront(Item element) {
        DoubleLinearNode<Item> newNode = new DoubleLinearNode<Item>(element);

        if (this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            size++;
        } else {
            newNode.next = this.first;
            this.first = newNode;
            size++;
        }
    }

    @Override
    public void enqueueBack(Item element) {
        DoubleLinearNode<Item> newNode = new DoubleLinearNode<Item>(element);

        if (this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            size++;
        } else {
            newNode.previous = this.last;
            this.last = newNode;
            size++;
        }
    }

    @Override
    public Item dequeueFront() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else if (this.size == 1) {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.first.getElement());

            this.first = null;
            this.last = null;
            size--;

            return tempNode.element;
        } else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.first.getElement());
            tempNode.next = this.first.getNext();
            tempNode.previous = this.first.getPrevious();

            this.first = tempNode.next;
            tempNode.next.previous = null;
            size--;

            return tempNode.element;
        }
    }

    @Override
    public Item dequeueBack() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else if (this.size == 1) {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.last.getElement());

            this.first = null;
            this.last = null;
            size--;

            return tempNode.element;
        } else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.last.getElement());
            tempNode.next = this.last.getNext();
            tempNode.previous = this.last.getPrevious();

            this.last = tempNode.previous;
            tempNode.previous.next = null;
            size--;

            return tempNode.element;
        }
    }

    @Override
    public Item first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else {
            return first.element;
        }
    }

    @Override
    public Item last() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else {
            return last.element;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String output = "";

        if (isEmpty()) {
            return output = "empty";
        }
        else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.last.getElement());
            tempNode.previous = this.last.getPrevious();

            while (tempNode.getPrevious() != null) {
                output += tempNode.getElement() + " ";
                tempNode.element = tempNode.getPrevious().getElement();
                tempNode.previous = tempNode.getPrevious().getPrevious();
            }

            return output;
        }
    }

    public class DoubleLinearNode<Item> {
        private DoubleLinearNode<Item> next;
        private DoubleLinearNode<Item> previous;
        private Item element;

        public DoubleLinearNode() {
            this.next = null;
            this.previous = null;
            this.element = null;
        }

        public DoubleLinearNode(Item elem) {
            this.next = null;
            this.previous = null;
            this.element = elem;
        }

        public DoubleLinearNode<Item> getNext () {
        return next;
        }

        public DoubleLinearNode<Item> getPrevious () {
        return previous;
        }

        public Item getElement () {
        return element;
        }
    }

    /**
     * Program entry point for deque.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CompletedDeque<Integer> deque = new CompletedDeque<>();

        // standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());

        // deque features
        System.out.println(deque.dequeueFront());
        deque.enqueueFront(1);
        deque.enqueueFront(11);
        deque.enqueueFront(3);
        deque.enqueueFront(5);
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());
        System.out.println(deque.last());
        deque.dequeueFront();
        deque.dequeueFront();
        System.out.println(deque.first());
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
    }
}