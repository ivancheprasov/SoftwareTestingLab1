package splayTree;

public class Node {
    private final int key;
    private Node node = null;
    private Node right = null;
    private Node parent = null;
    public Node(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return node;
    }

    public void setLeft(Node left) {
        this.node = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
