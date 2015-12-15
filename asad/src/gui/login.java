package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import project.Messages;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class login extends JPanel {
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Create the panel.
	 * @param mainobject 
	 */
	public login(final JFrame mainobject) {
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(403, 376, 83, 14);
		add(lblUserName);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(496, 373, 164, 20);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(403, 407, 60, 14);
		add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(496, 404, 164, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

		
		JButton btnLogin = new JButton("login");
		btnLogin.addMouseListener(new MouseAdapter() {
			
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					Messages m = new Messages();
					m.success("Successfully Login.", "success");
				
					welcomePage o=new welcomePage(mainobject);	
					mainobject.setContentPane(o);
					mainobject.repaint();
					mainobject.revalidate();
					
					
				}
			}
		});
		btnLogin.setBounds(573, 435, 89, 23);
		add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\FAST\\Desktop\\Project_new\\images\\login.png"));
		label.setBounds(0, 453, 12, 73);
		add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\FAST\\Desktop\\Project_new\\images\\fast.jpg"));
		label_1.setBounds(-174, -85, 884, 510);
		add(label_1);

	}
}
