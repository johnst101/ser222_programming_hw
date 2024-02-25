package edu.ser222.m04_02;

import java.util.LinkedList;

/**
 * An implementation of the TopologicalSort interface that builds a topological sort from a BetterDiGraph without using DFS
 *
 * Completion time: 1.0 hrs
 *
 * @author Tyler Johnson
 * @version 02/24/2024
 */

public class IntuitiveTopological implements TopologicalSort {
    private LinkedList<Integer> order;

    public IntuitiveTopological(EditableDiGraph graph) {
        DirectedCycle cycleFinder = new DirectedCycle(graph);
        order = new LinkedList<>();
        if (!cycleFinder.hasCycle()) {
            findOrder(graph);
        }
    }

    @Override
    public Iterable<Integer> order() {
        return order;
    }

    @Override
    public boolean isDAG() {
        return order != null;
    }

    private void findOrder(EditableDiGraph graph) {
        for (int i : graph.vertices()) {
            int inDegree = graph.getIndegree(i);
            if (inDegree == 0) {
                order.add(i);
                graph.removeVertex(i);
            }
        }
    }

    private class DirectedCycle {
        private boolean[] marked;
        private int[] edgeTo;
        private LinkedList<Integer> cycle;
        private boolean[] onStack;

        public DirectedCycle(EditableDiGraph graph) {
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

        private void dfs(EditableDiGraph graph, int v) {
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
}
