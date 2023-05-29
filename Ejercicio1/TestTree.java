package Ejercicio1;

public class TestTree {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        // Crear un árbol BST
        BSTree<Integer> bstTree = new BSTree<>();
        bstTree.insert(7);
        bstTree.insert(10);
        bstTree.insert(3);
        bstTree.insert(15);
        bstTree.insert(5);
        bstTree.insert(20);
        bstTree.insert(12);
  
        // Crear un árbol AVL
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(15);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(12);
        avlTree.insert(20);

        // Realizar una búsqueda en el árbol BST
        System.out.println("Busqueda en arbol BST:");
        int searchKeyBST = 7;
        Integer resultBST = bstTree.search(searchKeyBST);
        if (resultBST != null) {
            System.out.println("El elemento " + searchKeyBST + " fue encontrado en el arbol BST.");
        } else {
            System.out.println("El elemento " + searchKeyBST + " no fue encontrado en el arbol BST.");
        }

        // Calcular la altura del árbol BST
        System.out.println("Altura del arbol BST: " + bstTree.height());

        // Realizar una búsqueda en el árbol AVL
        System.out.println("Busqueda en arbol AVL:");
        int searchKeyAVL = 7;
        Integer resultAVL = avlTree.search(searchKeyAVL);
        if (resultAVL != null) {
            System.out.println("El elemento " + searchKeyAVL + " fue encontrado en el arbol AVL.");
        } else {
            System.out.println("El elemento " + searchKeyAVL + " no fue encontrado en el arbol AVL.");
        }

        // Calcular la altura del árbol AVL
        System.out.println("Altura del rbol AVL: " + avlTree.height(avlTree.getRoot()));
    }
}
