package edu.ser222.m04_02;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.awt.image.ImageWatched;

import java.util.HashMap;
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
        LinkedList<Integer> vertsToDel = new LinkedList<>();
        while (graph.getVertexCount() != 0) {
            for (int i : graph.vertices()) {
                int inDegree = graph.getIndegree(i);
                if (inDegree == 0) {
                    order.add(i);
                    vertsToDel.add(i);
                }
            }
            for (int j : vertsToDel) {
                graph.removeVertex(j);
            }
        }
    }

    private class DirectedCycle {
        private HashMap<Integer, Boolean> marked;
        private HashMap<Integer, Integer> edgeTo;
        private LinkedList<Integer> cycle;
        private HashMap<Integer, Boolean> onStack;

        public DirectedCycle(EditableDiGraph graph) {
            Iterable<Integer> verts = graph.vertices();
            onStack = new HashMap<>();
            for (int v : verts) {
                onStack.put(v, false);
            }
            edgeTo = new HashMap<>();
            marked = new HashMap<>();
            for (int v : verts) {
                marked.put(v, false);
            }
            for (int v : verts) {
                if (!marked.get(v)) {
                    dfs(graph, v);
                }
            }
        }

        private void dfs(EditableDiGraph graph, int v) {
            onStack.put(v, true);
            marked.put(v, true);
            for (int w : graph.getAdj(v)) {
                if (this.hasCycle()) {
                    return;
                } else if (!marked.get(w)) {
                    edgeTo.put(w, v);
                    dfs(graph, w);
                } else if (onStack.get(w)) {
                    cycle = new LinkedList<>();
                    for (int x = v; x != w; x = edgeTo.get(x)) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
            onStack.put(v, false);
        }

        public boolean hasCycle() {
            return cycle != null;
        }

        public Iterable<Integer> cycle() {
            return cycle;
        }
    }
}
