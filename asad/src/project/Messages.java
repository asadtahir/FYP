package project;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Messages {
	private String address;
	public Messages()
	{
		this.address="C:\\Users\\FAST\\Desktop\\Project_new\\images\\ok";
	}
	private ImageIcon icon;

	public void success(String msg,String title) {
		icon = new ImageIcon(address+"ok.png");
		JOptionPane.showOptionDialog(null, msg, title,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icon, null, null);
	}

	public void Failure(String msg,String title) {
		icon = new ImageIcon(address+"X.png");
		JOptionPane.showOptionDialog(null, msg, title,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icon, null, null);
	}

	public void Warning(String msg,String title) {
		icon = new ImageIcon(address+"war.png");
		JOptionPane.showOptionDialog(null, msg, title,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icon, null, null);
		
	}
}
