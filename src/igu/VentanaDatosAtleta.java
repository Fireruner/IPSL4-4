package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import src.DataBaseManager;

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

public class VentanaDatosAtleta extends JDialog {

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
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblCarrera;
	private JLabel lblApellidos;
	private JTextField txtfldApellidos;
	private JComboBox<String> comboCarreras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaDatosAtleta dialog = new VentanaDatosAtleta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaDatosAtleta() {
		setTitle("Gesti\u00F3n de carreras - Datos del corredor");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNombre());
		getContentPane().add(getTxtfldNombre());
		getContentPane().add(getLblDni());
		getContentPane().add(getTxtfldDNI());
		getContentPane().add(getPnSexo());
		getContentPane().add(getPanel_1());
		getContentPane().add(getBtnAceptar());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getLblCarrera());
		getContentPane().add(getLblApellidos());
		getContentPane().add(getTxtfldApellidos());
		getContentPane().add(getComboCarreras());
		setBounds(100, 100, 591, 335);
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setLabelFor(getTxtfldNombre());
			lblNombre.setDisplayedMnemonic('N');
			lblNombre.setBounds(27, 44, 65, 14);
		}
		return lblNombre;
	}

	private JTextField getTxtfldNombre() {
		if (txtfldNombre == null) {
			txtfldNombre = new JTextField();
			txtfldNombre.setBounds(85, 41, 164, 20);
			txtfldNombre.setColumns(10);
		}
		return txtfldNombre;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI: ");
			lblDni.setLabelFor(lblDni);
			lblDni.setDisplayedMnemonic('D');
			lblDni.setBounds(27, 135, 46, 14);
		}
		return lblDni;
	}

	private JTextField getTxtfldDNI() {
		if (txtfldDNI == null) {
			txtfldDNI = new JTextField();
			txtfldDNI.setBounds(85, 132, 164, 20);
			txtfldDNI.setColumns(10);
		}
		return txtfldDNI;
	}

	private JPanel getPnSexo() {
		if (pnSexo == null) {
			pnSexo = new JPanel();
			pnSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSexo.setBackground(Color.WHITE);
			pnSexo.setBounds(251, 44, 215, 74);
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
			pnFecha.setBounds(27, 211, 246, 74);
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

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setMnemonic('A');
			btnAceptar.setBounds(333, 237, 89, 23);
			btnAceptar.addActionListener(new ActionListener() {
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
					
					String carrera = (String)comboCarreras.getSelectedItem();;
					
					try {
						
						valido = DataBaseManager.atletaEstaEnCarrera(txtfldDNI.getText().toUpperCase(), carrera);
						if (valido) {

							JOptionPane.showMessageDialog(null, "El atleta ya est\u00E1 inscrito en la carrera");
						}

						else {

							Atleta atleta = new Atleta(txtfldDNI.getText().toUpperCase(), txtfldNombre.getText(),
									txtfldApellidos.getText(), comprobarSexo(), comprobarFechaNacimiento(),
									carrera, comprobarFechaInscripcion(), "inscrito", null, null, "indefinido");
							DataBaseManager.anadirCiertoAtleta(atleta);
							atleta.imprimirResguardo();
							dispose();
						}
					} catch (SQLException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
			});

		}
		return btnAceptar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setMnemonic('C');
			btnCancelar.setBounds(444, 237, 89, 23);
		}
		return btnCancelar;
	}

	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Carrera:");
			lblCarrera.setBounds(27, 174, 65, 14);
		}
		return lblCarrera;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos: ");
			lblApellidos.setBounds(27, 92, 76, 14);
		}
		return lblApellidos;
	}

	private JTextField getTxtfldApellidos() {
		if (txtfldApellidos == null) {
			txtfldApellidos = new JTextField();
			txtfldApellidos.setBounds(85, 89, 164, 20);
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
		// int a�o = (c.get(Calendar.YEAR));
		// int dia = (c.get(Calendar.DATE));
		// int mes = (c.get(Calendar.MONTH));

		LocalDate fechaDeInscripcion = LocalDate.now(ZoneId.of("UTC"));
		return fechaDeInscripcion;
	}
	private JComboBox getComboCarreras() {
		if (comboCarreras == null) {
			comboCarreras = new JComboBox();
			comboCarreras.setBounds(85, 163, 164, 37);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboCarreras.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "Error accediendo a la base de datos" + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboCarreras;
	}
}
