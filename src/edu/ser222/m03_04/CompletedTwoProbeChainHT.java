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
    private LinkedList<Entry<Key, Value>>[] entries;

    //any constructors must be made public
    public CompletedTwoProbeChainHT() {
        this.N = 0;
        this.M = 97;
        this.entries = (LinkedList<Entry<Key, Value>>[]) new LinkedList[M];
    }

    public CompletedTwoProbeChainHT(int M) {
        this.N = 0;
        this.M = M;
        this.entries = (LinkedList<Entry<Key, Value>>[]) new LinkedList[M];
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
        int hash1 = hash(key);
        int hash2 = hash2(key);
        Entry<Key, Value> newEntry = new Entry<>(key, val);
        if (entries[hash1].size() <= entries[hash2].size()) {
            entries[hash1].add(newEntry);
        } else {
            entries[hash2].add(newEntry);
        }
        this.N++;
    }

    @Override
    public Value get(Key key) {
        int hash1 = hash(key);
        int hash2 = hash2(key);
        for (Entry<Key, Value> entry : entries[hash1]) {
            if (entry.getKey() == key) {
                return entry.getVal();
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey() == key) {
                return entry.getVal();
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int hash1 = hash(key);
        int hash2 = hash2(key);
        for (Entry<Key, Value> entry : entries[hash1]) {
            if (entry.getKey() == key) {
                entries[hash1].remove(entry);
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey() == key) {
                entries[hash2].remove(entry);
            }
        }
    }

    @Override
    public boolean contains(Key key) {
        int hash1 = hash(key);
        int hash2 = hash2(key);
        for (Entry<Key, Value> entry : entries[hash1]) {
            if (entry.getKey() == key) {
                return true;
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
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

    public static class Entry<Key, Value> {
        private final Key key;
        private final Value val;

        public Entry(Key newKey, Value newVal) {
            this.key = newKey;
            this.val = newVal;
        }

        public Key getKey() {
            return this.key;
        }

        public Value getVal() {
            return this.val;
        }
    }
}