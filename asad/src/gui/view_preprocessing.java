package gui;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import project.FileChooserDialog;
import project.Messages;
import project.PreProcessing;
import java.awt.Rectangle;
import java.awt.Color;

public class view_preprocessing extends JPanel {
	
	private FileChooserDialog fcd = new FileChooserDialog();
	private PreProcessing pc;
	private JPanel contentPane;
	private JTextPane textPane;
	private JComboBox comboBox;
	private JTable table;
	private JTable table1;
	private int row_1;
	private int col_1;
	private String address;

	public view_preprocessing(JFrame mainobject) {
		setLayout(null);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(new Rectangle(10, 11, 699, 443));
		add(panel3);
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(null, "Attribute",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 171, 308, 266);
		panel3.add(panel_8);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setEnabled(false);
		scrollPane1.setBounds(10, 21, 290, 204);
		panel_8.add(scrollPane1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new TitledBorder(null, "Selected Attribute",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(328, 99, 359, 338);
		panel3.add(panel_9);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setEnabled(false);
		scrollPane2.setBounds(10, 96, 340, 199);
		panel_9.add(scrollPane2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 41, 677, 47);
		panel3.add(panel_6);
		
		///////////////////////////////////////////////////////////////////////
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(null, "Current Relation",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(10, 99, 308, 62);
		panel3.add(panel_7);
		JLabel lblRelation = new JLabel("Relation:");
		lblRelation.setBounds(6, 16, 62, 14);
		panel_7.add(lblRelation);
		JLabel lblNone = new JLabel("none");
		lblNone.setBounds(78, 16, 259, 14);
		panel_7.add(lblNone);
		JLabel lblAttributes = new JLabel("Attributes:");
		lblAttributes.setBounds(6, 41, 62, 14);
		panel_7.add(lblAttributes);
		JLabel lblNewLabel = new JLabel("none");
		lblNewLabel.setBounds(78, 41, 95, 14);
		panel_7.add(lblNewLabel);
		JLabel lblInstances = new JLabel("Instances:");
		lblInstances.setBounds(183, 41, 59, 14);
		panel_7.add(lblInstances);
		JLabel label_2 = new JLabel("none");
		label_2.setBounds(256, 41, 81, 14);
		panel_7.add(label_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Normalize"}));
		comboBox_1.setBounds(80, 17, 481, 23);
		panel_6.add(comboBox_1);
		
        //////////////////////////////////////////////////////////////////////////////////
        
		JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 24, 46, 14);
        panel_9.add(lblName);
        JLabel lblNewLabel_1 = new JLabel("None");
        lblNewLabel_1.setBounds(66, 24, 278, 14);
        panel_9.add(lblNewLabel_1);
        JLabel lblType = new JLabel("Type:");
        lblType.setBounds(10, 46, 46, 14);
        panel_9.add(lblType);
        JLabel lblNewLabel_2 = new JLabel("None");
        lblNewLabel_2.setBounds(66, 46, 110, 14);
        panel_9.add(lblNewLabel_2);
        JLabel lblMissing = new JLabel("Missing:");
        lblMissing.setBounds(193, 71, 63, 14);
        panel_9.add(lblMissing);
        JLabel lblNewLabel_3 = new JLabel("None");
        lblNewLabel_3.setBounds(259, 71, 85, 14);
        panel_9.add(lblNewLabel_3);
        JLabel lblDistint = new JLabel("Distinct:");
        lblDistint.setBounds(10, 71, 46, 14);
        panel_9.add(lblDistint);
        JLabel lblNewLabel_4 = new JLabel("None");
        lblNewLabel_4.setBounds(66, 71, 110, 14);
        panel_9.add(lblNewLabel_4);
		JLabel lblChose = new JLabel("Choose ");
		lblChose.setHorizontalAlignment(SwingConstants.CENTER);
		lblChose.setFont(new Font("Calibri", Font.BOLD, 14));
		lblChose.setBounds(6, 18, 89, 20);
		panel_6.add(lblChose);

        //////////////////////////////////////////////////////////////////////////////
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(158, 306, 166, 23);
		panel_9.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				welcomePage o=new welcomePage(mainobject);	
				mainobject.setContentPane(o);
				mainobject.repaint();
				mainobject.revalidate();
			}
		});
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fcd.fileChooser();
				if (!fcd.getPath().endsWith(".arff") && !fcd.getPath().endsWith(".csv")) {
					try {
						fcd.fileChooser();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					pc = new PreProcessing(fcd.getPath());
					lblNone.setText(pc.getCurrentRelation());
					lblNewLabel.setText(Integer.toString(pc.getNumOfAtt()));
					label_2.setText(Integer.toString(pc.getNumOfinstances()));
					List temp = pc.getAttributeNames();
					System.out.println("Tem Size     " + temp.size());
			
					table = new JTable();
					scrollPane1.setViewportView(table);

					DefaultTableModel model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					table.setModel(model);

					ListSelectionModel cellSelectionModel = table.getSelectionModel();
					cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
								private Object selectedData;

								public void valueChanged(ListSelectionEvent e) {
									selectedData = null;
									int[] selectedRow = table.getSelectedRows();
									int[] selectedColumns = table.getSelectedColumns();
									row_1 = table.getSelectedRow();
									int col_1 = table.getSelectedColumn();
									int tempRow_1 = table.getSelectedRow();
									for (int i = 0; i < selectedRow.length; i++) {
										for (int j = 0; j < selectedColumns.length; j++) {
											selectedData = table.getValueAt(selectedRow[i],selectedColumns[j]);
										}
									}
									lblNewLabel_1.setText(temp.get(row_1).toString());
									lblNewLabel_2.setText(pc.getType(row_1));
									lblNewLabel_3.setText(Integer.toString(pc.getMissing(row_1)));
									lblNewLabel_4.setText(Integer.toString(pc.getDistict(row_1)));
									
									table1 = new JTable();
									scrollPane2.setViewportView(table1);

									DefaultTableModel model1 = new DefaultTableModel() {
										@Override
										public boolean isCellEditable(int row,
												int column) {
											return false;
										}
									};
									table1.setModel(model1);

									if (pc.getType(row_1).matches("Numeric")) {

										model1.addColumn("Statistics");
										model1.addColumn("value");

										model1.addRow(new Object[0]);
										model1.setValueAt("Minimum", 0, 0);
										model1.setValueAt(pc.getMinimum(row_1),0, 1);
										model1.addRow(new Object[1]);
										model1.setValueAt("Maximum", 1, 0);
										model1.setValueAt(pc.getMaximum(row_1),1, 1);
										model1.addRow(new Object[2]);
										model1.setValueAt("Mean", 2, 0);
										model1.setValueAt(pc.getMeanOrMode(row_1), 2, 1);
										model1.addRow(new Object[3]);
										model1.setValueAt("StdDev", 3, 0);
										model1.setValueAt(pc.getStdDev(row_1),3, 1);

									} else if(pc.getType(row_1).matches("Nominal")) {
										table1.setModel(model1);

										model1.addColumn("No");
										model1.addColumn("Label");
										model1.addColumn("Count");// 

										List labels = pc.getLabels(row_1);
										List labelsCount = pc.getLaabelsCount(row_1);
										int row = 0;
										while (row < labels.size()) {
											model1.addRow(new Object[0]);
											model1.setValueAt(row, row, 0);
											model1.setValueAt(labels.get(row).toString(), row, 1);
											model1.setValueAt(labelsCount.get(row).toString(), row, 2);
											row++;
										}

									}
									else if(pc.getType(row_1).matches("String"))
									{
										table1.setModel(model1);
									}
								}

							});

					model.addColumn("Number");
					model.addColumn("Name");

					int row = 0;

					while (row < temp.size()) {
						model.addRow(new Object[0]);
						model.setValueAt(row, row, 0);
						model.setValueAt(temp.get(row).toString(), row, 1);
						row++;
					}

				}
			}
		});
		btnOpenFile.setBounds(21, 11, 201, 23);
		panel3.add(btnOpenFile);
		
		/////////////////////////////////////////remove ///////////////////////////////////////
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pc.deleteAttribute(row_1);
				List temp = pc.getAttributeNames();
				lblNewLabel.setText(Integer.toString(pc.getNumOfAtt()));
				// Table
				table = new JTable();
				scrollPane1.setViewportView(table);

				// Model for Table
				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				table.setModel(model);

				ListSelectionModel cellSelectionModel = table
						.getSelectionModel();
				cellSelectionModel
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				cellSelectionModel
						.addListSelectionListener(new ListSelectionListener() {
							private Object selectedData;

							public void valueChanged(ListSelectionEvent e) {
								selectedData = null;
								int[] selectedRow = table.getSelectedRows();
								int[] selectedColumns = table
										.getSelectedColumns();
								row_1 = table.getSelectedRow();
								int col_1 = table.getSelectedColumn();
								int tempRow_1 = table.getSelectedRow();
								for (int i = 0; i < selectedRow.length; i++) {
									for (int j = 0; j < selectedColumns.length; j++) {
										selectedData = table.getValueAt(
												selectedRow[i],
												selectedColumns[j]);
									}
								}
								lblNewLabel_1.setText(temp.get(row_1)
										.toString());
								lblNewLabel_2.setText(pc.getType(row_1));
								lblNewLabel_4.setText(Integer.toString(pc
										.getDistict(row_1)));
								lblNewLabel_3.setText(Integer.toString(pc
										.getMissing(row_1)));

								table1 = new JTable();
								scrollPane2.setViewportView(table1);

								// Model for Table
								DefaultTableModel model1 = new DefaultTableModel() {
									@Override
									public boolean isCellEditable(int row,
											int column) {
										return false;
									}
								};
								table1.setModel(model1);

								if (pc.getType(row_1).matches("Numeric")) {

									model1.addColumn("Statistics");
									model1.addColumn("value");

									model1.addRow(new Object[0]);
									model1.setValueAt("Minimum", 0, 0);
									model1.setValueAt(pc.getMinimum(row_1), 0,
											1);
									model1.addRow(new Object[1]);
									model1.setValueAt("Maximum", 1, 0);
									model1.setValueAt(pc.getMaximum(row_1), 1,
											1);
									model1.addRow(new Object[2]);
									model1.setValueAt("Mean", 2, 0);
									model1.setValueAt(pc.getMeanOrMode(row_1),
											2, 1);
									model1.addRow(new Object[3]);
									model1.setValueAt("StdDev", 3, 0);
									model1.setValueAt(pc.getStdDev(row_1), 3, 1);

								} else if(pc.getType(row_1).matches("Nominal")) {
									table1.setModel(model1);

									model1.addColumn("No");
									model1.addColumn("Label");
									model1.addColumn("Count");

									List labels = pc.getLabels(row_1);
									List labelsCount = pc
											.getLaabelsCount(row_1);
									int row = 0;
									while (row < labels.size()) {
										model1.addRow(new Object[0]);
										model1.setValueAt(row, row, 0);
										model1.setValueAt(labels.get(row)
												.toString(), row, 1);
										model1.setValueAt(
												labelsCount.get(row)
														.toString(), row, 2);
										row++;
									}

								}
								else if(pc.getType(row_1).matches("String"))
								{
									table1.setModel(model1);
								}
							}

						});

				model.addColumn("Number");
				model.addColumn("Name");

				int row = 0;

				while (row < temp.size()) {
					model.addRow(new Object[0]);
					model.setValueAt(row, row, 0);
					model.setValueAt(temp.get(row).toString(), row, 1);
					row++;
				}
				
			}
		});
		

		btnNewButton_1.setBounds(12, 236, 288, 23);
		panel_8.add(btnNewButton_1);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			private List temp1;
			public void actionPerformed(ActionEvent arg0) {
				
				String str = (String) comboBox_1.getSelectedItem();
				pc.setMethod(str);   //combobox
				pc.preprocessing();
				
				//////////////////////////////// initializes the labels///////////////////////
				lblNone.setText(pc.getCurrentRelation());
				lblNewLabel.setText(Integer.toString(pc.getNumOfAtt()));
				label_2.setText(Integer.toString(pc.getNumOfinstances()));
				temp1 = null;
				temp1 = pc.getAttributeNames();
				System.out.println("Tem Size     " + temp1.size());
				
				table = new JTable();
				scrollPane1.setViewportView(table); 
		
				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setModel(model);

				ListSelectionModel cellSelectionModel = table.getSelectionModel();
				cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
							private Object selectedData;
							public void valueChanged(ListSelectionEvent e) {
								selectedData = null;

								int[] selectedRow = table.getSelectedRows();
								int[] selectedColumns = table.getSelectedColumns();
								row_1 = table.getSelectedRow();
								col_1 = table.getSelectedColumn();
								int tempRow_1 = table.getSelectedRow();
								for (int i = 0; i < selectedRow.length; i++) {
									for (int j = 0; j < selectedColumns.length; j++) {
										selectedData = table.getValueAt(selectedRow[i],selectedColumns[j]);
									}
								}

								lblNewLabel_1.setText(temp1.get(row_1).toString());
								lblNewLabel_2.setText(pc.getType(row_1));
								lblNewLabel_3.setText(Integer.toString(pc.getMissing(row_1)));
								lblNewLabel_4.setText(Integer.toString(pc.getDistict(row_1)));
								
								table1 = new JTable();
								scrollPane2.setViewportView(table1);

								// Model for Table
								DefaultTableModel model1 = new DefaultTableModel() {
									@Override
									public boolean isCellEditable(int row,int column) {
										return false;
									}
								};
								table1.setModel(model1);

								if (pc.getType(row_1).matches("Numeric")) {

									model1.addColumn("Statistics");
									model1.addColumn("value");

									model1.addRow(new Object[0]);
									model1.setValueAt("Minimum", 0, 0);
									model1.setValueAt(pc.getMinimum(row_1), 0,1);
									model1.addRow(new Object[1]);
									model1.setValueAt("Maximum", 1, 0);
									model1.setValueAt(pc.getMaximum(row_1), 1,1);
									model1.addRow(new Object[2]);
									model1.setValueAt("Mean", 2, 0);
									model1.setValueAt(pc.getMeanOrMode(row_1),2, 1);
									model1.addRow(new Object[3]);
									model1.setValueAt("StdDev", 3, 0);
									model1.setValueAt(pc.getStdDev(row_1), 3, 1);

								}else if(pc.getType(row_1).matches("Nominal")) {
									table1.setModel(model1);

									model1.addColumn("No");
									model1.addColumn("Label");
									model1.addColumn("Count");

									List labels = pc.getLabels(row_1);
									List labelsCount = pc.getLaabelsCount(row_1);
									int row = 0;
									while (row < labels.size()) {
										model1.addRow(new Object[0]);
										model1.setValueAt(row, row, 0);
										model1.setValueAt(labels.get(row).toString(), row, 1);
										model1.setValueAt(labelsCount.get(row).toString(), row, 2);
										row++;
									}

								}
								else if(pc.getType(row_1).matches("String"))
								{
									table1.setModel(model1);
								}

							}

						});

				model.addColumn("Number");
				model.addColumn("Name");

				int row = 0;
				while (row < temp1.size()) {
					model.addRow(new Object[0]);
					model.setValueAt(row, row, 0);
					model.setValueAt(temp1.get(row).toString(), row, 1);
					row++;
				}

			}
			
		});
		btnApply.setBounds(571, 17, 96, 23);
		panel_6.add(btnApply);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btnNewButton = new JButton("Save The Working Relation To File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter writer;
				try {
					fcd.fileChooser2();
					writer = new BufferedWriter(new FileWriter(fcd.getPath2()));
					writer.write(pc.getData().toString());
					writer.flush();
					writer.close();
					Messages m=new Messages();
					m.success("Successfully wrote.", "success");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(488, 11, 201, 23);
		panel3.add(btnNewButton);
		//////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////////
		JButton btnViewData = new JButton("View Data");
		btnViewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewDataSet vds =new ViewDataSet(pc.getData());
				vds.setVisible(true);
			}
		});
		btnViewData.setBounds(257, 11, 201, 23);
		panel3.add(btnViewData);

        //////////////////////////////////////////////////////////////////////////////////////
		
	}

}
