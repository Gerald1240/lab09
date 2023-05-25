package avltree;


public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    class NodeAVL extends Node {
        protected int bf;

        public NodeAVL(E data) {
            super(data);
            bf = 0;
        }

        public String toString() {
            return super.toString() + " (BF: " + bf + ")";
        }
    }

    private boolean height; // Indicador de cambio de altura

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected NodeAVL insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.data.compareTo(x);

            if (resC == 0)
                throw new ItemDuplicated(x + " ya se encuentra en el arbol...");

            if (resC < 0) {
                fat.right = insert(x, (NodeAVL) node.right);

                if (this.height) {
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.height = true;
                            break;
                        case 1: // bf = 2
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            } else {
                fat.left = insert(x, (NodeAVL) node.left);

                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1: // bf = -2
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }
    protected NodeAVL balanceToLeft(NodeAVL node) {
        if (node.left != null) {
            if (node.left.bf == -1) {
                node = rotateRight(node);
                node.bf = 0;
                node.right.bf = 0;
            } else if (node.left.bf == 0) {
                node.left.bf = -1;
                node.bf = 1;
                node = rotateRight(node);
            } else {
                NodeAVL leftChild = (NodeAVL) node.left;
                node.left = leftChild.right;
                leftChild.right = node;
                if (leftChild.bf == -1) {
                    node.bf = 0;
                    leftChild.bf = 0;
                } else {
                    node.bf = -1;
                    leftChild.bf = 1;
                }
                node = leftChild;
            }
        }
        return node;
    }

    protected NodeAVL balanceToRight(NodeAVL node) {
        if (node.right != null) {
            if (node.right.bf == 1) {
                node = rotateLeft(node);
                node.bf = 0;
                node.left.bf = 0;
            } else if (node.right.bf == 0) {
                node.right.bf = 1;
                node.bf = -1;
                node = rotateLeft(node);
            } else {
                NodeAVL rightChild = (NodeAVL) node.right;
                node.right = rightChild.left;
                rightChild.left = node;
                if (rightChild.bf == 1) {
                    node.bf = 0;
                    rightChild.bf = 0;
                } else {
                    node.bf = 1;
                    rightChild.bf = -1;
                }
                node = rightChild;
            }
        }
        return node;
    }
    protected NodeAVL rotateLeft(NodeAVL node) {
        NodeAVL pivot = (NodeAVL) node.right;
        node.right = pivot.left;
        pivot.left = node;
        return pivot;
    }

    protected NodeAVL rotateRight(NodeAVL node) {
        NodeAVL pivot = (NodeAVL) node.left;
        node.left = pivot.right;
        pivot.right = node;
        return pivot;
    }

    // Resto de la implementación de la clase AVLTree
}

