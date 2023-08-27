import java.util.Map;
import java.util.Set;

import java.util.Collection; //for returning in the values() function only

//If you uncomment the import to ArrayList below, the grader will manually
//look to make sure you are not using it anywhere else... if you use it
//somewhere else you will get 0pts on the entire project (not a joke).

//uncomment the line below ONLY if you are implementing values().
//import java.util.ArrayList; //for returning in the values() function only

/**
 *  This is my submission of the ThreeTenHashMap class.
 *  @author Isaac Kim G01201648
 *  @param <K> a generic type variable for keys
 *  @param <V> a generic type variable for values
 */
class ThreeTenHashMap<K,V> implements Map<K,V> {
	//********************************************************************************
	//   DO NOT EDIT ANYTHING IN THIS SECTION (except to add the JavaDocs)
	//********************************************************************************
	
	//you must use this storage for the hash table
	//and you may not alter this variable's name, type, etc.
	/**storage var.*/
	private Node<K,V>[] storage;
	
	//you must use to track the current number of elements
	//and you may not alter this variable's name, type, etc.
	/**number of elements.*/
	private int numElements = 0;
	
	//********************************************************************************
	//   YOUR CODE GOES IN THIS SECTION
	//********************************************************************************
	/**the og size.*/
	private int originalSize;

	/**
	 *  Default constructor for ThreeTenHashMap.
	 *  @param size the size for our hashmap
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashMap(int size) {
		//Create a hash table where the size of the storage is
		//the provided size (number of "slots" in the table)
		//You may assume size is >= 1
		storage = (Node<K,V>[]) new Node<?,?>[size];
		this.originalSize = size;
		this.numElements = 0;

		//Remember... if you want an array of ClassWithGeneric<V>, the following format ___SHOULD NOT___ be used:
		//         ClassWithGeneric<V>[] items = (ClassWithGeneric<V>[]) new Object[10];
		//instead, use this format:
		//         ClassWithGeneric<V>[] items = (ClassWithGeneric<V>[]) new ClassWithGeneric[10];
	}
	/**
	 *  This method resets hashmap.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		//the table should return to the original size it had
		//when constructed
		//O(1)
		this.storage = (Node<K,V>[]) new Node<?,?>[originalSize];
		this.numElements = 0;
	}
	/**
	 *  This method determines if our hashmap is empty or not.
	 *  @return returns true if empty, and false otherwise
	 */
	public boolean isEmpty() {
		//O(1)
		if(this.numElements == 0){
			return true;
		}
		return false;
	}
	/**
	 *  This method gets the number of slots in our hashmap.
	 *  @return returns int of number of slots
	 */
	public int getSlots() {
		//return how many "slots" are in the table
		//O(1)
		return (this.storage.length);
	}
	/**
	 *  This method gets the size of our hashmap.
	 *  @return returns int of size of hashmap
	 */
	public int size() {
		//return the number of elements in the table
		//O(1)
		return this.numElements;
	}
	/**
	 *  This method gets value of a given key.
	 *  @param key the key we are accessing
	 *  @return returns the value stored at that key
	 */
	public V get(Object key) {
		//Given a key, return the value from the table.
		
		//If the value is not in the table, return null.
		
		//Worst case: O(n), Average case: O(1)
		int index = hashingIndex(key);
		if(this.storage[index] != null){
			return this.storage[index].entry.value;
		}

		return null;
	}
	/**
	 *  This method gets all the keys in our hashmap.
	 *  @return returns a set of keys
	 */
	public Set<K> keySet() {
		//O(n+m) or better, where n is the size and m is the
		//number of slots
		
		//Hint: you aren't allowed to import
		//anything, but a ThreeTenHashSet is a Set
		Set<K> newSet = new ThreeTenHashSet<>();
		int i;
		Node<K,V> currentNode;
		for(i = 0; i < this.storage.length; i++){
			if(this.storage[i] == null){
				continue;
			}
			else{
				currentNode = this.storage[i];
				while(currentNode != null){
					newSet.add(currentNode.entry.key);
					currentNode = currentNode.next;
				}
			}
		}
		return newSet;
	}
	/**
	 *  This method removes a key value pair from our hashmap.
	 *  @param key the key we are removing
	 *  @return returns the value of the key that we removed
	 */
	public V remove(Object key) {
		//Remove the given key (and associated value)
		//from the table. Return the value removed.		
		//If the value is not in the table, return null.
		int index = hashingIndex(key);
		Node<K,V> currentNode;
		Node<K,V> temp;
		V ans;
		if(this.storage[index] == null){
			return null;
		}
		currentNode = this.storage[index];
		temp = null;
		while(currentNode != null){
			//if the keys match
			if(currentNode.entry.key == key || currentNode.entry.key.toString().equals(key.toString())){
				//if first one in hashmap
				//System.out.println("hi");
				if(temp == null){
					//System.out.println("hi");
					this.storage[index] = currentNode.next;
					ans = currentNode.entry.value;
					return ans;
				}
				//reassign pointers to remove
				temp.next = currentNode.next;
				ans = currentNode.entry.value;
				return ans;
			}
			//increment for loop
			temp = currentNode;
			currentNode = currentNode.next;
		}
			
		//Hint: Remember there are no tombstones for
		//separate chaining! Don't leave empty nodes!
		
		//Worst case: O(n), Average case: O(1)
		
		return null;
	}
	/**
	 *  This method inserts a key value pair using seperate chaining.
	 *  @param key the key we are inserting
	 *  @param value the value we are inserting
	 *  @return returns the value we inserted
	 */
	private V putNoExpand(K key, V value) {
		//Place value v at the location of key k.
		//Use separate chaining if that location is in use.
		//You may assume both k and v will not be null.
		//This method does NOT handle issues with the load,
		//that is handled by put(K,V) in the provided section
		//The return value is the same as specified by put()
		//(see the Map interface).
		int index = hashingIndex(key);

		//Hint 1: Make a TableEntry to store in storage
		//and use the absolute value of k.hashCode() for
		//the location of the entry.
		
		TableEntry<K,V> newTableEntry = new TableEntry<>(key, value);
		Node<K,V> newNode = new Node<>(newTableEntry, null);
		//Here's something you may want to know about Math's
		//absolute value method:
		//	"If the argument is equal to the value of Integer.MIN_VALUE,
		//  the most negative representable int value, the result is
		//  that same value, which is negative." -Oracle Docs
		
		//Hint 2: Remember that you're dealing with an array
		//of linked lists in this part of the project, not
		//an array of table entries.
		
		//System.out.println(this.storage.length);
		
		if(this.storage[index] == null){
			this.storage[index] = newNode;
			numElements++;
		}
		//If the key already exists in the table
		//replace the current value with v.
		if(this.storage[index].entry.key.equals(key)){
			V temp = this.storage[index].entry.value;
			this.storage[index].entry.value = temp;
			return temp;
		}

		//If the key does not exist in the table, add
		//the new node to the _end_ of the linked list.
		else{
			Node<K,V> currentNode;
			currentNode = this.storage[index];
			while(currentNode.next != null){
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
			numElements++;
		}
		

		//This method is used by the provided put() and
		//rehash() methods, so if those aren't working,
		//look here!
		
		//Worst case: O(n) where n is the number
		//of items in the list, NOT O(m) where m
		//is the number of slots, and NOT O(n+m)
		
		return null;
	}
	/** This method gets an index for storage.
	 *	@param key the key we are hashing
	 *  @return returns the array index for the key
	 */
	private int hashingIndex(Object key){
		return Math.abs((key.hashCode() % (this.storage.length)));
	}
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	/**
	 *  Main method for testing.
	 *  @param args command line arguments
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		ThreeTenHashMap<String,String> st1 = new ThreeTenHashMap<>(10);
		ThreeTenHashMap<String,Integer> st2 = new ThreeTenHashMap<>(5);
		
		st1.put("a","apple");
		st1.put("b","banana");
		st1.put("banana","b");
		st1.put("b","butter");
		
		//System.out.println(st1.toStringDebug());
		
		if(st1.toString().equals("a:apple\nbanana:b\nb:butter") && st1.toStringDebug().equals("[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: [a:apple]->[banana:b]->null\n[8]: [b:butter]->null\n[9]: null")) {
			System.out.println("Yay 1");
		}
		
		if(st1.getSlots() == 10 && st1.size() == 3 && st1.get("a").equals("apple")) {
			System.out.println("Yay 2");
		}
		
		st2.rehash(1);
		st2.put("a",1);
		st2.put("b",2);

		//System.out.println(st2.toString());
		//System.out.println(st2.toStringDebug());
		
		if(st2.toString().equals("b:2\na:1") && st2.toStringDebug().equals("[0]: [b:2]->null\n[1]: [a:1]->null")
			&& st2.put("e",3) == null && st2.put("y",4) == null &&
			st2.toString().equals("a:1\ne:3\ny:4\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: [a:1]->[e:3]->[y:4]->null\n[2]: [b:2]->null\n[3]: null")) {
			System.out.println("Yay 3");
		}
		//System.out.println(st2.toStringDebug());

		if(st2.remove("e").equals(3) && st2.rehash(8) == true &&
			st2.size() == 3 && st2.getSlots() == 8 &&
			st2.toString().equals("a:1\ny:4\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: [a:1]->[y:4]->null\n[2]: [b:2]->null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: null")) {
			System.out.println("Yay 4");
		}
		//System.out.println(st2.toStringDebug());

		ThreeTenHashMap<String,String> st3 = new ThreeTenHashMap<>(2);
		st3.put("a","a");
		st3.remove("a");

		//System.out.println(st3.toString());
		
		if(st3.toString().equals("") && st3.toStringDebug().equals("[0]: null\n[1]: null")) {
			st3.put("a","a");
			if(st3.toString().equals("a:a") && st3.toStringDebug().equals("[0]: null\n[1]: [a:a]->null")) {
				System.out.println("Yay 5");
			}
		}
		//System.out.println(st3.toStringDebug());
		//This is NOT all the testing you need... several methods are not
		//being tested here! Some method return types aren't being tested
		//either. You need to write your own tests!
		
		//Also, try this and see if it works:
		//ThreeTenHashMap<Integer,Integer> st4 = new ThreeTenHashMap<>(10);
		//st4.put(Integer.MIN_VALUE, Integer.MIN_VALUE);
	}
	
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write,
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//********************************************************************************
	/**
	 *  {@inheritDoc}
	 *  @return returns collection
	 */
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param m map
	 */
	public void	putAll(Map<? extends K,? extends V> m) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param value object
	 *  @return returns boolean
	 */
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @return set
	 */
	public Set<Map.Entry<K,V>> entrySet() {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param o object
	 *  @return returns boolean
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns int
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param key object
	 *  @return returns boolean
	 */
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will check these things to make sure they still work, so don't break them.
	//********************************************************************************
	
	//THIS CLASS IS PROVIDED, DO NOT CHANGE IT
	/**
	 *  Node Class for hashmap.
	 *  @author Not me
	 *  @param <K> a generic type variable for keys
	 *  @param <V> a generic type variable for values
	 */
	public static class Node<K,V> {
		/**entry var.*/
		public TableEntry<K,V> entry;
		/**next node poitner.*/
		public Node<K,V> next;
		/**
		 *  Default Constructor.
		 *  @param entry the entry in the node
		 */
		public Node(TableEntry<K,V> entry) {
			this.entry = entry;
		}
		/**
		 *  Default Constructor with 2 parameters.
		 *  @param entry the entry in the node
		 *  @param next the next node pointer
		 */
		public Node(TableEntry<K,V> entry, Node<K,V> next) {
			this(entry);
			this.next = next;
		}
		/**
		 *  toString method.
		 *  @return returns string of the node entry
		 */
		public String toString() {
			return "[" + entry.toString() + "]->";
		}
	}
	
	//THIS CLASS IS PROVIDED, DO NOT CHANGE IT
	/**
	 *  TableEntry Class for Node.
	 *  @author Not me
	 *  @param <K> a generic type variable for keys
	 *  @param <V> a generic type variable for values
	 */
	public static class TableEntry<K,V> {
		/**key var.*/
		public K key;
		/**value var.*/
		public V value;
		/**
		 *  Default Constructor.
		 *  @param key the key to be stored
		 *  @param value the value for the key
		 */
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 *  toString method.
		 *  @return returns string of the node entry
		 */
		public String toString() {
			return key.toString()+":"+value.toString();
		}
	}
	/**
	 *  This method gets the table entries and puts them into an array.
	 *  @return returns an array of the table entries
	 */
	public TableEntry[] toArray(){
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		TableEntry[] collection = new TableEntry[this.numElements];
		int index = 0;
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				Node<K,V> curr = storage[i];
				while(curr != null) {
					collection[index] = curr.entry;
					index++;
					curr = curr.next;
				}
			}
		}
		return collection;		
	}
	/**
	 *  toString method.
	 *  @return returns string entries
	 */
	public String toString() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			Node<K,V> curr = storage[i];
			if(curr == null) continue;
			
			while(curr != null) {
				s.append(curr.entry.toString());
				s.append("\n");
				curr = curr.next;
			}
		}
		return s.toString().trim();
	}
	/**
	 *  toString method for debugging.
	 *  @return returns string entries
	 */
	public String toStringDebug() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			Node<K,V> curr = storage[i];
			
			s.append("[" + i + "]: ");
			while(curr != null) {
				s.append(curr.toString());
				curr = curr.next;
			}
			s.append("null\n");
		}
		return s.toString().trim();
	}
	/**
	 *  This method rehashes our hashmap.
	 *  @param size the new size of our hashmap
	 *  @return returns true if resize is sucessful, and false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		if(size < 1) return false;
		
		Node<K,V>[] oldTable = storage;
		storage = (Node<K,V>[]) new Node[size];
		numElements = 0;
		
		for(Node<K,V> node : oldTable) {
			while(node != null) {
				putNoExpand(node.entry.key, node.entry.value);
				node = node.next;
			}
		}
		
		return true;
	}
	/**
	 *  This method puts key value pairs into our hashmap.
	 *  @param key the key we are putting into hashmap
	 *  @param value the value for the key
	 *  @return returns the value we put
	 */
	public V put(K key, V value) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		V ret = putNoExpand(key, value);
		while((numElements/(double)storage.length) >= 2) {
			rehash(storage.length*2);
		}
		return ret;
	}
}