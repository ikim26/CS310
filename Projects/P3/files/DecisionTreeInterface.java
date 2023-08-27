public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T>{
	
	public boolean isAnswer();

	public void moveToNo();

	public void moveToYes();

	public void resetCurrentNode();

	public BinaryNode<T> getCurrentNode();

	public T getCurrentData();

	public void setResponses(T responseForNo, T responseForYes);
}