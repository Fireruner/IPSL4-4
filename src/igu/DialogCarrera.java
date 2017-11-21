package igu;		
 		
 import java.awt.BorderLayout;		
 import java.awt.FlowLayout;		
 		
 import javax.swing.JButton;		
 import javax.swing.JDialog;		
 import javax.swing.JPanel;		
 import javax.swing.border.EmptyBorder;		
 import java.awt.GridBagLayout;		
 import net.miginfocom.swing.MigLayout;		
 import src.Carrera;
import src.Categoria;
import src.CategoriasCarrera;
import src.DataBaseManager;		
 		
 import javax.swing.JLabel;		
 import javax.swing.JOptionPane;		
 import javax.swing.JTextField;		
 import javax.swing.JSpinner;		
 import javax.swing.JComboBox;		
 import javax.swing.SwingConstants;		
 import javax.swing.SpinnerNumberModel;		
 import javax.swing.ComboBoxModel;		
 import javax.swing.DefaultComboBoxModel;		
 import java.awt.event.ActionListener;		
 import java.sql.SQLException;		
 import java.time.LocalDate;		
 import java.util.Date;		
 import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;		
 		
 public class DialogCarrera extends JDialog {		
 		
 	private final JPanel contentPanel = new JPanel();		
 	private JTextField txtNombre;		
 	private JSpinner spPlazas;		
 	private JComboBox<Integer> cbDia;		
 	private JComboBox<Integer>  cbMes;		
 	private JComboBox<Integer>  cbAno;		
 	private JSpinner spDevolucion;		
 	JSpinner spPrecio;		
 	private JButton okButton;
 	
 	private boolean editadas = false;
 		
 	/**		
 	 * Launch the application.		
 	 */		
 	public static void main(String[] args) {		
 		try {		
 			DialogCarrera dialog = new DialogCarrera();		
 			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
 			dialog.setVisible(true);		
 		} catch (Exception e) {		
 			e.printStackTrace();		
 		}		
 	}		
 		
 	/**		
 	 * Create the dialog.		
 	 */		
 	public DialogCarrera() {		
 		setTitle("Registro nueva competicion");		
 		setBounds(100, 100, 450, 329);		
 		getContentPane().setLayout(new BorderLayout());		
 		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
 		getContentPane().add(contentPanel, BorderLayout.CENTER);		
 		contentPanel.setLayout(new MigLayout("", "[9.00][248.00,grow]", "[30.00][34.00][19.00][][28.00][][30.00][]"));		
 		{		
 			JLabel lblNombre = new JLabel("Nombre:");		
 			contentPanel.add(lblNombre, "flowx,cell 1 0");		
 		}		
 		{		
 			txtNombre = new JTextField();		
 			txtNombre.addFocusListener(new FocusAdapter() {
 				@Override
 				public void focusLost(FocusEvent arg0) {
 					if(!txtNombre.getText().equals(""))
 						okButton.setEnabled(true);
 					else {
 						okButton.setEnabled(false);
 					}
 				}
 			});
 			
 			contentPanel.add(txtNombre, "cell 1 0,growx");		
 			txtNombre.setColumns(10);		
 		}		
 		{		
 			JLabel lblPlazas = new JLabel("Plazas");		
 			contentPanel.add(lblPlazas, "flowx,cell 1 1,alignx leading");		
 		}		
 		{		
 			spPlazas = new JSpinner();		
 			spPlazas.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));		
 			contentPanel.add(spPlazas, "cell 1 1,growx");		
 		}		
 		{		
 			JLabel lblFechaDeCelebracin = new JLabel("Fecha de celebraci\u00F3n");		
 			contentPanel.add(lblFechaDeCelebracin, "cell 1 2,alignx center");		
 		}		
 		{		
 			JLabel lblDia = new JLabel("Dia");		
 			lblDia.setHorizontalAlignment(SwingConstants.CENTER);		
 			contentPanel.add(lblDia, "flowx,cell 1 3,growx");		
 		}		
 		{		
 			cbDia = new JComboBox<Integer>();		
 			for(int i = 1; i <= 31; i++)		
 				cbDia.addItem(i);		
 			contentPanel.add(cbDia, "flowx,cell 1 4,growx");		
 		}		
 		{		
 			cbMes = new JComboBox<Integer>();		
 			for(int i = 1; i<= 12; i++)		
 				cbMes.addItem(i);		
 			contentPanel.add(cbMes, "cell 1 4,growx");		
 		}		
 		{		
 			cbAno = new JComboBox<Integer>();		
 			for(int i = 2017; i < 2050; i++)		
 				cbAno.addItem(i);		
 			contentPanel.add(cbAno, "cell 1 4,growx");		
 		}		
 		{		
 			JLabel lblMes = new JLabel("Mes");		
 			lblMes.setHorizontalAlignment(SwingConstants.CENTER);		
 			contentPanel.add(lblMes, "cell 1 3,growx");		
 		}		
 		{		
 			JLabel lblAo = new JLabel("A\u00F1o");		
 			lblAo.setHorizontalAlignment(SwingConstants.CENTER);		
 			contentPanel.add(lblAo, "cell 1 3,growx");		
 		}		
 		{		
 			JLabel lblPrecioDeInscripcin = new JLabel("Precio de inscripci\u00F3n:");		
 			contentPanel.add(lblPrecioDeInscripcin, "flowx,cell 1 5");		
 		}		
 		{		
 			JLabel lblPorcentajeDeDevolucin = new JLabel("Porcentaje de devoluci\u00F3n:");		
 			contentPanel.add(lblPorcentajeDeDevolucin, "flowx,cell 1 6");		
 		}		
 		{		
 			spDevolucion = new JSpinner();		
 			spDevolucion.setModel(new SpinnerNumberModel(20, 0, 100, 1));		
 			contentPanel.add(spDevolucion, "cell 1 6,growx");		
 		}		
 		{		
 			JLabel label = new JLabel("%");		
 			contentPanel.add(label, "cell 1 6");		
 		}		
 		{		
 			spPrecio = new JSpinner();		
 			spPrecio.setModel(new SpinnerNumberModel(new Integer(60), new Integer(0), null, new Integer(1)));		
 			contentPanel.add(spPrecio, "cell 1 5,growx");		
 		}		
 		{		
 			JLabel label = new JLabel("\u20AC");		
 			contentPanel.add(label, "cell 1 5,alignx right");		
 		}		
 		{
 			JButton btnCategoras = new JButton("Categor\u00EDas");
 			btnCategoras.addActionListener(new ActionListener() {
 				public void actionPerformed(ActionEvent arg0) {
 					editadas = true;
 					EditorCategorias ec = new EditorCategorias(txtNombre.getText());
 					ec.setVisible(true);
 				}
 			});
 			contentPanel.add(btnCategoras, "cell 1 7");
 		}
 		{		
 			JPanel buttonPane = new JPanel();		
 			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));		
 			getContentPane().add(buttonPane, BorderLayout.SOUTH);		
 			{		
 				okButton = new JButton("OK");		
 				okButton.setEnabled(false);
 				okButton.addActionListener(new ActionListener() {		
 					public void actionPerformed(ActionEvent arg0) 		
 					{		
 								
 						try {		
 							if(!DataBaseManager.existeCarrera(txtNombre.getText()) && !txtNombre.getText().equals(""))		
 							{		
 								LocalDate actualDate = LocalDate.now();		
 								LocalDate selectedDate;		
 								try		
 								{		
 									selectedDate = LocalDate.of((int)cbAno.getSelectedItem(),(int) cbMes.getSelectedItem(),(int) cbDia.getSelectedItem());		
 								}		
 								catch(Exception e)		
 								{		
 									JOptionPane.showMessageDialog(null, "El formato de la fecha es incorrecto!");		
 									return;		
 								}		
 								if(selectedDate.isBefore(actualDate))		
 								{		
 									JOptionPane.showMessageDialog(null, "Fecha mal configurada.\nNo puede asignar una fecha en el pasado.");		
 									return;		
 								}		
 								else		
 								{		
 									Carrera carrera = new Carrera(txtNombre.getText(), (int)spPlazas.getValue(), selectedDate,"creada", (int)spPrecio.getValue(), (int)spDevolucion.getValue());		
 									DataBaseManager.anadirCarrera(carrera);	
 									if(!editadas) {
 										System.out.println("Nombre: " + txtNombre.getText());
		 								CategoriasCarrera.getlm().add(new Categoria("Infantil-M-" +txtNombre.getText(), "Infantil-M", 3, 18, "masculino", txtNombre.getText()));
		 								CategoriasCarrera.getlm().add(new Categoria("Junior-M-" +txtNombre.getText(), "Junior-M", 19, 23, "masculino", txtNombre.getText()));
		 								CategoriasCarrera.getlm().add(new Categoria("Senior-M-" +txtNombre.getText(), "Senior-M", 24, 35, "masculino", txtNombre.getText()));
		 								CategoriasCarrera.getlm().add(new Categoria("Master-M-" +txtNombre.getText(), "Master-M", 36, 125, "masculino", txtNombre.getText()));
		 								CategoriasCarrera.getlf().add(new Categoria("Infantil-F-" +txtNombre.getText(), "Infantil-F", 3, 18, "femenino", txtNombre.getText()));
		 								CategoriasCarrera.getlf().add(new Categoria("Junior-F-" +txtNombre.getText(), "Junior-F", 19, 23, "femenino", txtNombre.getText()));
		 								CategoriasCarrera.getlf().add(new Categoria("Senior-F-" +txtNombre.getText(), "Senior-F", 24, 35, "femenino", txtNombre.getText()));
		 								CategoriasCarrera.getlf().add(new Categoria("Master-F-" +txtNombre.getText(), "Master-F", 36, 125, "femenino", txtNombre.getText()));
		 								for(int i = 0; i<CategoriasCarrera.getlm().size(); i++) {
											System.out.println(CategoriasCarrera.getlm().get(i).getId());
		 									DataBaseManager.anadirCategoriaACarrera(CategoriasCarrera.getlm().get(i), carrera.getNombre());
										}
		 								for(int i = 0; i<CategoriasCarrera.getlf().size(); i++) {
		 									DataBaseManager.anadirCategoriaACarrera(CategoriasCarrera.getlf().get(i), carrera.getNombre());
									}
 									}
 									else {
 										for(int i = 0; i<CategoriasCarrera.getlm().size(); i++) {
 											DataBaseManager.anadirCategoriaACarrera(CategoriasCarrera.getlm().get(i), carrera.getNombre());
 										}
 										for(int i = 0; i<CategoriasCarrera.getlf().size(); i++) {
 											DataBaseManager.anadirCategoriaACarrera(CategoriasCarrera.getlf().get(i), carrera.getNombre());
 										}
 									}
 									JOptionPane.showMessageDialog(null, "Se ha registrado la carrera "+ carrera.getNombre() +" correctamente.");
 									dispose();
 								}		
 										
 							}		
 							else		
 							{		
 								JOptionPane.showMessageDialog(null, "Esa carrera ya está registrada o no le ha asignado nombre.");		
 							}
 						} catch (SQLException e) {		
 							// TODO Autogenerated catch block		
 							e.printStackTrace();		
 						}		
 								
 					}		
 				});		
 				okButton.setActionCommand("OK");		
 				buttonPane.add(okButton);		
 				getRootPane().setDefaultButton(okButton);		
 			}		
 			{		
 				JButton cancelButton = new JButton("Cancel");		
 				cancelButton.addActionListener(new ActionListener() {		
 					public void actionPerformed(ActionEvent arg0) {		
 						dispose();		
 					}		
 				});		
 				cancelButton.setActionCommand("Cancel");		
 				buttonPane.add(cancelButton);		
 			}		
 		}		
 	}
 	
 	public void setEditadas(boolean t) {
 		editadas = true;
 	}
 		
 }