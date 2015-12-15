package project;

import java.io.File;
import javax.swing.JFileChooser;
public class FileChooserDialog {
	private String path,path2;
	
	public void fileChooser() {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		fileChooser.addChoosableFileFilter(new MyFilter());

		fileChooser.showOpenDialog(null);
		File f = fileChooser.getSelectedFile();
		path = f.getAbsolutePath();
	}
	
	// save this file by given path
	public void fileChooser2() {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		fileChooser.addChoosableFileFilter(new MyFilter());

		fileChooser.showOpenDialog(null);
		File f = fileChooser.getSelectedFile();
		path2 = f.getAbsolutePath();
	}
	
	public String getPath()
	{
		return this.path;
	}
	public String getPath2()
	{
		return this.path2;
	}

}