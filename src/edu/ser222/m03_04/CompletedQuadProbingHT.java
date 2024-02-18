package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with quadratic probing.
 * 
 * @author Tyler Johnson, Acuna
 */
public class CompletedQuadProbingHT<Key, Value> extends CompletedLinearProbingHT<Key, Value> {
    private int N;
    private int M;
    private Entry<Key, Value>[] entries;

    //any constructors must be made public
    public CompletedQuadProbingHT() {
        this.N = 0;
        this.M = 997;
        this.entries = (Entry<Key, Value>[]) new Entry[this.M];
    }

    public CompletedQuadProbingHT(int size) {
        this.N = 0;
        this.M = size;
        this.entries = (Entry<Key, Value>[]) new Entry[size];
    }

    public int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i * i) % this.M;
    }
}