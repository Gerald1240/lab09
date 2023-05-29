package Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Empresa {
    AVLTree<Empleado> arbolComercial;
    private AVLTree<Empleado> arbolProduccion;
    private AVLTree<Empleado> arbolComunicaciones;
    
    public Empresa() {
        arbolComercial = new AVLTree<>();
        setArbolProduccion(new AVLTree<>());
        setArbolComunicaciones(new AVLTree<>());
    }
    
    public void distribuirEmpleados(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                int numeroIdt = Integer.parseInt(campos[0].trim());
                int origen = Integer.parseInt(campos[1].trim());
                int destino = Integer.parseInt(campos[2].trim());
                
                Empleado empleado = new Empleado(numeroIdt, obtenerDepartamento(origen), obtenerDepartamento(destino));
                insertarEmpleado(empleado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void insertarEmpleado(Empleado empleado) {
        int origen = obtenerNumeroDepartamento(empleado.getOrigen());
        switch (origen) {
            case 1:
                arbolComercial.insert(empleado);
                break;
            case 2:
                getArbolProduccion().insert(empleado);
                break;
            case 3:
                getArbolComunicaciones().insert(empleado);
                break;
        }
    }
    
    public Empleado buscarEmpleado(int destino) {
        switch (destino) {
            case 1:
                return arbolComercial.buscarEmpleado(destino);
            case 2:
                return getArbolProduccion().buscarEmpleado(destino);
            case 3:
                return getArbolComunicaciones().buscarEmpleado(destino);
            default:
                return null;
        }
    }
    
    public void eliminarEmpleado(int numeroIdt) {
        arbolComercial.eliminarEmpleado(numeroIdt);
        getArbolProduccion().eliminarEmpleado(numeroIdt);
        getArbolComunicaciones().eliminarEmpleado(numeroIdt);
    }
    
    private String obtenerDepartamento(int numero) {
        switch (numero) {
            case 1:
                return "Comercial";
            case 2:
                return "Producción";
            case 3:
                return "Comunicaciones";
            default:
                return "";
        }
    }
    
    private int obtenerNumeroDepartamento(String departamento) {
        switch (departamento) {
            case "Comercial":
                return 1;
            case "Producción":
                return 2;
            case "Comunicaciones":
                return 3;
            default:
                return 0;
        }
    }

	public AVLTree<Empleado> getArbolProduccion() {
		return arbolProduccion;
	}

	public void setArbolProduccion(AVLTree<Empleado> arbolProduccion) {
		this.arbolProduccion = arbolProduccion;
	}

	public AVLTree<Empleado> getArbolComunicaciones() {
		return arbolComunicaciones;
	}

	public void setArbolComunicaciones(AVLTree<Empleado> arbolComunicaciones) {
		this.arbolComunicaciones = arbolComunicaciones;
	}
}

