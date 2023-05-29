package Ejercicio3;

public class Empleado implements Comparable<Empleado> {
    private int numeroIdt;
    private String origen;
    private String destino;

    public Empleado(int numeroIdt, String origen, String destino) {
        this.numeroIdt = numeroIdt;
        this.origen = origen;
        this.destino = destino;
    }

    public Empleado(String string, int origen2, int destino2) {
		// TODO Auto-generated constructor stub
	}

	public int getNumeroIdt() {
        return numeroIdt;
    }

    public void setNumeroIdt(int numeroIdt) {
        this.numeroIdt = numeroIdt;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public int compareTo(Empleado otroEmpleado) {
        // Comparar empleados por número de identificación (numeroIdt)
        return Integer.compare(this.numeroIdt, otroEmpleado.numeroIdt);
    }

	public void setOrigen(int destino2) {
		// TODO Auto-generated method stub
		
	}
}
