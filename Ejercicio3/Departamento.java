package Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private int codigo;
    private AVLTree<Empleado> empleados;

    public Departamento(int codigo) {
        this.codigo = codigo;
        this.empleados = new AVLTree<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public AVLTree<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(AVLTree<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.insert(empleado);
    }

    public void eliminarEmpleado(int numeroIdt) {
        Empleado empleado = new Empleado(numeroIdt, "", "");
        empleados.delete(empleado);
    }

    public void intercambiarEmpleados(int departamentoOrigen, int departamentoDestino) {
        AVLTree<Empleado> empleadosOrigen = getEmpleadosPorDepartamento(departamentoOrigen);
        AVLTree<Empleado> empleadosDestino = getEmpleadosPorDepartamento(departamentoDestino);

        if (empleadosOrigen != null && empleadosDestino != null) {
            List<Empleado> empleadosIntercambio = new ArrayList<>();

            for (Empleado empleado : empleadosOrigen) {
                if (empleado.getDestino().equals(String.valueOf(departamentoDestino))) {
                    empleadosIntercambio.add(empleado);
                }
            }

            for (Empleado empleado : empleadosIntercambio) {
                empleadosOrigen.delete(empleado);
                empleado.setOrigen(String.valueOf(departamentoDestino));
                empleadosDestino.insert(empleado);
            }
        }
    }

    private AVLTree<Empleado> getEmpleadosPorDepartamento(int departamento) {
        AVLTree<Empleado> empleadosPorDepartamento = new AVLTree<>();

        for (Empleado empleado : empleados) {
            if (empleado.getOrigen().equals(String.valueOf(departamento))) {
                empleadosPorDepartamento.insert(empleado);
            }
        }

        return empleadosPorDepartamento;
    }
}
