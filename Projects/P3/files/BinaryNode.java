public class BinaryNode<T>{

	protected BinaryNode<T> rightChild;
	protected BinaryNode<T> leftChild;
	protected T data;
	
	//constructors
	public BinaryNode(){
		this.rightChild = null;
		this.leftChild = null;
		this.data = null;
	}
	public BinaryNode(T data){
		this.rightChild  = null;
		this.leftChild = null;
		this.data = data;
	}
	public BinaryNode(T data, BinaryNode<T> leftNode, BinaryNode<T> rightNode){
		this.rightChild = rightNode;
		this.leftChild = leftNode;
		this.data = data;
	}

	//setters and getters for data
	public void setData(T newData){
		this.data = newData;
	}
	public T getData(){
		return this.data;
	}

	//setters and getters for left child
	public void setLeftChild(BinaryNode<T> leftNode){
		this.leftChild = leftNode;
	}
	public BinaryNode<T> getLeftChild(){
		return this.leftChild;
	}

	//setters and getters for right child
	public void setRightChild(BinaryNode<T> rightNode){
		this.rightChild = rightNode;
	}
	public BinaryNode<T> getRightChild(){
		return this.rightChild;
	}

	//methods that check if childs exist
	public boolean hasLeftChild(){
		if(this.leftChild != null){
			return true;
		}
		return false;
	}
	public boolean hasRightChild(){
		if(this.rightChild != null){
			return true;
		}
		return false;
	}

	//method to check if node is a leaf
	public boolean isLeaf(){
		if(this.leftChild == null && this.rightChild == null){
			return true;
		}
		return false;
	}

	//get height of tree at current node
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