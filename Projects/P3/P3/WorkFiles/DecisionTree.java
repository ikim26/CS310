/**
 *  This is my submission of the DecisionTree class.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public class DecisionTree<T> extends BinaryTree<T> implements DecisionTreeInterface<T> {
	//class variables
	/**variable to hold currentNode.*/
	protected BinaryNode<T> currentNode;

	/**
	 *  Default constructor for DecisionTree.
	 */
	public DecisionTree(){
		this.currentNode = null;
	}
	/**
	 *  Default constructor for DecisionTree with parameter.
	 *  @param data the data the currentNode holds
	 */
	public DecisionTree(T data){
		this.currentNode = new BinaryNode<T>(data);
	}
	//interface methods
	/**
	 *  This method determines if a node is the "last" answer.
	 *  @return returns true if node is an answer, and false otherwise
	 */
	public boolean isAnswer(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		else if(this.currentNode.isLeaf() == true){
			return true;
		}
		else if(this.currentNode.getLeftChild().getData().equals("null") && this.currentNode.getRightChild().getData().equals("null")){
			return true;
		}
		return false;
	}
	/**
	 *  This method moves the currentNode to the left(no).
	 */
	public void moveToNo(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		this.currentNode = currentNode.getLeftChild();
	}
	/**
	 *  This method moves the currentNode to the right(yes).
	 */
	public void moveToYes(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		this.currentNode = currentNode.getRightChild();
	}
	/**
	 *  This method resets currentNode to the root of the tree.
	 */
	public void resetCurrentNode(){
		this.currentNode = super.root;
	}
	/**
	 *  Getter method for currentNode.
	 *  @return returns the currentNode
	 */
	public BinaryNode<T> getCurrentNode({
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		return this.currentNode;
	}
	/**
	 *  Getter method for currentNode's data.
	 *  @return returns the currentNode data
	 */
	public T getCurrentData(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		return this.currentNode.getData();
	}
	/**
	 *  This method updates the responses (left and right child).
	 *  @param responseForNo the data for left child
	 *  @param responseForYes the data for right child
	 */
	public void setResponses(T responseForNo, T responseForYes){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		if(this.currentNode.getLeftChild() == null){
			BinaryNode<T> leftTemp =  new BinaryNode<T>();
			currentNode.setLeftChild(leftTemp);
		}
		if(this.currentNode.getRightChild() == null){
			BinaryNode<T> rightTemp =  new BinaryNode<T>();
			currentNode.setRightChild(rightTemp);
		}
		this.currentNode.getLeftChild().setData(responseForNo);
		this.currentNode.getRightChild().setData(responseForYes);
	}
}