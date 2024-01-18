package edu.ser222.m01_03;

import java.util.Iterator;

/**
 * CompletedOrderedList testing area.
 * 
 * @author (your name), Acuna
 * @version (version)
 */
public class Driver {
    public static void main(String [] args) {
        OrderedListADT<Integer> list = new CompletedOrderedList<>();
        
        //RA: These are _extremely_ simple tests! You will need to write more!
        
        list.add(23);
        System.out.println("Got Here 1");
        list.add(24);
        System.out.println("Got Here 2");
        list.add(16);
        System.out.println("Got Here 3");
        list.add(3);
        System.out.println("Got Here 4");
        list.add(7);
        System.out.println("Got Here 5");
        list.add(17);
        System.out.println("Got Here 6");
        list.add(9);
        System.out.println("Got Here 7");
        list.add(13);
        System.out.println("Got Here 8");
        list.add(14);
        System.out.println("Got Here 9");
        list.add(1);
        System.out.println("Got Here 10");

        System.out.println(list);
        System.out.println("Got Here 11");
        
        list.remove(7);
        System.out.println("Got Here 12");
        list.removeFirst();
        System.out.println("Got Here 13");
        list.remove(17);
        System.out.println("Got Here 14");
        list.removeLast();
        System.out.println("Got Here 15");
        list.remove(14);
        System.out.println("Got Here 16");
        list.removeLast();
        System.out.println("Got Here 17");

        //display using toString()
        System.out.println(list);
        
        /* Test Results:
            1 3 7 9 13 14 16 17 23 24 
            3 9 13 16 
        */

        //display using automatic iterator way
        for(Integer x : list) {
            System.out.print(x + " ");
        }
        System.out.println();

        //display using manual iterator way
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next());
            if (iter.hasNext())
                System.out.print(" ");
        }

        //toString is probably the nicest if we just need to display.

        //You should definitely write a test for ConcurrentModificationException...
    }
}
