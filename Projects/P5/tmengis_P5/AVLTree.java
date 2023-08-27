/**
   A class that represents nodes in a binary tree.
   
   @author Isaac Kim G01201648
   @version 1.0
*/
public class AVLTree<T> extends BinarySearchTree<T> {
	//class vars
	//private AVLNode<T> rootNode;


	public AVLTree(){
		super();
	}

	public AVLTree(T rootEntry){
		//make a node of type AVLNode
		AVLNode<T> newRoot = new AVLNode<T>(rootEntry);
		//type cast it to binary node so we can later down cast it again
		setRootNode((BinaryNode<T>) newRoot);
	}

	public T add(T newEntry){
		//if tree is empty
		if(isEmpty()){
			//set root node to type of AVLNode
			setRootNode(new AVLNode<>(newEntry));
		}
		//call parent add method
		T data = super.add(newEntry);

		AVLNode<T> rootNode = (AVLNode<T>) getRootNode();
		//update height of root
		rootNode.getHeight();

		//balancing
		//data is null if a new node is added
		if(data == null){
			rebalance(rootNode, newEntry);
		}
		//if data is not null, then we don't need to rebalance as
		//nothing was added (it was replaced instead)

		return data;
	}

	public T remove(T entry){
		T data = super.remove(entry);

		AVLNode<T> rootNode = (AVLNode<T>) getRootNode();
		rebalance(rootNode);

	}

	private AVLNode<T> rotateRight(AVLNode<T> subRoot){
		//we dont need null checks because we wont call this method
		//if there are no nodes that violate the avl tree balance

		//assign vars for reference
		AVLNode leftNode = subRoot.getLeftChild();
		AVLNode leftRight;
		//null check for right child of left node
		if(leftNode.hasRightChild() == false){
			leftRight = null;
		}
		else{
			leftRight = leftNode.getRightChild();
		}

		//rotate:
		//set left childs right child to root
		leftNode.setRightChild(subRoot);
		//set subroots(now right child) left child to lefts old right child
		subroot.setLeftChild(leftRight);

		//change heights accordingly
		leftNode.getHeight();
		subroot.getHeight();

		//return the new subroot
		return leftNode;
	}

	private AVLNode<T> rotateLeft(AVLNode<T> subRoot){
		//we dont need null checks because we wont call this method
		//if there are no nodes that violate the avl tree balance

		//assign vars for reference
		AVLNode rightNode = subRoot.getRightChild();
		AVLNode rightLeft;
		//null check for left child of right node
		if(rightNode.hasLeftChild() == false){
			rightLeft = null;
		}
		else{
			rightLeft = rightNode.getLeftChild();
		}

		//rotate:
		//set right childs left child to root
		rightNode.setLeftChild(subRoot);
		//set subroots(now left child) right child to rights old left child
		subroot.setRightChild(rightLeft);

		//change heights accordingly
		rightNode.getHeight();
		subroot.getHeight();

		//return the new subroot
		return rightNode;
		
	}

	private void rebalance(AVLNode<T> subRoot, T value){
		//compare < 0 if value is smaller than subroot
		//compare > 0 if value is greater than subroot
		int compare = value.compareTo(subRoot.getData());

		//iterating through tree recursively
		if(compare < 0){
			if(subRoot.hasLeftChild()){
				rebalance(subRoot.getLeftChild(), value);
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		else if(compare > 0){
			if(subRoot.hasRightChild()){
				rebalance(subRoot.getRightChild(), value);
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		else{
			//compare is 0 (we reached the node we added)
			return;
		}

		//recursive checks for balance:

		//ROTATIONS:

		//if LEFT subtree has more nodes
		if(getHeightDifference(subRoot) > 1){
			//insertion was either LL or LR
			//if LL
			if(compare < 0){
				rotateRight(subRoot);
			}
			else{
				//else it was LR
				subRoot.setLeftChild(rotateLeft(subRoot.getLeftChild()));
				rotateRight(subRoot);
			}

		}
		//if RIGHT subtree has more nodes
		else if(getHeightDifference(subRoot) < -1){
			//insertion was either RR or RL
			//if RR
			if(compare > 0){
				rotateLeft(subRoot);
			}
			else{
				//else it was RL
				subRoot.setRightChild(rotateRight(subRoot.getRightChild()));
				rotateLeft(subRoot);
			}
		}
		//else it is balanced
		else{
			return;
		}
	}

	private int getHeightDifference(AVLNode n){
		int left;
		int right;

		if(n == null){
			return 0;
		}
		if(n.hasLeftChild() == false){
			left = 0;
		}
		else{
			left = n.getLeftChild.getHeight();
		}

		if(n.hasRightChild() == false){
			right = 0;
		}
		else{
			right = n.getRightChild().getHeight();
		}

		return left - right;
		//returns positive if left has more nodes (violates property)
		//returns negative if right has more nodes (violates property)
	}
}