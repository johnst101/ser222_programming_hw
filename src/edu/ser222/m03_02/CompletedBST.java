package edu.ser222.m03_02;

/**
 * A binary search tree based implementation of a symbol table.
 * <p>
 * Completion time: 11.0
 *
 * @author Tyler Johnson, Sedgewick, Acuna
 * @version 1.0
 */

import sun.awt.image.ImageWatched;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CompletedBST<Key extends Comparable<Key>, Value> implements BST<Key, Value> {
    private Node<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> iter = root;

        while (iter != null) {
            int cmp = key.compareTo(iter.key);

            if (cmp < 0)
                iter = iter.left;
            else if (cmp > 0)
                iter = iter.right;
            else
                return iter.val;
        }

        return null;
    }

    private Value get(Node<Key, Value> x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node<Key, Value> x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    @Override
    public Key min() {
        if (root == null)
            throw new NoSuchElementException();
        return min(root).key;
    }

    private Node<Key, Value> min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        if (root == null)
            throw new NoSuchElementException();
        return max(root).key;
    }

    private Node<Key, Value> max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        if (root == null)
            throw new NoSuchElementException();

        Node<Key, Value> x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node<Key, Value> t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node<Key, Value> select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node<Key, Value> x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    @Override
    public void deleteMin() {
        if (root == null)
            throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        if (root == null)
            return new LinkedList<>();
        else
            return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public Key ceiling(Key key) {
        //SKIP, UNNEEDED
        return null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMax() {
        if (root == null)
            throw new NoSuchElementException();
        root = deleteMax(root);
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size(Key lo, Key hi) {
        Iterable selectedKeys = keys(lo, hi);
        int count = 0;
        for (Object elem : selectedKeys) {
            count++;
        }
        return count;
    }

    public void putFast(Key key, Value val) {
        Node<Key, Value> x = root;
        if (isEmpty()) {
            root = new Node(key, val, 1);
            return;
        }
        Iterable keys = keys();
        for (Object elem : keys) {
            int cmp = key.compareTo((Key) elem);
            if (cmp == 0) {
                x.val = val;
                return;
            }
        }
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                if (x.left == null) {
                    x.N = x.N + 1;
                    x.left = new Node(key, val, 1);
                    return;
                }
                x.N = x.N + 1;
                x = x.left;
            } else if (cmp > 0) {
                if (x.right == null) {
                    x.N = x.N + 1;
                    x.right = new Node(key, val, 1);
                    return;
                }
                x.N = x.N + 1;
                x = x.right;
            }
        }
    }

    public Value getFast(Key key) {
        Node<Key, Value> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    public void balance() {
        if (root == null) return;
        root = balance(root);
    }

    private Node<Key, Value> balance(Node<Key, Value> x) {
        if (x == null) return x;
        int balanceFactor = size(x.left) - size(x.right);
        //if mismatch between left and right
        //while one side is greater than the other
            //then move min or max node to parent of x
            //balance both sides
        if (balanceFactor > 1) {
            int newFactor = size(x.left) - size(x.right);
            while (newFactor > 1) {
                x = reRootMax(x);
                root = x;
                balance(x.left);
                balance(x.right);
                newFactor = size(x.left) - size(x.right);
            }
        } else if (balanceFactor < -1) {
            int newFactor = size(x.left) - size(x.right);
            while (newFactor < -1) {
                x = reRootMin(x);
                root = x;
                balance(x.left);
                balance(x.right);
                newFactor = size(x.left) - size(x.right);
            }
        } else {
            //double check it's balanced
            balance(x.left);
            balance(x.right);
        }
        balance(x.left);
        balance(x.right);

        return x;
    }

    private Node<Key, Value> reRootMin(Node<Key, Value> x) {
        Node<Key, Value> parentRoot = parent(x);
        Node<Key, Value> min = min(x.right);
        Node<Key, Value> temp = x;
        x = min;
        x.left = temp;
        x.right = temp.right;
        if (parentRoot != null) {
            parentRoot.right = x;
        }
        temp.right = null;
        Node<Key, Value> parentMin = parent(x, min);
        if (parentMin != null) {
            parentMin.right = null;
        }
        return x;
    }

    private Node<Key, Value> reRootMax(Node<Key, Value> x) {
        //find min or max (potential caveat of if min or max has a child within subtree)
        Node<Key, Value> parentRoot = parent(x);
        Node<Key, Value> max = max(x.left);
        //temp node to parent of root
        Node<Key, Value> temp = x;
        //make min or max new parent of root with left or right (depending) child as root
        x = max;
        x.right = temp;
        x.left = temp.left;
        if (parentRoot != null) {
            parentRoot.left = x;
        }
        //swap right or left side of old root with the new root
        //old parent right or left needs to be null now
        temp.left = null;
        if (temp.right != null) {
            temp.N = temp.right.N + 1;
        } else {
            temp.N = 1;
        }
        //min or max parent must now be null
        Node<Key, Value> parentMax = parent(x, max);
        if (parentMax != null) {
            parentMax.right = null;
        }
        updateSizes(x);
        return x;
    }

    private Node<Key, Value> parent(Node<Key, Value> x) {
        return parent(root, x);
    }

    private Node<Key, Value> parent(Node<Key, Value> currentNode, Node<Key, Value> x) {
        if ((x == root) || (currentNode == null)) return null;
        else {
            if((currentNode.left == x) || (currentNode.right == x))
                return currentNode;
            else {
                if (currentNode.key.compareTo(x.key) < 0) {
                    return parent(currentNode.right, x);
                }
                else {
                    return parent(currentNode.left, x);
                }
            }
        }
    }

    private void updateSizes(Node<Key, Value> x) {
        if (x == null) return;
        updateSizes(x.left);
        updateSizes(x.right);
        if ((x.left != null) && (x.right != null)) {
            x.N = x.left.N + x.right.N + 1;
        } else if ((x.left == null) && (x.right != null)) {
            x.N = x.right.N + 1;
        } else if (x.left != null) {
            x.N = x.left.N + 1;
        } else {
            x.N = 1;
        }
    }

    public String displayLevel(Key key) {
        //TODO
        String result = "";
        Queue<Node<Key, Value>> queue = new LinkedList<>();
        Node<Key, Value> current = root;
        boolean found = false;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                found = true;
                break;
            }
        }
        if (found) {
            queue.add(current);
            while (!queue.isEmpty()) {
                Node<Key, Value> node = queue.poll();
                result += node.val + " ";
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    /**
     * entry point for testing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BST<Integer, String> bst = new CompletedBST();

        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");

        System.out.println(bst.contains(1));
        System.out.println(bst.contains(4));

        System.out.println(bst.size(1, 10));
        System.out.println(bst.size(4, 6));

        Iterable testKeys = bst.keys();
        String result = testKeys.toString();
        System.out.println(result);

        System.out.println("Before balance:");
        System.out.println(bst.displayLevel(10)); //root

        System.out.println("After balance:");
        bst.balance();
        System.out.println(bst.displayLevel(5)); //root
    }
}