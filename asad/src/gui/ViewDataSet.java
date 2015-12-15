package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import weka.core.Instance;
import weka.core.Instances;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

public class ViewDataSet extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public ViewDataSet(Instances data) {
		
		setTitle("View Data");
		ImageIcon img = new ImageIcon("C:\\Users\\FAST\\Desktop\\Project_new\\images\\logo" + ".PNG");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Data Set",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 730, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 21, 710, 460);
		panel_1.add(scrollPane);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBounds(400, 11, 520, 390);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		
		
		for (int i = 0; i < data.numAttributes(); i++) 
		{
			model.addColumn(data.attribute(i).name() + " " + data.attribute(i).type());
		}
		try {
			for (int i = 0; i < data.numInstances(); i++) 
			{
				model.addRow(new Object[0]);
				Instance current=data.instance(i);
				for (int j = 0; j < current.numAttributes(); j++) 
				{
					model.setValueAt(current.value(j), i, j);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();			
			}
		});
		btnClose.setBounds(651, 514, 89, 23);
		contentPane.add(btnClose);
	}

}
