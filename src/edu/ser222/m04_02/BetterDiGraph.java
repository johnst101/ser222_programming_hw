package edu.ser222.m04_02;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An implementation of the EditableDiGraph interface that builds a Directed Graph to use for Kanji characters
 *
 * Completion time: TODO: fill in
 *
 * @author Tyler Johnson
 * @version 02/24/2024
 */

public class BetterDiGraph implements EditableDiGraph {
    private int V;
    private int E;
    private int arraySize;
    private LinkedList<Integer>[] adj;

    public BetterDiGraph() {
        this.V = 0;
        this.E = 0;
        this.arraySize = 100;
        adj = (LinkedList<Integer>[]) new LinkedList[this.arraySize];
    }

    public BetterDiGraph(int V) {
        this.V = V;
        this.E = 0;
        this.arraySize = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        // TODO: implement
        if (isEmpty()) {
            addVertex(v);
            addVertex(w);
        }
        if (!containsVertex(v)) {
            addVertex(v);
        }
        if (!containsVertex(w)) {
            addVertex(w);
        }
        adj[v].add(w);
        E++;
    }

    @Override
    public void addVertex(int v) {
        if (v > arraySize) {
            resizeAdj();
        }
        adj[v] = new LinkedList<>();
        this.V++;
    }

    @Override
    public Iterable<Integer> getAdj(int v) {
        // TODO: implement
        return null;
    }

    @Override
    public int getEdgeCount() {
        return E;
    }

    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        // TODO: implement
        return 0;
    }

    @Override
    public int getVertexCount() {
        return V;
    }

    @Override
    public void removeEdge(int v, int w) {
        // TODO: implement
    }

    @Override
    public void removeVertex(int v) {
        // TODO: implement
    }

    @Override
    public Iterable<Integer> vertices() {
        // TODO: implement
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.V == 0;
    }

    @Override
    public boolean containsVertex(int v) {
        return adj[v] != null;
    }

    public void resizeAdj() {
        this.arraySize *= 2;
        LinkedList<Integer>[] temp = (LinkedList<Integer>[]) new LinkedList[this.arraySize];
        for (int i = 0; i < adj.length; i++) {
            temp[i] = adj[i];
        }
        adj = temp;
    }
}
