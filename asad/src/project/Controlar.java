package project;

import java.util.ArrayList;
import java.util.HashMap;

import random_forest.PathSelection;
import random_forest.RandomForest;

public class Controlar {
	
	public void assignmentprediction() 
	{
		String traindata,testdata;
		traindata="C:\\Users\\FAST\\Desktop\\Project_new\\asad\\train.txt";
		testdata="C:\\Users\\FAST\\Desktop\\Project_new\\asad\\test.txt";
	
		PathSelection DT = new PathSelection(traindata);
	    ArrayList<ArrayList<String>> Train = DT.CreateInputCateg(traindata);
	    ArrayList<ArrayList<String>> Test = DT.CreateInputCateg(testdata);
	
	    HashMap<String, Integer> Classes = new HashMap<String, Integer>();
	    
	    for(ArrayList<String> dp : Train)
	    {		
	    	String clas = dp.get(dp.size()-1);
	    	if(Classes.containsKey(clas))		
	    	{			
	    		Classes.put(clas, Classes.get(clas)+1);		
	    	}		
	    	else		
	    	{
	    		Classes.put(clas, 1);
	    	}
	}	
	
	    int numTrees=10;
	    int M=Train.get(0).size()-1;
	    int Ms = (int)Math.round(Math.log(M)/Math.log(2)+1);
	    int C = Classes.size();
	    RandomForest RFC = new RandomForest(numTrees, M, Ms, Train, Test);
	    RFC.AlgoStart();
	    
		
	}
	
	public void count()
	{
		RandomForest RFobj = new RandomForest();
		ArrayList<ArrayList<String>> s = RFobj.Prediction;
		System.out.println("baba g list a gai h =     "+s);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void totalprediction() ///////  call in button of prediction of for assigment prediction
	{
		String traindata,testdata;
		traindata="C:\\Users\\FAST\\Desktop\\total prediction data\\trainmypro.txt";
		testdata="C:\\Users\\FAST\\Desktop\\total prediction data\\test1mypro.txt";
	
	    PathSelection DT = new PathSelection(traindata);
	    ArrayList<ArrayList<String>> Train = DT.CreateInputCateg(traindata);
	    ArrayList<ArrayList<String>> Test = DT.CreateInputCateg(testdata);
	
	    HashMap<String, Integer> Classes = new HashMap<String, Integer>();
	    
	    for(ArrayList<String> dp : Train)
	    {		
	    	String clas = dp.get(dp.size()-1);
	    	if(Classes.containsKey(clas))		
	    	{			
	    		Classes.put(clas, Classes.get(clas)+1);		
	    	}		
	    	else		
	    	{
	    		Classes.put(clas, 1);
	    	}
	}	
	
	    int numTrees=10;
	    int M=Train.get(0).size()-1;
	    int Ms = (int)Math.round(Math.log(M)/Math.log(2)+1);
	    int C = Classes.size();
	    RandomForest RFC = new RandomForest(numTrees, M, Ms, Train, Test);
	    RFC.AlgoStart();
	    
		
	}
	
	

}
