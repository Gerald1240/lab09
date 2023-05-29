package Ejercicio3;

public class TestEmpresa {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        empresa.distribuirEmpleados("C:\\Users\\USER\\Desktop\\Ejercicio Video\\ejercicio-integrador-POO-II-master\\Lab09\\src\\Ejercicio3\\Empresa.txt");

        // Imprimir los árboles AVL antes del intercambio
        System.out.println("Árbol Comercial antes del intercambio:");
        empresa.arbolComercial.printTree();
        System.out.println();

        System.out.println("Árbol Producción antes del intercambio:");
        empresa.getArbolProduccion().printTree();
        System.out.println();

        System.out.println("Árbol Comunicaciones antes del intercambio:");
        empresa.getArbolComunicaciones().printTree();
        System.out.println();

        // Realizar intercambio de empleados
        int destino = 2;
        Empleado empleado = empresa.buscarEmpleado(destino);
        if (empleado != null) {
            System.out.println("Empleado encontrado con destino " + destino + ": " + empleado);
            empresa.eliminarEmpleado(empleado.getNumeroIdt());
            empleado.setDestino("Comercial");
            empresa.insertarEmpleado(empleado);
            System.out.println("Empleado movido a Comercial: " + empleado);
        } else {
            System.out.println("No se encontró ningún empleado con destino " + destino);
        }

        // Imprimir los árboles AVL después del intercambio
        System.out.println("\nÁrbol Comercial después del intercambio:");
        empresa.arbolComercial.printTree();
        System.out.println();

        System.out.println("Árbol Producción después del intercambio:");
        empresa.getArbolProduccion().printTree();
        System.out.println();

        System.out.println("Árbol Comunicaciones después del intercambio:");
        empresa.getArbolComunicaciones().printTree();
        System.out.println();
    }
}
