package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author Tyler Johnson, Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    private int N; // number of key value pairs
    private int M; // hash table size
    private CompletedTwoProbeChainHT<Key, Value>[] st; //TODO: not understanding what to use for the array


    //any constructors must be made public
    public CompletedTwoProbeChainHT() {

    }

    @Override
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int hash2(Key key) {
        return (((key.hashCode() & 0x7f) % M) * 31) % M;
    }

    @Override
    public void put(Key key, Value val) {
        //TODO

    }

    @Override
    public Value get(Key key) {
        //TODO
        return null;
    }

    @Override
    public void delete(Key key) {
        //TODO
    }

    @Override
    public boolean contains(Key key) {
        //TODO
        return false;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return false;
    }

    @Override
    public int size() {
        //TODO
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        //TODO
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public int getChainSize(int i) {
        return entries[i].size();
    }
}