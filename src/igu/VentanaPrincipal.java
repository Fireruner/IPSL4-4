package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Atleta;
import src.DataBaseManager;
import src.GestorComprobaciones;
import src.MyTableModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Font;

//import net.miginfocom.swing.MigLayout;

import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

public class VentanaPrincipal {
	
	private final String SQLError = "Ha ocurrido un error ejecutando una consulta a la base de datos! REF:";

	private JFrame frame;
	private MyTableModel modelEquipo;
	private JPanel pnControles;
	private JButton btnPagos;
	private JPanel pnTittle;
	private JPanel pnControlPagos;
	private JButton btnActualizar;
	private JTable tablePagos;
	private MyTableModel modelPagos;
	private JButton btnPagar;
	
	
	
	private JPanel pnClasificacion;
	private JPanel panelDatos;
	private JPanel panelContenido;
	private JTable tablaResultados;
	private JPanel panelBoton;
	private JButton btnMostrarResultados;
	private JPanel panelTabla;
	private JLabel lblTitulo;
	private JLabel lblNombreDeCarrera;
	private JPanel panelNombreCarrera;
	
	//Otros datos
		private String m = "masculino";
		private String f = "femenino";
		private JPanel panelFiltroCarrera;
		private JPanel panelTituloYColumnas;
		private JPanel panelColumnas;
		private JLabel lblCDNI;
		private JLabel lblCPosicion;
		private JLabel lblCSexo;
		private JLabel lblCDorsal;
		private JLabel lblCNombre;
		private JLabel lblCApellidos;
		private JLabel lblCFNacimiento;
		private JLabel lblCFInscripcion;
		private JLabel lblCTiempo;
		private JButton btnClasificacion;
		private JButton btnVolverAlMenu;
		private JButton btMenu;
		private JButton btnRegistrarCorredor;
		
		private JPanel panelFicheros;
		private JPanel panelSeleccion;
		private JButton btnSeleccion;
		private JPanel panelInformacion;
		private JLabel lblCarreraElegida;
		private JPanel panelBotones;
		private JButton btnCargar;
		private JPanel panelVolver;
		private JButton btnVolverAlMen;
		private File archivo;
		private boolean sinFallosFormato = true;
		private boolean sinFallosDni = true;
		private boolean sinFallosNombreCarrera = true;
		private boolean sinFallosEstructura = true;
		private GestorComprobaciones gc = new GestorComprobaciones();	
		private JButton btnRegistrarTiempos;
		
		private JPanel pnlAtletasSegunCarrera;
		private JTable tableAtletas;
		private JButton btnMostrar;
		private JButton btnMenu;
		private JLabel lblListarAtletasSegn;
		private JButton btnAtletas;
		private MyTableModel modelAtletas;
		
		private JButton btnAsignarDorsales;
		private JButton btnAsignarDorsal;
		private String carreraSeleccionada;
		private JLabel lblCarreraSeleccionada;
		private JComboBox comboBox;
		private JComboBox comboClasificacion;
		private JComboBox comboCarreras;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(getPnTittle(), "panelTitulo");
		frame.getContentPane().add(getPanel_4(), "panelPagos");
		frame.getContentPane().add(getPnClasificacion(), "panelClasificacion");
		frame.getContentPane().add(getPanelFicheros(), "panelFicheros");
		frame.getContentPane().add(getPnlAtletasSegunCarrera(), "pnlAtletasSegunCarrera");
		
	}
	private JPanel getPnControles() {
		if (pnControles == null) {
			pnControles = new JPanel();
			pnControles.setBounds(357, 11, 292, 469);
			pnControles.setLayout(new GridLayout(0, 1, 0, 0));
			pnControles.add(getBtnRegistrarCorredor());
			pnControles.add(getBtnPagos());
			pnControles.add(getBtnClasificacion());
			pnControles.add(getBtnRegistrarTiempos());
			pnControles.add(getBtnAtletas());
			pnControles.add(getBtnAsignarDorsales());
		}
		return pnControles;
	}
	
	private JPanel getPnClasificacion()
	{
		if(pnClasificacion == null)
		{
			pnClasificacion = new JPanel();
			pnClasificacion.setLayout(new BorderLayout(0, 0));
			pnClasificacion.add(getPanelDatos(), BorderLayout.CENTER);
			pnClasificacion.add(getPanelFiltroCarrera(), BorderLayout.WEST);
		}
		return pnClasificacion;
	}
	private JButton getBtnPagos() {
		if (btnPagos == null) {
			btnPagos = new JButton("Control de pagos");
			btnPagos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					removeModelContent((MyTableModel)tablePagos.getModel());
					card.show(frame.getContentPane(), "panelPagos");
				}
			});
		}
		return btnPagos;
	}
	private JPanel getPnTittle() {
		if (pnTittle == null) {
			pnTittle = new JPanel();
			pnTittle.setLayout(null);
			pnTittle.add(getPnControles());
		}
		return pnTittle;
	}
	private JPanel getPanel_4() {
		if (pnControlPagos == null) {
			pnControlPagos = new JPanel();
			pnControlPagos.setLayout(null);
			pnControlPagos.add(getBtnActualizar());
			pnControlPagos.add(getTable_1());
			pnControlPagos.add(getBtnPagar());
			pnControlPagos.add(getBtMenu());
			pnControlPagos.add(getComboCarreras());
		}
		return pnControlPagos;
	}
	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					actualizarTablaPagos((String)comboCarreras.getSelectedItem());
				}
			});
			btnActualizar.setBounds(483, 134, 156, 23);
		}
		return btnActualizar;
	}
	
	private void addToModel(MyTableModel model, ArrayList<String[]> datos)
	{
		for(String[] dato : datos)
		{
			model.addRow(dato);
		}
	}
	
	private void removeModelContent(MyTableModel model)
	{
	    if(model.getRowCount() > 0)
	    	for (int i = model.getRowCount()-1; i>=0; i--)
	    		model.removeRow(i);
	}
	private JTable getTable_1() {
		if (tablePagos == null) {
			modelPagos = new MyTableModel();
			modelPagos.addColumn("Nombre Y Apellidos");
			modelPagos.addColumn("UO");
			modelPagos.addColumn("DNI");
			modelPagos.addColumn("Plazo");
			tablePagos = new JTable(modelPagos);
			tablePagos.setBounds(10, 11, 463, 432);
		}
		return tablePagos;
	}
	
	public void actualizarTablaPagos(String carrera)
	{
		ArrayList<String[]> atletasSinPagar;
		ArrayList<String[]> atletasFueraDePlazo;
		try {
			//Consulta para obtener los datos
			atletasSinPagar = DataBaseManager.getAtletasSinPagarCarrera(carrera);
			atletasFueraDePlazo = DataBaseManager.getAtletasFueraPlazoPago();
		    
		    
		    //Si ya hay datos en la tabla, los elimina
		    removeModelContent(modelPagos);
		    
		    String[] cabeceras = {"Nombre y Apellidos", "DNI", "Carrera", "Plazo"};
		    modelPagos.addRow(cabeceras);
		    int rowCount = 0;
		    
		    if(atletasSinPagar.size()>1)
		    {
			    for(String[] atleta : atletasSinPagar)
				{
			    	boolean coincide = false;
			    	for(String[] fueraPlazo: atletasFueraDePlazo)
			    	{
			    		if(atleta[1].equals(fueraPlazo[0]) && atleta[2].equals(fueraPlazo[1]))
			    		{
			    				coincide = true;
			    				break;
			    		}
			    	}
			    	if(coincide)
			    		atleta[3] = "Fuera de plazo";
			    	
				    modelPagos.addRow(atleta);
			    	rowCount ++;
			    	
				}
		    }
		    
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, SQLError + "control_pagos");
			System.err.println( SQLError + "control_pagos");
			e1.printStackTrace();
		}
	}
	
	public void miembrosEquipo()
	{
		ArrayList<String[]> miembrosEquipo;
		try {
			//Consulta para obtener los datos
			miembrosEquipo = DataBaseManager.getNombresEquipo();
		    
		    //Si ya hay datos en la tabla, los elimina
		    removeModelContent(modelEquipo);
		    
		    String[] cabeceras = {"Nombre y Apellidos", "DNI", "UO"};
		    modelEquipo.addRow(cabeceras);
		    
		    addToModel(modelEquipo, miembrosEquipo);
		    
		} catch (SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, SQLError + "miembros_equipo");
			System.err.println( SQLError + "miembros_equipo");
			e1.printStackTrace();
		}
	}
	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					if(tablePagos.getSelectedRow()>= 0)
					{
						int row = tablePagos.getSelectedRow();
						String DNI = (String)tablePagos.getModel().getValueAt(row, 1);
						String carrera = (String)tablePagos.getModel().getValueAt(row, 2);
						
						try 
						{
							DataBaseManager.actualizarPagado(DNI, carrera);
							JOptionPane.showMessageDialog(null, "Se ha realizado el pago de "+tablePagos.getModel().getValueAt(row, 1) +" para la carrera "
									+tablePagos.getModel().getValueAt(row, 2)+".");
						}
						catch (SQLException e) 
						{
							JOptionPane.showMessageDialog(null, "Error actualizando datos en la base de datos! Puede que sus cambios no hayan sido modificados!");
							e.printStackTrace();
						}
						actualizarTablaPagos((String)comboClasificacion.getSelectedItem());
					}
					else
						JOptionPane.showMessageDialog(null, "Para realizar el pago debe primero seleccionar un atleta que no haya pagado.");
				}
			});
			btnPagar.setBounds(483, 178, 156, 23);
		}
		return btnPagar;
	}
	
	//ESTE PANEL CONTIENE: LA TABLA, EL TITULO DE ESTA, LAS LABELS DE LAS COLUMNAS DE LA TABLA Y EL BTN DE MOSTRAR RESULTADOS
		private JPanel getPanelDatos() {
			if (panelDatos == null) {
				panelDatos = new JPanel();
				panelDatos.setLayout(new BorderLayout(0, 0));
				panelDatos.add(getPanelContenido());
				panelDatos.add(getPanelBoton(), BorderLayout.SOUTH);
			}
			return panelDatos;
		}
		
		//PANEL CONTENIDO EN PANELDATOS, CONTIENE LA TABLA, SU TITULO Y LAS LABELS DE LAS COLUMNAS DE LA TABLA
		private JPanel getPanelContenido() {
			if (panelContenido == null) {
				panelContenido = new JPanel();
				panelContenido.setLayout(new BorderLayout(0, 0));
				panelContenido.add(getPanelTabla(), BorderLayout.CENTER);
				panelContenido.add(getPanelTituloYColumnas(), BorderLayout.NORTH);
			}
			return panelContenido;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO SOLO CON LA TABLA
		 */
		
		//PANEL QUE CONTIENE LA TABLA
		private JPanel getPanelTabla() {
			if (panelTabla == null) {
				panelTabla = new JPanel();
				panelTabla.setLayout(new BorderLayout(0, 0));
				panelTabla.add(getTablaResultados());
			}
			return panelTabla;
		}
		
		//LA TABLA EN SI CON SUS COLUMNAS
		private JTable getTablaResultados() {
			if (tablaResultados == null) {
				MyTableModel model = new MyTableModel();
				model.addColumn("DNI");
				model.addColumn("PosiciÃƒÂ³n");
				model.addColumn("Sexo");	
				model.addColumn("Dorsal");
				model.addColumn("Nombre");
				model.addColumn("Apellidos");
				model.addColumn("Fecha nacimiento");
				model.addColumn("Fecha Inscripcion");
				model.addColumn("Tiempo");
				tablaResultados = new JTable(model);
			}
			return tablaResultados;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL BOTON DE MUESTRA DE RESULTADOS EN LA TABLA
		 */
		
		//PANEL CONTENIDO EN PANELDATOS, CONTIENE EL BOTON MOSTRAR RESULTADOS
		private JPanel getPanelBoton() {
			if (panelBoton == null) {
				panelBoton = new JPanel();
				panelBoton.setLayout(new BorderLayout(0, 0));
				panelBoton.add(getBtnMostrarResultados(), BorderLayout.EAST);
				panelBoton.add(getBtnVolverAlMenu(), BorderLayout.WEST);
			}
			return panelBoton;
		}
		
		//BOTON ENCARGADO DE MOSTRAR LOS RESULTADOS DE CIERTA CARRERA ESCRITA EN EL JTEXTFIELD EN LA JTABLE
		private JButton getBtnMostrarResultados() {
			if (btnMostrarResultados == null) {
				btnMostrarResultados = new JButton("Mostrar resultados");
				btnMostrarResultados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String carrera = (String) comboClasificacion.getSelectedItem();
						List<String[]> participantes;
						List<Atleta> atletasConTiempo = new ArrayList<Atleta>();
						List<Atleta> atletasSinTiempo = new ArrayList<Atleta>();	//ya que el order by coloca primero a los sin tiempo
						
						
						try{
							
							MyTableModel model = (MyTableModel) tablaResultados.getModel();
							participantes = DataBaseManager.getCorredores();
								
<<<<<<< HEAD
							if(participantes.size()!=0) {
							for(int i = 0; i<participantes.size();i++){
								if(participantes.get(i)[5].equals(carrera)) {
									if(participantes.get(i)[8] != null){
										atletasConTiempo.add(new Atleta(participantes.get(i)[0],participantes.get(i)[1],participantes.get(i)[2],
										participantes.get(i)[3],participantes.get(i)[4],participantes.get(i)[5],participantes.get(i)[6],
										participantes.get(i)[7],participantes.get(i)[8],participantes.get(i)[9]));
=======
							if(0<participantes.size()) {
								for(int i = 0; i<participantes.size();i++){
									if(participantes.get(i)[5].equals(carrera)) {
										if(participantes.get(i)[8] != null){
											atletasConTiempo.add(new Atleta(participantes.get(i)[0],participantes.get(i)[1],participantes.get(i)[2],
													participantes.get(i)[3],participantes.get(i)[4],participantes.get(i)[5],participantes.get(i)[6],
													participantes.get(i)[7],participantes.get(i)[8],participantes.get(i)[9]));
										}
										else{
											atletasSinTiempo.add(new Atleta(participantes.get(i)[0],participantes.get(i)[1],participantes.get(i)[2],
											participantes.get(i)[3],participantes.get(i)[4],participantes.get(i)[5],participantes.get(i)[6],
											participantes.get(i)[7],participantes.get(i)[8],participantes.get(i)[9]));
										}
									}
								}						
							
								//Si hay datos en la tabla, los borra
								removeModelContent((MyTableModel)tablaResultados.getModel());
							
							
							
								int contadorPosM = 1;	//contador para las posiciones masculinas
								int contadorPosF = 1;	//contador para las posiciones femeninas
								for(int i = 0; i<atletasConTiempo.size(); i++){	//ANNADIMOS PRIMERO LOS QUE TIENEN TIEMPO
								
									if(atletasConTiempo.get(i).getSexo().equals(m)) {
										Object[] temp = {atletasConTiempo.get(i).getDni() ,contadorPosM, atletasConTiempo.get(i).getSexo(), 
												atletasConTiempo.get(i).getDorsal(), atletasConTiempo.get(i).getNombre(), atletasConTiempo.get(i).getApellidos(),
												atletasConTiempo.get(i).getFechaDeNacimiento(), atletasConTiempo.get(i).getFecha_inscripcion(), 
												atletasConTiempo.get(i).getTiempo()};
									
										atletasConTiempo.get(i).setPosicion(String.valueOf(contadorPosM));
										model.addRow(temp);
										contadorPosM++;		
>>>>>>> branch 'master' of https://github.com/Fireruner/IPSL4-4
									}
									else{
										Object[] temp = {atletasConTiempo.get(i).getDni() ,contadorPosF, atletasConTiempo.get(i).getSexo(), 
												atletasConTiempo.get(i).getDorsal(), atletasConTiempo.get(i).getNombre(), atletasConTiempo.get(i).getApellidos(),
												atletasConTiempo.get(i).getFechaDeNacimiento(), atletasConTiempo.get(i).getFecha_inscripcion(), 
												atletasConTiempo.get(i).getTiempo()};
										
										atletasConTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
										model.addRow(temp);
										contadorPosF++;	
									}
								}	

								for(int i = 0; i<atletasSinTiempo.size(); i++){	//Y LUEGO LOS QUE NO
									if(atletasSinTiempo.get(i).getSexo().equals(m)) {
										Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosM, atletasSinTiempo.get(i).getSexo(), 
												atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
												atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
												"---"};
									
										atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
										model.addRow(temp);
										contadorPosM++;
									}
									else{
										Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosF, atletasSinTiempo.get(i).getSexo(), 
												atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
												atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
												"---"};
									
										atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
										model.addRow(temp);
										contadorPosF++;
									}
								}
								
								tablaResultados.setModel(model);
							}
<<<<<<< HEAD

							for(int i = 0; i<atletasSinTiempo.size(); i++){	//Y LUEGO LOS QUE NO
								if(atletasSinTiempo.get(i).getSexo().equals(m)) {
									Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosM, atletasSinTiempo.get(i).getSexo(), 
											atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
											atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
											"---"};
									
									atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
									model.addRow(temp);
									contadorPosM++;
								}
								else{
									Object[] temp = {atletasSinTiempo.get(i).getDni() ,contadorPosF, atletasSinTiempo.get(i).getSexo(), 
											atletasSinTiempo.get(i).getDorsal(), atletasSinTiempo.get(i).getNombre(), atletasSinTiempo.get(i).getApellidos(),
											atletasSinTiempo.get(i).getFechaDeNacimiento(), atletasSinTiempo.get(i).getFecha_inscripcion(), 
											"---"};
									
									atletasSinTiempo.get(i).setPosicion(String.valueOf(contadorPosF));
									model.addRow(temp);
									contadorPosF++;
								}
=======
							else {
								MyTableModel m = new MyTableModel();
								tablaResultados.setModel(m);
>>>>>>> branch 'master' of https://github.com/Fireruner/IPSL4-4
							}
<<<<<<< HEAD
							tablaResultados.setModel(model);
							}
							else {
								MyTableModel m = new MyTableModel();
								tablaResultados.setModel(m);
							}
=======
>>>>>>> branch 'master' of https://github.com/Fireruner/IPSL4-4
						}
						
						catch (SQLException ex){
							JOptionPane.showMessageDialog(null, "Error en atleta");
							System.err.println("Error en atleta");
							ex.printStackTrace();
						}
						
						
					}
				});
			}
			return btnMostrarResultados;
		}
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL FILTRO DEL NOMBRE DE LA CARRERA (PARTE IZQ DE LA IGU)
		 */
		
		//PANEL QUE CONTIENE TANTO LA LABEL QUE INDICA LA ESCRITURA COMO EL JTEXTFIELD
		private JPanel getPanelFiltroCarrera() {
			if (panelFiltroCarrera == null) {
				panelFiltroCarrera = new JPanel();
				panelFiltroCarrera.setLayout(new GridLayout(2, 1, 0, 0));
				panelFiltroCarrera.add(getLblNombreDeCarrera());
				panelFiltroCarrera.add(getPanelNombreCarrera());
			}
			return panelFiltroCarrera;
		}
			
		//LABEL QUE INDICA QUE SE DEBE FILTRAR
		private JLabel getLblNombreDeCarrera() {
			if (lblNombreDeCarrera == null) {
				lblNombreDeCarrera = new JLabel("Nombre de la carrera a filtrar:");
				lblNombreDeCarrera.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombreDeCarrera.setVerticalAlignment(SwingConstants.BOTTOM);
				lblNombreDeCarrera.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			}
			return lblNombreDeCarrera;
		}
		
		//PANEL QUE CONTIENE EL JTEXTFIELD
		private JPanel getPanelNombreCarrera() {
			if (panelNombreCarrera == null) {
				panelNombreCarrera = new JPanel();
				panelNombreCarrera.setLayout(new BorderLayout(0, 0));
				panelNombreCarrera.add(getComboClasificacion(), BorderLayout.NORTH);
			}
			return panelNombreCarrera;
		}
		
		
		
		
		
		
		
		/*
		 * A PARTIR DE AQUI TODO LO RELACIONADO CON EL TÃ?TULO: "RESULTADOS" Y LAS COLUMNAS DE LA JTABLE 
		 */
			
		
		//PANEL QUE CONTIENE LA LABEL DEL TITULO Y DE LAS CATEGORIAS DE LA TABLA
		private JPanel getPanelTituloYColumnas() {
			if (panelTituloYColumnas == null) {
				panelTituloYColumnas = new JPanel();
				panelTituloYColumnas.setLayout(new BorderLayout(0, 0));
				panelTituloYColumnas.add(getLblTitulo());
				panelTituloYColumnas.add(getPanelColumnas(), BorderLayout.SOUTH);
			}
			return panelTituloYColumnas;
		}	
		
		//LABEL DEL TITULO DE LA TABLA
		private JLabel getLblTitulo() {
			if (lblTitulo == null) {
				lblTitulo = new JLabel("Resultados");
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			}
			return lblTitulo;
		}
		
		//PANEL QUE SOLO CONTIENE LAS LABELS DE LAS CATEGORIAS DE LA TABLA
		private JPanel getPanelColumnas() {
			if (panelColumnas == null) {
				panelColumnas = new JPanel();
				panelColumnas.setLayout(new GridLayout(0, 9, 0, 0));
				panelColumnas.add(getLblCDNI());
				panelColumnas.add(getLblCPosicion());
				panelColumnas.add(getLblCSexo());
				panelColumnas.add(getLblCDorsal());
				panelColumnas.add(getLblCNombre());
				panelColumnas.add(getLblCApellidos());
				panelColumnas.add(getLblCFNacimiento());
				panelColumnas.add(getLblCFInscripcion());
				panelColumnas.add(getLblCTiempo());
			}
			return panelColumnas;
		}
		
		
		//LABELS QUE INDICAN LAS COLUMNAS DE LA JTABLE
		private JLabel getLblCDNI() {
			if (lblCDNI == null) {
				lblCDNI = new JLabel("DNI");
				lblCDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCDNI.setHorizontalAlignment(SwingConstants.CENTER);
			}
			return lblCDNI;
		}
		private JLabel getLblCPosicion() {
			if (lblCPosicion == null) {
				lblCPosicion = new JLabel("PosiciÃ³n");
				lblCPosicion.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCPosicion.setHorizontalAlignment(SwingConstants.CENTER);
			}
			return lblCPosicion;
		}
		private JLabel getLblCSexo() {
			if (lblCSexo == null) {
				lblCSexo = new JLabel("Sexo");
				lblCSexo.setHorizontalAlignment(SwingConstants.CENTER);
				lblCSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCSexo;
		}
		private JLabel getLblCDorsal() {
			if (lblCDorsal == null) {
				lblCDorsal = new JLabel("Dorsal");
				lblCDorsal.setHorizontalAlignment(SwingConstants.CENTER);
				lblCDorsal.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCDorsal;
		}
		private JLabel getLblCNombre() {
			if (lblCNombre == null) {
				lblCNombre = new JLabel("Nombre");
				lblCNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblCNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCNombre;
		}
		private JLabel getLblCApellidos() {
			if (lblCApellidos == null) {
				lblCApellidos = new JLabel("Apellidos");
				lblCApellidos.setHorizontalAlignment(SwingConstants.CENTER);
				lblCApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCApellidos;
		}
		private JLabel getLblCFNacimiento() {
			if (lblCFNacimiento == null) {
				lblCFNacimiento = new JLabel("F. Nacimiento");
				lblCFNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
				lblCFNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCFNacimiento;
		}
		private JLabel getLblCFInscripcion() {
			if (lblCFInscripcion == null) {
				lblCFInscripcion = new JLabel("F. InscripciÃ³n");
				lblCFInscripcion.setHorizontalAlignment(SwingConstants.CENTER);
				lblCFInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCFInscripcion;
		}
		private JLabel getLblCTiempo() {
			if (lblCTiempo == null) {
				lblCTiempo = new JLabel("Tiempo");
				lblCTiempo.setHorizontalAlignment(SwingConstants.CENTER);
				lblCTiempo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			return lblCTiempo;
		}
	private JButton getBtnClasificacion() {
		if (btnClasificacion == null) {
			btnClasificacion = new JButton("ClasificaciÃ³n");
			btnClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelClasificacion");
				}
			});
		}
		return btnClasificacion;
	}
	private JButton getBtnVolverAlMenu() {
		if (btnVolverAlMenu == null) {
			btnVolverAlMenu = new JButton("Volver al Menu");
			btnVolverAlMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
					removeModelContent((MyTableModel)tablaResultados.getModel());
				}
			});
		}
		return btnVolverAlMenu;
	}
	private JButton getBtMenu() {
		if (btMenu == null) {
			btMenu = new JButton("Volver al menu");
			btMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
			btMenu.setBounds(483, 212, 156, 23);
		}
		return btMenu;
	}
	private JButton getBtnRegistrarCorredor() {
		if (btnRegistrarCorredor == null) {
			btnRegistrarCorredor = new JButton("Registrar Corredor");
			btnRegistrarCorredor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					VentanaDatosAtleta ventanaRegistro = new VentanaDatosAtleta();
					ventanaRegistro.setAlwaysOnTop(true);
					ventanaRegistro.setVisible(true);
				}
			});
		}
		return btnRegistrarCorredor;
	}
	
	private JButton getBtnRegistrarTiempos() {
		if (btnRegistrarTiempos == null) {
			btnRegistrarTiempos = new JButton("Registrar Tiempos");
			btnRegistrarTiempos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelFicheros");
				}
			});
		}
		return btnRegistrarTiempos;
	}
	
	private JPanel getPanelFicheros() {
		if(panelFicheros==null) {
			panelFicheros = new JPanel();
			panelFicheros.setLayout(new BorderLayout(0, 0));
			panelFicheros.add(getPanelSeleccion(), BorderLayout.SOUTH);
			panelFicheros.add(getPanelInformacion(), BorderLayout.CENTER);
		}
		return panelFicheros;
	}
	
	private JPanel getPanelSeleccion() {
		if (panelSeleccion == null) {
			panelSeleccion = new JPanel();
			panelSeleccion.setLayout(new BorderLayout(0, 0));
			panelSeleccion.add(getPanelBotones(), BorderLayout.EAST);
			panelSeleccion.add(getPanelVolver(), BorderLayout.WEST);
		}
		return panelSeleccion;
	}
	
	//BOTON QUE ABRE UN JFILECHOOSER PARA SELECCIONAR EL FICHERO A CARGAR
	private JButton getBtnSeleccion(){
		if (btnSeleccion == null) {
			btnSeleccion = new JButton("Seleccionar archivo");
			btnSeleccion.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
						    "TXT files", "txt");
					fc.setFileFilter(filter);
	                fc.setCurrentDirectory(new java.io.File("."));
	                fc.setDialogTitle("Selector de archivos");
	                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                fc.setMultiSelectionEnabled(false);
	                if (fc.showOpenDialog(btnSeleccion) == JFileChooser.APPROVE_OPTION) {
	                	if(!fc.getSelectedFile().getName().endsWith(".txt"))
	                		JOptionPane.showMessageDialog(null, "Solo se admiten archivos de texto (.txt)");
	                	else
	                	{
	                		btnCargar.setEnabled(true);
		                	archivo = fc.getSelectedFile();   
		                	lblCarreraElegida.setText("Carrera elegida: " + obtenNombreCarrera(archivo.getName()));
	                	}
	                }
	            }
	        });
		}	
		return btnSeleccion;
	}
	private JPanel getPanelInformacion() {
		if (panelInformacion == null) {
			panelInformacion = new JPanel();
			panelInformacion.setLayout(new BorderLayout(0, 0));
			panelInformacion.add(getLblCarreraElegida(), BorderLayout.CENTER);
		}
		return panelInformacion;
	}
	private JLabel getLblCarreraElegida() {
		if (lblCarreraElegida == null) {
			lblCarreraElegida = new JLabel("Carrera elegida:");
			lblCarreraElegida.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarreraElegida.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			
		}
		return lblCarreraElegida;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnSeleccion());
			panelBotones.add(getBtnCargar());
		}
		return panelBotones;
	}
	
	//BOTON QUE LLAMA AL METODO QUE CARGA EL FICHERO SELECCIONADO EN LA BASE DE DATOS
	private JButton getBtnCargar() {
		if (btnCargar == null) {
			btnCargar = new JButton("Cargar");
			btnCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						cargaContenido(archivo);
						if(sinFallosFormato && sinFallosDni && sinFallosNombreCarrera && sinFallosEstructura) {
							JOptionPane.showMessageDialog(null, "OperaciÃ³n realizada con Ã©xito.");
							btnCargar.setEnabled(false);
							archivo = null;
						}
						sinFallosFormato = true;
						sinFallosDni = true;
						sinFallosNombreCarrera = true;
						sinFallosEstructura = true;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					btnCargar.setEnabled(false);
				}
			});
			btnCargar.setEnabled(false);
		}
		return btnCargar;
	}
	
	private JPanel getPanelVolver() {
		if (panelVolver == null) {
			panelVolver = new JPanel();
			panelVolver.add(getBtnVolverAlMen());
		}
		return panelVolver;
	}
	private JButton getBtnVolverAlMen() {
		if (btnVolverAlMen == null) {
			btnVolverAlMen = new JButton("Volver al menu");
			btnVolverAlMen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
		}
		return btnVolverAlMen;
	}
	
	//METODO QUE CARGA EL CONTENIDO EN LA BBDD, SI ALGUN DATO ESTA MAL NO LO CARGA, NO IMPLICA QUE NO CARGUE LA TOTALIDAD DEL FICHERO, SOLO AQUELLOS DATOS QUE TENGAN EL FORMATO CORRECTO
<<<<<<< HEAD
	void cargaContenido(File archivo) throws FileNotFoundException, IOException, SQLException {
	      boolean errorFormato = false;
	      boolean errorPresencia = false;
	      boolean errorNombreCarrera = false;
	      boolean errorEstructura = false;
	      boolean errorPago = false;
	      ArrayList<String> datosIncorrectos = new ArrayList<String>();
	      
	      String carrera = archivo.getName();
	      String nombreCarrera = obtenNombreCarrera(carrera);
	      
	      if(!gc.comprobadorCarrera(nombreCarrera)) {	//si la carrera no existe error carrera
	    	  errorNombreCarrera = true;
	      }
	      
	      
		  String cadena;							//ESTRUCTURA DEL FICHERO: TIEMPO DNI
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      
	      while((cadena = b.readLine())!=null) {
	          boolean comprobadorTiempo = false;
	          boolean comprobadorDNI = false;
	          boolean comprobadorPago = false;
	          String[] partes = cadena.split(" ");	//dividimos las partes en tiempo y dni respectivamente
	          if(partes.length==2) {
	        	  if(partes[0].equals("---"))
	        		  if(gc.comprobadorPresencia(partes[1],nombreCarrera)&&"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) {     	//sin tiempo?
	        			  DataBaseManager.añadirTiempoAtleta(nombreCarrera, partes[0], partes[1]);						//buscamos su dni en la bbdd y le asignamos su tiempo null
	        		  }
	        	  else	{			//con tiempo        			  
	        		  if(gc.comprobadorTiempos(partes[0])&&gc.comprobadorPresencia(partes[1],nombreCarrera)&&"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) { //si el tiempo es valido buscamos su dni en la bbdd y le asignamos su tiempo
	        			  DataBaseManager.añadirTiempoAtleta(nombreCarrera, partes[0], partes[1]);
	        		  }
	        	  }
	        	  System.out.println("pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera)) + "   " + DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera));
	        	  
	          	//Lo que conseguimos así es que añada los corredores cuyo formato es correcto, los que tengan un formato incorrecto han de ser revisados por el cliente
	          
	          	//Distintas comprobaciones con respecto a cada linea, los comprobadores se usan a la hora de imprimir los fallos en el fichero de fallos, los errores para la ventana emergente
	          	if(!gc.comprobadorPresencia(partes[1],nombreCarrera)) {
	  			  	comprobadorDNI = true;
	          		errorPresencia = true;
	          	}
	          	else {
	          		if(!"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) {
	          			comprobadorPago = true;
	          			errorPago = true;
	          		}
	          	}
	          	if(!gc.comprobadorTiempos(partes[0])&&!partes[0].equals("---")) {
	  			    comprobadorTiempo = true;        			  
	  			    errorFormato = true;
	          	}
	          }
	          else {
	        	 //aqui no hace falta que añada datos incorrectos, si la estructura del fichero esta mal no debe añadir cada linea al fichero de salida
	        	 errorEstructura = true; 
	          }      
	          
	          //Comprobamos los distintos errores
	          if(comprobadorTiempo&&!comprobadorDNI&&!comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto");
	          }
	          else if(!comprobadorTiempo&&comprobadorDNI&&!comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El corredor no está en la base de datos");
	          }
	          else if(!comprobadorTiempo&&!comprobadorDNI&&comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El corredor no ha pagado para competir en esta carrera");
	          }
			  else if(comprobadorTiempo&&comprobadorDNI&&!comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El corredor no está en la base de datos y el formato de tiempo es incorrecto");
			  }
			  else if(comprobadorTiempo&&!comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto y el corredor no ha pagado para competir en esta carrera");
			  }
			  else if(!comprobadorTiempo&&comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El corredor no está en la base de datos y el corredor no ha pagado para competir en esta carrera");
			  }
			  else if(comprobadorTiempo&&comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto, el corredor no está en la base de datos y no ha pagado para competir en esta carrera");
			  }
	      }
	      
	      if(errorNombreCarrera) {
	    	  JOptionPane.showMessageDialog(null, "La carrera referente al nombre del fichero no existe en la base de datos.");
	    	  sinFallosNombreCarrera = false;
	      }
	      else {
	    	  if(errorFormato) {
	    		  JOptionPane.showMessageDialog(null, "Algunos tiempos no han sido añadidos a la base de datos. Por favor, compruebe el fichero de tiempos.");
	    		  sinFallosFormato=false;
	    	  }
	    	  if(errorPresencia) {
	    		  JOptionPane.showMessageDialog(null, "Alguno de los corredores del fichero no se encuentra en esta carrera, por tanto no ha sido añadido.");
	    		  sinFallosDni = false;
	    	  }
	    	  if(errorEstructura) {
	    		  JOptionPane.showMessageDialog(null, "Los datos del fichero poseen una estructura incorrecta.");
	    		  sinFallosEstructura = false;
	    	  }
	    	  if(errorPago) {
	    		  JOptionPane.showMessageDialog(null, "Alguno de los corredores no ha sido añadido debido a que no ha pagado la carrera.");
=======
		void cargaContenido(File archivo) throws FileNotFoundException, IOException, SQLException {
	      boolean errorFormato = false;
	      boolean errorPresencia = false;
	      boolean errorNombreCarrera = false;
	      boolean errorEstructura = false;
	      boolean errorPago = false;
	      ArrayList<String> datosIncorrectos = new ArrayList<String>();
	      
	      String carrera = archivo.getName();
	      String nombreCarrera = obtenNombreCarrera(carrera);
	      
	      if(!gc.comprobadorCarrera(nombreCarrera)) {	//si la carrera no existe error carrera
	    	  errorNombreCarrera = true;
	      }
	      
	      
		  String cadena;							//ESTRUCTURA DEL FICHERO: TIEMPO DNI
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      
	      while((cadena = b.readLine())!=null) {
	          boolean comprobadorTiempo = false;
	          boolean comprobadorDNI = false;
	          boolean comprobadorPago = false;
	          String[] partes = cadena.split(" ");	//dividimos las partes en tiempo y dni respectivamente
	          if(partes.length==2) {
	           	  if(partes[0].equals("---")) {
	        		  if(gc.comprobadorPresencia(partes[1],nombreCarrera)&&"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) {     	//sin tiempo?
	        			  DataBaseManager.añadirTiempoAtleta(nombreCarrera, partes[0], partes[1]);						//buscamos su dni en la bbdd y le asignamos su tiempo null
	        		  }
	           	  }
	        	  else	{		
	        		  //con tiempo        			  
	        		  if(gc.comprobadorTiempos(partes[0])&&gc.comprobadorPresencia(partes[1],nombreCarrera)&&"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) { //si el tiempo es valido buscamos su dni en la bbdd y le asignamos su tiempo
	        			  DataBaseManager.añadirTiempoAtleta(nombreCarrera, partes[0], partes[1]);
	        		  }
	        	  }
	        	  
	          	//Lo que conseguimos asi es que aÃ±ada los corredores cuyo formato es correcto, los que tengan un formato incorrecto han de ser revisados por el cliente
	          
	          	//Distintas comprobaciones con respecto a cada linea, los comprobadores se usan a la hora de imprimir los fallos en el fichero de fallos, los errores para la ventana emergente
	          	if(!gc.comprobadorPresencia(partes[1],nombreCarrera)) {
	  			  	comprobadorDNI = true;
	          		errorPresencia = true;
	          	}
	          	else {
	          		if(!"pagado".equals(DataBaseManager.comprobarAtletaPagado(partes[1],nombreCarrera))) {
	          			comprobadorPago = true;
	          			errorPago = true;
	          		}
	          	}
	          	if(!gc.comprobadorTiempos(partes[0])&&!partes[0].equals("---")) {
	  			    comprobadorTiempo = true;        			  
	  			    errorFormato = true;
	          	}
	          }
	          
	          else {
	        	 //aqui no hace falta que aÃ±ada datos incorrectos, si la estructura del fichero esta mal no debe aÃ±adir cada linea al fichero de salida
	        	 errorEstructura = true; 
	          }      
	          
	          //Comprobamos los distintos errores
	          if(comprobadorTiempo&&!comprobadorDNI&&!comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto");
	          }
	          else if(!comprobadorTiempo&&comprobadorDNI&&!comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El corredor no estÃ¡ en la base de datos");
	          }
	          else if(!comprobadorTiempo&&!comprobadorDNI&&comprobadorPago) {
	        	  datosIncorrectos.add(cadena + "   El corredor no ha pagado para competir en esta carrera");
	          }
			  else if(comprobadorTiempo&&comprobadorDNI&&!comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El corredor no estÃ¡ en la base de datos y el formato de tiempo es incorrecto");
			  }
			  else if(comprobadorTiempo&&!comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto y el corredor no ha pagado para competir en esta carrera");
			  }
			  else if(!comprobadorTiempo&&comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El corredor no estÃ¡ en la base de datos y el corredor no ha pagado para competir en esta carrera");
			  }
			  else if(comprobadorTiempo&&comprobadorDNI&&comprobadorPago) {
				  datosIncorrectos.add(cadena + "   El formato de tiempo es incorrecto, el corredor no estÃ¡ en la base de datos y no ha pagado para competir en esta carrera");
			  }
	      }
	      
	      if(errorNombreCarrera) {
	    	  JOptionPane.showMessageDialog(null, "La carrera referente al nombre del fichero no existe en la base de datos.");
	    	  sinFallosNombreCarrera = false;
	      }
	      else {
	    	  if(errorFormato) {
	    		  JOptionPane.showMessageDialog(null, "Algunos tiempos no han sido aÃ±adidos a la base de datos. Por favor, compruebe el fichero de tiempos.");
	    		  sinFallosFormato=false;
	    	  }
	    	  if(errorPresencia) {
	    		  JOptionPane.showMessageDialog(null, "Alguno de los corredores del fichero no se encuentra en esta carrera, por tanto no ha sido aÃ±adido.");
	    		  sinFallosDni = false;
	    	  }
	    	  if(errorEstructura) {
	    		  JOptionPane.showMessageDialog(null, "Los datos del fichero poseen una estructura incorrecta.");
	    		  sinFallosEstructura = false;
	    	  }
	    	  if(errorPago) {
	    		  JOptionPane.showMessageDialog(null, "Alguno de los corredores no ha sido aÃ±adido debido a que no ha pagado la carrera.");
>>>>>>> branch 'master' of https://github.com/Fireruner/IPSL4-4
	    		  sinFallosEstructura = false;
	    	  }
	      }   
	      b.close();
	      String fichero = generaFicheroFallos(datosIncorrectos, nombreCarrera);
	      if(!fichero.equals("Se ha producido un error")) {
	    	  //abre el fichero
	    	  abrirarchivo("./"+fichero);
	      }
	      lblCarreraElegida.setText("Carrera elegida:");
	}

	public void abrirarchivo(String archivo){

		     try {

		            File objetofile = new File (archivo);
		            Desktop.getDesktop().open(objetofile);

		     }catch (IOException ex) {

		            System.out.println(ex);
		     }
		}

	private String generaFicheroFallos(ArrayList<String> datos, String nombreCarrera) {
		 int idFallo = (int) (Math.random()*9999999 + 10000);
		 boolean fallo = true;
		 String nombreFichero = "Fallo_"+idFallo+"_"+nombreCarrera+".txt";
		 FileWriter fichero = null;
	     PrintWriter pw = null;
	     if(datos.size()>0) {
	    	 try
	    	 {
	    		 fichero = new FileWriter("./"+nombreFichero);
	    		 pw = new PrintWriter(fichero);
	    		 pw.println("El fichero de la carrera " + nombreCarrera + " contiene datos que no se han podido introducir.");
	    		 pw.println("Datos que no han podido ser introducidos en la base de datos:");
	    		 for (int i = 0; i < datos.size(); i++)
	    			 pw.println("    "+datos.get(i));
	    	 } catch (Exception e) {
	    		 e.printStackTrace();
	    	 } finally {
	    		 try {
	    			 // Nuevamente aprovechamos el finally para 
	    			 // asegurarnos que se cierra el fichero.
	    			 if (null != fichero) {
	    				 fichero.close();
	    				 fallo = false;
	    			 }
	    		 } catch (Exception e2) {
	    			 e2.printStackTrace();
	    		 }
	    	 }
	     }
	     if(!fallo)
	    	 return nombreFichero;
	     else {
	    	 return "Se ha producido un error";
	     }
		}

	private String obtenNombreCarrera(String carrera) {
	int caracteresBorrar = 4; //la extension .txt
	return carrera.substring(0, carrera.length()-caracteresBorrar);
	}
<<<<<<< HEAD

=======
	
>>>>>>> branch 'master' of https://github.com/Fireruner/IPSL4-4
	private JButton getBtnAsignarDorsales() {
		if (btnAsignarDorsales == null) {
			btnAsignarDorsales = new JButton("Asignar dorsales");
			btnAsignarDorsales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					DialogDorsales dialogDorsales = new DialogDorsales();
					dialogDorsales.setAlwaysOnTop(true);
					dialogDorsales.setVisible(true);
				}
			});
		}
		return btnAsignarDorsales;
	}
	
	private JPanel getPnlAtletasSegunCarrera() {
		if (pnlAtletasSegunCarrera == null) {
			pnlAtletasSegunCarrera = new JPanel();
			pnlAtletasSegunCarrera.setBackground(Color.LIGHT_GRAY);
			pnlAtletasSegunCarrera.setLayout(null);
			pnlAtletasSegunCarrera.add(getTableAtletas());
			pnlAtletasSegunCarrera.add(getBtnMostrar());
			pnlAtletasSegunCarrera.add(getBtnMenu());
			pnlAtletasSegunCarrera.add(getLblListarAtletasSegn());
			pnlAtletasSegunCarrera.add(getBtnAsignarDorsal());
			pnlAtletasSegunCarrera.add(getLblCarreraSeleccionada());
			pnlAtletasSegunCarrera.add(getComboBox());
		}
		return pnlAtletasSegunCarrera;
	}

	private JTable getTableAtletas() {
		if (tableAtletas == null) {
			modelAtletas = new MyTableModel();
			modelAtletas.addColumn("DNI");
			modelAtletas.addColumn("Nombre");
			modelAtletas.addColumn("Sexo");
			modelAtletas.addColumn("Fecha de inscripciÃ³n");
			modelAtletas.addColumn("Estado");
			modelAtletas.addColumn("Dorsal");
			tableAtletas = new JTable(modelAtletas);
			tableAtletas.setBounds(10, 11, 461, 328);
		}
		return tableAtletas;
	}

	private void actualizarTablaAtletas() {
		ArrayList<String[]> atletas;
		try {
				if(DataBaseManager.existeCarrera((String) comboBox.getSelectedItem()))
				{
					carreraSeleccionada = (String) comboBox.getSelectedItem();
					atletas = DataBaseManager.listarAtletas(carreraSeleccionada);
					lblCarreraSeleccionada.setText(carreraSeleccionada + " seleccionada");
					removeModelContent(modelAtletas);
					String[] cabeceras = { "DNI", "Nombre", "Sexo", "Fecha de InscripciÃ³n", "Estado", "Dorsal" };
					modelAtletas.addRow(cabeceras);
					if(atletas.size()>1)
					{
						for (String[] a : atletas) {
							if(a[5] == null)
								a[5] = "No asignado";
							modelAtletas.addRow(a);
						}
					}
					tableAtletas.setModel(modelAtletas);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "La carrera especificada no se encuentra en la base de datos.");
				}
				

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, SQLError + "lista de atletas");
			e.printStackTrace();
		}

	}


	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar");
			btnMostrar.setMnemonic('M');
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarTablaAtletas();
				}
			});
			btnMostrar.setBounds(686, 60, 89, 23);
		}
		return btnMostrar;
	}

	private JButton getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new JButton("MenÃº");
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					removeModelContent(modelAtletas);
					carreraSeleccionada = "Ninguna carrera";
					lblCarreraSeleccionada.setText(carreraSeleccionada + " seleccionada");
					CardLayout card = (CardLayout)frame.getContentPane().getLayout();
					card.show(frame.getContentPane(), "panelTitulo");
				}
			});
			btnMenu.setMnemonic('U');
			btnMenu.setBounds(481, 316, 145, 23);
		}
		return btnMenu;
	}

	private JLabel getLblListarAtletasSegn() {
		if (lblListarAtletasSegn == null) {
			lblListarAtletasSegn = new JLabel("Listar atletas segÃºn la siguiente carrera: ");
			lblListarAtletasSegn.setDisplayedMnemonic('L');
			lblListarAtletasSegn.setBounds(481, 35, 294, 14);
		}
		return lblListarAtletasSegn;
	}

	private JButton getBtnAtletas() {
		if (btnAtletas == null) {
			btnAtletas = new JButton("Atletas");
			btnAtletas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CardLayout card = (CardLayout) frame.getContentPane().getLayout();
					//actualizarTablaAtletas();
					card.show(frame.getContentPane(), "pnlAtletasSegunCarrera");
				}
			});
		}
		return btnAtletas;
	}
	private JButton getBtnAsignarDorsal() {
		if (btnAsignarDorsal == null) {
			btnAsignarDorsal = new JButton("Asignar Dorsal");
			btnAsignarDorsal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					if(tableAtletas.getSelectedRow()>= 0)
					{
						int row = tableAtletas.getSelectedRow();
						String dni = (String)tableAtletas.getValueAt(row, 0);
						String dorsal = (String)tableAtletas.getValueAt(row, 5);
						String carrera = carreraSeleccionada;
						String estado = (String)tableAtletas.getValueAt(row, 4);
						if(dorsal.equals("No asignado"))
						{
							if(estado.equals("pagado"))
							{
								try 
								{
									int siguienteDorsal = DataBaseManager.getSiguienteDorsalDisponible(carrera);
									dorsal  = ""+siguienteDorsal;
									DataBaseManager.añadirDorsalCorredor(dni, carrera, dorsal);
									JOptionPane.showMessageDialog(null, "Dorsal "+dorsal+" aÃ±adido al corredor "+dni+" para la carrera "+carrera);
								} 
								catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "No se han podido realizar los cambios!");
									e1.printStackTrace();
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "No puedes asignar dorsal a un corredor que aÃºn no ha pagado.");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "No puedes asignar dorsal a un corredor que ya tiene un dorsal asignado");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Para ejecutar esta opciÃ³n debe seleccionar el atleta al que quiere asignar un dorsal.");
					}
				}
			});
			btnAsignarDorsal.setBounds(479, 117, 147, 23);
		}
		return btnAsignarDorsal;
	}
	private JLabel getLblCarreraSeleccionada() {
		if (lblCarreraSeleccionada == null) {
			lblCarreraSeleccionada = new JLabel("Ninguna Carrera Seleccionada");
			lblCarreraSeleccionada.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCarreraSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarreraSeleccionada.setBounds(10, 350, 461, 23);
		}
		return lblCarreraSeleccionada;
	}
	
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(481, 61, 195, 20);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboBox.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, SQLError + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboBox;
	}
	
	private JComboBox getComboCarreras() {
		if (comboCarreras == null) {
			comboCarreras = new JComboBox();
			comboCarreras.setBounds(481, 61, 195, 20);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboCarreras.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, SQLError + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboCarreras;
	}
	private JComboBox getComboClasificacion() {
		if (comboClasificacion == null) {
			comboClasificacion = new JComboBox();
			comboClasificacion.setBounds(481, 61, 195, 20);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboClasificacion.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, SQLError + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboClasificacion;
	}
}