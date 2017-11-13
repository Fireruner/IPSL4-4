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
	private JButton btnAnadir;
	private DefaultTableModel model;
	private JButton btnOrdenar;
	
	private String carrera;

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
			panelSup.setLayout(new GridLayout(2, 0, 0, 0));
			panelSup.add(getLblEditorDeCategoras());
			panelSup.add(getPanelColumnas());
		}
		return panelSup;
	}
	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setLayout(new BorderLayout(0, 0));
			panelTabla.add(getTable());
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
			panelColumnas.add(getLblEdadMaxima());
			panelColumnas.add(getLblEdadMinima());
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
			panelBotones.add(getBtnAnadir());
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
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("A\u00F1adir");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.addRow(new Object[] {"",0,0});
					table.setModel(model);
				}
			});
		}
		return btnAnadir;
	}
	
	public boolean comprobadorEdadCategorias() {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		for(int i = 0; i<model.getRowCount(); i++) {
			categorias.add(new Categoria(table.getValueAt(i, 0).toString() + "-" + carrera, table.getValueAt(i, 0).toString(), 
					Integer.parseInt(table.getValueAt(i, 1).toString()), Integer.parseInt(table.getValueAt(i, 2).toString()), carrera));
		}
		Collections.sort(categorias);
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
		CategoriasCarrera.setl(categorias);
		return solucionado;
	}
	
	public void ordenarFilas() {
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
					ordenarFilas();
				}
			});
		}
		return btnOrdenar;
	}
}
