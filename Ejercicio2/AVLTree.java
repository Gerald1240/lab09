package Ejercicio2;

public class AVLTree<E extends Comparable<E>> {
    protected class Node {
        protected E data;
        protected Node left;
        protected Node right;
        protected int height;
        protected int balanceFactor;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
            this.balanceFactor = 0;
        }
    }

    protected Node root;

    public AVLTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E data) throws ItemDuplicated {
        this.root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("El elemento ya existe en el árbol AVL");
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.balanceFactor = getBalanceFactor(node);

        if (node.balanceFactor > 1 && data.compareTo(node.left.data) < 0) {
            return rotateRight(node);
        }

        if (node.balanceFactor < -1 && data.compareTo(node.right.data) > 0) {
            return rotateLeft(node);
        }

        if (node.balanceFactor > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (node.balanceFactor < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void remove(E data) throws ItemNotFound {
        this.root = removeRec(root, data);
    }

    private Node removeRec(Node node, E data) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("El elemento no existe en el árbol AVL");
        }

        if (data.compareTo(node.data) < 0) {
            node.left = removeRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = removeRec(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = findSuccessor(node.right);
                node.data = successor.data;
                node.right = removeRec(node.right, successor.data);
            }
        }

        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.balanceFactor = getBalanceFactor(node);

            if (node.balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
                return rotateRight(node);
            }

            if (node.balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (node.balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
                return rotateLeft(node);
            }

            if (node.balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private Node findSuccessor(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public E search(E data) {
        return searchRec(root, data);
    }

    private E searchRec(Node node, E data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return searchRec(node.right, data);
        } else {
            return node.data;
        }
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        y.balanceFactor = getBalanceFactor(y);

        x.height = 1 + Math.max(height(x.left), height(x.right));
        x.balanceFactor = getBalanceFactor(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        x.balanceFactor = getBalanceFactor(x);

        y.height = 1 + Math.max(height(y.left), height(y.right));
        y.balanceFactor = getBalanceFactor(y);

        return y;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
        } else {
            printTreeRec(root, 0);
        }
    }

    private void printTreeRec(Node node, int level) {
        if (node != null) {
            printTreeRec(node.right, level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }

            System.out.println(node.data);

            printTreeRec(node.left, level + 1);
        }
    }
}

