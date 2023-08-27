/**
 *  This is my submission of the UniquePairList class.
 *  @author Isaac Kim G01201648
 *  @param <K> a generic type variable for keys
 *  @param <V> a generic type variable for values
 */
class UniquePairList<K,V> {
	/**
	 *  Class for pairs in UniquePairList.
	 *  @param <K> a generic type variable for keys
	 *  @param <V> a generic type variable for values
	 */
	private static class Pair<K,V> {

		//initializing class vars
		/**The key of the pair.*/
		protected K key;
		/**The value of the pair.*/
		protected V value;
		/**
		 *  Default Constructor.
		 *  @param key the key for the pair
		 *  @param value the value for the pair
		 */
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 *  This method checks if 2 pairs are equal or not.
		 *  @param o object that we are comparing
		 *  @return returns true if they are equal, and false otherwise
		 */
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			//Two pairs are equal ONLY if their KEYS are
			//equal. Values don't matter for equality.
			//O(1)
			Pair p = (Pair) o;
			if(this.key.equals(p.getKey())){
				return true;
			}
			return false;
		}
		/**
		 *  toString method for pair.
		 *  @return returns string of pair
		 */
		public String toString() {
			//this method is done for you
			return "<" + getKey() + "," + getValue() + ">";
		}
		/**
		 *  Getter method for key.
		 *  @return returns the key of the pair
		 */
		public K getKey() {
			//returns the key from the pair
			//O(1)
			return this.key;
		}
		/**
		 *  Getter method for value.
		 *  @return returns the value of the pair
		 */
		public V getValue() {
			//returns the value from the pair
			//O(1)
			return this.value;
		}
	}
	
	//example test code... edit this as much as you want!
	/**
	 *  Main method.
	 *  @param args command line arguments
	 */
	public static void main(String[] args) {
		Pair<String,Integer> p1 = new Pair<>("Fred", 1);
		Pair<String,Integer> p2 = new Pair<>("Alex", 1);
		Pair<String,Integer> p3 = new Pair<>("Fred", 2);
		
		if(p1.getKey().equals("Fred") && p1.getValue() == 1 && p1.equals(p3)) {
			System.out.println("Yay 1");
		}
		
		if(!p1.equals(p2)) {
			System.out.println("Yay 2");
		}
		
		//this is actually a test of UniqueList, not UniquePairList
		UniqueList<Pair<String,Integer>> set = new UniqueList<>();
		set.append(p1);
		//System.out.println(p1);
		
		//get the value from the set that is _equal to_ p3 (in this case, p1)
		Pair<String,Integer> p1fromSet = set.get(p3);
		if(p1fromSet.getValue() == 1) {
			System.out.println("Yay 3");
		}
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//********************* EXCEPT TO ADD JAVADOCS ********************/
	//*****************************************************************/
	/**
	 *  The set for all the actions below.
	 */
	private UniqueList<Pair<K,V>> set = new UniqueList<>();
	/**
	 *  This method attempts to add a value to the unique pair list.
	 *  @param key the key of the pair we are trying to add
	 *  @param value the value of the pair we are trying to add
	 *  @return returns true if successfully added and false otherwise
	 */
	public boolean append(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		return set.append(pair);
	}
	/**
	 *  This method attempts to update a pair in the unique pair list.
	 *  @param key the key of the pair we are trying to update
	 *  @param value the value of the pair we are trying to update
	 *  @return returns true if successfully updated and false otherwise
	 */
	public boolean update(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		if(!remove(key)) {
			return false;
		}
		return set.append(pair);
	}
	/**
	 *  This method attempts to a pair with a certain key from the unique pair list.
	 *  @param key the key of the pair we are trying to remove
	 *  @return returns true if successfully added and false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.remove(pair);
	}
	/**
	 *  This method gets the value of a pair with a certain key from the unique pair list.
	 *  @param key the key of the pair we are trying to get the value of
	 *  @return returns the value of the pair with the corresponding key
	 */
	@SuppressWarnings("unchecked")
	public V getValue(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.get(pair).getValue();
	}
	/**
	 *  This method gets a uniquelist of all the keys of pairs.
	 *  @return returns a uniquelist of keys for all the pairs
	 */
	public UniqueList<K> getKeys() {
		UniqueList<K> keySet = new UniqueList<>();
		for(Pair<K,V> p : set) {
			keySet.append(p.getKey());
		}
		return keySet.clone();
	}
	/**
	 *  This method returns the size of the unique pair list.
	 *  @return returns an int for the size of the list
	 */
	public int size() {
		return set.size();
	}
}