/**
   A class that represents nodes in a binary tree.
   
   @author Isaac Kim G01201648
   @version 1.0
*/
public class AVLNode<T> extends BinaryNode<T> {
	//class vars
	int height;

	public AVLNode(){
		super();
		this.height = -1;
	}

	public AVLNode(T data){
		super(data);
		this.height = getHeight();
	}

	public AVLNode(T data, AVLNode<T> leftNode, AVLNode<T> rightNode){
		super(data, leftNode, rightNode);
		this.height = getHeight();
	}

	public void setLeftChild(AVLNode<T> leftNode){
		super.setLeftChild(leftNode);
	}

	public void setRightChild(AVLNode<T> rightNode){
		super.setRightChild(rightNode);
	}

	public int getHeight(){
		//update height
		this.height = super.getHeight();
		return super.getHeight();
	}
}