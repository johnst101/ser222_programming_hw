package edu.ser222.m04_02;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private LinkedList<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(BetterDiGraph graph) {
        int vCount = graph.getVertexCount();;
        onStack = new boolean[vCount];
        edgeTo = new int[vCount];
        marked = new boolean[vCount];
        for (int v = 0; v < vCount; v++) {
            if (!marked[v]) {
                    dfs(graph, v);
            }
        }
    }

    private void dfs(BetterDiGraph graph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : graph.getAdj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            } else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
