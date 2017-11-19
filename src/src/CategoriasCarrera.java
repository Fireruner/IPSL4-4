package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;

public class CategoriasCarrera{
	public static ArrayList<Categoria> lm = new ArrayList<Categoria>();
	public static ArrayList<Categoria> lf = new ArrayList<Categoria>();
	public String nombreCarrera;
	
	//HAY QUE PASAR EL NOMBRE DE LA CARRERA PARA HACER EL ID DE LA CATEGORIA
	
	public CategoriasCarrera() {
		
	}
	
	public static void vaciarListas() {
		lm.clear();
		lf.clear();
	}
	
	public static ArrayList<Categoria> getlm(){
		return lm;
	}
	
	public static void setlm(ArrayList<Categoria> c) {
		lm = c;
	}
	
	public static ArrayList<Categoria> getlf(){
		return lf;
	}
	
	public static void setlf(ArrayList<Categoria> c) {
		lf = c;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
}
