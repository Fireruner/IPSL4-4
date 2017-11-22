package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import src.Carrera;
import src.Club;
import src.DataBaseManager;
import src.MiembrosClub;
import src.MyTableModel;

public class VentanaRegistroClub extends JDialog{

	private JFrame frame;
	private JPanel panelRC;
	private JPanel panelRCDatos;
	private JLabel lblRegistroDeClubes;
	private JPanel panelRCDatosBase;
	private JPanel panelNombreClub;
	private JLabel lblNombreDelClub;
	private JPanel panelEscrituraClub;
	private JTextField textField;
	private JPanel panelTablaInscritosClub;
	private JPanel panel;
	private JLabel lblCarrera;
	private JPanel panelEleccionCarrera;
	private JComboBox comboBox;
	private JPanel panelDatosInscritos;
	private JLabel lblListaDeAtletas;
	private JPanel panelColumnasTabla;
	private JLabel lblDni;
	private JLabel lblCategora;
	private JLabel lblNombre;
	private JTable tableClubInscritos;
	private JPanel panelRCBotones;
	private JPanel panelRCEditarDatos;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnConfirmar;
	private JButton btnActualizar;
	private JButton btnAnnadir;
	private JButton btnReiniciar;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
				VentanaRegistroClub dialog = new VentanaRegistroClub();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistroClub() {
		setTitle("Registro de club");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanelRC());
		setBounds(100, 100, 1057, 335);
	}
	
	private JPanel getPanelRC() {
		if(panelRC==null) {
			panelRC = new JPanel();
			panelRC.setLayout(new BorderLayout(0, 0));
			panelRC.add(getPanelRCDatos(), BorderLayout.CENTER);
			panelRC.add(getLblRegistroDeClubes(), BorderLayout.NORTH);
		}
		return panelRC;
	}
	
	private JPanel getPanelRCDatos() {
		if (panelRCDatos == null) {
			panelRCDatos = new JPanel();
			panelRCDatos.setLayout(new BorderLayout(0, 0));
			panelRCDatos.add(getPanelRCDatosBase(), BorderLayout.WEST);
			panelRCDatos.add(getPanelTablaInscritosClub(), BorderLayout.CENTER);
		}
		return panelRCDatos;
	}
	private JLabel getLblRegistroDeClubes() {
		if (lblRegistroDeClubes == null) {
			lblRegistroDeClubes = new JLabel("Registro de clubes");
			lblRegistroDeClubes.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeClubes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		}
		return lblRegistroDeClubes;
	}
	private JPanel getPanelRCDatosBase() {
		if (panelRCDatosBase == null) {
			panelRCDatosBase = new JPanel();
			panelRCDatosBase.setLayout(new GridLayout(2, 1, 0, 0));
			panelRCDatosBase.add(getPanelNombreClub());
			panelRCDatosBase.add(getPanel());
		}
		return panelRCDatosBase;
	}
	private JPanel getPanelNombreClub() {
		if (panelNombreClub == null) {
			panelNombreClub = new JPanel();
			panelNombreClub.setLayout(new GridLayout(0, 2, 0, 0));
			panelNombreClub.add(getLblNombreDelClub());
			panelNombreClub.add(getPanelEscrituraClub());
		}
		return panelNombreClub;
	}
	private JLabel getLblNombreDelClub() {
		if (lblNombreDelClub == null) {
			lblNombreDelClub = new JLabel("Nombre del club:");
			lblNombreDelClub.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombreDelClub.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombreDelClub.setBorder(null);
		}
		return lblNombreDelClub;
	}
	private JPanel getPanelEscrituraClub() {
		if (panelEscrituraClub == null) {
			panelEscrituraClub = new JPanel();
			panelEscrituraClub.setBorder(null);
			panelEscrituraClub.setLayout(null);
			panelEscrituraClub.add(getTextField());
		}
		return panelEscrituraClub;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(10, 61, 176, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JPanel getPanelTablaInscritosClub() {
		if (panelTablaInscritosClub == null) {
			panelTablaInscritosClub = new JPanel();
			panelTablaInscritosClub.setLayout(new BorderLayout(0, 0));
			panelTablaInscritosClub.add(getPanelDatosInscritos(), BorderLayout.NORTH);
			panelTablaInscritosClub.add(getTableClubInscritos(), BorderLayout.CENTER);
			panelTablaInscritosClub.add(getPanelRCBotones(), BorderLayout.SOUTH);
			panelTablaInscritosClub.setEnabled(false);
		}
		return panelTablaInscritosClub;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getLblCarrera());
			panel.add(getPanelEleccionCarrera());
		}
		return panel;
	}
	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Carrera en la que participar: ");
			lblCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblCarrera;
	}
	private JPanel getPanelEleccionCarrera() {
		if (panelEleccionCarrera == null) {
			panelEleccionCarrera = new JPanel();
			panelEleccionCarrera.setLayout(null);
			panelEleccionCarrera.add(getComboBox());
			panelEleccionCarrera.add(getBtnReiniciar());
			panelEleccionCarrera.add(getBtnConfirmar());
		}
		return panelEleccionCarrera;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(10, 62, 176, 20);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboBox.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "Error accediendo a la base de datos" + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboBox;
	}
	private JPanel getPanelDatosInscritos() {
		if (panelDatosInscritos == null) {
			panelDatosInscritos = new JPanel();
			panelDatosInscritos.setLayout(new GridLayout(2, 0, 0, 0));
			panelDatosInscritos.add(getLblListaDeAtletas());
			panelDatosInscritos.add(getPanelColumnasTabla());
		}
		return panelDatosInscritos;
	}
	private JLabel getLblListaDeAtletas() {
		if (lblListaDeAtletas == null) {
			lblListaDeAtletas = new JLabel("Lista de atletas inscritos");
			lblListaDeAtletas.setHorizontalAlignment(SwingConstants.CENTER);
			lblListaDeAtletas.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblListaDeAtletas;
	}
	private JPanel getPanelColumnasTabla() {
		if (panelColumnasTabla == null) {
			panelColumnasTabla = new JPanel();
			panelColumnasTabla.setLayout(new GridLayout(0, 3, 0, 0));
			panelColumnasTabla.add(getLblDni());
			panelColumnasTabla.add(getLblCategora());
			panelColumnasTabla.add(getLblNombre());
		}
		return panelColumnasTabla;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDni;
	}
	private JLabel getLblCategora() {
		if (lblCategora == null) {
			lblCategora = new JLabel("Categor\u00EDa");
			lblCategora.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategora.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		return lblCategora;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNombre;
	}
	private JTable getTableClubInscritos() {
		if (tableClubInscritos == null) {
			MyTableModel m = new MyTableModel();
			m.addColumn("DNI");
			m.addColumn("Categoria");
			m.addColumn("Nombre");
			tableClubInscritos = new JTable();
			tableClubInscritos.setModel(m);
			tableClubInscritos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableClubInscritos;
	}
	private JPanel getPanelRCBotones() {
		if (panelRCBotones == null) {
			panelRCBotones = new JPanel();
			panelRCBotones.setLayout(new BorderLayout(0, 0));
			panelRCBotones.add(getPanelRCEditarDatos());
		}
		return panelRCBotones;
	}
	private JPanel getPanelRCEditarDatos() {
		if (panelRCEditarDatos == null) {
			panelRCEditarDatos = new JPanel();
			panelRCEditarDatos.setLayout(new GridLayout(0, 5, 1, 0));
			panelRCEditarDatos.add(getBtnAnnadir());
			panelRCEditarDatos.add(getBtnEditar());
			panelRCEditarDatos.add(getBtnEliminar());
			panelRCEditarDatos.add(getBtnActualizar());
			panelRCEditarDatos.add(getBtnRegistrar());
			for(int i = 0; i< panelRCEditarDatos.getComponentCount(); i++) {
				panelRCEditarDatos.getComponent(i).setEnabled(false);
			}
		}
		return panelRCEditarDatos;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int seleccionado = tableClubInscritos.getSelectedRow();
					if(seleccionado>= 0) {
						VentanaEditarDatosClubAtleta v = new VentanaEditarDatosClubAtleta(textField.getText(), (String)comboBox.getSelectedItem(), MiembrosClub.getl().get(seleccionado).getDni(),
								MiembrosClub.getl().get(seleccionado).getNombre(), MiembrosClub.getl().get(seleccionado).getApellidos());
						v.setVisible(true);
						//v.setAlwaysOnTop(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un corredor para eliminarlo.");
					}
				}
			});
		}
		return btnEditar;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int seleccionado = tableClubInscritos.getSelectedRow();
					if(seleccionado>= 0) {
						MyTableModel m = (MyTableModel) tableClubInscritos.getModel();
						String dni = (String) m.getValueAt(seleccionado, 0);
						for(int i = 0; i<MiembrosClub.getl().size(); i++) {
							if(MiembrosClub.getl().get(i).getDni().equals(dni)) {
								MiembrosClub.getl().remove(i);
							}
						}
						m.removeRow(seleccionado);
					}
					else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un corredor para eliminarlo.");
					}
				}
			});
		}
		return btnEliminar;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(100, 115, 96, 23);
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textField.getText().isEmpty()) {
						comboBox.setEnabled(false);
						textField.setEnabled(false);
						btnConfirmar.setEnabled(false);
						btnReiniciar.setEnabled(true);
						for(int i = 0; i< panelRCEditarDatos.getComponentCount(); i++) {
							panelRCEditarDatos.getComponent(i).setEnabled(true);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "El club ha de tener un nombre.");
					}
				}
			});
		}
		return btnConfirmar;
	}

	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MyTableModel m = new MyTableModel();
					m.addColumn("DNI");
					m.addColumn("Categoria");
					m.addColumn("Nombre");
					
					for(int i = 0; i<MiembrosClub.getl().size(); i++) {
						if(MiembrosClub.getl().get(i).getCategoria()!=null) {
							String[] categoriaBuena = MiembrosClub.getl().get(i).getCategoria().split("-");
							m.addRow(new Object[] {MiembrosClub.getl().get(i).getDni(), categoriaBuena[0], MiembrosClub.getl().get(i).getNombre()}); //CAMBIAR AL AÑADIR CATEGORIAS
						}
						else {
							m.addRow(new Object[] {MiembrosClub.getl().get(i).getDni(), "---", MiembrosClub.getl().get(i).getNombre()}); //CAMBIAR AL AÑADIR CATEGORIAS
						}
					}
					tableClubInscritos.setModel(m);
				}
			});
		}
		return btnActualizar;
	}
	private JButton getBtnAnnadir() {
		if (btnAnnadir == null) {
			btnAnnadir = new JButton("A\u00F1adir");
			btnAnnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaDatosClubAtleta v = new VentanaDatosClubAtleta(textField.getText(), (String)comboBox.getSelectedItem());
					v.setVisible(true);
					//v.setAlwaysOnTop(true);
				}
			});
		}
		return btnAnnadir;
	}
	private JButton getBtnReiniciar() {
		if (btnReiniciar == null) {
			btnReiniciar = new JButton("Reiniciar");
			btnReiniciar.setEnabled(false);
			btnReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int x = MiembrosClub.getl().size();
					MyTableModel m = (MyTableModel) tableClubInscritos.getModel();
					for(int i = 0; i<x; i++) {
						MiembrosClub.getl().remove(i);
					}
					MyTableModel m2 = new MyTableModel();
					m.addColumn("DNI");
					m.addColumn("Categoria");
					m.addColumn("Nombre");
					tableClubInscritos.setModel(m2);
					comboBox.setEnabled(true);
					textField.setEnabled(true);
					btnConfirmar.setEnabled(true);
					btnReiniciar.setEnabled(false);
					for(int i = 0; i< panelRCEditarDatos.getComponentCount(); i++) {
						panelRCEditarDatos.getComponent(i).setEnabled(false);
					}
				}
			});
			btnReiniciar.setBounds(0, 115, 89, 23);
		}
		return btnReiniciar;
	}
	
	private JButton getBtnRegistrar(){
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(MiembrosClub.getl().size()!=0) {
						if(!textField.getText().isEmpty()) {
						Club club = new Club(textField.getText(), MiembrosClub.getl(), (String) comboBox.getSelectedItem());
						String nombreCarrera = (String) comboBox.getSelectedItem();
						ArrayList<Carrera> carreras = new ArrayList<Carrera>();
						try {
							carreras = DataBaseManager.getCarrerasEnteras();
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						Carrera carreraElegida = null;
						for(int i = 0; i<carreras.size(); i++) {
							if(carreras.get(i).getNombre().equals(nombreCarrera)) {
								carreraElegida = carreras.get(i);
							}
						}
						ArrayList<String> a = new ArrayList<String>();
						try {
							a = DataBaseManager.getClubs();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						
						boolean existe = false;
						for(int i = 0; i<a.size(); i++) {
							if(club.getNombre().equals(a.get(i))) {
								existe = true;
							}
						}
						
						if(!existe) {
							try {
								DataBaseManager.anadirCiertoClub(club, nombreCarrera);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							for(int i = 0; i<MiembrosClub.getl().size(); i++) {
								try {
									DataBaseManager.anadirCiertoAtleta(MiembrosClub.getl().get(i));
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							int numeroAtletas = tableClubInscritos.getRowCount();
	
							try {
								if(carreraElegida!=null) {
									if(numeroAtletas>0 && numeroAtletas<=50) {
										club.imprimirResguardo("5%",carreraElegida.getPrecio());
									}
									else if(numeroAtletas>50 && numeroAtletas<=100) {
										club.imprimirResguardo("10%",carreraElegida.getPrecio());
									}
									else {
										club.imprimirResguardo("20%",carreraElegida.getPrecio());
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							MiembrosClub.vaciarLista();
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Este club ya está inscrito para otra carrera.");
						}
					}
						else {
							JOptionPane.showMessageDialog(null, "Debe de introducir el nombre del club.");
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe de tener al menos 1 atleta inscrito en el club.");
				}
			}
			});	
		}
		return btnRegistrar;
	}
}
