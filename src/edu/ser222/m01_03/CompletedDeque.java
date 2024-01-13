package edu.ser222.m01_03;

import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface. Also provides a main that
 * demonstrates it.
 * 
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */


public class CompletedDeque<Item> implements Deque<Item> {
    private CompletedNode<Item> first;
    private CompletedNode<Item> last;
    private int size;


    //TODO: implement all the methods
    public CompletedDeque () {

    }

    public void enqueueFront (Item element) {

    }

    public void enqueueBack (Item element) {

    }

    public Item dequeueFront() throws NoSuchElementException {
        // throws NoSuchElementException if deque is empty
    }

    public Item dequeueBack() throws NoSuchElementException {
        // throws NoSuchElementException if deque is empty
    }

    public Item first () throws NoSuchElementException {
        return first;
    }

    public Item last () throws NoSuchElementException {
        return last;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public class CompletedNode<Item> {
        private CompletedNode<Item> next;
        private CompletedNode<Item> previous;
        private Item element;

        public CompletedNode () {
            this.next = null;
            this.previous = null;
            this.element = null;
        }

        public CompletedNode (Item elem) {
            this.next = null;
            this.previous = null;
            this.element = elem;
        }

        public CompletedNode<Item> getNext () {
            return next;
        }

        public CompletedNode<Item> getprevious () {
            return previous;
        }

        public void setNext (CompletedNode<Item> nextNode) {
            next = nextNode;
        }

        public void setPrevious (CompletedNode<Item> previousNode) {
            previous = previousNode;
        }

        public Item getElement () {
            return element;
        }

        public void setElement (Item elem) {
            element = elem;
        }
    }

    /**
     * Program entry point for deque. 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        CompletedDeque<Integer> deque = new CompletedDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();        
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());   

        //deque features
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