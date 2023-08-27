import java.io.*;
import java.util.*;
/**
 *  This is my submission of the CovidHealthBuilder class.
 *  @author Isaac Kim G01201648
 */
public class CovidHealthBuilder{
	//initializing class variables
	/**arraylist that holds data for our tree.*/
	protected ArrayList<String> arrayList = new ArrayList<String>();
	/**decisiontreeinterface for our tree.*/
	protected DecisionTreeInterface<String> healthTree;
	/**placeholder for root of tree.*/
	private BinaryNode<String> currentRoot;

	/**
	 *  Default constructor for CovidHealthBuilder.
	 *  @param fileName name of the file we scan in
	 */
	public CovidHealthBuilder(String fileName){
		this.arrayList = readData(fileName);
		healthTree = new DecisionTree<String>();
		currentRoot = buildTree(this.arrayList, null, 0);
	
		healthTree.setRootNode(this.currentRoot);
		healthTree.resetCurrentNode();
		//for(int j = 0; j < arrayList.size(); j++){
		//	System.out.print(arrayList.get(j));
		//	System.out.print(",");
		//}
	}
	/**
	 *  This method reads the data in the line of text from the file.
	 *  @param content line of text we scan in
	 *  @return returns the arraylist of seperated strings
	 */
	public ArrayList<String> readData(String content){
		try{
			File inputFile = new File(content);
			Scanner fileScanner = new Scanner(inputFile);

			//add the lines in the file to a string var
			while(fileScanner.hasNextLine()){
				String s = fileScanner.nextLine();
				//split by commas
				String[] temp = s.split(",");

				int i;
				for(i = 0; i < temp.length; i++){
					//add each substring to arraylist
					this.arrayList.add(temp[i].trim());
				}
			}	
			return this.arrayList;
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
		return this.arrayList;
	}
	/**
	 *  This method builds our binary tree.
	 *  @param list the arraylist of data for our tree
	 *  @param currentNode the currentNode of the tree
	 *  @param index the index that we are currently accessing
	 *  @return returns the root of the tree
	 */
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
	/**
	 *  This method determines where to move to based on user input: yes or no.
	 */
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
		System.out.print(healthTree.getCurrentData());

		System.out.println("\n\nSatisfied by my intelligence?\n");
		yesOrNo = userInput.nextLine();
		if(yesOrNo.equals("yes")){
			return;
		}
		else if(yesOrNo.equals("no")){
			learn();
			return;
		}
	}
	/**
	 *  This method updates the tree with user given questions and answers.
	 */
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
	/**
	 *  This method updates our tree from learn().
	 *  @param question the question for the node
	 *  @param noAnswer the left node data
	 *  @param yesAnswer the right node data
	 */
	public void updateTree(String question, String noAnswer, String yesAnswer){
		BinaryNode<String> current = healthTree.getCurrentNode();
		
		current.setData(question);

		healthTree.setResponses(noAnswer, yesAnswer);
	}
	/**
	 *  Getter method for healthTree.
	 *  @return returns the healthTree
	 */
	public DecisionTreeInterface<String> getHealthTree(){
		if(this.healthTree == null){
			throw new EmptyTreeException();
		}
		return this.healthTree;
	}
}