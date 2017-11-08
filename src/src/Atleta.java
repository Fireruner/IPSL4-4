package src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

public class Atleta{

	private String sexo, nombre, dni, apellidos, estado, tiempo,fk_carrera, dorsal, posicion, categoria, club;
	LocalDate fechaDeNacimiento;
	LocalDate fecha_inscripcion;

	public Atleta(String dni, String nombre, String apellidos, String sexo, LocalDate fechaDeNacimiento,
			String fk_carrera, LocalDate fecha_inscripcion, String estado, String tiempo, String dorsal, 
			String categoria, String club) {
		this.sexo = sexo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.apellidos = apellidos;
		this.categoria = categoria;
		this.dni = dni;
		this.estado = estado;
		this.dorsal = dorsal;
		this.fk_carrera = fk_carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.club = club;
	}
	
	public Atleta(String dni, String nombre, String apellidos, String sexo, String fechaDeNacimiento,
			String fk_carrera, String fecha_inscripcion, String estado, String tiempo, String dorsal, 
			String categoria, String club) {
		this.sexo = sexo;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.apellidos = apellidos;
		this.dni = dni;
		this.estado = estado;
		this.categoria = categoria;
		this.dorsal = dorsal;
		this.fk_carrera = fk_carrera;
		this.fecha_inscripcion = LocalDate.parse(fecha_inscripcion);
		this.fechaDeNacimiento = LocalDate.parse(fechaDeNacimiento);
		this.club = club;
	}
	
	public String getDorsal() {
		return dorsal;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public String getFk_carrera() {
		return fk_carrera;
	}

	public LocalDate getFecha_inscripcion() {
		return fecha_inscripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTiempo() {
		return tiempo;
	}

	public String getDni() {
		return dni;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getClub() {
		return club;
	}
	
	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public void imprimirResguardo() throws IOException {
		FileOutputStream os = new FileOutputStream("Resguardo_"+dni+"_" +fk_carrera+ ".txt");
		PrintStream ps = new PrintStream(os);
		ps.println(this.getNombre() + " " + this.getApellidos() + " " + this.getFk_carrera() + " "
				+ this.getFecha_inscripcion() + " ");
		ps.close();
		os.close();
	}
}
