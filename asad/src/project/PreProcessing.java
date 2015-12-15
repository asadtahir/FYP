package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class PreProcessing {
	private Instances data;
	private String method; 
	private ArrayList labels;
	boolean isString;
	private weka.filters.unsupervised.attribute.Normalize filter1;
	
	public PreProcessing(String path) {
		this.isString = false;
		try {
			data = new Instances(new BufferedReader(new FileReader(path)));
			if (data.classIndex() == -1)
				{
				  data.setClassIndex(data.numAttributes() - 1);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void preprocessing() {
		Instances newData = null;
		this.isString = false;
		
		if (isString == false) {
			try {
				if (method.matches("Normalize")) {
					this.filter1 = new weka.filters.unsupervised.attribute.Normalize();
					filter1.setInputFormat(data);
					newData = Filter.useFilter(data, filter1);
					this.data = newData;
					System.out.println(newData);
					System.out.println(newData.numAttributes());
					System.out.println(newData.meanOrMode(newData.attribute(0)));
					for (int j = 0; j < newData.numAttributes(); j++)
						System.out.println(newData.attribute(j));
				} 
				
				else {
					System.out.println("do nothing");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		else {
			Messages m = new Messages();
			m.Failure("String attributes cannot handel", "Failure");
		}
	}

	public Instances getData() {
		return this.data;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCurrentRelation() {
		return this.data.relationName();
	}

	public int getNumOfAtt() {
		return this.data.numAttributes();
		
	}

	public int getNumOfinstances() {
		return this.data.numInstances();
	}

	//////////////////////////// ////////////////////// ////////////////////////////
	public List getAttributeNames() {
		List name = new ArrayList();
		for (int i = 0; i < this.data.numAttributes(); i++) {
			name.add(i, this.data.attribute(i).name());
		}
		return name;
	}
	///////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////
	public String getType(int i) {
		String type = "";
		int j = this.data.attribute(i).type();
		if (this.data.attribute(i).isNumeric()) {
			type = "Numeric";
		} else if (this.data.attribute(i).isNominal()) {
			type = "Nominal";
		} else if (this.data.attribute(i).isString()) {
			type = "String";
		}
		return type;
	}
    ///////////////////////////////////////////////////////////////////////////////
	
	public int getDistict(int i) {
		return this.data.numDistinctValues(i); // district
	}

	////////////////////////////////////////////////////////////////////////////////
	public int getMissing(int i) {
		int count = 0;
		int in = 0;
		Instance temp;
		while (in < data.numInstances()) {
			temp = data.instance(in);
			if (temp.isMissing(i)) {
				count++;
			}
			in++;
		}
		return count;
	}
	
	///////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////
	public double getMinimum(int i) {
		double min = 0;
		if (this.data.firstInstance().isMissing(i)) {
			int in = 0;
			Instance temp;
			while (in < data.numInstances()) {
				temp = data.instance(in);
				if (temp.isMissing(i)) {
					in++;
				} else {
					min = temp.value(i);
					break;
				}
			}

		} 
		else {
			min = this.data.firstInstance().value(i);
		}
		int in = 0;
		Instance temp;
		while (in < data.numInstances()) {
			temp = data.instance(in);
			if (temp.value(i) < min) {
				min = temp.value(i);
			}
			in++;
		}

		return min;
	}

    ///////////////////////////////////////////////////////////////////////////////////
	
	
    ///////////////////////////////////////////////////////////////////////////////////
	public double getMaximum(int i) {
		double max = 0;
		if (this.data.firstInstance().isMissing(i)) {
			int in = 0;
			Instance temp;
			while (in < data.numInstances()) {
				temp = data.instance(in);
				if (temp.isMissing(i)) {
					in++;
				} else {
					max = temp.value(i);
					break;
				}
			}

		} else {
			max = this.data.firstInstance().value(i);
		}
		int in = 0;
		Instance temp;
		while (in < data.numInstances()) {
			temp = data.instance(in);
			if (temp.value(i) > max) {
				max = temp.value(i);
			}
			in++;
		}

		return max;
	}

    ///////////////////////////////////////////////////////////////////////////////////
   
	public double getMeanOrMode(int i) {
		return this.data.meanOrMode(i);
	}

	///////////////////////////////////////////////////////////////////////////////////

  	public double getStdDev(int i) {
		return Math.sqrt(this.data.variance(i));
	}

	///////////////////////////////////////////////////////////////////////////////////

	public List getLabels(int i) {
		labels = new ArrayList();

		int in = 0;
		Instance temp;
		while (in < data.numInstances()) {
			boolean match = false;
			temp = data.instance(in);
			String str;
			if (temp.isMissing(i)) {
				in++;
			} else {
				str = temp.toString(i);
				for (int j = 0; j < labels.size(); j++) {
					if (labels.get(j).toString().matches(str)) {
						match = true;
					}
				}
				if (match == false) {
					labels.add(str);
				}
				in++;
			}
		}
		return labels;
	}

	//////////////////// get lables count///////////////////////
	public List getLaabelsCount(int i) {
		List count = new ArrayList();
		for (int j = 0; j < labels.size(); j++) {
			count.add(0);
		}
		int in = 0;
		Instance temp;
		while (in < data.numInstances()) {
			String str;
			temp = data.instance(in);
			if (temp.isMissing(i)) {
				in++;
			} else {
				str = temp.toString(i);
				for (int j = 0; j < count.size(); j++) {
					if (labels.get(j).equals(str)) {
						int c = (int) count.get(j);
						count.set(j, (c + 1));
					}
				}
				in++;
			}
		}
		return count;
	}

	///////////////////////////////////////////////////////////////////////////
	
	public boolean deleteAttribute(int i) {
		if (i == data.classIndex()) {
			Messages m = new Messages();
			m.Failure("Class attribute can't be deleted", "Failure");
			return false;
		} else {
			data.deleteAttributeAt(i);
			Messages m = new Messages();
			m.success("Successfully Deleted.", "success");
			return true;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	

}
