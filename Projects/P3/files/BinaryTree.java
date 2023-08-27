public class BinaryTree<T> implements BinaryTreeInterface<T> {
	
	protected BinaryNode<T> root;

	public BinaryTree(){
		this.root = null;
	}
	public BinaryTree(T data){
		this.root = new BinaryNode<>(data);
	}
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		this.root = new BinaryNode<>(data, leftTree.getRootNode(), rightTree.getRootNode());
	}
	//setter for Tree
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
	//inorder traversal
	public void inorderTraversal(){
		Traverse(this.root);
		return;
	}
	private void Traverse(BinaryNode<T> current){
		if(current == null){
			throw new EmptyTreeException();
		}

		//recursive calls

		//left first
		Traverse(current.getLeftChild());
		System.out.print(current.getData());
		System.out.print(" ");
		Traverse(current.getRightChild());
	}

	//Interface override methods

	public T getRootData(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getData();
	}

	public BinaryNode<T> getRootNode(){
		return this.root;
	}

	public void setRootNode(BinaryNode<T> newNode){
		this.root = newNode;
	}

	public int getHeight(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getHeight();
	}

	public int getNumberOfNodes(){
		if(this.root == null){
			throw new EmptyTreeException();
		}
		return this.root.getNumberOfNodes();
	}

	public boolean isEmpty(){
		if(this.root == null){
			return true;
		}
		return false;
	}

	public void clear(){
		this.root = null;
	}
}