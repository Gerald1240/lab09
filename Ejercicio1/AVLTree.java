package Ejercicio1;

public class AVLTree<E extends Comparable<E>> {
    protected class NodeAVL {
        protected E data;
        protected NodeAVL left;
        protected NodeAVL right;
        protected int height;
        protected int bf; // Factor de balance

        public NodeAVL(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
            this.bf = 0;
        }
    }

    protected NodeAVL root;
    protected String lastRotation;
    protected int lastBalanceFactor;

    public AVLTree() {
        this.root = null;
        this.lastRotation = "";
        this.lastBalanceFactor = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E data) throws ItemDuplicated {
        this.root = insertRec(root, data);
    }

    private NodeAVL insertRec(NodeAVL node, E data) throws ItemDuplicated {
        if (node == null) {
            return new NodeAVL(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("El elemento ya existe en el árbol AVL");
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.bf = getBalance(node);

        if (node.bf > 1 && data.compareTo(node.left.data) < 0) {
            lastRotation = "RSR";
            lastBalanceFactor = node.bf;
            return rotateRight(node);
        }

        if (node.bf < -1 && data.compareTo(node.right.data) > 0) {
            lastRotation = "RSL";
            lastBalanceFactor = node.bf;
            return rotateLeft(node);
        }

        if (node.bf > 1 && data.compareTo(node.left.data) > 0) {
            lastRotation = "RDR";
            lastBalanceFactor = node.bf;
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (node.bf < -1 && data.compareTo(node.right.data) < 0) {
            lastRotation = "RDL";
            lastBalanceFactor = node.bf;
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public E search(E x) {
        return searchRec(root, x);
    }

    private E searchRec(NodeAVL node, E x) {
        if (node == null) {
            return null;
        }

        if (x.compareTo(node.data) < 0) {
            return searchRec(node.left, x);
        } else if (x.compareTo(node.data) > 0) {
            return searchRec(node.right, x);
        } else {
            return node.data;
        }
    }

    private NodeAVL rotateRight(NodeAVL y) {
        NodeAVL x = y.left;
        NodeAVL T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.bf = getBalance(y);

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.bf = getBalance(x);

        return x;
    }

    private NodeAVL rotateLeft(NodeAVL x) {
        NodeAVL y = x.right;
        NodeAVL T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.bf = getBalance(x);

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.bf = getBalance(y);

        return y;
    }

    int height(NodeAVL node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int getBalance(NodeAVL node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    public String getLastRotation() {
        return lastRotation;
    }

    public int getLastBalanceFactor() {
        return lastBalanceFactor;
    }

    public NodeAVL getRoot() {
        return root;
    }
}

