import java.io.*;
import java.util.*;

public class CovidHealthBuilder{

	protected ArrayList<String> arrayList = new ArrayList<String>();
	protected DecisionTreeInterface<String> healthTree;
	private BinaryNode<String> currentRoot;

	public CovidHealthBuilder(String fileName){
		try{
		File inputFile = new File(fileName);
		Scanner fileScanner = new Scanner(inputFile);

		//add the lines in the file to a string var
		while(fileScanner.hasNextLine()){
			String s = fileScanner.nextLine();
			readData(s);
		}

		healthTree = new DecisionTree<String>();

		currentRoot = buildTree(this.arrayList, null, 0);
		
		healthTree.setRootNode(this.currentRoot);
		healthTree.resetCurrentNode();

		//for(int j = 0; j < arrayList.size(); j++){
		//	System.out.print(arrayList.get(j));
		//	System.out.print(",");
		//}
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
	}
	public ArrayList<String> readData(String content){
		//split by commas
		String[] temp = content.split(",");

		int i;
		for(i = 0; i < temp.length; i++){
			//add each substring to arraylist
			this.arrayList.add(temp[i].trim());
		}
		return this.arrayList;
	}
	public BinaryNode<String> buildTree(ArrayList<String> list, BinaryNode<String> currentNode, int index){
		if(index < list.size()){
			BinaryNode<String> temp = new BinaryNode<String>(list.get(index));
			currentNode = temp;
			//if the data is null
			if(list.get(index) == "null"){
				currentNode.setData(null);
			}
			
			//left
			currentNode.setLeftChild(buildTree(list, currentNode, (index*2)+1));

			//right
			currentNode.setRightChild(buildTree(list, currentNode, (index*2)+2));
		}
		return currentNode;
	}
	public void decide(){
		Scanner userInput = new Scanner(System.in);

		healthTree.setRootNode(this.currentRoot);
		healthTree.resetCurrentNode();

		if(healthTree.getCurrentNode() == null){
			throw new EmptyTreeException();
		}
		if(healthTree.getCurrentData().equals("null")){
			throw new EmptyTreeException();
		}
		String yesOrNo;

		BinaryNode<String> temp = healthTree.getCurrentNode();

		while(healthTree.isAnswer() != true && healthTree.getCurrentNode().isLeaf() == false){
			//checking if data is somehow equal to the child
			if(temp.getRightChild().getData().equals(healthTree.getCurrentData()) ||
				temp.getLeftChild().getData().equals(healthTree.getCurrentData())){
				break;
			}
			//ask question
			System.out.println(healthTree.getCurrentData());
			//get input
			yesOrNo = userInput.nextLine();

			//if yes
			if(yesOrNo.equals("yes")){
				healthTree.moveToYes();
				temp = healthTree.getCurrentNode();
			}
			//if no
			else if(yesOrNo.equals("no")){
				healthTree.moveToNo();
				temp = healthTree.getCurrentNode();
			}
			//if answer is neither yes or no (for testing sake)
			else{
				System.out.println("please input yes or no");
			}
		}
		System.out.println(healthTree.getCurrentData());
		System.out.println("");
		System.out.println("Satisfied by my intelligence?\n");
		yesOrNo = userInput.nextLine();
		if(yesOrNo.equals("yes")){
			return;
		}
		else if(yesOrNo.equals("no")){
			learn();
			return;
		}
	}
	public void learn(){
		String question;
		String newAns;
		String oldAns = healthTree.getCurrentData().toString();

		Scanner newScanner = new Scanner(System.in);

		System.out.println("What should be the answer");
		newAns = newScanner.nextLine();

		System.out.print("Give me a question whose answer is yes for ");
		System.out.print(newAns);
		System.out.print(" but no for ");
		System.out.println(oldAns);
		question = newScanner.nextLine();

		updateTree(question, oldAns, newAns);

	}
	public void updateTree(String question, String noAnswer, String yesAnswer){
		BinaryNode<String> current = healthTree.getCurrentNode();
		
		current.setData(question);

		healthTree.setResponses(noAnswer, yesAnswer);
	}
	public DecisionTreeInterface<String> getHealthTree(){
		if(this.healthTree == null){
			throw new EmptyTreeException();
		}
		return this.healthTree;
	}
}