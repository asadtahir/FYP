package random_forest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PathSelection {

	BufferedReader BR = null;
	String path;
	public PathSelection(String path){
		this.path=path;
	}
	public ArrayList<ArrayList<String>> CreateInputCateg(String path){

		ArrayList<ArrayList<String>> DataInput = new ArrayList<ArrayList<String>>();
		try {
		String sCurrentLine;
		BR = new BufferedReader(new FileReader(path));
		while ((sCurrentLine = BR.readLine()) != null) {
			ArrayList<Integer> Sp=new ArrayList<Integer>();
			int i;
			if(sCurrentLine!=null){
				
			if(sCurrentLine.indexOf(",")>=0)
				{
					sCurrentLine=","+sCurrentLine+",";
					char[] c =sCurrentLine.toCharArray();
					for(i=0;i<sCurrentLine.length();i++){
						if(c[i]==',')
							Sp.add(i);
					}ArrayList<String> DataPoint=new ArrayList<String>();
					for(i=0;i<Sp.size()-1;i++){
						DataPoint.add(sCurrentLine.substring(Sp.get(i)+1, Sp.get(i+1)).trim());
					}DataInput.add(DataPoint);System.out.println("DataPoint            " + DataPoint);
				}
				
				else if(sCurrentLine.indexOf(" ")>=0){
					//has spaces
					sCurrentLine=" "+sCurrentLine+" ";
					for(i=0;i<sCurrentLine.length();i++){
						if(Character.isWhitespace(sCurrentLine.charAt(i)))
							Sp.add(i);
					}
					ArrayList<String> DataPoint=new ArrayList<String>();
					for(i=0;i<Sp.size()-1;i++){
						DataPoint.add(sCurrentLine.substring(Sp.get(i), Sp.get(i+1)).trim());
					}
					DataInput.add(DataPoint);System.out.println("DataPoint            " + DataPoint);
				}
				
			}
		}System.out.println("Input generated");

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (BR != null)BR.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	//System.out.println("Data point =   "+DataInput);	For both training and testing
	return DataInput;
	
}
}