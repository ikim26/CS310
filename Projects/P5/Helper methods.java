private T addAVLNode(AVLNode<T> subRoot, T newEntry){
		T data = null;
		T rootData = subRoot.getData();

		//if entry is already in tree, replace and return old value
		if(newEntry.compareTo(rootData) == 0){
			data = rootData;
			setRootData(newEntry);
		}
		//if entry is less than root (left subtree)
		else if(newEntry.compareTo(rootData) < 0){
			//if there is another left child, call add recursively for left child
			if(subRoot.hasLeftChild() == true){
				data = addAVLNode((AVLNode<T>)subRoot.getLeftChild(), newEntry);
			}
			//if there is no left child, set left child to entry
			else{
				subRoot.setLeftChild(new AVLNode<>(newEntry));
			}
		}
		//if entry is not less and not equal to, it has to be greater (right subtree)
		else{
			//if there is another right child, call add recursively for right child
			if(subRoot.hasRightChild() == true){
				data = addAVLNode((AVLNode<T>)subRoot.getRightChild(), newEntry);
			}
			//if there is no right child, set right child to entry
			else{
				subRoot.setRightChild(new AVLNode<>(newEntry));
			}
		}
		//return null or the data of the node that we replaced
		return data;
	}

		//TESTING CODE FROM INTERNET
	//-----------------------------------------------
	public static void preOrder(AVLNode<Integer> node) { 
		//preorder is root, left, right
        if (node != null) { 
            System.out.print(node.getData() + " "); 
            preOrder((AVLNode<Integer>)node.getLeftChild()); 
            preOrder((AVLNode<Integer>)node.getRightChild()); 
        } 
    } 
  
    public static void main(String[] args) { 
        AVLTree<Integer> tree = new AVLTree<>(); 
  
        /* Constructing tree given in the above figure */
        tree.add(10); 
        tree.add(20); 
        tree.add(30); 
        tree.add(40); 
        tree.add(50); 
        tree.add(25); 
        tree.add(15);
        //tree.remove(30);

        /* The constructed AVL Tree would be 
             30 
            /  \ 
          20   40 
         /  \     \ 
        10  25    50 
        */
        System.out.println("Preorder traversal" + 
                        " of constructed tree is : "); 
        AVLNode<Integer> newNode = (AVLNode<Integer>) tree.getRootNode();
        preOrder(newNode);
    } 