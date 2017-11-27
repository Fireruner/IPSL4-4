package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager 
{
	private static String CONNECTION_STRING = "jdbc:hsqldb:hsql://localhost/labdb";
	private static String PASSWORD = "";
	private static String USERNAME = "SA";
	
	public static Connection getConnection() throws SQLException
	{			
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			return DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
	}
		
	public static ArrayList<String[]> getNombresEquipo() throws SQLException
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		String texto = "select * from miembros_equipo;";
		ResultSet rs = st.executeQuery(texto);
		while(rs.next())
		{
			String[] result = new String[3];
			result[0] = rs.getString("NOMBRE") + " " + rs.getString("APELLIDOS");
			result[1] = rs.getString("DNI");
			result[2] = rs.getString("UO");
			results.add(result);
//			System.out.println("Nombre: " + rs.getString("NOMBRE")+ " APELLIDO: " 
//					+ rs.getString("APELLIDOS") + "DNI: " + rs.getString("DNI")
//					+ "UO: "+rs.getString("UO") );
		}
		rs.close();
		st.close();
		con.close();
		
		return results;
		
	}
	
	public static ArrayList<String[]> getAtletasSinPagar() throws SQLException
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		String texto = "select a.nombre as nombre, a.apellidos as apellidos, a.dni as DNI, c.nombre as carrera "
				+ "from atleta a, carrera c "
				+ "where a.estado = 'inscrito' and a.fk_carrera = c.nombre";
		ResultSet rs = st.executeQuery(texto);
		while(rs.next())
		{
			String[] result = new String[4];
			result[0] = rs.getString("NOMBRE") + " " + rs.getString("APELLIDOS");
			result[1] = rs.getString("DNI");
			result[2] = rs.getString("CARRERA");
			result[3] = "En plazo";
			results.add(result);
		}
		rs.close();
		st.close();
		con.close();
		
		return results;
	}
	
	public static ArrayList<String[]> getAtletasFueraPlazoPago() throws SQLException
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		String texto = "select dni, fk_carrera "
				+ "from atleta "
				+ "where (current_date - fecha_inscripcion) >= 2 "
				+ "and estado = 'inscrito'";
		ResultSet rs = st.executeQuery(texto);
		while(rs.next())
		{
			String[] result = new String[2];
			result[0] = rs.getString("DNI");
			result[1] = rs.getString("FK_CARRERA");
			results.add(result);
		}
		rs.close();
		st.close();
		con.close();
		
		return results;
	}
	
	public static void actualizarPagado(String DNI, String carrera) throws SQLException
	{
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("update atleta set estado = 'pagado' where dni = ? and fk_carrera = ?");
		
		st.setString(1, DNI);
		st.setString(2, carrera);
		
		st.execute();

		st.close();
		con.close();
	}
	
	
	//Con este metodo cogemos los corredores de la bbdd ya ordenados por tiempo
		public static List<String[]> getCorredores() throws SQLException
		{
			List<String[]> results = new ArrayList<String[]>();
			
			Connection con = getConnection();
			Statement st = con.createStatement();
			String texto = "select * from atleta order by tiempo asc;";
			ResultSet rs = st.executeQuery(texto);
			while(rs.next())
			{
				String[] result = new String[12];
				result[0] = rs.getString("DNI");	
				result[1] = rs.getString("NOMBRE");
				result[2] = rs.getString("APELLIDOS");
				result[3] = rs.getString("fk_categoria");
				result[4] = rs.getString("SEXO");
				result[5] = rs.getString("FECHA_NACIMIENTO");
				result[6] = rs.getString("FK_CARRERA");
				result[7] = rs.getString("FECHA_INSCRIPCION");
				result[8] = rs.getString("ESTADO");
				result[9] = rs.getString("TIEMPO");
				result[10] = rs.getString("DORSAL");
				result[11] = rs.getString("FK_CLUB");
				results.add(result);
			}
			rs.close();
			st.close();
			con.close();
			
			return results;
			
		}
		
		public static ArrayList<String> getCarreras() throws SQLException
		{
			ArrayList<String> carreras = new ArrayList<String>();
			
			Connection con = getConnection();
			Statement st = con.createStatement();
			String texto = "select nombre from carrera";
			ResultSet rs = st.executeQuery(texto);
			while(rs.next())
			{
				String result = rs.getString(1);
				carreras.add(result);
			}
			rs.close();
			st.close();
			con.close();
			
			return carreras;
		}
		
		/*
		 * Devuelve un array de arrays con los datos de todos los atletas en la base de datos. No muy ï¿½til.
		 */
		
		public static ArrayList<String[]> getAtletas() throws SQLException
		{
			ArrayList<String[]> results = new ArrayList<String[]>();
			Connection con = getConnection();
			Statement st = con.createStatement();
			String texto = "select dni,nombre,apellidos,fecha_nacimiento,fk_carrera,estado from atletas";
			ResultSet rs = st.executeQuery(texto);
			while(rs.next())
			{
				String[] result = new String[6];
				result[0] = rs.getString("DNI");
				result[1] = rs.getString("NOMBRE");
				result[2] = rs.getString("APELLIDOS");
				//result[3] = rs.getDate("FECHA_NACIMIENTO");
				result[4] = rs.getString("FK_CARRERA");
				result[5] = rs.getString("ESTADO");
				results.add(result);
			}
			rs.close();
			st.close();
			con.close();		
			return results;
		}
		
		/*
		 * Para un atleta pasado como parámetro (dni) confirma si estï¿½ o no registrado en cierta carrera(pasada como parámetro)
		 */
		public static boolean atletaEstaEnCarrera(String dniAtleta, String fk_carrera) throws SQLException 
		{
			
			boolean coincide = false;
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dni from atleta where DNI = ? and fk_carrera = ?");
			ps.setString(1, dniAtleta);
			ps.setString(2, fk_carrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				coincide = rs.getString("DNI").equals(dniAtleta);
			}
			return coincide;
				
		}
			
		public static boolean anadirCiertoAtleta(Atleta atleta) throws SQLException {
			Connection con = getConnection();
			String dni = atleta.getDni();
			String nombre = atleta.getNombre();
			String apellidos = atleta.getApellidos();
			String sexo = atleta.getSexo();
			String categoria = atleta.getCategoria();
			java.sql.Date fechaDeNacimiento = java.sql.Date.valueOf(atleta.getFechaDeNacimiento());
			String fk_carrera = atleta.getFk_carrera();
			java.sql.Date fecha_inscripcion = java.sql.Date.valueOf(atleta.getFecha_inscripcion());
			String estado = atleta.getEstado();
			String tiempo = atleta.getTiempo();
			String dorsal = atleta.getDorsal();
			String club = atleta.getClub();
			
			PreparedStatement ps = con.prepareStatement("insert into atleta values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, categoria);
			ps.setString(5, sexo);
			ps.setDate(6, fechaDeNacimiento);
			ps.setString(7, fk_carrera);
			ps.setDate(8, fecha_inscripcion);
			ps.setString(9, estado);
			ps.setString(10 , dorsal);
			ps.setString(11, tiempo);
			ps.setString(12, club);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
			else
				return false;
				
		}
		
		public static void anadirTiempoAtleta(String carrera, String tiempo, String dni) throws SQLException {
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("UPDATE ATLETA SET tiempo = ? WHERE dni = ? and fk_carrera = ?");
			if(tiempo.equals("---")) {
				ps.setString(1, null);
			}
			else {
				ps.setString(1, tiempo);
			}
			ps.setString(2, dni);
			ps.setString(3, carrera);
			
			ps.executeUpdate();
			
			con.close();
		}
		
		public static ArrayList<String> getDNIs(String carrera) throws SQLException
		{
			ArrayList<String> dnis = new ArrayList<String>();
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dni from atleta where fk_carrera = ?");
			ps.setString(1, carrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				dnis.add(rs.getString("DNI"));
			}
			con.close();		
			return dnis;
		}
		
		
		 /* Lista atletas segï¿½n una carrera pasada como parï¿½metro*/

		public static ArrayList<String[]> listarAtletas(String fk_carrera) throws SQLException {
			ArrayList<String[]> c = new ArrayList<String[]>();
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dni, nombre, fk_categoria, sexo, fecha_inscripcion, estado, dorsal"
					+ " from atleta where fk_carrera= ? order by fecha_inscripcion, estado");
			ps.setString(1, fk_carrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String[] a = new String[7];
				a[0] = rs.getString("dni");
				a[1] = rs.getString("nombre");
				a[2] = rs.getString("fk_categoria");
				a[3] = rs.getString("sexo");
				a[4] = rs.getString("fecha_inscripcion");
				a[5] = rs.getString("estado");
				a[6] = rs.getString("dorsal");
				c.add(a);
				
			}
			rs.close();
			ps.cancel();
			con.close();
			return c;
			
		}
		
		public static boolean existeCarrera(String carrera) throws SQLException
		{
			boolean coincide = false;
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select nombre from carrera where nombre = ?");
			ps.setString(1, carrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				coincide = rs.getString("nombre").equals(carrera);
			}
			return coincide;
		}
		
		//Recibe con el mismo formato con que listarAtletas devuelve
		public static void actualizarDorsales(ArrayList<String[]> atletas, String carrera) throws SQLException
		{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("update atleta set dorsal = ? where dni = ? and fk_carrera = ?");
			for (String[] a : atletas)
			{
				ps.setString(1, a[6]);
				ps.setString(2, a[0]);
				ps.setString(3, carrera);
				ps.executeUpdate();
			}
		}
		
		public static int getSiguienteDorsalDisponible(String carrera) throws SQLException
		{
			ArrayList<String[]> c = new ArrayList<String[]>();
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dorsal from atleta where fk_carrera = ? order by dorsal asc");
			ps.setString(1, carrera);
			ResultSet rs = ps.executeQuery();
			boolean haLlegadoADiez = true;
			int nextOne = 11;
			while(rs.next()) 
			{
				if(rs.getString("dorsal") != null)
				{
					int number = Integer.parseInt(rs.getString("dorsal"));
					if(!haLlegadoADiez)
					{
						if(number < 10) nextOne = number +1;
						else if (nextOne == 10) haLlegadoADiez = true;
					}
					else
						nextOne = number +1;
				}
			}
			rs.close();
			ps.cancel();
			con.close();
			return nextOne;
		}
		
		public static void anadirDorsalCorredor(String dni, String carrera, String dorsal) throws SQLException
		{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("update atleta set dorsal = ? where dni = ? and fk_carrera = ?");
			ps.setString(1, dorsal);
			ps.setString(2, dni);
			ps.setString(3, carrera);
			ps.executeUpdate();
		}
	
		public static ArrayList<String[]> getAtletasSinPagarCarrera(String carrera) throws SQLException
		{
			ArrayList<String[]> results = new ArrayList<String[]>();
			
			Connection con = getConnection();
			String texto = "select nombre as nombre, apellidos as apellidos, dni as DNI, fk_carrera as carrera "
					+ "from atleta  "
					+ "where estado = 'inscrito' and fk_carrera = ?";
			PreparedStatement st = con.prepareStatement(texto);
			st.setString(1, carrera);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				String[] result = new String[4];
				result[0] = rs.getString("NOMBRE") + " " + rs.getString("APELLIDOS");
				result[1] = rs.getString("DNI");
				result[2] = rs.getString("CARRERA");
				result[3] = "En plazo";
				results.add(result);
			}
			rs.close();
			st.close();
			con.close();
			
			return results;
		}
		public static ArrayList<Carrera> getCarrerasEnteras() throws SQLException{
			ArrayList<Carrera> carreras = new ArrayList<Carrera>();
			String nombre;
			int plazas;
			LocalDate fecha_celebracion;
			String estado;
			int precio;
			int porcentaje_devolucion;
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from carrera");
			
			while(rs.next()) {
				nombre = rs.getString("Nombre");
				plazas = rs.getInt("plazas_disponibles");
				fecha_celebracion = LocalDate.parse(rs.getString("fecha_celebracion"));
				estado = rs.getString("Estado");
				precio = rs.getInt("Precio");
				porcentaje_devolucion = rs.getInt("Porcentaje_devolucion");
				carreras.add(new Carrera(nombre,plazas,fecha_celebracion, estado, precio, porcentaje_devolucion));				
			}
			rs.close();
			st.close();
			con.close();
			return carreras;
		}
		
		public static String comprobarAtletaPagado(String dni, String carrera) throws SQLException
		{
			String estado = "vacio";
			Connection con = getConnection();
			String texto = "select estado as estado "
					+ "from atleta  "
					+ "where dni = ? and fk_carrera = ?";
			PreparedStatement st = con.prepareStatement(texto);
			st.setString(1, dni);
			st.setString(2, carrera);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				String result = new String();
				result = rs.getString("estado");
				if(result.equals("pagado")) {
					estado = "pagado";
		 		}
		 	}
		 	return estado;
		 }
		
		public static String comprobarAtletaPresentado(String dni, String carrera) throws SQLException
		{
			String estado = "vacio";
			Connection con = getConnection();
			String texto = "select estado as estado "
					+ "from atleta  "
					+ "where dni = ? and fk_carrera = ?";
			PreparedStatement st = con.prepareStatement(texto);
			st.setString(1, dni);
			st.setString(2, carrera);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				String result = new String();
				result = rs.getString("estado");
				if(result.equals("presentado")) {
					estado = "presentado";
		 		}
		 	}
		 	return estado;
		 }
		
		public static boolean anadirCiertoClub(Club club, String nombreCarrera) throws SQLException {
			Connection con = getConnection();
			String nombre = club.getNombre();
			ArrayList<Atleta> atletas = club.getParticipantes();
			
			PreparedStatement ps = con.prepareStatement("insert into club values (?, ?) ");
			ps.setString(1, nombre);
			ps.setString(2, nombreCarrera);
			
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
			else
				return false;
			
		}
		
		public static ArrayList<String> getClubs() throws SQLException
		{
			ArrayList<String> clubs = new ArrayList<String>();
			
			Connection con = getConnection();
			Statement st = con.createStatement();
			String texto = "select nombreclub from club";
			ResultSet rs = st.executeQuery(texto);
			while(rs.next())
			{
				String result = rs.getString(1);
				clubs.add(result);
			}
			rs.close();
			st.close();
			con.close();
			
			return clubs;
		}
		
		public static boolean existeDorsal(String carrera, String dorsal) throws SQLException{
			boolean coincide = false;
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dni from atleta where dorsal = ? and fk_carrera = ?");
			ps.setString(1, dorsal);
			ps.setString(2, carrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				coincide = rs.getString("DNI") != null;
			}
			return coincide;
		}
		public static int getPorcentajeDevolucion(String carrera) throws SQLException{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select porcentaje_devolucion from Carrera where nombre = ?");
			ps.setString(1, carrera);
			ResultSet rs = ps.executeQuery();
			int porcentajeDevolucion = 0;
			while(rs.next()) {
				porcentajeDevolucion = rs.getInt("porcentaje_devolucion");
			}
			return porcentajeDevolucion;
			
		}
		
		public static int getPrecioCarrera(String carrera) throws SQLException{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select precio from Carrera where nombre = ?");
			ps.setString(1, carrera);
			ResultSet rs = ps.executeQuery();
			int precio = 0;
			while(rs.next()) {
			precio = rs.getInt("precio");
			}
			return precio;
		}
		
		public static void borrarAtleta(String carrera, String dni) throws SQLException{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from atleta where dni = ? and fk_carrera = ? ");
			ps.setString(1, dni);
			ps.setString(2, carrera);
			ps.executeUpdate();
		}
		
		
		
		public static boolean anadirCarrera(Carrera carrera) throws SQLException
		{
			
			Connection con = getConnection();
			String nombre = carrera.getNombre();
			int plazas = carrera.getPlazasDisponibles();
			java.sql.Date fecha = java.sql.Date.valueOf(carrera.getFechaCelebracion());
			String estado = carrera.getEstado();
			int precio = carrera.getPrecio();
			int porcentaje = carrera.getPorcentajeDevolucion();
			
			PreparedStatement ps = con.prepareStatement("insert into carrera values (?,?,?,?,?,?)");
			ps.setString(1, nombre);
			ps.setInt(2, plazas);
			ps.setDate(3, fecha);
			ps.setString(4, estado);
			ps.setInt(5, precio);
			ps.setInt(6, porcentaje);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
			else
				return false;
			
		}
		
		public static ArrayList<Categoria> getCategoriasPorCarrera(String nombreCarrera) throws SQLException{
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("select * from categoria where fk_carrera_categ = ?");
			ps.setString(1, nombreCarrera);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String id = rs.getString(1);
				String nombre = rs.getString(2);
				String edadm = rs.getString(3);
				String edadM = rs.getString(4);
				String sexo = rs.getString(5);
				categorias.add(new Categoria(id,nombre,Integer.parseInt(edadm),Integer.parseInt(edadM), sexo, nombreCarrera));
			}
			ps.close();
			rs.close();
			con.close();
			
			return categorias;
		}
		
		public static boolean anadirCategoriaACarrera(Categoria categoria, String carrera) throws SQLException{
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("insert into categoria values (?, ?, ?, ?, ?, ?) ");
			ps.setString(1, categoria.getId());
			ps.setString(2, categoria.getNombre());
			ps.setInt(3, categoria.getEdadm());
			ps.setInt(4, categoria.getEdadM());
			ps.setString(5, categoria.getSexo());
			ps.setString(6, carrera);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}
			else
				return false;
		}	
		
		public static boolean cambiarEstadoAtleta(String dni, String fk_carrera, String estado) throws SQLException {
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("update atleta Set estado = ? where dni = ? and fk_carrera = ?");
			ps.setString(1, estado);
			ps.setString(2, dni);
			ps.setString(3, fk_carrera);
			
			
			
			if(ps.executeUpdate() == 1) {
				ps.close();
				con.close();
				return true;
			
			} else {
				ps.close();
				con.close();
				return false;
			}
		}
		
		public static boolean annadirAtletaCancelado(AtletaCancelado atleta) throws SQLException {
			Connection con = getConnection();
			String dni = atleta.getDni();
			String nombre = atleta.getNombre();
			String fk_carrera = atleta.getFk_carrera();
			String estado = atleta.getEstado();
			java.sql.Date fecha_inscripcion = java.sql.Date.valueOf(atleta.getFecha_inscripcion());
			float devolucion = (float)atleta.getDevolucion();
			
			PreparedStatement ps = con.prepareStatement("insert into AtletaCancelado values(?,?,?,?,?,?)");
			
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, fk_carrera);
			ps.setDate(4, fecha_inscripcion);
			ps.setString(5, estado);
			ps.setFloat(6, devolucion);
			
			if(ps.executeUpdate() == 1) {
				return true;
			} else
				return false;
			
			
			
		}
		
		public static ArrayList<AtletaCancelado> listarAtletasCancelados(String fk_carrera) throws SQLException{
			ArrayList<AtletaCancelado> a = new ArrayList<AtletaCancelado>();
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select dni,nombre,fecha_inscripcion, estado, devolucion from AtletaCancelado where fk_carrera = ?");
			ps.setString(1, fk_carrera);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String fecha_inscripcion = rs.getString("fecha_inscripcion");
				String estado = rs.getString("estado");
				double devolucion = (double)rs.getFloat("devolucion");
				a.add(new AtletaCancelado(dni,nombre,fk_carrera,LocalDate.parse(fecha_inscripcion),estado, devolucion));
				
			}
			return a;
			
		}
		public static boolean cambiarEstadoAtletaCancelado(String dni, String fk_carrera,String fecha_inscripcion, String estado, double devolucion) throws SQLException {
			Connection con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("update atletaCancelado Set estado = ?, devolucion = ? where dni = ? and fk_carrera = ? and fecha_inscripcion = ?");
			ps.setString(1, estado);
			ps.setFloat(2, (float) devolucion);
			ps.setString(3, dni);
			ps.setString(4, fk_carrera);
			java.sql.Date fecha_inscripcion1 = java.sql.Date.valueOf(fecha_inscripcion);
			ps.setDate(5, fecha_inscripcion1);
			
			
			
			if(ps.executeUpdate() == 1) {
				ps.close();
				con.close();
				return true;
			
			} else {
				ps.close();
				con.close();
				return false;
			}
		} 
		
}
