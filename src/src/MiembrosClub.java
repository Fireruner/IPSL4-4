package src;

import java.util.ArrayList;

public class MiembrosClub {
	public static ArrayList<Atleta> l = new ArrayList<Atleta>();
	
	public MiembrosClub() {
		
	}
	
	public static void vaciarLista() {
		l.clear();
	}
	
	public static ArrayList<Atleta> getl(){
		return l;
	}
}
