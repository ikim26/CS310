import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

//You need to add javadocs to this class.
//You need to submit this file for grading.
//If you don't submit this for grading we will use
//a vanialla version which doesn't have javadocs.
//This will mean that your code won't pass the style checker.

//Remember that for interface methods with existing documentation
//(such as the java.util.Set interface), the documentation is already
//written, you just need to resuse that (don't copy-and-paste it,
//use inheritdoc).

/**
 *  This is my submission of the ThreeTenHashSet class.
 *  @author Isaac Kim G01201648
 *  @param <E> a generic type variable for storage
 */
class ThreeTenHashSet<E> implements Set<E> {
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write.
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//********************************************************************************
	
	/**
	 *  {@inheritDoc}
	 *  @param c collection param
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * {@inheritDoc}
	 * @param c collection param
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param a array
	 *  @return returns array
	 */
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param c collection param
	 *  @return returns boolean
	 */
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 *  {@inheritDoc}
	 *  @param c collection param
	 *  @return returns boolean
	 */
	public boolean containsAll(Collection<?> c) {
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
	
	//
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will grade these methods to make sure they still work, so don't break them.
	//
	/** 
	 *  Storage var.
	 */
	private ThreeTenHashMap<E,E> storage = new ThreeTenHashMap<>(5);
	/**
	 *  {@inheritDoc}
	 *  @param e edge to add
	 *  @return returns boolean
	 */
	public boolean add(E e) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.put(e, e) == null;
	}
	/**
	 *  {@inheritDoc}
	 */
	public void clear() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		storage = new ThreeTenHashMap<>(5);
	}
	/**
	 *  {@inheritDoc}
	 *  @param o object
	 *  @return returns boolean
	 */
	public boolean contains(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.get(o) != null;
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns boolean
	 */
	public boolean isEmpty() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return size() == 0;
	}
	/**
	 *  {@inheritDoc}
	 *  @param o object
	 *  @return returns boolean
	 */
	public boolean remove(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.remove(o) != null;
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns int
	 */
	public int size() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.size();
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns object array
	 */
	public Object[] toArray() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		ThreeTenHashMap.TableEntry[] arr = storage.toArray();
		Object[] ret = new Object[arr.length];
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i].key;
		}
		return ret;
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns string
	 */
	public String toString(){
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString();
	}
	/**
	 *  {@inheritDoc}
	 *  @return returns iterator
	 */
	public Iterator<E> iterator() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return new Iterator<E>() {
			private Object[] values = toArray();
			private int currentLoc = 0;
			
			@SuppressWarnings("unchecked")
			public E next() {
				return (E) values[currentLoc++];
			}
			
			public boolean hasNext() {
				return currentLoc != values.length;
			}
		};
	}
}