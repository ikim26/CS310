/**
 *  This is my submission of the BinaryTree class.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
	//class variables
	/**variable to hold root of tree.*/
	protected BinaryNode<T> root;

	/**
	 *  Default constructor for BinaryTree.
	 */
	public BinaryTree(){
		this.root = null;
	}
	/**
	 *  Default constructor for BinaryTree with one parameter.
	 *  @param data the data for the root
	 */
	public BinaryTree(T data){
		this.root = new BinaryNode<>(data);
	}
	/**
	 *  Default constructor for DecisionTree with 3 parameters.
	 *  @param data the data for the root
	 *  @param leftTree the left tree for the left side
	 *  @param rightTree the right tree for the right side
	 */
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		this.root = new BinaryNode<>(data, leftTree.getRootNode(), rightTree.getRootNode());
	}
	/**
	 *  Setter method for binaryTree.
	 *  @param data the data for the root
	 *  @param leftTree the left tree for the left side
	 *  @param rightTree the right tree for the right side
	 */
	public void setTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		if(leftTree == null && rightTree == null){
			this.root = new BinaryNode<T>(data);
			return;
		}
		else if(rightTree == null){
			this.root = new BinaryNode<T>(data, leftTree.getRootNode(), null);
			return;
		}
		else if(leftTree == null){
			this.root = new BinaryNode<T>(data, null, rightTree.getRootNode());
			return;
		}
		else{
			this.root = new BinaryNode<T>(data, leftTree.getRootNode(), rightTree.getRootNode());
			return;
		}
	}
	/**
	 *  This method traverses the tree using Inorder traversal recursively.
	 */
	public void inorderTraversal(){
		//call private method for recursion
		traverse(this.root);
		return;
	}
	/**
	 *  This method is the actual traversing method.
	 *  @param current the currentNode we are visiting
	 */
	private void traverse(BinaryNode<T> current){
		if(current == null){
			throw new EmptyTreeException();
		}

		//recursive calls

		//left first
		traverse(current.getLeftChild());
		System.out.print(current.getData());
		System.out.print(" ");
		traverse(current.getRightChild());
	}

	//Interface override methods
	/**
	 *  Getter method for root's data.
	 *  @return returns root's data
	 */
	public T getRootData(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getData();
	}
	/**
	 *  Getter method for root node.
	 *  @return returns root node
	 */
	public BinaryNode<T> getRootNode(){
		return this.root;
	}
	/**
	 *  Setter method for root node.
	 *  @param newNode the new node for root
	 */
	public void setRootNode(BinaryNode<T> newNode){
		this.root = newNode;
	}
	/**
	 *  Getter method for height of the tree (method in binaryNode class).
	 *  @return returns an int for the height of the tree
	 */
	public int getHeight(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getHeight();
	}
	/**
	 *  Getter method for num of nodes in the tree (method in binaryNode class).
	 *  @return returns an int for the num of nodes
	 */
	public int getNumberOfNodes(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getNumberOfNodes();
	}
	/**
	 *  This method determines if the tree is empty or not.
	 *  @return returns true if root is null (empty), and false otherwise
	 */
	public boolean isEmpty(){
		if(this.root == null){
			return true;
		}
		return false;
	}
	/**
	 *  This method resets the root(sets it to null).
	 */
	public void clear(){
		this.root = null;
	}
}