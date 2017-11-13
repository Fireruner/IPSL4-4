package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;

public class CategoriasCarrera{
	public static ArrayList<Categoria> l = new ArrayList<Categoria>();
	public String nombreCarrera;
	
	//HAY QUE PASAR EL NOMBRE DE LA CARRERA PARA HACER EL ID DE LA CATEGORIA
	
	public CategoriasCarrera() {
		
	}
	
	public static void vaciarLista() {
		l.clear();
	}
	
	public static ArrayList<Categoria> getl(){
		return l;
	}
	
	public static void setl(ArrayList<Categoria> c) {
		l = c;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
}
