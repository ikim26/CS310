/**
 *  This is my submission of the BinaryTreeInterface.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public interface BinaryTreeInterface<T> {
	/**
	 *  Getter method for root's data.
	 *  @return returns root's data
	 */
	public T getRootData();
	/**
	 *  Getter method for root node.
	 *  @return returns root node
	 */
	public BinaryNode<T> getRootNode();
	/**
	 *  Setter method for root node.
	 *  @param node the new node for root
	 */
	public void setRootNode(BinaryNode<T> node);
	/**
	 *  Getter method for height of the tree (method in binaryNode class).
	 *  @return returns an int for the height of the tree
	 */
	public int getHeight();
	/**
	 *  Getter method for num of nodes in the tree (method in binaryNode class).
	 *  @return returns an int for the num of nodes
	 */
	public int getNumberOfNodes();
	/**
	 *  This method determines if the tree is empty or not.
	 *  @return returns true if root is null (empty), and false otherwise
	 */
	public boolean isEmpty();
	/**
	 *  This method resets the root(sets it to null).
	 */
	public void clear();
}