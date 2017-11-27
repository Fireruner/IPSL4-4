package src;

import java.time.LocalDate;

public class AtletaCancelado {
	
	String dni,nombre,fk_carrera, estado;
	LocalDate fecha_inscripcion;
	double devolucion;
	
	
	public AtletaCancelado(String dni, String nombre, String fk_carrera, LocalDate fecha_inscripcion, String estado,
			double devolucion) {
		this.dni = dni;
		this.nombre = nombre;
		this.fk_carrera = fk_carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.estado = estado;
		this.devolucion = devolucion;
	}
	
	public String getEstado() {
		return estado;
	}

	private void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}
	private void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFk_carrera() {
		return fk_carrera;
	}
	private void setFk_carrera(String fk_carrera) {
		this.fk_carrera = fk_carrera;
	}
	public LocalDate getFecha_inscripcion() {
		return fecha_inscripcion;
	}
	private void setFecha_inscripcion(LocalDate fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}

	public double getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(double devolucion) {
		this.devolucion = devolucion;
	}
	
	

}
