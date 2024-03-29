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
        for (int i = 0; i < M; i++) {
            entries[i] = new LinkedList<>();
        }
    }

    public CompletedTwoProbeChainHT(int size) {
        this.N = 0;
        this.M = size;
        this.entries = (LinkedList<Entry<Key, Value>>[]) new LinkedList[size];
        for (int i = 0; i < M; i++) {
            entries[i] = new LinkedList<>();
        }
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
        Entry<Key, Value> newEntry = new Entry<>(key, val);
        int hash1 = hash(key);
        if (this.N == 0) {
            entries[hash1].add(newEntry);
        } else {
            int hash2 = hash2(key);
            if (entries[hash1].size() <= entries[hash2].size()) {
                for (Entry<Key, Value> entry : entries[hash1]) {
                    if (entry.getKey().equals(key)) {
                        entry.val = val;
                        return;
                    }
                }
                entries[hash1].add(newEntry);
            } else {
                for (Entry<Key, Value> entry : entries[hash2]) {
                    if (entry.getKey().equals(key)) {
                        entry.val = val;
                        return;
                    }
                }
                entries[hash2].add(newEntry);
            }
        }
        this.N++;
    }

    @Override
    public Value get(Key key) {
        int hash1 = hash(key);
        int hash2 = hash2(key);
        for (Entry<Key, Value> entry : entries[hash1]) {

            if (entry.getKey().equals(key)) {
                return entry.getVal();
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey().equals(key)) {
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
            if (entry.getKey().equals(key)) {
                entries[hash1].remove(entry);
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey().equals(key)) {
                entries[hash2].remove(entry);
            }
        }
        this.N--;
    }

    @Override
    public boolean contains(Key key) {
        int hash1 = hash(key);
        int hash2 = hash2(key);
        for (Entry<Key, Value> entry : entries[hash1]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        for (Entry<Key, Value> entry : entries[hash2]) {
            if (entry.getKey().equals(key)) {
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
        LinkedList<Key> allKeys = new LinkedList<>();
        for (LinkedList<Entry<Key, Value>> chain : entries) {
            if (!chain.isEmpty()) {
                for (Entry<Key, Value> entry : chain) {
                    if (entry != null) {
                        allKeys.add(entry.getKey());
                    }
                }
            }
        }
        return allKeys;
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
        private Value val;

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