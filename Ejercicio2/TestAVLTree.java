package Ejercicio2;

public class TestAVLTree {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        // Crear un arbol AVL
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(15);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(12);
        avlTree.insert(20);
        
        // Imprimir el arbol AVL antes de la eliminacion
        System.out.println("Arbol AVL antes de la eliminacion:");
        avlTree.printTree();

        // Eliminar un elemento del arbol AVL
        int keyToRemove = 5;
        avlTree.remove(keyToRemove);

        // Imprimir el arbol AVL despues de la eliminacion
        System.out.println("\nArbol AVL despues de la eliminacion:");
        avlTree.printTree();
    }
}
