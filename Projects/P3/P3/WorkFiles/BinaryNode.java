/**
 *  This is my submission of the BinaryNode class.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public class BinaryNode<T> {
	//class variables
	/**variable for right child.*/
	protected BinaryNode<T> rightChild;
	/**variable for left child.*/
	protected BinaryNode<T> leftChild;
	/**variable for this node's data.*/
	protected T data;
	
	/**
	 *  Default constructor for BinaryNode, sets everything to null.
	 */
	public BinaryNode(){
		this.rightChild = null;
		this.leftChild = null;
		this.data = null;
	}
	/**
	 *  Default constructor for BinaryNode with one parameter.
	 *  @param data the data for current node
	 */
	public BinaryNode(T data){
		this.rightChild  = null;
		this.leftChild = null;
		this.data = data;
	}
	/**
	 *  Default constructor for BinaryNode with 3 parameters.
	 *  @param data the data for current node
	 *  @param leftNode the left node of current
	 *  @param rightNode the right node of current
	 */
	public BinaryNode(T data, BinaryNode<T> leftNode, BinaryNode<T> rightNode){
		this.rightChild = rightNode;
		this.leftChild = leftNode;
		this.data = data;
	}

	//setters and getters for data
	/**
	 *  Setter method for current data.
	 *  @param newData the data for current node
	 */
	public void setData(T newData){
		this.data = newData;
	}
	/**
	 *  Getter method for current data.
	 *  @return returns the data for current node
	 */
	public T getData(){
		return this.data;
	}

	//setters and getters for left child
	/**
	 *  Setter method for left child.
	 *  @param leftNode the left child for current node
	 */
	public void setLeftChild(BinaryNode<T> leftNode){
		this.leftChild = leftNode;
	}
	/**
	 *  Getter method for left child.
	 *  @return returns the left child
	 */
	public BinaryNode<T> getLeftChild(){
		return this.leftChild;
	}

	//setters and getters for right child
	/**
	 *  Setter method for right child.
	 *  @param rightNode the right child for current node
	 */
	public void setRightChild(BinaryNode<T> rightNode){
		this.rightChild = rightNode;
	}
	/**
	 *  Getter method for right child.
	 *  @return returns the right child
	 */
	public BinaryNode<T> getRightChild(){
		return this.rightChild;
	}

	//methods that check if childs exist
	/**
	 *  This method checks if left child exists.
	 *  @return returns true if it has left child, and false otherwise
	 */
	public boolean hasLeftChild(){
		if(this.leftChild != null){
			return true;
		}
		return false;
	}
	/**
	 *  This method checks if right child exists.
	 *  @return returns true if it has right child, and false otherwise
	 */
	public boolean hasRightChild(){
		if(this.rightChild != null){
			return true;
		}
		return false;
	}

	//method to check if node is a leaf
	/**
	 *  This method checks if current node is a leaf.
	 *  @return returns true if left and right don't exist, and false otherwise
	 */
	public boolean isLeaf(){
		if(this.leftChild == null && this.rightChild == null){
			return true;
		}
		return false;
	}

	//get height of tree at current node
	/**
	 *  Gets height of tree rooted at current node.
	 *  @return returns an int for the current height
	 */
	public int getHeight(){
		if(this.data == null){
			return -1;
		}
		else{
			int left = -1;
			int right = -1;
			if(hasLeftChild() == true){
				left = this.leftChild.getHeight();
			}
			if(hasRightChild() == true){
				right = this.rightChild.getHeight();
			}
			return 1+ Math.max(left, right);
		}
	}

	//get num of nodes in tree at this node
	/**
	 *  Gets number of nodes in tree rooted at current node.
	 *  @return returns an int for number of nodes
	 */
	public int getNumberOfNodes(){

		if(this.data == null){
			return 0;
		}
		else{
			int left = 0;
			int right = 0;
			if(hasLeftChild() == true){
				left = this.leftChild.getNumberOfNodes();
			}
			if(hasRightChild() == true){
				right = this.rightChild.getNumberOfNodes();
			}
			return 1 + left + right;
		}
	}

	//copy the tree
	/**
	 *  This method makes a copy of the tree rooted at current node.
	 *  @return returns the root of the new tree
	 */
	public BinaryNode<T> copy(){

		BinaryNode<T> newRoot = new BinaryNode<T>(this.data);

		if(this.leftChild != null){
			newRoot.leftChild = leftChild.copy();
		}
		if(this.rightChild != null){
			newRoot.rightChild = rightChild.copy();
		}
		return newRoot;
	}
}