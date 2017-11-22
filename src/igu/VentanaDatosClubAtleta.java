package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import src.Atleta;
import src.Categoria;
import src.DataBaseManager;
import src.MiembrosClub;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class VentanaDatosClubAtleta extends JDialog {

	private final static int AnOS = 90;
	private final static int AnO_INICIAL = 2016;

	private JLabel lblNombre;
	private JTextField txtfldNombre;
	private JLabel lblDni;
	private JTextField txtfldDNI;
	private JPanel pnSexo;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnFecha;
	private JComboBox<Integer> cbxDia;
	private JComboBox<String> cbxMes;
	private JComboBox<Integer> cbxAno;
	private JButton btnAnnadir;
	private JButton btnAceptar;
	private JLabel lblApellidos;
	private JTextField txtfldApellidos;
	private JButton btnEditar;
	
	private String carrera; 
	private String club;
	
	private boolean sexo = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaDatosClubAtleta dialog = new VentanaDatosClubAtleta("club", "carrera");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VentanaDatosClubAtleta(String club, String carrera) {
		setTitle("Gesti\u00F3n de carreras - Datos del corredor del club: " + club);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNombre());
		getContentPane().add(getTxtfldNombre());
		getContentPane().add(getLblDni());
		getContentPane().add(getTxtfldDNI());
		getContentPane().add(getPnSexo());
		getContentPane().add(getPanel_1());
		getContentPane().add(getBtnAnnadir());
		getContentPane().add(getBtnAceptar());
		getContentPane().add(getLblApellidos());
		getContentPane().add(getTxtfldApellidos());
		this.carrera = carrera;
		this.club = club;
		setBounds(100, 100, 591, 230);
	}
	


	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setLabelFor(getTxtfldNombre());
			lblNombre.setDisplayedMnemonic('N');
			lblNombre.setBounds(27, 14, 65, 14);
		}
		return lblNombre;
	}

	private JTextField getTxtfldNombre() {
		if (txtfldNombre == null) {
			txtfldNombre = new JTextField();
			txtfldNombre.setBounds(85, 11, 164, 20);
			txtfldNombre.setColumns(10);

		}
		return txtfldNombre;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI: ");
			lblDni.setLabelFor(lblDni);
			lblDni.setDisplayedMnemonic('D');
			lblDni.setBounds(28, 97, 46, 14);
		}
		return lblDni;
	}

	private JTextField getTxtfldDNI() {
		if (txtfldDNI == null) {
			txtfldDNI = new JTextField();
			txtfldDNI.setBounds(85, 94, 164, 20);
			txtfldDNI.setColumns(10);
		}
		return txtfldDNI;
	}

	private JPanel getPnSexo() {
		if (pnSexo == null) {
			pnSexo = new JPanel();
			pnSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSexo.setBackground(Color.WHITE);
			pnSexo.setBounds(303, 97, 215, 74);
			pnSexo.setLayout(null);
			pnSexo.add(getRdbtnHombre());
			pnSexo.add(getRdbtnMujer());
		}
		return pnSexo;
	}

	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sexo = true;
				}
			});
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBackground(Color.WHITE);
			rdbtnHombre.setBounds(19, 28, 70, 23);
			
		}
		return rdbtnHombre;
	}

	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			rdbtnMujer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sexo = true;
				}
			});
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setBackground(Color.WHITE);
			rdbtnMujer.setBounds(100, 28, 109, 23);
		}
		return rdbtnMujer;
	}

	private JPanel getPanel_1() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.setBorder(
					new TitledBorder(null, "Fecha de nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFecha.setBackground(Color.WHITE);
			pnFecha.setBounds(293, 14, 246, 74);
			pnFecha.setLayout(null);
			pnFecha.add(getCbxDia());
			pnFecha.add(getCbxMes());
			pnFecha.add(getCbxAno());
		}
		return pnFecha;
	}

	private JComboBox<Integer> getCbxDia() {
		if (cbxDia == null) {
			cbxDia = new JComboBox<Integer>();
			String[] dias = new String[31];
			for (int i = 0; i < dias.length; i++) {
				dias[i] = String.valueOf(i + 1);
			}
			cbxDia.setModel(new DefaultComboBoxModel(dias));
			cbxDia.setBackground(Color.WHITE);
			cbxDia.setBounds(10, 29, 37, 20);
		}
		return cbxDia;
	}

	private JComboBox<String> getCbxMes() {
		if (cbxMes == null) {
			cbxMes = new JComboBox<String>();
			cbxMes.setModel(new DefaultComboBoxModel<String>(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			cbxMes.setBackground(Color.WHITE);
			cbxMes.setBounds(67, 29, 69, 20);
		}
		return cbxMes;
	}

	private JComboBox<Integer> getCbxAno() {
		if (cbxAno == null) {
			cbxAno = new JComboBox<Integer>();
			String[] anos = new String[AnOS];
			for (int i = AnO_INICIAL; i > AnO_INICIAL - AnOS; i--) {
				anos[AnO_INICIAL - i] = String.valueOf(i);
			}
			cbxAno.setModel(new DefaultComboBoxModel(anos));
			cbxAno.setBackground(Color.WHITE);
			cbxAno.setBounds(146, 29, 73, 20);
		}
		return cbxAno;
	}

	private JButton getBtnAnnadir() {
		if (btnAnnadir == null) {
			btnAnnadir = new JButton("A\u00F1adir");
			btnAnnadir.setBounds(25, 148, 89, 23);
			btnAnnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						try {
							if(DataBaseManager.getCategoriasPorCarrera(carrera).size()>0) {
								String campoNombre = txtfldNombre.getText();
								String campoApellido = txtfldApellidos.getText();
								String campoDNI = txtfldDNI.getText();
								
								if(campoNombre.equals("") || campoApellido.equals("") || campoDNI.equals("") || !sexo)
								{
									JOptionPane.showMessageDialog(null, "Debe introducir todos los campos para hacer el registro!");
									return;
								}

								boolean valido;
								
								try {
									
									valido = DataBaseManager.atletaEstaEnCarrera(txtfldDNI.getText().toUpperCase(), carrera); 
									if (valido) {

										JOptionPane.showMessageDialog(null, "El atleta ya est\u00E1 inscrito en la carrera");
									}

									else {
										if(existeCategoria()) {
											Atleta atleta = new Atleta(txtfldDNI.getText().toUpperCase(), txtfldNombre.getText(),
													txtfldApellidos.getText(), comprobarSexo(), comprobarFechaNacimiento(),
													carrera, comprobarFechaInscripcion(), "inscrito", null, null, comprobarCategoria(),club);
											boolean estaEnLista = false;
											for(int i = 0; i<MiembrosClub.getl().size(); i++) {
												if(MiembrosClub.getl().get(i).getDni().equals(atleta.getDni())){
													estaEnLista = true;
												}
											}
											if(!estaEnLista) {
												MiembrosClub.getl().add(atleta);
												txtfldNombre.setText("");
												txtfldApellidos.setText("");
												txtfldDNI.setText("");
												buttonGroup.clearSelection();
												cbxDia.setSelectedIndex(0);
												cbxMes.setSelectedIndex(0);
												cbxAno.setSelectedIndex(0);
											}
											else {
												JOptionPane.showMessageDialog(null, "El atleta ya ha sido añadido a esta inscripci\u00F3n");
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "Esta carrera no consta de una categoria para el atleta que se desea introducir.\nPor favor compruebe que existe una categoria referente al sexo y edad del atleta.");
										}
									}
								} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
							}
							else {
								String campoNombre = txtfldNombre.getText();
								String campoApellido = txtfldApellidos.getText();
								String campoDNI = txtfldDNI.getText();
								
								if(campoNombre.equals("") || campoApellido.equals("") || campoDNI.equals("") || !sexo)
								{
									JOptionPane.showMessageDialog(null, "Debe introducir todos los campos para hacer el registro!");
									return;
								}

								boolean valido;
								
								try {
									
									valido = DataBaseManager.atletaEstaEnCarrera(txtfldDNI.getText().toUpperCase(), carrera); 
									if (valido) {

										JOptionPane.showMessageDialog(null, "El atleta ya est\u00E1 inscrito en la carrera");
									}

									else {
										Atleta atleta = new Atleta(txtfldDNI.getText().toUpperCase(), txtfldNombre.getText(),
												txtfldApellidos.getText(), comprobarSexo(), comprobarFechaNacimiento(),
												carrera, comprobarFechaInscripcion(), "inscrito", null, null, null,club);
										boolean estaEnLista = false;
										for(int i = 0; i<MiembrosClub.getl().size(); i++) {
											if(MiembrosClub.getl().get(i).getDni().equals(atleta.getDni())){
												estaEnLista = true;
											}
										}
										if(!estaEnLista) {
											MiembrosClub.getl().add(atleta);
											txtfldNombre.setText("");
											txtfldApellidos.setText("");
											txtfldDNI.setText("");
											buttonGroup.clearSelection();
											cbxDia.setSelectedIndex(0);
											cbxMes.setSelectedIndex(0);
											cbxAno.setSelectedIndex(0);
										}
										else {
											JOptionPane.showMessageDialog(null, "El atleta ya ha sido añadido a esta inscripci\u00F3n");
										}
									}
								} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return btnAnnadir;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAceptar.setBounds(160, 148, 89, 23);
		}
		return btnAceptar;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos: ");
			lblApellidos.setBounds(27, 56, 76, 14);
		}
		return lblApellidos;
	}

	private JTextField getTxtfldApellidos() {
		if (txtfldApellidos == null) {
			txtfldApellidos = new JTextField();
			txtfldApellidos.setBounds(85, 53, 164, 20);
			txtfldApellidos.setColumns(10);
		}
		return txtfldApellidos;
	}

	private String comprobarSexo() {
		String sexo1 = "";
		boolean sexo = rdbtnHombre.isSelected();
		if (sexo) {
			sexo1 = "masculino";
		} else
			sexo1 = "femenino";
		return sexo1;
	}

	private LocalDate comprobarFechaNacimiento() {
		int ano = Integer.valueOf(cbxAno.getSelectedItem().toString());
		int dia = Integer.valueOf(cbxDia.getSelectedItem().toString());
		int mes = Integer.valueOf(cbxMes.getSelectedItem().toString());

		LocalDate fechaDeNacimiento = LocalDate.of(ano, mes, dia);
		return fechaDeNacimiento;

	}

	private LocalDate comprobarFechaInscripcion() {
		// Calendar c = Calendar.getInstance();
		// int aï¿½o = (c.get(Calendar.YEAR));
		// int dia = (c.get(Calendar.DATE));
		// int mes = (c.get(Calendar.MONTH));

		LocalDate fechaDeInscripcion = LocalDate.now(ZoneId.of("UTC"));
		return fechaDeInscripcion;
	}
	
	private String comprobarCategoria() throws SQLException {
		LocalDate fechaDeInscripcion = LocalDate.now(ZoneId.of("UTC"));
		int ano = Integer.valueOf(cbxAno.getSelectedItem().toString());
		int annoActual = fechaDeInscripcion.getYear();
		int edad = annoActual - ano;
		String sexo= "";
		if(rdbtnHombre.isSelected())
			sexo = "masculino";
		else
			sexo = "femenino";

		ArrayList<Categoria> categoriasCarrera = DataBaseManager.getCategoriasPorCarrera(carrera);
		
		String solucion = "";
		for(int i = 0; i<categoriasCarrera.size(); i++) {
			if(categoriasCarrera.get(i).getEdadM()>=edad && categoriasCarrera.get(i).getEdadm()<=edad 
					&& categoriasCarrera.get(i).getSexo().equals(sexo)) {
				solucion = categoriasCarrera.get(i).getId();
			}
		}
		return solucion;
	}
	
	private boolean existeCategoria() throws SQLException {
		boolean solucion = false;
		LocalDate fechaDeInscripcion = LocalDate.now(ZoneId.of("UTC"));
		int ano = Integer.valueOf(cbxAno.getSelectedItem().toString());
		int annoActual = fechaDeInscripcion.getYear();
		int edad = annoActual - ano;
		String sexo= "";
		if(rdbtnHombre.isSelected())
			sexo = "masculino";
		else
			sexo = "femenino";
		ArrayList<Categoria> categoriasCarrera = DataBaseManager.getCategoriasPorCarrera(carrera);
		for(int i = 0; i<categoriasCarrera.size(); i++) {
			if(categoriasCarrera.get(i).getEdadM()>=edad && categoriasCarrera.get(i).getEdadm()<=edad 
					&& categoriasCarrera.get(i).getSexo().equals(sexo)) {
				solucion = true;
			}
		}
		return solucion;
	}
}
