package avltree;

import java.util.Stack;

import avltree.AVLTree.NodeAVL;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        try {
            // Casos de prueba de inserción que provocan desequilibrios y requieren rotaciones

            // Caso 1: Inserción de elementos en orden ascendente
            System.out.println("Caso 1: Inserción de elementos en orden ascendente");
            testInsert(avlTree, 10);
            testInsert(avlTree, 20);
            testInsert(avlTree, 30);
            testInsert(avlTree, 40);
            testInsert(avlTree, 50);
            testInsert(avlTree, 60);
            testInsert(avlTree, 70);
            testInsert(avlTree, 80);
            testInsert(avlTree, 90);
            testInsert(avlTree, 100);

            // Caso 2: Inserción de elementos en orden descendente
            System.out.println("\nCaso 2: Inserción de elementos en orden descendente");
            testInsert(avlTree, 100);
            testInsert(avlTree, 90);
            testInsert(avlTree, 80);
            testInsert(avlTree, 70);
            testInsert(avlTree, 60);
            testInsert(avlTree, 50);
            testInsert(avlTree, 40);
            testInsert(avlTree, 30);
            testInsert(avlTree, 20);
            testInsert(avlTree, 10);

            // Caso 3: Inserción de elementos en orden alternado
            System.out.println("\nCaso 3: Inserción de elementos en orden alternado");
            testInsert(avlTree, 50);
            testInsert(avlTree, 30);
            testInsert(avlTree, 70);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
            testInsert(avlTree, 60);
            testInsert(avlTree, 80);
            testInsert(avlTree, 10);
            testInsert(avlTree, 90);
            testInsert(avlTree, 100);

            // Caso 4: Inserción de elementos que requieren rotación RSR
            System.out.println("\nCaso 4: Inserción de elementos que requieren rotación RSR");
            testInsert(avlTree, 50);
            testInsert(avlTree, 30);
            testInsert(avlTree, 70);
            testInsert(avlTree, 60);
            testInsert(avlTree, 80);
            testInsert(avlTree, 75);
            testInsert(avlTree, 85);
            testInsert(avlTree, 90);

            // Caso 5: Inserción de elementos que requieren rotación RSL
            System.out.println("\nCaso 5: Inserción de elementos que requieren rotación RSL");
            testInsert(avlTree, 50);
            testInsert(avlTree, 70);
            testInsert(avlTree, 30);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
            testInsert(avlTree, 35);
            testInsert(avlTree, 45);
            testInsert(avlTree, 10);

            // Caso 6: Inserción de elementos que requieren rotación RDR
            System.out.println("\nCaso 6: Inserción de elementos que requieren rotación RDR");
            testInsert(avlTree, 50);
            testInsert(avlTree, 30);
            testInsert(avlTree, 70);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
            testInsert(avlTree, 80);
            testInsert(avlTree, 75);
            testInsert(avlTree, 90);
            testInsert(avlTree, 85);
            testInsert(avlTree, 95);

            // Caso 7: Inserción de elementos que requieren rotación RDL
            System.out.println("\nCaso 7: Inserción de elementos que requieren rotación RDL");
            testInsert(avlTree, 50);
            testInsert(avlTree, 70);
            testInsert(avlTree, 30);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
            testInsert(avlTree, 60);
            testInsert(avlTree, 80);
            testInsert(avlTree, 10);
            testInsert(avlTree, 25);
            testInsert(avlTree, 15);

            // Caso 8: Inserción de elementos con valores duplicados
            System.out.println("\nCaso 8: Inserción de elementos con valores duplicados");
            testInsert(avlTree, 50);
            testInsert(avlTree, 30);
            testInsert(avlTree, 70);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
            testInsert(avlTree, 60);
            testInsert(avlTree, 80);
            testInsert(avlTree, 30);
            testInsert(avlTree, 20);
            testInsert(avlTree, 40);
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testInsert(AVLTree<Integer> avlTree, int data) throws ItemDuplicated {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Insertando: " + data);

        // Mostrar el árbol antes de la inserción
        System.out.println("Antes de la inserción:");
        printTreeAsStack(avlTree.getRoot());

        try {
            avlTree.insert(data);
            System.out.println("Inserción exitosa!");

            // Mostrar el árbol después de la inserción
            System.out.println("Después de la inserción:");
            printTreeAsStack(avlTree.getRoot());

            // Mostrar información sobre la rotación realizada, el factor de balanceo y el dato
            System.out.println("Rotación: " + avlTree.getLastRotation());
            System.out.println("Factor de balanceo: " + avlTree.getLastBalanceFactor());
            System.out.println("Dato: " + data);
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printTreeAsStack(AVLTree.NodeAVL node) {
        if (node == null) {
            return;
        }

        Stack<AVLTree.NodeAVL> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            AVLTree.NodeAVL curr = stack.pop();
            System.out.println("Dato: " + curr.data + ", Factor de balanceo: " + curr.bf);

            if (curr.right != null) {
                stack.push((NodeAVL) curr.right);
            }
            if (curr.left != null) {
                stack.push((NodeAVL) curr.left);
            }
        }
    }
}


