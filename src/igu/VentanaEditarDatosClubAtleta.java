package igu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import src.Atleta;
import src.DataBaseManager;
import src.MiembrosClub;

public class VentanaEditarDatosClubAtleta extends JDialog{

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
	private String dni = ""; 
	private String nombre = ""; 
	private String apellidos = "";
	private String club;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaEditarDatosClubAtleta dialog = new VentanaEditarDatosClubAtleta("club", "carrera", "", "", "");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public VentanaEditarDatosClubAtleta(String club, String carrera, String dni, String nombre, String apellidos) {
		setTitle("Gesti\u00F3n de carreras - Datos del corredor del club: " + club);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNombre());
		getContentPane().add(getTxtfldNombre());
		getContentPane().add(getLblDni());
		getContentPane().add(getTxtfldDNI());
		getContentPane().add(getPnSexo());
		getContentPane().add(getPanel_1());
		getContentPane().add(getBtnEditar());
		getContentPane().add(getLblApellidos());
		getContentPane().add(getTxtfldApellidos());
		this.carrera = carrera;
		this.club = club;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		setBounds(100, 100, 591, 230);
	}	
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setLabelFor(getTxtfldNombre());
			lblNombre.setDisplayedMnemonic('N');
			lblNombre.setBounds(24, 16, 65, 14);
		}
		return lblNombre;
	}

	private JTextField getTxtfldNombre() {
		if (txtfldNombre == null) {
			txtfldNombre = new JTextField();
			txtfldNombre.setBounds(85, 13, 164, 20);
			txtfldNombre.setColumns(10);
			if(!nombre.equals("")) {
				txtfldNombre.setText(nombre);
			}
		}
		return txtfldNombre;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI: ");
			lblDni.setLabelFor(lblDni);
			lblDni.setDisplayedMnemonic('D');
			lblDni.setBounds(24, 104, 46, 14);
		}
		return lblDni;
	}

	private JTextField getTxtfldDNI() {
		if (txtfldDNI == null) {
			txtfldDNI = new JTextField();
			txtfldDNI.setBounds(85, 101, 164, 20);
			txtfldDNI.setColumns(10);
			if(!dni.equals("")) {
				txtfldDNI.setText(dni);
			}
		}
		return txtfldDNI;
	}

	private JPanel getPnSexo() {
		if (pnSexo == null) {
			pnSexo = new JPanel();
			pnSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSexo.setBackground(Color.WHITE);
			pnSexo.setBounds(285, 104, 215, 74);
			pnSexo.setLayout(null);
			pnSexo.add(getRdbtnHombre());
			pnSexo.add(getRdbtnMujer());
		}
		return pnSexo;
	}

	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBackground(Color.WHITE);
			rdbtnHombre.setBounds(19, 28, 70, 23);
		}
		return rdbtnHombre;
	}

	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
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
			pnFecha.setBounds(275, 16, 246, 74);
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
			btnAnnadir.setBounds(333, 237, 89, 23);
			btnAnnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String campoNombre = txtfldNombre.getText();
					String campoApellido = txtfldApellidos.getText();
					String campoDNI = txtfldDNI.getText();
					
					if(campoNombre.equals("") || campoApellido.equals("") || campoDNI.equals(""))
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
									carrera, comprobarFechaInscripcion(), "inscrito", null, null, null, club);
							MiembrosClub.getl().add(atleta);
							txtfldNombre.setText("");
							txtfldApellidos.setText("");
							txtfldDNI.setText("");
						}
					} catch (SQLException e) {

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
			btnAceptar.setBounds(444, 237, 89, 23);
		}
		return btnAceptar;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos: ");
			lblApellidos.setBounds(24, 62, 76, 14);
		}
		return lblApellidos;
	}

	private JTextField getTxtfldApellidos() {
		if (txtfldApellidos == null) {
			txtfldApellidos = new JTextField();
			txtfldApellidos.setBounds(85, 59, 164, 20);
			txtfldApellidos.setColumns(10);
			if(!apellidos.equals("")) {
				txtfldApellidos.setText(apellidos);
			}
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
		// int a�o = (c.get(Calendar.YEAR));
		// int dia = (c.get(Calendar.DATE));
		// int mes = (c.get(Calendar.MONTH));

		LocalDate fechaDeInscripcion = LocalDate.now(ZoneId.of("UTC"));
		return fechaDeInscripcion;
	}
	
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Aceptar y editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i = 0; i<MiembrosClub.getl().size(); i++) {
						if(MiembrosClub.getl().get(i).getDni().equals(dni)) {
							MiembrosClub.getl().get(i).setDni(txtfldDNI.getText().toUpperCase());
							MiembrosClub.getl().get(i).setNombre(txtfldNombre.getText());
							MiembrosClub.getl().get(i).setApellidos(txtfldApellidos.getText());
							MiembrosClub.getl().get(i).setSexo(comprobarSexo());
							MiembrosClub.getl().get(i).setFechaDeNacimiento(comprobarFechaNacimiento());
						}
					}
					dispose();
				}
			});
			btnEditar.setBounds(85, 144, 142, 23);
		}
		return btnEditar;
	}
}

