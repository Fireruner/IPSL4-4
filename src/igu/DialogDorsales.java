package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.DataBaseManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DialogDorsales extends JDialog {
	
	private final String SQLError = "Ha ocurrido un error ejecutando una consulta a la base de datos! REF:";

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboDorsales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDorsales dialog = new DialogDorsales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDorsales() {
		setBounds(100, 100, 450, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel lblIntroduzcaElNombre = new JLabel("Introduzca el nombre de la carrera en la que desea asignar dorsales:");
			lblIntroduzcaElNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblIntroduzcaElNombre.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblIntroduzcaElNombre);
		}
		{
			comboDorsales = getComboCarreras();
			contentPanel.add(comboDorsales);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						try 
						{
							String carrera = (String)comboDorsales.getSelectedItem();
							if(DataBaseManager.existeCarrera(carrera))
							{
								ArrayList<String[]> atletas =DataBaseManager.listarAtletas(carrera);
								int contador = DataBaseManager.getSiguienteDorsalDisponible(carrera);
								int noPagado = 0;
								String atletasSinPago = "";
								for(String[] a: atletas)
								{
									if(a[4].equals("pagado"))
									{
										if(a[5] == null)
										{
											a[5] = ""+ contador;
											contador++;
										}
									}
									else
									{
										atletasSinPago += a[0] +"\n";
										noPagado++;
									}
								}
								DataBaseManager.actualizarDorsales(atletas, carrera);
								if(atletasSinPago.equals(""))
									JOptionPane.showMessageDialog(null, "Dorsales generados correctamente!");
								else
									JOptionPane.showMessageDialog(null, "No se han podido asignar dorsales a los siguientes "+noPagado+" corredores\npor no haber pagado:\n"+atletasSinPago);
								dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No se pudo encontrar la carrera "+carrera+" en la base de datos!");
							}
						}
						catch(SQLException ex)
						{
							JOptionPane.showMessageDialog(null, "Error recuperando carreras desde la base de datos.\n Compruebe su conexión con la base de datos.");
							ex.printStackTrace();
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
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private JComboBox getComboCarreras() {
		if (comboDorsales == null) {
			comboDorsales = new JComboBox();
			comboDorsales.setBounds(481, 61, 195, 20);
			try {
				ArrayList<String> carreras = DataBaseManager.getCarreras();
				for (String carrera : carreras)
				{
					comboDorsales.addItem(carrera);
				}
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, SQLError + " recuperacionCarreras");
				e.printStackTrace();
			}
		}
		return comboDorsales;
	}

}
