package edu.ser222.m01_03;

/**
 * CompletedOrderedList represents an implementation of an ordered list that builds on
 * CompletedList.
 *
 * @author Tyler Johnson, Acuna
 * @version 1.0
 */
public class CompletedOrderedList<T extends Comparable<T>> extends CompletedList<T>
         implements OrderedListADT<T> {
    //TODO: add MORE???? member variables
    private CompletedList<T> list;

    public CompletedOrderedList() {
        list = new CompletedList<>();
    }

    @Override
    public void add(T element) throws NullPointerException {
        DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element, null, null);
        if (list.isEmpty()) {
            list.head = newNode;
            list.tail = newNode;
            list.count++;
            list.modChange++;
        } else {
            DoubleLinearNode<T> curNode = new DoubleLinearNode<>(list.head.getElement(), list.head.getNext(), list.head.getPrevious());

            while (list.iterator().hasNext()) {
                if (element.compareTo(curNode.getElement()) <= 0) {
                    //TODO: complete add

                }
            }
        }
    }
}
