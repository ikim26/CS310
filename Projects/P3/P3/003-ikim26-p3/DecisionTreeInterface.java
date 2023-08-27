/**
 *  This is my submission of the DecisionTreeInterface.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T> {
	/**
	 *  This method determines if a node is the "last" answer.
	 *  @return returns true if node is an answer, and false otherwise
	 */
	public boolean isAnswer();
	/**
	 *  This method moves the currentNode to the left(no).
	 */
	public void moveToNo();
	/**
	 *  This method moves the currentNode to the right(yes).
	 */
	public void moveToYes();
	/**
	 *  This method resets currentNode to root.
	 */
	public void resetCurrentNode();
	/**
	 *  Getter method for currentNode.
	 *  @return returns the currentNode
	 */
	public BinaryNode<T> getCurrentNode();
	/**
	 *  Getter method for currentNode's data.
	 *  @return returns the currentNode data
	 */
	public T getCurrentData();
	/**
	 *  This method updates the responses (left and right child).
	 *  @param responseForNo the data for left child
	 *  @param responseForYes the data for right child
	 */
	public void setResponses(T responseForNo, T responseForYes);
}