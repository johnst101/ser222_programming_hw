package edu.ser222.m01_03;

/**
 * CompletedOrderedlist represents an implementation of an ordered list that builds on
 * Completedlist.
 *
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */
public class CompletedOrderedList<T extends Comparable<T>> extends CompletedList<T>
         implements OrderedListADT<T> {

    @Override
    public void add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element, null, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            count++;
            modChange++;
        } else {
            DoubleLinearNode<T> curNode = new DoubleLinearNode<>(head.getElement(), head.getNext(), head.getPrevious());

            for (T elem : this) {
                if (element.compareTo(curNode.getElement()) <= 0) {
                    if (curNode.equals(head)) {
                        newNode.setNext(head);
                        head.setPrevious(newNode);
                        head = newNode;
                        count++;
                        modChange++;
                        return;
                    } else {
                        newNode.setNext(curNode);
                        newNode.setPrevious(curNode.getPrevious());
                        curNode.getPrevious().setNext(newNode);
                        curNode.setPrevious(newNode);
                        count++;
                        modChange++;
                        return;
                    }
                } else if ((element.compareTo(curNode.getElement()) > 0) && (curNode.getNext() == null)) {
                    newNode.setPrevious(tail);
                    tail.setNext(newNode);
                    tail = newNode;
                    count++;
                    modChange++;
                    return;
                }

                curNode = curNode.getNext();
            }
        }
    }
}
