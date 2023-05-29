package Ejercicio1;

public class BSTree<E extends Comparable<E>> {
    protected class Node {
        protected E data;
        protected Node left;
        protected Node right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return data.toString();
        }
    }

    protected Node root;

    public BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) throws ItemDuplicated {
        this.root = insertRec(this.root, x);
    }

    private Node insertRec(Node node, E x) throws ItemDuplicated {
        if (node == null) {
            return new Node(x);
        }

        if (x.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, x);
        } else if (x.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, x);
        } else {
            throw new ItemDuplicated("El elemento ya existe en el BST");
        }

        return node;
    }

    public E search(E x) throws ItemNotFound {
        return searchRec(this.root, x);
    }

    private E searchRec(Node node, E x) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Elemento no encontrado en el BST");
        }

        if (x.compareTo(node.data) < 0) {
            return searchRec(node.left, x);
        } else if (x.compareTo(node.data) > 0) {
            return searchRec(node.right, x);
        } else {
            return node.data;
        }
    }

    public void remove(E x) throws ItemNotFound {
        this.root = removeRec(this.root, x);
    }

    private Node removeRec(Node node, E x) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Elemento no encontrado en el BST");
        }

        if (x.compareTo(node.data) < 0) {
            node.left = removeRec(node.left, x);
        } else if (x.compareTo(node.data) > 0) {
            node.right = removeRec(node.right, x);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = minValue(node.right);

            node.right = removeRec(node.right, node.data);
        }

        return node;
    }

    private E minValue(Node node) {
        E minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }
    
    public int height() {
        return heightRec(this.root);
    }
    
    private int heightRec(Node node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = heightRec(node.left);
        int rightHeight = heightRec(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

