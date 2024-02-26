package edu.ser222.m04_02;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An implementation of the EditableDiGraph interface that builds a Directed Graph to use for Kanji characters
 *
 * Completion time: 2.0 hrs
 *
 * @author Tyler Johnson
 * @version 02/24/2024
 */

public class BetterDiGraph implements EditableDiGraph {
    private int V;
    private int E;
    private HashMap<Integer, LinkedList<Integer>> adj;

    public BetterDiGraph() {
        this.V = 0;
        this.E = 0;
        adj = new HashMap<>();
    }

    public BetterDiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new HashMap<>();
    }

    @Override
    public void addEdge(int v, int w) {
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
        adj.get(v).add(w);
        this.E++;
    }

    @Override
    public void addVertex(int v) {
        if (containsVertex(v)) {
            return;
        }
        adj.put(v, new LinkedList<>());
        this.V++;
    }

    @Override
    public Iterable<Integer> getAdj(int v) {
        return adj.get(v);
    }

    @Override
    public int getEdgeCount() {
        return E;
    }

    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        int inDegCount = 0;
        for (LinkedList<Integer> list : adj.values()) {
            for (int vertex : list) {
                if (vertex == v) {
                    inDegCount++;
                }
            }
        }
        return inDegCount;
    }

    @Override
    public int getVertexCount() {
        return V;
    }

    @Override
    public void removeEdge(int v, int w) {
        if (containsVertex(v)) {
            adj.get(v).remove(w);
            this.E--;
        }
    }

    @Override
    public void removeVertex(int v) {
        if (containsVertex(v)) {
            adj.remove(v);
            this.V--;
            this.E--;
        }
    }

    @Override
    public Iterable<Integer> vertices() {
        return adj.keySet();
    }

    @Override
    public boolean isEmpty() {
        return this.V == 0;
    }

    @Override
    public boolean containsVertex(int v) {
        return adj.get(v) != null;
    }
}
