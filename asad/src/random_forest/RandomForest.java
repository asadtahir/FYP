package random_forest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class RandomForest {
private static final int NUM_THREADS=20;
public static int attributes;
public static int pickedattributes;
private ArrayList<DecisionTree> trees2;
private int numTrees;
private double update;
private double progress;
private HashMap<int[],int[]> estimateOOB;
private ExecutorService treePool;
private ArrayList<ArrayList<String>> data;
private ArrayList<ArrayList<String>> testdata;
public ArrayList<ArrayList<String>> Prediction;
public ArrayList<Integer> TrainAttributes;
public ArrayList<Integer> TestAttributes;

public RandomForest(int numTrees,int attributes,int pickedattributes, ArrayList<ArrayList<String>> train,ArrayList<ArrayList<String>> test) {

this.numTrees=numTrees;
this.data=train;
this.testdata=test;
this.attributes=attributes;
this.pickedattributes=pickedattributes;
this.TrainAttributes=GetAttributes(train);
this.TestAttributes=GetAttributes(test);
trees2 = new ArrayList<DecisionTree>(numTrees);
update=100/((double)numTrees);

System.out.println("creating "+numTrees+" trees in a random Forest. . . ");
System.out.println("total data size is "+train.size());
System.out.println("number of attributes "+attributes);
System.out.println("number of selected attributes "+pickedattributes);
estimateOOB=new HashMap<int[],int[]>(data.size());
Prediction = new ArrayList<ArrayList<String>>();
}
public RandomForest() {
	
}

public void AlgoStart() {
System.out.println("Number of threads started : "+NUM_THREADS);
System.out.println("Starting trees");
treePool=Executors.newFixedThreadPool(NUM_THREADS);
for (int t=0;t<numTrees;t++)
{
treePool.execute(new CreateTree(data,this,t+1));
}
treePool.shutdown();
try {
treePool.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
} catch (InterruptedException ignored){
System.out.println("interrupted exception in Random Forests");
}
if(data.get(0).size()>testdata.get(0).size())
{
	System.out.println("Cannot test this data");
	
}
else if(data.get(0).size()==testdata.get(0).size())
{
   TestForest2(trees2, data, testdata);
   
}
else
{
   System.out.println("Cannot test this data"); 
}
}
private void TestForestNoLabel2(ArrayList<DecisionTree> trees22,ArrayList<ArrayList<String>> data2,ArrayList<ArrayList<String>> testdata2) {
ArrayList<String> TestResult = new ArrayList<String>();
System.out.println("Predicting Labels now");
for(ArrayList<String> DP:testdata2){
ArrayList<String> Predict = new ArrayList<String>();
for(DecisionTree DT:trees22){
Predict.add(DT.Evaluate(DP, testdata2));
}
TestResult.add(ModeofList(Predict));
}
}
public void TestForest2(ArrayList<DecisionTree> trees,ArrayList<ArrayList<String>> train,ArrayList<ArrayList<String>> test)
{
int correctness=0;ArrayList<String> ActualValues = new ArrayList<String>();
for(ArrayList<String> s:test){
ActualValues.add(s.get(s.size()-1));
}
int treee=1;
System.out.println("Testing forest now ");
for(DecisionTree DTC : trees)
{
DTC.CalculateClasses(train, test, treee);
treee++;
if(DTC.predictions!=null)
  {
    Prediction.add(DTC.predictions);
  }
}
for(int i = 0;i<test.size();i++){
ArrayList<String> Val = new ArrayList<String>();
for(int j=0;j<trees.size();j++){
Val.add(Prediction.get(j).get(i));
}
String pred = ModeofList(Val);
if(pred.equalsIgnoreCase(ActualValues.get(i))){
correctness = correctness +1;
}
}

System.out.println("The Result of Predictions :-" +Prediction);
}

public String ModeofList(ArrayList<String> predictions) {
String MaxValue = null; 
int MaxCount = 0;
for(int i=0;i<predictions.size();i++){
int count=0;
for(int j=0;j<predictions.size();j++){
if(predictions.get(j).trim().equalsIgnoreCase(predictions.get(i).trim()))
count++;
if(count>MaxCount){
MaxValue=predictions.get(i);
MaxCount=count;
}
}
}
return MaxValue;
}
private class CreateTree implements Runnable{
private ArrayList<ArrayList<String>> data;
private RandomForest forest;
private int treenum;
public CreateTree(ArrayList<ArrayList<String>> data,RandomForest forest,int num){
this.data=data;
this.forest=forest;
this.treenum=num;
}
public void run() {
trees2.add(new DecisionTree(data, forest, treenum));
progress+=update;
}
}


private ArrayList<Integer> GetAttributes(List<ArrayList<String>> data){
ArrayList<Integer> Attributes = new ArrayList<Integer>();
int iter = 0;
ArrayList<String> DataPoint = data.get(iter);
if(DataPoint.contains("n/a") || DataPoint.contains("N/A"))
{
  iter = iter +1;
  DataPoint = data.get(iter);
}
for(int i =0;i<DataPoint.size();i++){
Attributes.add(0);
}
return Attributes;
}
}