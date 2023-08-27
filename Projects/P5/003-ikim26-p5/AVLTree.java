/**
   A class that represents nodes in a binary tree.
   
   @author Isaac Kim G01201648
   @version 1.0
*/
public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {
	//class vars
	//private AVLNode<T> rootNode;

	public AVLTree(){
		AVLNode<T> newRoot = null;
		setRootNode(newRoot);
	}

	public AVLTree(T rootEntry){
		//make a node of type AVLNode
		AVLNode<T> newRoot = new AVLNode<T>(rootEntry);
		//type cast it to binary node so we can later down cast it again
		setRootNode(newRoot);
	}

	public T add(T newEntry){
		//intiialize return value to null
		T data = null;
		//if tree is empty
		if(isEmpty()){
			//set root node to type of AVLNode
			setRootNode(new AVLNode<T>(newEntry));
		}
		//call parent add method
		else{
			AVLNode<T> currentNode = (AVLNode<T>)getRootNode();

			//keep traversing through tree until we reach a leaf
			while(currentNode != null){
				//if entry is same value, we replace and return old value
				if(newEntry.compareTo(currentNode.getData()) == 0){
					//assign data to old value
					data = currentNode.getData();
					currentNode.setData(newEntry);
					break;
				}
				//if entry is less than root (left subtree)
				else if(newEntry.compareTo(currentNode.getData()) < 0){
					//if value is less than currentNode value (in left subtree)
					if(currentNode.hasLeftChild() == true){
						currentNode = (AVLNode<T>)currentNode.getLeftChild();
					}
					//if there is no left child, set left child to entry
					else{
						currentNode.setLeftChild(new AVLNode<>(newEntry));
						break;
					}
				}
				//if entry is not less and not equal to, it has to be greater (right subtree)
				else{
					//if value is greater than currentNode value (in right subtree)
					if(currentNode.hasRightChild() == true){
						currentNode = (AVLNode<T>)currentNode.getRightChild();
					}
					//if there is no right child, set right child to entry
					else{
						currentNode.setRightChild(new AVLNode<>(newEntry));
						break;
					}
				}
			}
		}

		AVLNode<T> rootNode = (AVLNode<T>) getRootNode();
		//update height of root
		rootNode.getHeight();

		//balancing
		//if data is not null, we replaced, so no need to rebalance
		//System.out.println(data);
		if(data == null){
			//System.out.println("called rebalance");
			setRootNode(rebalanceAdd(rootNode, newEntry));
		}
		//debugging print statements
		//System.out.print("Current Root: ");
		//System.out.println(getRootData());

		//if data is not null, then we don't need to rebalance as
		//nothing was added (it was replaced instead)

		return data;
	}

	public T remove(T entry){
		T data = super.remove(entry);

		AVLNode<T> rootNode = (AVLNode<T>) getRootNode();

		//update height
		rootNode.getHeight();
		//if data is null, nothing was removed, so no need to rebalance
		if(data != null){
			rebalanceRemove(rootNode, data);
		}
		
		return data;
	}

	private AVLNode<T> rotateRight(AVLNode<T> subRoot){
		//we dont need null checks because we wont call this method
		//if there are no nodes that violate the avl tree balance

		//assign vars for reference
		AVLNode<T> leftNode = (AVLNode<T>) subRoot.getLeftChild();
		AVLNode<T> leftRight;
		//null check for right child of left node
		if(leftNode.hasRightChild() == false){
			leftRight = null;
		}
		else{
			leftRight = (AVLNode<T>) leftNode.getRightChild();
		}

		//rotate:
		//set left childs right child to root
		leftNode.setRightChild(subRoot);
		//set subroots(now right child) left child to lefts old right child
		subRoot.setLeftChild(leftRight);

		//change heights accordingly
		leftNode.getHeight();
		subRoot.getHeight();

		//debug print statements
		//System.out.println("rotated right");

		//return the new subroot
		return leftNode;
	}

	private AVLNode<T> rotateLeft(AVLNode<T> subRoot){
		//we dont need null checks because we wont call this method
		//if there are no nodes that violate the avl tree balance

		//assign vars for reference
		AVLNode<T> rightNode = (AVLNode<T>) subRoot.getRightChild();
		AVLNode<T> rightLeft;
		//null check for left child of right node
		if(rightNode.hasLeftChild() == false){
			rightLeft = null;
		}
		else{
			rightLeft = (AVLNode<T>) rightNode.getLeftChild();
		}

		//rotate:
		//set right childs left child to root
		rightNode.setLeftChild(subRoot);
		//set subroots(now left child) right child to rights old left child
		subRoot.setRightChild(rightLeft);

		//change heights accordingly
		rightNode.getHeight();
		subRoot.getHeight();

		//debug print statements
		//System.out.println("rotated left");
		//System.out.println(getRootData());

		//return the new subroot
		return rightNode;
		
	}
	//rebalancing after we add a node
	private AVLNode<T> rebalanceAdd(AVLNode<T> subRoot, T value){
		//compare < 0 if value is smaller than subroot
		//compare > 0 if value is greater than subroot
		int compare = value.compareTo(subRoot.getData());

		//null check
		if(subRoot == null){
			return null;
		}

		//iterating through tree recursively from root
		if(compare < 0){
			if(subRoot.hasLeftChild()){
				subRoot.setLeftChild(rebalanceAdd((AVLNode<T>)subRoot.getLeftChild(), value));
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		else if(compare > 0){
			if(subRoot.hasRightChild()){
				subRoot.setRightChild(rebalanceAdd((AVLNode<T>)subRoot.getRightChild(), value));
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		//if we reach here without proc'ing the above if statements,
		//compare is 0 (we reached the node we added)

		//recursive checks for balance:

		//ROTATIONS:

		//debugging print statements
		//System.out.println(getHeightDifference(subRoot));

		//if LEFT subtree has more nodes
		if(getHeightDifference(subRoot) > 1){
			//insertion was either LL or LR
			//if LL (left most is least value)
			if(compare < 0 && value.compareTo(subRoot.getLeftChild().getData()) < 0){
				return rotateRight(subRoot);
			}
			else{
				//else it was LR
				subRoot.setLeftChild(rotateLeft((AVLNode<T>)subRoot.getLeftChild()));
				return rotateRight(subRoot);
			}

		}
		//if RIGHT subtree has more nodes
		else if(getHeightDifference(subRoot) < -1){
			//insertion was either RR or RL
			//if RR (right most is greatest value)
			if(compare > 0 && value.compareTo(subRoot.getRightChild().getData()) > 0) {
				return rotateLeft(subRoot);
			}
			else{
				//else it was RL
				subRoot.setRightChild(rotateRight((AVLNode<T>)subRoot.getRightChild()));
				return rotateLeft(subRoot);
			}
		}
		//else it is balanced
		else{
			return subRoot;
		}
	}
	//rebalancing after we remove a node
	private AVLNode<T> rebalanceRemove(AVLNode<T> subRoot, T value){
		//null check
		if(subRoot == null){
			return null;
		}

		//compare < 0 if value is smaller than subroot
		//compare > 0 if value is greater than subroot
		int compare = value.compareTo(subRoot.getData());

		//iterating through tree recursively from root
		if(compare < 0){
			if(subRoot.hasLeftChild()){
				subRoot.setLeftChild(rebalanceRemove((AVLNode<T>)subRoot.getLeftChild(), value));
			}
			//if we reach here, then we are where we removed from
			else{
				//treat it as if we are adding since we need to traverse to find any
				//potential imbalance
				subRoot.setRightChild(rebalanceRemove((AVLNode<T>)subRoot.getRightChild(), value));
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		else if(compare > 0){
			if(subRoot.hasRightChild()){
				subRoot.setRightChild(rebalanceRemove((AVLNode<T>)subRoot.getRightChild(), value));
			}
			//if we reach here, then we are where we removed from
			else{
				//same reasoning as above else block
				subRoot.setLeftChild(rebalanceRemove((AVLNode<T>)subRoot.getLeftChild(), value));
			}
			//if we are at a leaf (no right or left), we do nothing and continue
		}
		//if we reach here without proc'ing the above if statements,
		//compare is 0 (we reached the node we added)

		//recursive checks for balance:

		//ROTATIONS:

		//debugging print statements
		//System.out.println(getHeightDifference(subRoot));

		//if LEFT subtree has more nodes
		if(getHeightDifference(subRoot) > 1){
			//removal was either LL or LR
			//if LR (left most is least value)
			if(compare > 0){
				return rotateRight(subRoot);
			}
			else{
				//else it was LL
				subRoot.setLeftChild(rotateLeft((AVLNode<T>)subRoot.getLeftChild()));
				return rotateRight(subRoot);
			}

		}
		//if RIGHT subtree has more nodes
		else if(getHeightDifference(subRoot) < -1){
			//removal was either RR or RL
			//if RL (right most is greatest value)
			if(compare < 0) {
				return rotateLeft(subRoot);
			}
			else{
				//else it was RR
				subRoot.setRightChild(rotateRight((AVLNode<T>)subRoot.getRightChild()));
				return rotateLeft(subRoot);
			}
		}
		//else it is balanced
		else{
			return subRoot;
		}
	}
	private int getHeightDifference(AVLNode<T> n){
		int left;
		int right;

		if(n == null){
			return 0;
		}
		//left child height
		if(n.hasLeftChild() == false){
			left = 0;
		}
		else{
			left = n.getLeftChild().getHeight() + 1;
		}
		//right child height
		if(n.hasRightChild() == false){
			right = 0;
		}
		else{
			right = n.getRightChild().getHeight() + 1;
		}

		return left - right;
		//returns positive if left has more nodes (violates property)
		//returns negative if right has more nodes (violates property)
	}
}