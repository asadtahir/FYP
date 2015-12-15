package random_forest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class DecisionTree {
	
	private static final int INDEX_SKIP=3;
	private static final int MIN_SIZE_TO_CHECK_EACH=10;
	private static final int MIN_NODE_SIZE=5;
	private int N;
	private int testN;
	private int correct;
	private int[] importances;
	public ArrayList<String> predictions;
	private TreeNode root;
	private RandomForest forest;
	public DecisionTree(ArrayList<ArrayList<String>> data,RandomForest forestvar, int treenum) {
		this.forest=forestvar;
		N=data.size();
		importances = new int[RandomForest.attributes];
		
		ArrayList<ArrayList<String>> trainData = new ArrayList<ArrayList<String>>(N);
		ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		
		BootStrapSample(data,trainData,test,treenum);
		testN=test.size();
		correct=0;
		
		
		root=CreateTree(trainData,treenum);
		FlushData(root, treenum);
	}
	private void BootStrapSample(ArrayList<ArrayList<String>> data,ArrayList<ArrayList<String>> train,ArrayList<ArrayList<String>> test,int numb){
		ArrayList<Integer> indices=new ArrayList<Integer>();
		for (int n=0;n<N;n++)
			indices.add((int)Math.floor(Math.random()*N));
		ArrayList<Boolean> in=new ArrayList<Boolean>();
		for (int n=0;n<N;n++)
			in.add(false); 
		for (int num:indices){
			ArrayList<String> k = data.get(num);
			train.add((ArrayList<String>) k.clone());
			in.set(num,true);
		}
		for (int i=0;i<N;i++)
			if (!in.get(i))
				test.add(data.get(i));
	}
	
	private TreeNode CreateTree(ArrayList<ArrayList<String>> train, int ntree){
		TreeNode root=new TreeNode();
		root.label = "|ROOT|";
		root.data=train;
		RecursiveSplit(root,ntree);
		return root;
	}
	public class TreeNode implements Cloneable{
		public boolean isLeaf;
		public ArrayList<TreeNode> ChildNode ;
		public HashMap<String, String> Missingdata;
		public int splitAttributeM;
		public boolean spiltonCateg ;
		public String Class;
		public ArrayList<ArrayList<String>> data;
		public String splitValue;
		public String label;
		public int generation;
		
		public TreeNode(){
			splitAttributeM=-99;
			splitValue="-99";
			generation=1;
		}
		public TreeNode clone(){ 
			TreeNode copy=new TreeNode();
			copy.isLeaf=isLeaf;
			for(TreeNode TN : ChildNode){
				if(TN != null){
					copy.ChildNode.add(TN.clone());
				}
			}
			clone();
			copy.splitAttributeM=splitAttributeM;
			copy.Class=Class;
			copy.splitValue=splitValue;
			copy.spiltonCateg = spiltonCateg;
			copy.label=label;
			return copy;
		}
	}
	private class DoubleWrap{
		public double d;
		public DoubleWrap(double d){
			this.d=d;
		}		
	}
	public ArrayList<String> CalculateClasses(ArrayList<ArrayList<String>> traindata,ArrayList<ArrayList<String>> testdata, int treenumber){
		ArrayList<String> predicts = new ArrayList<String>();
		for(ArrayList<String> record:testdata){
			String Clas = Evaluate(record, traindata);
			if(Clas==null)
				System.out.println("Evaluation Nul hua");
			predicts.add(Clas);
		}
		predictions = predicts;
		return predicts;
	}
	
	public String Evaluate(ArrayList<String> record, ArrayList<ArrayList<String>> tester){
		TreeNode evalNode=root;		
			while (true) {				
				if(evalNode.isLeaf)
					return evalNode.Class;
				else{
					if(evalNode.spiltonCateg){
					String recordCategory = record.get(evalNode.splitAttributeM);
						boolean found=false;String Res = evalNode.Missingdata.get(GetClass(record));
						
						for(TreeNode child:evalNode.ChildNode){
							if(recordCategory.equalsIgnoreCase(child.label)){
								evalNode = child;
								found = true;
								break;
							}
						}if(!found){
							for(TreeNode child:evalNode.ChildNode){
								if(Res!=null){
									if(Res.trim().equalsIgnoreCase(child.label)){
										evalNode = child;
										break;
									}
								}else{
									return "n/a";
								}
							}
						}
					}else{
						
						double Compare = Double.parseDouble(evalNode.splitValue);
						double Actual = Double.parseDouble(record.get(evalNode.splitAttributeM));
						if(Actual <= Compare){
							if(evalNode.ChildNode.get(0).label.equalsIgnoreCase("Left"))
								evalNode=evalNode.ChildNode.get(0);
							else
								evalNode=evalNode.ChildNode.get(1);
						}else{
							if(evalNode.ChildNode.get(0).label.equalsIgnoreCase("Right"))
								evalNode=evalNode.ChildNode.get(0);
							else
								evalNode=evalNode.ChildNode.get(1);
						}
					}
				}
		}
	}
	private  TreeNode getChildtoTraverse(ArrayList<TreeNode> Chil,int splitAttributeM, String classofRecord) {
		int max=0;TreeNode res=new TreeNode();
		for(int i=0;i<Chil.size();i++){
			if(Chil.get(i)!=null && Chil.get(i).data.size()>0){
				int k=0;
				for(ArrayList<String> SSS:Chil.get(i).data){
					if(GetClass(SSS).equalsIgnoreCase(classofRecord))
						k++;
				}if(k>max){
					max=k;
					res = Chil.get(i);
				}
			}
		}return res;
	}
	private void RecursiveSplit(TreeNode parent, int Ntreenum){
		
		if (!parent.isLeaf){
			String Class=CheckIfLeaf(parent.data);
			if (Class != null){
				parent.isLeaf=true;
				parent.Class=Class;
				return;
			}
			int Nsub=parent.data.size();			
			
			parent.ChildNode = new ArrayList<DecisionTree.TreeNode>();
			for(TreeNode TN: parent.ChildNode){
				TN = new TreeNode();
				TN.generation = parent.generation+1;
			}
			
			ArrayList<Integer> vars=GetVarsToInclude();
			DoubleWrap lowestE=new DoubleWrap(Double.MAX_VALUE);
			
			for (int m:vars){
				SortAtAttribute(parent.data,m);
				ArrayList<Integer> DataPointToCheck=new ArrayList<Integer>(); 
				for (int n=1;n<Nsub;n++){
					String classA=GetClass(parent.data.get(n-1));
					String classB=GetClass(parent.data.get(n));
					if(!classA.equalsIgnoreCase(classB))
						DataPointToCheck.add(n);
				}
				if (DataPointToCheck.size() == 0){
					parent.isLeaf=true;
					parent.Class=GetClass(parent.data.get(0));
					continue;
				}
				
				if (DataPointToCheck.size() > MIN_SIZE_TO_CHECK_EACH){
					for (int i=0;i<DataPointToCheck.size();i+=INDEX_SKIP){
						CheckPosition(m, DataPointToCheck.get(i), Nsub, lowestE, parent, Ntreenum);
						if (lowestE.d == 0)
							break;
					}
				}else{
					for (int k:DataPointToCheck){
						CheckPosition(m,k, Nsub, lowestE, parent, Ntreenum);
						if (lowestE.d == 0)
							break;
					}
				}
				if (lowestE.d == 0)
					break;
			}
			
			for(TreeNode Child:parent.ChildNode){
				if(Child.data.size()==1){
					Child.isLeaf=true;
					Child.Class=GetClass(Child.data.get(0));
				}else if(Child.data.size()<MIN_NODE_SIZE){
					Child.isLeaf=true;
					Child.Class=GetMajorityClass(Child.data);
				}else{
					Class=CheckIfLeaf(Child.data);
					if(Class==null){
						Child.isLeaf=false;
						Child.Class=null;
					}else{
						Child.isLeaf=true;
						Child.Class=Class;
					}
				}
				if(!Child.isLeaf){
					RecursiveSplit(Child, Ntreenum);
				}
			}
		}
	}
	
	private String GetMajorityClass(ArrayList<ArrayList<String>> data){
		
		ArrayList<String> ToFind = new ArrayList<String>();
		for(ArrayList<String> s:data){
			ToFind.add(s.get(s.size()-1));
		}
		String MaxValue = null; int MaxCount = 0;
		for(String s1:ToFind){
			int count =0;
			for(String s2:ToFind){
				if(s2.equalsIgnoreCase(s1))
					count++;
			}
			if(count > MaxCount){
				MaxValue = s1;
				MaxCount = count;
			}
		}return MaxValue;
	}
	
	private double CheckPosition(int m,int n,int Nsub,DoubleWrap lowestE,TreeNode parent, int nTre){
	
		String real_OR_categ = parent.data.get(n).get(m);
		double entropy =0;
		
		if (n < 1) 
			return 0;
		if (n > Nsub)
			return 0;
		
		if(isAlphaNumeric(real_OR_categ)){
			
			ArrayList<String> uni_categ = new ArrayList<String>(); 
			ArrayList<String> uni_classes = new ArrayList<String>(); 
			HashMap<String, String> ChildMissingMap = new HashMap<String, String>();
			HashMap<String, Integer> ChilFreq = new HashMap<String, Integer>();
			
			for(ArrayList<String> s:parent.data){
				if(!uni_categ.contains(s.get(m).trim())){
					uni_categ.add(s.get(m).trim());
					ChilFreq.put(s.get(m), 0);
				}
					
				if(!uni_classes.contains(GetClass(s)))
					uni_classes.add(GetClass(s));
			}
			
			HashMap<String, ArrayList<ArrayList<String>>> ChildDataMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			for(String s:uni_categ){
				ArrayList<ArrayList<String>> child_data = new ArrayList<ArrayList<String>>();
				for(ArrayList<String> S:parent.data){
					if(s.trim().equalsIgnoreCase(S.get(m).trim()))
						child_data.add(S);
				}
				ChildDataMap.put(s, child_data);
			}
			
			for(String S1:uni_classes){
				int max=0;String Resul = null;
				for(ArrayList<String> S2:parent.data){
					if(GetClass(S2).equalsIgnoreCase(S1)){
						if(ChilFreq.containsKey(S2.get(m)))
							ChilFreq.put(S2.get(m), ChilFreq.get(S2.get(m))+1);
					}
					if(ChilFreq.get(S2.get(m))>max){
						max=ChilFreq.get(S2.get(m));
						Resul = S2.get(m);
					}
				}
				ChildMissingMap.put(S1, Resul);
			}
			
			for(Entry<String,ArrayList<ArrayList<String>>> entry:ChildDataMap.entrySet()){
				entropy+=CalEntropy(getClassProbs(entry.getValue()))*entry.getValue().size();
			}
			entropy = entropy/((double)Nsub);
			
			if (entropy < lowestE.d){
				lowestE.d=entropy;
				parent.splitAttributeM=m;
				parent.spiltonCateg = true;
				parent.splitValue=parent.data.get(n).get(m);
				parent.Missingdata=ChildMissingMap;
				
				ArrayList<TreeNode> Children = new ArrayList<TreeNode>();
				for(Entry<String,ArrayList<ArrayList<String>>> entry:ChildDataMap.entrySet()){
					TreeNode Child = new TreeNode();
					Child.data=entry.getValue();
					Child.label=entry.getKey();
					Children.add(Child);
				}
				parent.ChildNode=Children;
			}
		}else{
		
			
			ArrayList<ArrayList<String>> lower=GetLower(parent.data,n);
			ArrayList<ArrayList<String>> upper=GetUpper(parent.data,n);
			ArrayList<Double> pl=getClassProbs(lower);
			ArrayList<Double> pu=getClassProbs(upper);
			double eL=CalEntropy(pl);
			double eU=CalEntropy(pu);
		
			entropy =(eL*lower.size()+eU*upper.size())/((double)Nsub);
			
			if (entropy < lowestE.d){
				lowestE.d=entropy;
				parent.splitAttributeM=m;
				parent.spiltonCateg=false;
				parent.splitValue = parent.data.get(n).get(m).trim();
				
				ArrayList<TreeNode> Children2 = new ArrayList<TreeNode>();
				TreeNode Child_left = new TreeNode();
				Child_left.data=lower;
				Child_left.label="Left";
				Children2.add(Child_left);
				TreeNode Child_Right = new TreeNode();
				Child_Right.data=upper;
				Child_Right.label="Right";
				Children2.add(Child_Right);
				parent.ChildNode=Children2;//clone karo....
			}
		}
		return entropy;
	}
	
	private ArrayList<ArrayList<String>> GetLower(ArrayList<ArrayList<String>> data,int nSplit){
		ArrayList<ArrayList<String>> LS = new ArrayList<ArrayList<String>>();
		for(int n=0;n<nSplit;n++){
			LS.add(data.get(n));
		}return LS;
	}
	
	private ArrayList<ArrayList<String>> GetUpper(ArrayList<ArrayList<String>> data,int nSplit){
		int N=data.size();
		ArrayList<ArrayList<String>> LS = new ArrayList<ArrayList<String>>();
		for(int n=nSplit;n<N;n++){
			LS.add(data.get(n));
		}return LS;
	}
	
	private ArrayList<Double> getClassProbs(ArrayList<ArrayList<String>> record){
		double N=record.size();
		HashMap<String, Integer > counts = new HashMap<String, Integer>();
		for(ArrayList<String> s : record){
			String clas = GetClass(s);
			if(counts.containsKey(clas))
				counts.put(clas, counts.get(clas)+1);
			else
				counts.put(clas, 1);
		}
		ArrayList<Double> probs = new ArrayList<Double>();
		for(Entry<String, Integer> entry : counts.entrySet()){
			double prob = entry.getValue()/N;
			probs.add(prob);
		}return probs;
	}
	
	private static final double logoftwo=Math.log(2);
	
	private double CalEntropy(ArrayList<Double> ps){
		double e=0;		
		for (double p:ps){
			if (p != 0) 
				e+=p*Math.log(p)/logoftwo;
		}
		return -e; 
	}
	private boolean isAlphaNumeric(String s){
		char c[]=s.toCharArray();
		for(int j=0;j<c.length;j++){
			if(Character.isLetter(c[j])){
				return true;
			}
		}return false;
	}
	
	private void SortAtAttribute(ArrayList<ArrayList<String>> data,int m){
		if(isAlphaNumeric(data.get(0).get(m)))
			Collections.sort(data,new AttributeComparatorCateg(m));
		else
			Collections.sort(data,new AttributeComparatorReal(m));
	}
	private class AttributeComparatorCateg implements Comparator<ArrayList<String>>{		
	private int m;
	public AttributeComparatorCateg(int m){
			this.m=m;
		}
		@Override
		public int compare(ArrayList<String> arg1, ArrayList<String> arg2) {//compare strings
			String a = arg1.get(m);
			String b = arg2.get(m);
			return a.compareToIgnoreCase(b);
		}		
	}
	private class AttributeComparatorReal implements Comparator<ArrayList<String>>{		
		private int m;
		public AttributeComparatorReal(int m){
			this.m=m;
		}
		@Override
		public int compare(ArrayList<String> arg1, ArrayList<String> arg2) {
			double a2 = Double.parseDouble(arg1.get(m));
			double b2 = Double.parseDouble(arg2.get(m));
			if(a2<b2)
				return -1;
			else if(a2>b2)
				return 1;
			else
				return 0;
		}		
	}
	
	private ArrayList<Integer> GetVarsToInclude() {
		boolean[] whichVarsToInclude=new boolean[RandomForest.attributes];

		for (int i=0;i<RandomForest.attributes;i++)
			whichVarsToInclude[i]=false;
		
		while (true){
			int a=(int)Math.floor(Math.random()*RandomForest.attributes);
			whichVarsToInclude[a]=true;
			int N=0;
			for (int i=0;i<RandomForest.attributes;i++)
				if (whichVarsToInclude[i])
					N++;
			if (N == RandomForest.pickedattributes)
				break;
		}
		
		ArrayList<Integer> shortRecord=new ArrayList<Integer>(RandomForest.pickedattributes);
		
		for (int i=0;i<RandomForest.attributes;i++)
			if (whichVarsToInclude[i])
				shortRecord.add(i);
		return shortRecord;
	}
	
	public static String GetClass(ArrayList<String> record){
		return record.get(RandomForest.attributes).trim();
	}
	
	private String CheckIfLeaf(ArrayList<ArrayList<String>> data){
		boolean isCLeaf=true;
		String ClassA=GetClass(data.get(0));
		for(ArrayList<String> record : data){
			if(!ClassA.equalsIgnoreCase(GetClass(record))){
				isCLeaf = false;
				return null;
			}
		}
		if (isCLeaf)
			return GetClass(data.get(0));
		else
			return null;
	}
	
	private void FlushData(TreeNode node, int treenum){
		node.data=null;
		if(node.ChildNode!=null){
			for(TreeNode TN : node.ChildNode){
				if(TN != null)
					FlushData(TN,treenum);
			}
		}
	}

}