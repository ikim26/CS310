import java.util.Iterator;

/**
 *  This is my submission of the UniqueList class.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
class UniqueList<T> implements Iterable<T> {

	//initializing class variables
	/**The head of the unique list.*/
	private Node head = null;
	/**The tail of the unique list.*/
	private Node tail = null;
	/**The number of items in the list.*/
	protected int numOfItems;

	//my own private class for node
	/**
	 *  Class for nodes in UniqueList.
	 */
	private class Node {

		//initializing node vars
		/**The data that the node holds.*/
		private T data;
		/**The next node pointer.*/
		protected Node nextNode;

		/**
		 *  Default Constructor.
		 *  @param data data that the node holds
		 */
		public Node(T data){
			this.data = data;
			this.nextNode = null;
		}
		/**
		 *  Getter method for data.
		 *  @return returns the data
		 */
		public T getData(){
			return this.data;
		}
		/**
		 *  Setter method for data.
		 *  @param data data that we set in the node
		 */
		public void setData(T data){
			this.data = data;
		}
		/**
		 *  Getter method for next Node.
		 *  @return returns the next node
		 */
		public Node getNextNode(){
			return this.nextNode;
		}
		/**
		 *  Setter method for next Node.
		 *  @param nextNode the next Node we set next to
		 */
		public void setNextNode(Node nextNode){
			this.nextNode = nextNode;
		}
		/**
		 *  toString method for node for comparison.
		 *  @return returns a string format of data
		 */
		public String toString(){
			return this.data.toString();
		}
	}
	/**
	 *  This method attempts to add a value to the unique list.
	 *  @param value the value we are trying to add
	 *  @return returns true if successfully added and false otherwise
	 */
	public boolean append(T value) {
		//adds an item to the list at the end
		
		//returns false if the value can not be added
		//(i.e. the value already exists in the list)
		
		//O(n) worst case, where n = the number of items
		//because you need to check for duplicates!

		Node currentNode;

		//if empty list
		if(this.head == null){
			currentNode = new Node(value);
			this.head = currentNode;
			this.tail = currentNode;
			this.numOfItems++;
			return true;
		}
		//if list is not empty
		else{
			currentNode = this.head;
			while(currentNode != null){
				//check if data is the same
				if(currentNode.getData().equals(value)){
					return false;
				}
				//check for increment
				if(currentNode.getNextNode() == null){
					break;
				}
				//increment for loop
				currentNode = currentNode.getNextNode();
			}

			//if we reach here, then we can safely add to the list
			Node newNode = new Node(value);
			currentNode.setNextNode(newNode);
			this.tail = newNode;
			this.numOfItems++;
			
			return true;
		}
	}
	/**
	 *  This method attempts to remove a value from the unique list.
	 *  @param value the value we are trying to remove
	 *  @return returns true if successfully removed and false otherwise
	 */
	public boolean remove(T value) {
		//remove a value from the list
		
		//return false if the item could not be found
		//return true if you remove the item
		
		//O(n) worst case, where n = the number of items
		Node currentNode;
		Node prev = null;
		currentNode = this.head;
		while(currentNode != null){
			//if node is head
			if(currentNode == head && currentNode.getData().equals(value)){
				//remove by setting head to next node
				this.head = currentNode.getNextNode();
				this.numOfItems--;
				return true;
			}
			//if it is not head
			else if(currentNode.getData() == value){
				//remove by setting previous next to current next
				prev.setNextNode(currentNode.getNextNode());
				this.numOfItems--;
				return true;
			}
			prev = currentNode;
			currentNode = currentNode.getNextNode();
		}
		//if we reach here, we never removed anything
		return false;
	}
	/**
	 *  This method attempts to retrieve a value from the unique list.
	 *  @param value the value we are looking for
	 *  @return returns the data from the list that we are looking for and null if it doesn't find it
	 */
	@SuppressWarnings("unchecked")
	public T get(T value) {
		//return null if the item could not be found
		//return the item FROM THE LIST if it was found
		//(do not return the parameter, while the value
		//is "equal" they may not be the same in computer
		//memory... review the difference between
		//.equals() and == from CS211)
		
		//O(n) worst case, where n = the number of items
		Node currentNode;
		currentNode = this.head;
		while(currentNode != null){
			//if we find the value
			if((currentNode.getData()).equals(value)){
				return currentNode.getData();
			}
			
			currentNode = currentNode.getNextNode();
		}
		//if we reach here, we never found the value
		return null;
	}
	/**
	 *  This method checks if a value is in the unique list.
	 *  @param value the value we are looking for
	 *  @return returns true if value is found and false otherwise
	 */
	public boolean contains(T value) {
		//return true if the item can be found in the
		//list, reuse code from get() to implement this
		//method
		
		//O(n) worst case, where n = the number of items
		Node currentNode;
		currentNode = this.head;
		while(currentNode != null){
			//if we find the value
			if(currentNode.getData().equals(value)){
				return true;
			}
			currentNode = currentNode.getNextNode();
		}
		//if we reach here, we never found the value
		return false;
	}
	/**
	 *  This method gets the current size of unique list.
	 *  @return returns an int of the size of the list
	 */
	public int size() {
		//return the number of items in the set
		//O(1)
		return this.numOfItems;
	}
	/**
	 *  This method makes a copy/clone of unique list.
	 *  @return returns the copy of UniqueList
	 */
	public UniqueList<T> clone() {
		//make a copy of the UniqueList such that this is true:
		//	if s1 is a simple set containing {1, 2, 3} and s2 is a clone of s1, then:
		//	s1 != s2 BUT there exist three objects in s1 and s2 which are ==
		//see main method tests for example
		//O(n)
		Node currentNode = this.head;

		UniqueList<T> clone = new UniqueList<T>();

		while(currentNode != null){
			Node newNode = new Node(currentNode.getData());
			clone.append(newNode.getData());
			currentNode = currentNode.getNextNode();
		}

		return clone;
	}
	/**
	 *  Iterator method.
	 *  @return returns iterator that traverses the list
	 */
	public Iterator<T> iterator() {
		//return an iterator over the list, returning the first item
		//through the last item (in that order).
		
		Node temp = this.head;
		Iterator<T> uniqueIterator = new Iterator<T>() {
			private Node currentNode = temp;

			@Override
			public T next(){
				Node t = currentNode;
				currentNode = currentNode.getNextNode();
				return t.getData();
			}
			@Override
			public boolean hasNext(){
				if(currentNode != null){
					return true;
				}
				return false;
			}
		};
		//also, you only need to implement next() and
		//hasNext() for this iterator, previous(), add(), etc.
		//are all optional on this assignment (implement them
		//if you want to use them in your code).
		return uniqueIterator;
	}
	
	//example test code... edit this as much as you want!
	/**
	 *  Main method.
	 *  @param args command line arguments
	 */
	public static void main(String[] args) {
		UniqueList<String> names = new UniqueList<>();
		
		if(names.append("Fred") && names.append("Alex") && !names.append("Fred")) {
			System.out.println("Yay 0");
		}
		
		if(names.size() == 2 && names.contains("Fred") && names.get("Alex").equals("Alex")) {
			System.out.println("Yay 1");
		}
		
		if(names.remove("Alex") && names.size() == 1 && names.get("Alex") == null) {
			System.out.println("Yay 2");
		}
		
		boolean hasEverything = false;
		for(String name : names) {
			if(hasEverything) {
				hasEverything = false;
				break;
			}
			
			hasEverything = name.equals("Fred");
		}
		if(hasEverything) {
			System.out.println("Yay 3");
		}
		/**
		 *  Class for cats.
		 */
		class Cat {
			String name;
			/**
	 		 *  Constructor for Cat class.
			 *  @param name name for cat
			 */
			public Cat(String name) { this.name = name; }
			/**
	 		 *  This method checks if 2 cats are the same.
			 *  @param o car we are comparing
			 *  @return returns true if they are equal, and false otherwise
			 */
			public boolean equals(Object o) {
				if(o instanceof Cat) {
					Cat c = (Cat)o;
					return c.name.equals(this.name);
				}
				return false;
			}
		}
		
		UniqueList<Cat> catSet1 = new UniqueList<>();
		catSet1.append(new Cat("Sammy"));
		catSet1.append(new Cat("Grouchy"));
		UniqueList<Cat> catSet2 = catSet1.clone();
		if(catSet1 != catSet2 && catSet1.size() == catSet2.size()) {
			int matched = 0;
			for(Cat c : catSet1) {
				if(catSet2.get(c) == c) matched++;
			}
			if(matched == 2) {
				System.out.println("Yay 4");
			}
		}
	}
}