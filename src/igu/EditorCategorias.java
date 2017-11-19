package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import src.Categoria;
import src.CategoriasCarrera;
import src.MyTableModel;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class EditorCategorias extends JFrame {

	private JPanel contentPane;
	private JPanel panelSup;
	private JPanel panelTabla;
	private JPanel panelInf;
	private JLabel lblEditorDeCategoras;
	private JPanel panelColumnas;
	private JLabel lblNombre;
	private JLabel lblEdadMaxima;
	private JLabel lblEdadMinima;
	private JTable table;
	private JPanel panelBotones;
	private JButton btnAceptar;
	private JButton btnAnadirM;
	private DefaultTableModel model;
	private DefaultTableModel model_1;
	private JButton btnOrdenar;
	
	private String carrera;
	private JPanel panelDifSexos;
	private JPanel panelDatosGen1;
	private JTable table_1;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblMasculino;
	private JLabel lblFemenino;
	private JButton btnAnadirF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorCategorias frame = new EditorCategorias("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditorCategorias(String nombreCarrera) {
		carrera = nombreCarrera;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelSup(), BorderLayout.NORTH);
		contentPane.add(getPanelTabla(), BorderLayout.CENTER);
		contentPane.add(getPanelInf(), BorderLayout.SOUTH);
	}

	private JPanel getPanelSup() {
		if (panelSup == null) {
			panelSup = new JPanel();
			panelSup.setLayout(new GridLayout(3, 0, 0, 0));
			panelSup.add(getLblEditorDeCategoras());
			panelSup.add(getPanelDifSexos());
			panelSup.add(getPanelDatosGen1());
		}
		return panelSup;
	}
	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setLayout(new GridLayout(0, 2, 5, 0));
			panelTabla.add(getTable());
			panelTabla.add(getTable_1());
		}
		return panelTabla;
	}
	private JPanel getPanelInf() {
		if (panelInf == null) {
			panelInf = new JPanel();
			panelInf.setLayout(new BorderLayout(0, 0));
			panelInf.add(getPanelBotones(), BorderLayout.EAST);
		}
		return panelInf;
	}
	private JLabel getLblEditorDeCategoras() {
		if (lblEditorDeCategoras == null) {
			lblEditorDeCategoras = new JLabel("Editor de categor\u00EDas");
			lblEditorDeCategoras.setHorizontalAlignment(SwingConstants.CENTER);
			lblEditorDeCategoras.setFont(new Font("Tahoma", Font.ITALIC, 16));
		}
		return lblEditorDeCategoras;
	}
	private JPanel getPanelColumnas() {
		if (panelColumnas == null) {
			panelColumnas = new JPanel();
			panelColumnas.setLayout(new GridLayout(0, 3, 0, 0));
			panelColumnas.add(getLblNombre());
			panelColumnas.add(getLblEdadMinima());
			panelColumnas.add(getLblEdadMaxima());
		}
		return panelColumnas;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblNombre;
	}
	private JLabel getLblEdadMaxima() {
		if (lblEdadMaxima == null) {
			lblEdadMaxima = new JLabel("Edad maxima");
			lblEdadMaxima.setHorizontalAlignment(SwingConstants.CENTER);
			lblEdadMaxima.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblEdadMaxima;
	}
	private JLabel getLblEdadMinima() {
		if (lblEdadMinima == null) {
			lblEdadMinima = new JLabel("Edad minima");
			lblEdadMinima.setHorizontalAlignment(SwingConstants.CENTER);
			lblEdadMinima.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblEdadMinima;
	}
	private JTable getTable() {
		if (table == null) {
			model = new DefaultTableModel();
			model.addColumn("nombre");
			model.addColumn("edadm");
			model.addColumn("edadM");
			model.addRow(new Object[] {"Infantil", 3, 18});
			model.addRow(new Object[] {"Junior", 19, 23});
			model.addRow(new Object[] {"Senior", 24, 35});
			model.addRow(new Object[] {"Master", 36, 125});
			table = new JTable(model);		
		}
		return table;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelBotones.add(getBtnOrdenar());
			panelBotones.add(getBtnAnadirM());
			panelBotones.add(getBtnAnadirF());
			panelBotones.add(getBtnAceptar());
		}
		return panelBotones;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(comprobadorEdadCategorias()) {
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Se esta produciendo intercalacion entre las edades de las categorias o existen edades sin una categoría asociada,"
								+ " por favor compruebe que todas las edades no se intercalan unas a otras o las diferencias de edad entre categorías no sea superior a 1.");
					}
				}
			});
		}
		return btnAceptar;
	}
	private JButton getBtnAnadirM() {
		if (btnAnadirM == null) {
			btnAnadirM = new JButton("A\u00F1adir Masculino");
			btnAnadirM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.addRow(new Object[] {"",0,0});
					table.setModel(model);
				}
			});
		}
		return btnAnadirM;
	}
	
	public boolean comprobadorEdadCategorias() {
		ArrayList<Categoria> categoriasM = new ArrayList<Categoria>();
		ArrayList<Categoria> categoriasF = new ArrayList<Categoria>();
		for(int i = 0; i<table.getRowCount(); i++) {
			categoriasM.add(new Categoria(table.getValueAt(i, 0).toString() + "-M-" + carrera, table.getValueAt(i, 0).toString()+ "-M", 
					Integer.parseInt(table.getValueAt(i, 1).toString()), Integer.parseInt(table.getValueAt(i, 2).toString()), "masculino", 
					carrera));
		}
		for(int i = 0; i<table_1.getRowCount(); i++) {
			categoriasF.add(new Categoria(table_1.getValueAt(i, 0).toString() + "-F-" + carrera, table_1.getValueAt(i, 0).toString()+ "-F", 
					Integer.parseInt(table_1.getValueAt(i, 1).toString()), Integer.parseInt(table_1.getValueAt(i, 2).toString()), "femenino",
					carrera));
		}
		Collections.sort(categoriasM);
		Collections.sort(categoriasF);
		if(comprobacionLista(categoriasM, "m") &&
				comprobacionLista(categoriasF, "f"))
			return true;
		else
			return false;
	}
	
	public boolean comprobacionLista(ArrayList<Categoria> categorias, String sexo) {
		boolean solucionado = true;
		for (int j = 0; j<categorias.size(); j++) {
			if(j==0) {
				if(categorias.get(j).getEdadM()>categorias.get(j+1).getEdadm() || (categorias.get(j+1).getEdadm() -
						categorias.get(j).getEdadM()) > 1) {
					solucionado = false;
				}
			}
			else if(j==categorias.size()-1) {
				if(categorias.get(j).getEdadm()<categorias.get(j-1).getEdadM() || (categorias.get(j).getEdadm() -
						categorias.get(j-1).getEdadM()) > 1) {
					solucionado = false;
				}
			}
			else {
				if(categorias.get(j).getEdadM()>categorias.get(j+1).getEdadm() ||
						categorias.get(j).getEdadm()<categorias.get(j-1).getEdadM() || (categorias.get(j).getEdadm() -
								categorias.get(j-1).getEdadM()) > 1 || (categorias.get(j+1).getEdadm() -
										categorias.get(j).getEdadM()) > 1) {
					solucionado = false;
				}
			}
		}
		if(sexo.equals("m"))
			CategoriasCarrera.setlm(categorias);
		else {
			CategoriasCarrera.setlf(categorias);
		}
		return solucionado;
	}
	
	public void ordenarFilas(JTable table) {
		for(int i = 0; i<table.getRowCount()-1; i++) {
			 for(int j=0;j<table.getRowCount()-i-1;j++) {
				 if(Integer.parseInt(table.getValueAt(i+1, 1).toString())<Integer.parseInt(table.getValueAt(i, 1).toString()))
				 {
					 Object[] temp = new Object[] {table.getValueAt(i+1, 0),
							 table.getValueAt(i+1, 1), table.getValueAt(i+1, 2)};
					 table.setValueAt(table.getValueAt(i, 0), i+1, 0);
					 table.setValueAt(table.getValueAt(i, 1), i+1, 1);
					 table.setValueAt(table.getValueAt(i, 2), i+1, 2);
					 table.setValueAt(temp[0], i, 0);
					 table.setValueAt(temp[1], i, 1);
					 table.setValueAt(temp[2], i, 2);
				 }
			 }
		}
	}
	
	
	private JButton getBtnOrdenar() {
		if (btnOrdenar == null) {
			btnOrdenar = new JButton("Ordenar");
			btnOrdenar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ordenarFilas(table);
					ordenarFilas(table_1);
				}
			});
		}
		return btnOrdenar;
	}
	private JPanel getPanelDifSexos() {
		if (panelDifSexos == null) {
			panelDifSexos = new JPanel();
			panelDifSexos.setLayout(new GridLayout(0, 2, 5, 0));
			panelDifSexos.add(getLblMasculino());
			panelDifSexos.add(getLblFemenino());
		}
		return panelDifSexos;
	}
	private JPanel getPanelDatosGen1() {
		if (panelDatosGen1 == null) {
			panelDatosGen1 = new JPanel();
			panelDatosGen1.setLayout(new GridLayout(0, 2, 5, 0));
			panelDatosGen1.add(getPanelColumnas());
			panelDatosGen1.add(getPanel());
		}
		return panelDatosGen1;
	}
	private JTable getTable_1() {
		if (table_1 == null) {
			model_1 = new DefaultTableModel();
			model_1.addColumn("nombre");
			model_1.addColumn("edadm");
			model_1.addColumn("edadM");
			model_1.addRow(new Object[] {"Infantil", 3, 18});
			model_1.addRow(new Object[] {"Junior", 19, 23});
			model_1.addRow(new Object[] {"Senior", 24, 35});
			model_1.addRow(new Object[] {"Master", 36, 125});
			table_1 = new JTable(model_1);		
		}
		return table_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getLabel_2());
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Nombre");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Edad minima");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Edad maxima");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return label_2;
	}
	private JLabel getLblMasculino() {
		if (lblMasculino == null) {
			lblMasculino = new JLabel("Masculino");
			lblMasculino.setHorizontalAlignment(SwingConstants.CENTER);
			lblMasculino.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lblMasculino;
	}
	private JLabel getLblFemenino() {
		if (lblFemenino == null) {
			lblFemenino = new JLabel("Femenino");
			lblFemenino.setHorizontalAlignment(SwingConstants.CENTER);
			lblFemenino.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lblFemenino;
	}
	private JButton getBtnAnadirF() {
		if (btnAnadirF == null) {
			btnAnadirF = new JButton("A\u00F1adir Femenino");
			btnAnadirF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					model_1.addRow(new Object[] {"",0,0});
					table_1.setModel(model_1);
				}
			});
		}
		return btnAnadirF;
	}
}
