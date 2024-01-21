package edu.ser222.m01_03;

/**
 * Represents a node in a doubly linked list.
 *
 * @author Tyler Johnson, Acuna, Lewis, Chase
 * @version 1.0
 */
public class DoubleLinearNode<T> {
    private DoubleLinearNode<T> next;
    private DoubleLinearNode<T> previous;
    private T element;

    public DoubleLinearNode() {
        next = null;
        previous = null;
        element = null;
    }

    public DoubleLinearNode(T elem, DoubleLinearNode<T> nextNode, DoubleLinearNode<T> previousNode) {
        next = nextNode;
        previous = previousNode;
        element = elem;
    }

    public DoubleLinearNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinearNode<T> nextNode) {
        next = nextNode;
    }

    public DoubleLinearNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinearNode<T> previousNode) {
        previous = previousNode;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T elem ) {
        element = elem;
    }

    public boolean equals(DoubleLinearNode<T> o) {
        if (this == o) return true;
        return (o.getElement() == this.getElement()) && (o.getNext() == this.getNext()) && (o.getPrevious() == this.getPrevious());
    }
}
