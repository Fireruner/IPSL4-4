package src;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Club {
	String nombre, fk_carrera;
	ArrayList<Atleta> participantes = new ArrayList<Atleta>();

	public Club(String nombre, ArrayList<Atleta> participantes, String fk_carrera) {
		this.nombre = nombre;
		this.fk_carrera = fk_carrera;
		this.participantes = participantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Atleta> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Atleta> participantes) {
		this.participantes = participantes;
	}
	
	public String getFk_carrera() {
		return fk_carrera;
	}
	
	
	public void imprimirResguardo(String descuento, int precio) throws IOException {
		FileOutputStream os = new FileOutputStream("Resguardo_"+nombre+"_" +fk_carrera+ ".txt");
		PrintStream ps = new PrintStream(os);
		double precioPersona = calcularPrecioIndividual(descuento,precio);
		ps.println("Nombre del club registrado: " + nombre);
		ps.println("Descuento total realizado: " + descuento);
		ps.println("Precio final por persona; " + precioPersona);
		ps.println("Todos los atletas registrados: ");
		for(int i = 0; i<participantes.size(); i++) {
			ps.println("\t" + participantes.get(i).getNombre() + " " + participantes.get(i).getApellidos() + " " + participantes.get(i).getFk_carrera() + " "
					+ participantes.get(i).getFecha_inscripcion() + " ");
		}
		ps.close();
		os.close();
		abrirarchivo("./"+"Resguardo_"+nombre+"_" +fk_carrera+ ".txt");
	}
	
	public double calcularPrecioIndividual(String descuento, int precio) {
		double precioFinal = 0;
		if(descuento.equals("5%")) {
			precioFinal = precio - (precio*0.05);
		}
		else if
		(descuento.equals("10%")) {
			precioFinal = precio - (precio*0.1);
		}
		else if (descuento.equals("20%")) {
			precioFinal = precio - (precio*0.2);
		}
		return precioFinal;
	}
	
	public void abrirarchivo(String archivo){
		 try {
			 File objetofile = new File (archivo);
			 Desktop.getDesktop().open(objetofile);
	     }catch (IOException ex) {
         System.out.println(ex);
		     }
		}
}
