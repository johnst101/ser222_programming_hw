package edu.ser222.m02_01;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * TODO: description
 * 
 * Completion time: TODO: (estimation of hours spent on this program)
 *
 * @author Tyler Johnson, Acuna, Sedgewick
 * @version 1.0
 */


public class CompletedBenchmarkTool implements BenchmarkTool {

    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    //TODO: implement interface methods.
    @Override
    public Integer[] generateTestDataBinary(int size) {
        Integer[] binTest = new Integer[size];

        for (int i = 0; i < size / 2; i++) {
            binTest[i] = 0;
        }
        for (int j = size / 2; j < size; j++) {
            binTest[j] = 1;
        }

        return binTest;
    }

    @Override
    public Integer[] generateTestDataHalves(int size) {
        Integer[] halfTest = new Integer[size];
        int h = 1;
        int halfStart = size / 2;
        int fillLength = 0;

        for (int i = 0; i < size / 2; i++) {
            halfTest[i] = 0;
        }
        while (halfStart < size) {
            fillLength = (size - halfStart) / 2;
            if (fillLength == 0) {
                fillLength = 1;
            }
            for (int j = 0; j < fillLength; j++) {
                halfTest[halfStart + j] = h;
            }
            h++;
            halfStart += fillLength;
        }
        return halfTest;
    }

    @Override
    public Integer[] generateTestDataHalfRandom(int size) {
        Integer[] randIntTest = new Integer[size];
        Random rand = new Random();

        for (int i = 0; i < size / 2; i++) {
            randIntTest[i] = 0;
        }
        for (int j = size / 2; j < size; j++) {
            randIntTest[j] = rand.nextInt(Integer.MAX_VALUE);
        }

        return randIntTest;
    }

    @Override
    public double computeDoublingFormula(double t1, double t2) {
        return 0;
    }

    @Override
    public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
        double t1;
        double t2;

        Stopwatch sw1 = new Stopwatch();
        insertionSort(small);
        t1 = sw1.elapsedTime();
        
        Stopwatch sw2 = new Stopwatch();
        insertionSort(large);
        t2 = sw2.elapsedTime();
        
        return computeDoublingFormula(t1, t2);
    }

    @Override
    public double benchmarkShellsort(Integer[] small, Integer[] large) {
        double t1;
        double t2;

        Stopwatch sw1 = new Stopwatch();
        shellsort(small);
        t1 = sw1.elapsedTime();

        Stopwatch sw2 = new Stopwatch();
        shellsort(large);
        t2 = sw2.elapsedTime();

        return computeDoublingFormula(t1, t2);
    }

    @Override
    public void runBenchmarks(int size) {
        DecimalFormat formatter = new DecimalFormat("#0.000");

        Integer[] binSmall = generateTestDataBinary(size);
        Integer[] binLarge = generateTestDataBinary(2 * size);
        Integer[] halfSmall = generateTestDataHalves(size);
        Integer[] halfLarge = generateTestDataHalves(2 * size);
        Integer[] randSmall = generateTestDataHalfRandom(size);
        Integer[] randLarge = generateTestDataHalfRandom(2 * size);

        double insertionBin = benchmarkInsertionSort(binSmall, binLarge);
        double shellBin = benchmarkShellsort(binSmall, binLarge);
        double insertionHalf = benchmarkInsertionSort(halfSmall, halfLarge);
        double shellHalf = benchmarkShellsort(halfSmall, halfLarge);
        double insertionRand = benchmarkInsertionSort(randSmall, randLarge);
        double shellRand = benchmarkShellsort(randSmall, randLarge);

        System.out.println("       Insertion\tShellsort");
        System.out.println("Bin    " + formatter.format(insertionBin) + "    \t" + formatter.format(shellBin) + "    ");
        System.out.println("Half   " + formatter.format(insertionHalf) + "    \t" + formatter.format(shellHalf) + "    ");
        System.out.println("RanInt " + formatter.format(insertionRand) + "    \t" + formatter.format(shellRand) + "    ");
    }

    public static void main(String args[]) {
        BenchmarkTool me = new CompletedBenchmarkTool();
        int size = 4096;

        //NOTE: feel free to change size here. all other code must go in the
        //      methods.

        me.runBenchmarks(size);
    }
}