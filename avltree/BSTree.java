package avltree;

import java.util.Stack;

public class BSTree<E extends Comparable<E>> {
    	protected class Node {
            protected E data;
            protected Node left;
            protected Node right;
			public int bf;

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

    public BSTree(){ this.root = null; }

    public boolean isEmpty(){ return this.root == null; }
    
    public void insert (E x) throws ItemDuplicated {
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

    // Método para eliminar un nodo
    public void remove(E x) throws ItemNotFound {
        this.root = removeRec(this.root, x);
    }

    // Método recursivo para eliminar un nodo
    private Node removeRec(Node node, E x) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Elemento no encontrado en el BST");
        }

        if (x.compareTo(node.data) < 0) {
            node.left = removeRec(node.left, x);
        } else if (x.compareTo(node.data) > 0) {
            node.right = removeRec(node.right, x);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Nodo con dos hijos: obtener el inorden sucesor (el nodo más pequeño en el subárbol derecho)
            node.data = minValue(node.right);

            // Eliminar el inorden sucesor
            node.right = removeRec(node.right, node.data);
        }

        return node;
    }

    // Función para obtener el valor mínimo en el árbol (el nodo más a la izquierda)
    private E minValue(Node node) {
        E minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

    // Método para imprimir el árbol en InOrden
    public String toString(){
        return toStringRec(this.root);
    }

    // Método recursivo para imprimir el árbol en InOrden
    private String toStringRec(Node node){
        if (node == null) {
            return "";
        }

        return toStringRec(node.left) + node.data.toString() + " " + toStringRec(node.right);
    }
        // Método para iniciar el recorrido inOrden
        public void inOrden(){
            inOrden(this.root);
            System.out.println(); // Para agregar una nueva línea al final
        }
         
        // Método recursivo que realiza el recorrido inOrden a partir de un nodo
        protected void inOrden(Node actual){
            if (actual != null) {
                inOrden(actual.left);
                System.out.print(actual.data + " ");
                inOrden(actual.right);
            }
        }
//Ejercicios       
        //1.Agregue a la clase BSTree un método que retorne el número de nodos no-hojas de un BST
        public int countNonLeafNodes() {
            return countNonLeafNodes(root);
        }

        // Método auxiliar recursivo para contar los nodos no-hojas
        private int countNonLeafNodes(Node node) {
            if (node == null || (node.left == null && node.right == null)) {
                return 0;
            } else {
                return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
            }
        }
        //2.Agregue a la clase BSTree un método que retorne la altura de un nodo X cualquiera de un BST
        //siempre y cuando exista en el árbol.
        public int height(E x) throws ItemNotFound {
            Node node = findNode(root, x);
            if (node == null) {
                throw new ItemNotFound("El nodo no se encuentra en el BST.");
            }
            return height(node);
        }

        // Método auxiliar para encontrar un nodo en el BST
        private Node findNode(Node node, E x) {
            if (node == null) {
                return null;
            }
            int cmp = x.compareTo(node.data);
            if (cmp < 0) {
                return findNode(node.left, x);
            } else if (cmp > 0) {
                return findNode(node.right, x);
            } else {
                return node;
            }
        }

        // Método auxiliar recursivo para obtener la altura de un nodo dado
        private int height(Node node) {
            if (node == null) {
                return -1;
            } else {
                return 1 + Math.max(height(node.left), height(node.right));
            }
        }
        //3.Adicione a la clase BSTree un método iterative (no recursive) que recorra en PreOrden los nodos de un BST. 
        public void preOrderIterative() {
            if (root == null) {
                return;
            }

            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                System.out.print(node.data + " ");

                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        //4.Se define el área de un árbol binario como el número de nodos hojas multiplicado por la altura del árbol. Agregue un método a la clase BSTree que retorne el área del árbol binario de búsqueda. 
        public int getHeight() {
            return getHeightRecursive(root);
        }

        // Método auxiliar recursivo para calcular la altura
        private int getHeightRecursive(Node node) {
            if (node == null) {
                return 0;
            } else {
                return Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right)) + 1;
            }
        }

        // Método para obtener el número de nodos hojas del BST
        public int getLeafCount() {
            return getLeafCountRecursive(root);
        }

        // Método auxiliar recursivo para contar los nodos hojas
        private int getLeafCountRecursive(Node node) {
            if (node == null) {
                return 0;
            }
            if (node.left == null && node.right == null) {
                return 1;
            } else {
                return getLeafCountRecursive(node.left) + getLeafCountRecursive(node.right);
            }
        }

        // Método para obtener el área del BST
        public int getArea() {
            return getHeight() * getLeafCount();
        }
        //6.Adicione a la clase BSTree un método que retorne el nodo con el valor más bajo (mínimo) de un BST. 
        public E getMin() {
            if (isEmpty()) {
                return null; // Retorna null si el árbol está vacío
            } else {
                return getMin(root).data; // Inicia la búsqueda desde la raíz
            }
        }

        // Método recursivo para obtener el nodo con el valor mínimo
        private Node getMin(Node node) {
            if (node.left != null) {
                return getMin(node.left); // Si hay un nodo izquierdo, sigue buscando en el subárbol izquierdo
            } else {
                return node; // Si no hay un nodo izquierdo, este nodo tiene el valor mínimo
            }
        }
        //7. Adicione a la clase BSTree un método que retorne el nodo con el valor más alto (máximo) de un BST.
     // Método para obtener el valor máximo en el BST
        public E getMax() {
            if (isEmpty()) {
                return null; // Retorna null si el árbol está vacío
            } else {
                return getMax(root).data; // Inicia la búsqueda desde la raíz
            }
        }

        // Método recursivo para obtener el nodo con el valor máximo
        private Node getMax(Node node) {
            if (node.right != null) {
                return getMax(node.right); // Si hay un nodo derecho, sigue buscando en el subárbol derecho
            } else {
                return node; // Si no hay un nodo derecho, este nodo tiene el valor máximo
            }
        }
        //8.en la clase BSTree llamado parenthesize() que imprima esta
        //forma de visualización del árbol BST (en este caso se trata de un Binario de búsqueda).
        // Método para imprimir la representación entre paréntesis con sangría del BST
        public void parenthesize() {
            if (!isEmpty()) {
                parenthesize(root, 0); // Inicia la impresión desde la raíz con profundidad 0
            }
        }

        // Método recursivo para imprimir un nodo y sus subárboles con la sangría apropiada
        private void parenthesize(Node node, int depth) {
            for (int i = 0; i < depth; i++) { // Imprime la sangría
                System.out.print("  "); // Suponiendo que la sangría es de dos espacios
            }

            if (node == null) {
                System.out.println("null"); // Imprime "null" para los nodos vacíos
            } else {
                System.out.println(node.data + " ("); // Imprime el valor del nodo y abre un paréntesis

                parenthesize(node.left, depth + 1); // Imprime el subárbol izquierdo con una sangría adicional
                parenthesize(node.right, depth + 1); // Imprime el subárbol derecho con una sangría adicional

                for (int i = 0; i < depth; i++) { // Imprime la sangría
                    System.out.print("  "); // Suponiendo que la sangría es de dos espacios
                }
                System.out.println(")"); // Cierra el paréntesis
            }
        }
    }

