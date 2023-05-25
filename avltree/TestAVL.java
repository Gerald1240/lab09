package avltree;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        try {
            // Caso de prueba 1: Inserción de elementos en orden ascendente
            avlTree.insert(10);
            avlTree.insert(20);
            avlTree.insert(30);
            avlTree.insert(40);
            avlTree.insert(50);

            System.out.println("Árbol AVL después de la inserción en orden ascendente:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 2: Inserción de elementos en orden descendente
            avlTree = new AVLTree<>();
            avlTree.insert(50);
            avlTree.insert(40);
            avlTree.insert(30);
            avlTree.insert(20);
            avlTree.insert(10);

            System.out.println("Árbol AVL después de la inserción en orden descendente:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 3: Inserción de elementos en orden alternado
            avlTree = new AVLTree<>();
            avlTree.insert(30);
            avlTree.insert(10);
            avlTree.insert(40);
            avlTree.insert(20);
            avlTree.insert(50);

            System.out.println("Árbol AVL después de la inserción en orden alternado:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 4: Inserción de elementos para generar una rotación simple a la derecha (RSR)
            avlTree = new AVLTree<>();
            avlTree.insert(30);
            avlTree.insert(40);
            avlTree.insert(50);

            System.out.println("Árbol AVL después de la inserción para RSR:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 5: Inserción de elementos para generar una rotación simple a la izquierda (RSL)
            avlTree = new AVLTree<>();
            avlTree.insert(50);
            avlTree.insert(40);
            avlTree.insert(30);

            System.out.println("Árbol AVL después de la inserción para RSL:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 6: Inserción de elementos para generar una rotación doble a la derecha (RDR)
            avlTree = new AVLTree<>();
            avlTree.insert(30);
            avlTree.insert(10);
            avlTree.insert(20);

            System.out.println("Árbol AVL después de la inserción para RDR:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 7: Inserción de elementos para generar una rotación doble a la izquierda (RDL)
            avlTree = new AVLTree<>();
            avlTree.insert(30);
            avlTree.insert(50);
            avlTree.insert(40);

            System.out.println("Árbol AVL después de la inserción para RDL:");
            avlTree.parenthesize();
            System.out.println();

            // Caso de prueba 8: Inserción de elementos para generar una rotación doble a la derecha (RDR)
            avlTree = new AVLTree<>();
            avlTree.insert(30);
            avlTree.insert(20);
            avlTree.insert(40);
            avlTree.insert(10);

            System.out.println("Árbol AVL después de la inserción para RDR:");
            avlTree.parenthesize();
            System.out.println();

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
