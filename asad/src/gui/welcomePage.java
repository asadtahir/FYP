package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import random_forest.PathSelection;
import random_forest.RandomForest;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import project.Controlar;

public class welcomePage extends JPanel {
	private static JTextField textField_Gender;
	private static JTextField textField_ProgrammingType;
	private static JTextField textField_CGPA;
	private static JTextField textField_CourseName;
	private JTextField textField__Assignment;
	private JTextField textField_Quiz;
	private JTextField textField_S1;
	private JTextField textField_S2;
	private JTextField textField_Finals;
	private JTextField textField_Grade;
	
	
	public static void saveforassignment() throws FileNotFoundException, UnsupportedEncodingException
	{
		String CourseNamestr = textField_CourseName.getText();
		String Genderstr = textField_Gender.getText();
		String ProgrammingTypestr = textField_ProgrammingType.getText();
		String CGPAstr = textField_CGPA.getText();
		String forAssignmentstr = CourseNamestr + "," + Genderstr+","+ProgrammingTypestr+","+CGPAstr+",";
		System.out.println(forAssignmentstr);
		
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
		writer.println(forAssignmentstr);
		writer.close();
	}
	
	public welcomePage(JFrame mainobject) {
		setBackground(Color.GRAY);
		setForeground(new Color(64, 64, 64));
		setLayout(null);
		
		JButton btnPreprocessing = new JButton("Preprocessing");
		btnPreprocessing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view_preprocessing o=new view_preprocessing(mainobject);	
				mainobject.setContentPane(o);
				mainobject.repaint();
				mainobject.revalidate();
			}
		});
		btnPreprocessing.setBounds(347, 68, 100, 23);
		add(btnPreprocessing);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the I_CASE");
		lblWelcomeToThe.setBackground(new Color(240, 240, 240));
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblWelcomeToThe.setForeground(Color.BLACK);
		lblWelcomeToThe.setBounds(213, 0, 495, 50);
		add(lblWelcomeToThe);
		
		/////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btn_prediction_Grade = new JButton("Predict");
		btn_prediction_Grade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controlar Cobj = new Controlar();     /////// create object of controlar class to access the files to predict assignment marks
				Cobj.totalprediction();
			}
		});
		btn_prediction_Grade.setBounds(322, 380, 100, 23);
		add(btn_prediction_Grade);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(20, 184, 46, 14);
		add(lblGender);
		
		JLabel lblProgrammingType = new JLabel("Programming type");
		lblProgrammingType.setBounds(20, 209, 118, 14);
		add(lblProgrammingType);
		
		JLabel lblCgpa = new JLabel("CGPA");
		lblCgpa.setBounds(20, 234, 46, 14);
		add(lblCgpa);
		
		JLabel lblAssigment = new JLabel("Assigment");
		lblAssigment.setBounds(20, 259, 89, 14);
		add(lblAssigment);
		
		JLabel lblQuiz = new JLabel("Quiz");
		lblQuiz.setBounds(20, 284, 46, 14);
		add(lblQuiz);
		
		JLabel lblS = new JLabel("Mid1");
		lblS.setBounds(20, 309, 46, 14);
		add(lblS);
		
		JLabel lblS_1 = new JLabel("Mid2");
		lblS_1.setBounds(20, 334, 46, 14);
		add(lblS_1);
		
		JLabel lblFinals = new JLabel("Finals");
		lblFinals.setBounds(20, 359, 46, 14);
		add(lblFinals);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBounds(20, 384, 46, 14);
		add(lblGrade);
		
		textField_Gender = new JTextField();
		textField_Gender.setBounds(130, 181, 182, 20);
		add(textField_Gender);
		textField_Gender.setColumns(10);
		
		textField_ProgrammingType = new JTextField();
		textField_ProgrammingType.setBounds(130, 206, 182, 20);
		add(textField_ProgrammingType);
		textField_ProgrammingType.setColumns(10);
		
		textField_CGPA = new JTextField();
		textField_CGPA.setBounds(130, 231, 182, 20);
		add(textField_CGPA);
		textField_CGPA.setColumns(10);
		
		textField__Assignment = new JTextField();
		textField__Assignment.setBounds(131, 256, 182, 20);
		add(textField__Assignment);
		textField__Assignment.setColumns(10);
		
		textField_Quiz = new JTextField();
		textField_Quiz.setBounds(130, 281, 182, 20);
		add(textField_Quiz);
		textField_Quiz.setColumns(10);
		
		textField_S1 = new JTextField();
		textField_S1.setBounds(130, 306, 182, 20);
		add(textField_S1);
		textField_S1.setColumns(10);
		
		textField_S2 = new JTextField();
		textField_S2.setBounds(130, 331, 182, 20);
		add(textField_S2);
		textField_S2.setColumns(10);
		
		textField_Finals = new JTextField();
		textField_Finals.setBounds(130, 356, 182, 20);
		add(textField_Finals);
		textField_Finals.setColumns(10);
		
		textField_Grade = new JTextField();
		textField_Grade.setBounds(130, 381, 182, 20);
		add(textField_Grade);
		textField_Grade.setColumns(10);
		
		JButton btnPredict_Assignment = new JButton("Predict");
		btnPredict_Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					welcomePage.saveforassignment();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Controlar Cobj = new Controlar();     /////// create object of controlar class to access the files to predict assignment marks
				Cobj.assignmentprediction();
			}
		});
		btnPredict_Assignment.setBounds(322, 255, 100, 23);
		add(btnPredict_Assignment);
		
		JButton btnPredict_Quiz = new JButton("Predict");
		btnPredict_Quiz.setBounds(322, 280, 100, 23);
		add(btnPredict_Quiz);
		
		JButton btnPredict_S1 = new JButton("Predict");
		btnPredict_S1.setBounds(322, 305, 100, 23);
		add(btnPredict_S1);
		
		JButton btnPredict_S2 = new JButton("Predict");
		btnPredict_S2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPredict_S2.setBounds(322, 330, 100, 23);
		add(btnPredict_S2);
		
		JButton btnPredict_Finals = new JButton("Predict");
		btnPredict_Finals.setBounds(322, 355, 100, 23);
		add(btnPredict_Finals);
		
		JLabel lblYouNeedTo = new JLabel("You need to preprecess the data first if required");
		lblYouNeedTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYouNeedTo.setBounds(32, 71, 305, 14);
		add(lblYouNeedTo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\FAST\\Desktop\\Project_new\\images\\png.png"));
		label.setBounds(434, 90, 317, 270);
		add(label);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(20, 159, 89, 14);
		add(lblCourseName);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(130, 156, 182, 20);
		add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		}

	
}
