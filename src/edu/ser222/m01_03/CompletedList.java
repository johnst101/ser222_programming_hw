package edu.ser222.m01_03;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * CompletedList represents an implementation of a list.
 *
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */
public class CompletedList<T> implements ListADT<T>, Iterable<T> {
    //The following three variables are a suggested start if you are using a list implementation.
    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    public CompletedList() {
        count = 0;
        modChange = 0;
        head = null;
        tail = null;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.");
        } else if (count == 1) {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            head = null;
            tail = null;
            count--;
            modChange++;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            head = tempNode.getNext();
            head.setPrevious(null);
            count--;
            modChange++;

            return tempNode.getElement();
        }
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.");
        } else if (count == 1) {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(tail.getElement(), tail.getNext(), tail.getPrevious());

            head = null;
            tail = null;
            count--;
            modChange++;

            return tempNode.getElement();
        } else {
            DoubleLinearNode<T> tempNode = new DoubleLinearNode<>(tail.getElement(), tail.getNext(), tail.getPrevious());

            tail = tempNode.getPrevious();
            tail.setNext(null);
            count--;
            modChange++;

            return tempNode.getElement();
        }
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.");
        } else {
            DoubleLinearNode<T> curNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());
            for (T elem : this) {
                if (elem.equals(element)) {
                    if (count == 1) {
                        head = null;
                        tail = null;

                    } else if (curNode.equals(head)) {
                        removeFirst();
                    } else if (curNode.equals(tail)) {
                        removeLast();
                    } else {
                        curNode.getPrevious().setNext(curNode.getNext());
                        curNode.getNext().setPrevious(curNode.getPrevious());
                    }
                    count--;
                    modChange++;
                    return curNode.getElement();
                }
                curNode = curNode.getNext();
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.");
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There is nothing to remove.");
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        for (T elem : this) {
            return elem.equals(target);
        }

        return false;
    }

    @Override
    public boolean isEmpty() { return count == 0; }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public String toString() {
        String output = "";

        if (isEmpty()) {
            return output = "empty";
        } else {
            for (T elem : this) {
                output += elem + " ";
            }

            return output;
        }
    }

    private class ListIterator implements Iterator<T> {
        private final int modCounted = modChange;
        private DoubleLinearNode<T> iter = head;

        public boolean hasNext() {
            if (modChange != modCounted) {
                throw new ConcurrentModificationException();
            }
            return iter != null;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            if (modChange != modCounted)
                throw new ConcurrentModificationException();

            T element = iter.getElement();
            iter = iter.getNext();

            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}