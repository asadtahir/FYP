package gui;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.UIManager;

import project.Controlar;
import random_forest.PathSelection;
import random_forest.RandomForest;
import gui.GLOBAL;

public class MainPanel {
	private static JFrame mainobject;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainPanel window = new MainPanel();
					window.mainobject.setVisible(true);
					login log = new login(mainobject);  // set the first panel on the frame.
					window.mainobject.setContentPane(log);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				// gives color when uploading file show icon of files
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					//main frame = new main(mainobject); // open the preprocessing tab
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		/*
		Controlar C = new Controlar();
		C.count();*/
 }
		///////////////////////////////////////////////////////////////////////////////////////
	

	/**
	 * Create the application.
	 */
	public MainPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainobject = new JFrame();
		mainobject.setResizable(false); /////// we can not change the size of page after run the code.
	    mainobject.setTitle("I_CASE"); /////// tital
		mainobject.setBounds(GLOBAL.frameSize);
		mainobject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

}
