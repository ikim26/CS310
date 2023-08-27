public class DecisionTree<T> extends BinaryTree<T> implements DecisionTreeInterface<T> {
	
	protected BinaryNode<T> currentNode;

	//constructors
	public DecisionTree(){
		this.currentNode = null;
	}
	public DecisionTree(T data){
		this.currentNode = new BinaryNode<T>(data);
	}

	//interface methods
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

	public void moveToNo(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		this.currentNode = currentNode.getLeftChild();
	}

	public void moveToYes(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		this.currentNode = currentNode.getRightChild();
	}

	public void resetCurrentNode(){
		this.currentNode = super.root;
	}

	public BinaryNode<T> getCurrentNode(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		return this.currentNode;
	}

	public T getCurrentData(){
		if(this.currentNode == null){
			throw new EmptyTreeException();
		}
		return this.currentNode.getData();
	}

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