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
    //TODO: add member variables
    //protected int count;
    //protected int modChange;
    //protected DoubleLinearNode<T> head, tail;

    public CompletedList<T>() { //TODO: complete default constructor

    }

    public CompletedList<T>() { //TODO: complete input fed constructor

    }

    @Override
    public T removeFirst() throws NoSuchElementException { //TODO: complete removeFirst
        return null;
    }

    @Override
    public T removeLast() throws NoSuchElementException { //TODO: complete removeLast
        return null;
    }

    @Override
    public T remove(T element) { //TODO: complete remove
        return null;
    }

    @Override
    public T first() { //TODO: complete first
        return null;
    }

    @Override
    public T last() { //TODO: complete last
        return null;
    }

    @Override
    public boolean contains(T target) { //TODO: complete contains
        return false;
    }

    @Override
    public boolean isEmpty() { //TODO: complete isEmpty
        return false;
    }

    @Override
    public int size() { //TODO: complete size;
        return 0;
    }

    @Override
    public Iterator<T> iterator() { //TODO: complete iterator
        return null;
    }
}