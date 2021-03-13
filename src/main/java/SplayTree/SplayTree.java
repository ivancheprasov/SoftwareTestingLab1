package SplayTree;

import java.util.ArrayList;

public class SplayTree {
    private Node root = null;

    private void makeRightChildParent(Node child, Node parent) {
        if (parent.getParent() != null) {
            if (parent == parent.getParent().getLeft())
                parent.getParent().setLeft(child);
            else
                parent.getParent().setRight(child);
        }
        if (child.getLeft() != null) child.getLeft().setParent(parent);
        child.setParent(parent.getParent());
        parent.setParent(child);
        parent.setRight(child.getLeft());
        child.setLeft(parent);
    }

    private void makeLeftChildParent(Node child, Node parent) {
        if (parent.getParent() != null) {
            if (parent == parent.getParent().getLeft())
                parent.getParent().setLeft(child);
            else
                parent.getParent().setRight(child);
        }
        if (child.getRight() != null) child.getRight().setParent(parent);
        child.setParent(parent.getParent());
        parent.setParent(child);
        parent.setLeft(child.getRight());
        child.setRight(parent);
    }

    public void insertAll(int ... keys){
        for (int key: keys) {
            insert(key);
        }
    }

    public void insert(int key) {
        Node root = this.root;
        Node parent = null;
        while (root != null) {
            parent = root;
            if (key > parent.getKey())
                root = root.getRight();
            else
                root = root.getLeft();
        }
        root = new Node(key);
        root.setParent(parent);
        if (parent == null)
            this.root = root;
        else if (key > parent.getKey())
            parent.setRight(root);
        else
            parent.setLeft(root);
        splay(root);
    }

    public void remove(int key) {
        Node node = findNode(key);
        if (node == null) return;
        splay(node);
        if (node.getLeft() != null && node.getRight() != null) {
            Node max = node.getLeft();
            while (max.getRight() != null) max = max.getRight();
            node.getLeft().setParent(null);
            splay(max);
            max.setRight(node.getRight());
            root = max;
        } else if (node.getRight() != null) {
            node.getRight().setParent(null);
            root = node.getRight();
        } else if (node.getLeft() != null) {
            node.getLeft().setParent(null);
            root = node.getLeft();
        } else {
            root = null;
        }
    }

    private Node findNode(int key) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            parent = node;
            if (key > node.getKey())
                node = node.getRight();
            else if (key < node.getKey())
                node = node.getLeft();
            else if (key == node.getKey()) {
                splay(node);
                return node;
            }
        }
        if (parent != null) {
            splay(parent);
            return null;
        }
        return null;
    }

    public boolean search(int key) {
        return findNode(key) != null;
    }

    private void splay(Node node) {
        while (node.getParent() != null) {
            Node parent = node.getParent();
            Node grandParent = parent.getParent();
            if (grandParent == null) {
                if (node == parent.getLeft())
                    makeLeftChildParent(node, parent);
                else
                    makeRightChildParent(node, parent);
            } else {
                if (node == parent.getLeft()) {
                    if (parent == grandParent.getLeft()) {
                        makeLeftChildParent(parent, grandParent);
                        makeLeftChildParent(node, parent);
                    } else {
                        makeLeftChildParent(node, node.getParent());
                        makeRightChildParent(node, node.getParent());
                    }
                } else {
                    if (parent == grandParent.getLeft()) {
                        makeRightChildParent(node, node.getParent());
                        makeLeftChildParent(node, node.getParent());
                    } else {
                        makeRightChildParent(parent, grandParent);
                        makeRightChildParent(node, parent);
                    }
                }
            }
        }
        root = node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    private void getNodeKeys(Node node, ArrayList<Integer> result) {
        if (node != null) {
            result.add(node.getKey());
            getNodeKeys(node.getLeft(), result);
            getNodeKeys(node.getRight(), result);
        } else {
            result.add(null);
        }
    }

    public ArrayList<Integer> getRootKeys() {
        ArrayList<Integer> result = new ArrayList<>();
        getNodeKeys(root, result);
        return result;
    }
}