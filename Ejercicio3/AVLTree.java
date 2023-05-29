package Ejercicio3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AVLTree<E extends Comparable<E>> implements Iterable<E> {
    protected class NodeAVL {
        protected E data;
        protected NodeAVL left;
        protected NodeAVL right;
        protected int height;
        protected int balanceFactor;

        public NodeAVL(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
            this.balanceFactor = 0;
        }
    }

    protected NodeAVL root;

    public AVLTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E data) {
        root = insertRec(root, data);
    }

    private NodeAVL insertRec(NodeAVL node, E data) {
        if (node == null) {
            return new NodeAVL(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
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

    public void delete(E data) {
        root = deleteRec(root, data);
    }

    private NodeAVL deleteRec(NodeAVL node, E data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = deleteRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteRec(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                NodeAVL successor = findSuccessor(node.right);
                node.data = successor.data;
                node.right = deleteRec(node.right, successor.data);
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

    public boolean search(E data) {
        return searchRec(root, data);
    }

    private boolean searchRec(NodeAVL node, E data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return searchRec(node.right, data);
        } else {
            return true;
        }
    }

    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(NodeAVL node, int level) {
        if (node == null) {
            return;
        }

        printTreeRec(node.right, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.data);

        printTreeRec(node.left, level + 1);
    }

    private NodeAVL rotateRight(NodeAVL y) {
        NodeAVL x = y.left;
        NodeAVL T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.balanceFactor = getBalanceFactor(y);

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.balanceFactor = getBalanceFactor(x);

        return x;
    }

    private NodeAVL rotateLeft(NodeAVL x) {
        NodeAVL y = x.right;
        NodeAVL T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.balanceFactor = getBalanceFactor(x);

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.balanceFactor = getBalanceFactor(y);

        return y;
    }

    private NodeAVL findSuccessor(NodeAVL node) {
        NodeAVL current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(NodeAVL node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(NodeAVL node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    @Override
    public Iterator<E> iterator() {
        return new AVLTreeIterator();
    }

    private class AVLTreeIterator implements Iterator<E> {
        private NodeAVL nextNode;

        public AVLTreeIterator() {
            this.nextNode = findMin(root);
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E data = nextNode.data;
            nextNode = findSuccessor(nextNode);
            return data;
        }

        private NodeAVL findMin(NodeAVL node) {
            if (node == null) {
                return null;
            }

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        private NodeAVL findSuccessor(NodeAVL node) {
            if (node.right != null) {
                return findMin(node.right);
            }

            NodeAVL successor = null;
            NodeAVL ancestor = root;

            while (ancestor != node) {
                if (node.data.compareTo(ancestor.data) < 0) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }

            return successor;
        }
    }
    public void insertarEmpleado(Empleado empleado) {
        insert((E) empleado);
    }

    public static Empleado buscarEmpleado(int destino) {
        Empleado empleadoEncontrado = null;
		try {
			empleadoEncontrado = AVLTree.buscarEmpleado(destino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return empleadoEncontrado;
    }
    private boolean buscarEmpleadoRec(NodeAVL node, int destino) {
        if (node == null) {
            return false;
        }

        int empleadoDestino = Integer.parseInt(((Empleado) node.data).getDestino()); // Convertir el destino a entero

        if (destino < empleadoDestino) {
            return buscarEmpleadoRec(node.left, destino);
        } else if (destino > empleadoDestino) {
            return buscarEmpleadoRec(node.right, destino);
        } else {
            return true;
        }
    }


    public void eliminarEmpleado(int numeroIdt) {
        root = eliminarEmpleadoRec(root, numeroIdt);
    }

    private NodeAVL eliminarEmpleadoRec(NodeAVL node, int numeroIdt) {
        if (node == null) {
            return null;
        }

        Empleado empleado = (Empleado) node.data;
        if (empleado.getNumeroIdt() == numeroIdt) {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                NodeAVL sucesor = findSucesor(node.right);
                node.data = sucesor.data;
                node.right = eliminarEmpleadoRec(node.right, ((Empleado) sucesor.data).getNumeroIdt());
            }
        } else if (numeroIdt < empleado.getNumeroIdt()) {
            node.left = eliminarEmpleadoRec(node.left, numeroIdt);
        } else {
            node.right = eliminarEmpleadoRec(node.right, numeroIdt);
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

    private NodeAVL findSucesor(NodeAVL node) {
        NodeAVL current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


}


