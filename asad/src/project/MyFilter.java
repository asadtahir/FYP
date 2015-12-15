package project;
import java.io.File;
//import javax.swing.*;
import javax.swing.filechooser.*;

public class MyFilter extends FileFilter {
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.csv) || extension.equals(Utils.arff))
               {
                    return true;
               } 
            else 
               {
                    return false;
               }
        }
         return false;
    }
    public String getDescription() {
        return "arff & csv";
    }
}