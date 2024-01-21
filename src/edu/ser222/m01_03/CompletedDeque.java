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
        if (this.isEmpty()) {
            DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element, null, null);

            this.first = newNode;
            this.last = newNode;
            size++;
        } else {
            DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element, this.first, null);

            this.first.setPrevious(newNode);
            this.first = newNode;
            size++;
        }
    }

    @Override
    public void enqueueBack(Item element) {
        if (this.isEmpty()) {
            DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element, null, null);

            this.first = newNode;
            this.last = newNode;
            size++;
        } else {
            DoubleLinearNode<Item> newNode = new DoubleLinearNode<>(element, null, this.last);

            this.last.setNext(newNode);
            this.last = newNode;
            size++;
        }
    }

    @Override
    public Item dequeueFront() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else if (this.size == 1) {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<>(this.first.getElement(), this.first.getNext(), this.first.getPrevious());

            this.first = null;
            this.last = null;
            size--;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<>(this.first.getElement(), this.first.getNext(), this.first.getPrevious());

            this.first = tempNode.getNext();
            this.first.setPrevious(null);
            size--;

            return tempNode.getElement();
        }
    }

    @Override
    public Item dequeueBack() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else if (this.size == 1) {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<>(this.last.getElement(), this.last.getNext(), this.last.getPrevious());

            this.first = null;
            this.last = null;
            size--;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<>(this.last.getElement(), this.last.getNext(), this.last.getPrevious());

            this.last = tempNode.getPrevious();
            this.last.setNext(null);
            size--;

            return tempNode.getElement();
        }
    }

    @Override
    public Item first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else {
            return this.first.getElement();
        }
    }

    @Override
    public Item last() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        } else {
            return this.last.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String output = "";

        if (isEmpty()) {
            return output = "empty";
        } else {
            DoubleLinearNode<Item> tempNode = new DoubleLinearNode<Item>(this.last.getElement(), this.last.getNext(), this.last.getPrevious());

            while (tempNode.getPrevious() != null) {
                output += tempNode.getElement() + " ";
                tempNode.setElement(tempNode.getPrevious().getElement());
                tempNode.setPrevious(tempNode.getPrevious().getPrevious());
            }

            output += tempNode.getElement() + "";

            return output;
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