package src;

public class Categoria  implements Comparable<Categoria>{
	public int edadm, edadM;
	public String nombre, id;
	public String fk_carrera;
	
	public Categoria(String id, String nombre, int edadm, int edadM, String fk_carrera) {
		this.edadm = edadm;
		this.edadM = edadM;
		this.nombre = nombre;
		this.fk_carrera = fk_carrera;
		this.id = id;
	}

	public int getEdadm() {
		return edadm;
	}

	public void setEdadm(int edadm) {
		this.edadm = edadm;
	}

	public int getEdadM() {
		return edadM;
	}

	public void setEdadM(int edadM) {
		this.edadM = edadM;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFk_carrera() {
		return fk_carrera;
	}

	public void setFk_carrera(String fk_carrera) {
		this.fk_carrera = fk_carrera;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Categoria o) {
		if(edadM < o.getEdadM()) {
			return -1;
		}
		if(edadM > o.getEdadM()) {
			return 1;
		}
		return 0;
	}
}