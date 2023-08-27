public interface BinaryTreeInterface<T>{
	
	public T getRootData();

	public BinaryNode<T> getRootNode();

	public void setRootNode(BinaryNode<T> node);

	public int getHeight();

	public int getNumberOfNodes();

	public boolean isEmpty();

	public void clear();
}