package src;

import java.time.*;

public class Carrera {

	private String nombre, estado;
	private int precio, plazasDisponibles, porcentajeDevolucion;
	private LocalDate fechaCelebracion;

	public Carrera(String nombre, int plazasDisponibles, LocalDate fechaCelebracion,String estado, int precio, int porcentajeDevolucion) {

		this.nombre = nombre;
		this.plazasDisponibles = plazasDisponibles;
		this.fechaCelebracion = fechaCelebracion;
		this.estado = estado;
		this.precio = precio;
		this.porcentajeDevolucion = porcentajeDevolucion;

	}
	
	private void setPorcentajeDevolucion(int porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}

	public int getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	public Carrera(String nombre, int plazasDisponibles, String fechaCelebracion, String estado, int precio, int porcentajeDevolucion) {

		this.nombre = nombre;
		this.plazasDisponibles = plazasDisponibles;
		this.fechaCelebracion = LocalDate.parse(fechaCelebracion);
		this.estado = estado;
		this.precio = precio;
		this.porcentajeDevolucion = porcentajeDevolucion;

	}

	private void setEstado(String estado) {
		this.estado = estado;
	}

	private void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public int getPrecio() {
		return precio;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	private void setFechaCelebracion(LocalDate fechaCelebracion) {
		this.fechaCelebracion = fechaCelebracion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public LocalDate getFechaCelebracion() {
		return fechaCelebracion;
	}

}
