package edu.ser222.m01_03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CompletedList represents an implementation of a list.
 *
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */
public class CompletedList<T> implements ListADT<T>, Iterable<T> {
    //The following three variables are a suggested start if you are using a list implementation.
    //TODO: confirm these are the only needed members
    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    public CompletedList() {
        count = 0;
        modChange = 0;
        head = null;
        tail = null;
    }
//MAY NOT BE NEEDED
//    public CompletedList() { //TODO: complete input fed constructor??
//
//    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.")
        } else if(count == 1) {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            head = null;
            tail = null;
            count--;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            first = tempNode.getNext();
            first.setPrevious(null);
            count--;

            return tempNode.getElement();
        }
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.")
        } else if(count == 1) {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            head = null;
            tail = null;
            count--;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            last = tempNode.getPrevious();
            last.setNext(null);
            count--;

            return tempNode.getElement();
        }
    }

    @Override
    public T remove(T element) { //TODO: complete remove
        return null;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.")
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.")
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) { //TODO: complete contains
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() { //TODO: complete iterator
        return null;
    }
}