package edu.ser222.m02_02;

/**
 * Implements various divide and conquer algorithms.
 *
 * Last updated 02/3/24.
 *
 * Completion time: 3.0 hours TODO: fill in
 *
 * @author Tyler Johnson, Acuna, Sedgewick and Wayne
 * @verison 1.0
 */
import java.util.Random;

public class CompletedMerging implements MergingAlgorithms {

    //TODO: implement interface methods.
    @Override
    public <T extends Comparable> Queue<T> mergeQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> mergedQueue = new ListQueue<>();
        while(!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) {
                mergedQueue.enqueue(q2.dequeue());
                continue;
            }
            if (q2.isEmpty()) {
                mergedQueue.enqueue(q1.dequeue());
                continue;
            }
            T i = q1.peek(), j = q2.peek();
            if (less(i,j)) {
                mergedQueue.enqueue(q1.dequeue());
            } else {
                mergedQueue.enqueue(q2.dequeue());
            }
        }

        return mergedQueue;
    }

    @Override
    public void sort(Comparable[] a) {
        mergesort(a);
        assert isSorted(a);
    }

    @Override
    public Comparable[] mergesort(Comparable[] a) {
        //TODO: implement this!
        if (a.length <= 1) {
            return a;
        }
        Comparable[] split1;
        Comparable[] split2;
        int normLength = a.length / 2;
        if (a.length % 2 != 0) {
            split1 = new Comparable[normLength];
            split2 = new Comparable[(normLength) + 1];
        } else {
            split1 = new Comparable[normLength];
            split2 = new Comparable[normLength];
        }

        int i;
        for (i = 0; i < split1.length; i++) {
            split1[i] = a[i];
        }
        int k = i;
        for (int j = 0; j < split2.length; j++) {
            split2[j] = a[k];
            k++;
        }
        if (split1.length <= 1 && split2.length <= 1) {
            return merge(split1, split2);
        }
        if (split1.length > 1 && split2.length > 1) {
            mergesort(split1);
            mergesort(split2);
        }
        if (split1.length > 1){
            mergesort(split1);
        }
        if (split2.length > 1) {
            mergesort(split2);
        }

        return a = merge(split1, split2);
    }

    @Override
    public Comparable[] merge(Comparable[] a, Comparable[] b) {
        Comparable[] newArray = new Comparable[a.length + b.length];

        int aTrace = 0, bTrace = 0;
        while ((aTrace < a.length) || (bTrace < b.length)) {
            if (aTrace >= a.length) {
                if (newArray.length == 0) {
                    newArray[0] = b[bTrace];
                    b
                }
                for (int k = 0; k < newArray.length; k++) {
                    newArray[k] = b[bTrace];
                    bTrace++;
                continue;
            }
            if (bTrace >= b.length) {
                newArray[newArrTrace] = a[aTrace];
                aTrace++;
                newArrTrace++;
                continue;
            }
            if (less(a[aTrace],b[bTrace])) {
                newArray[newArrTrace] = a[aTrace];
                aTrace++;
                newArrTrace++;
            } else {
                newArray[newArrTrace] = b[bTrace];
                bTrace++;
                newArrTrace++;
            }
        }
        //TODO: double check below requirements are met and remove comments
        //types must match between input arrays
        //input arrays must not change
        //input arrays must not be returned as the result
        //can assume input does not contain nulls
        return newArray;
    }

    @Override
    public void shuffle(Object[] a) {
        //TODO: implement this!
    }
     
    /**
     * entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<String> q1 = new ListQueue<>(); q1.enqueue("E"); q1.enqueue("L"); q1.enqueue("O"); q1.enqueue("R"); q1.enqueue("T");
        System.out.println(q1.toString());
        Queue<String> q2 = new ListQueue<>(); q2.enqueue("A"); q2.enqueue("E"); q2.enqueue("M"); q2.enqueue("P"); q2.enqueue("S"); q2.enqueue("X");
        Queue<Integer> q3 = new ListQueue<>(); q3.enqueue(5); q3.enqueue(12); q3.enqueue(15); q3.enqueue(17); q3.enqueue(20);
        Queue<Integer> q4 = new ListQueue<>(); q4.enqueue(1); q4.enqueue(4); q4.enqueue(12); q4.enqueue(13); q4.enqueue(16); q4.enqueue(18);

        MergingAlgorithms ma = new CompletedMerging();

        //Q1 - sample test cases
        Queue merged1 = ma.mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = ma.mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] test1 = {"T", "Y", "L", "E", "R"};
        String[] test2 = {"B", "G", "J", "L", "O", "Q"};
        Comparable[] mergeTest = ma.merge(test1, test2);
        Comparable[] test = ma.mergesort(test1);
        show(mergeTest);
        show(test);
        ma.sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ma.shuffle(b);
        show(b);
        
        ma.shuffle(b);
        show(b);
    }

    //below are utilities functions, please do not change them.
        
    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}