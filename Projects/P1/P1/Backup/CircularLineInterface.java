/**
 *  This is my submission of the CircularLineInterface.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public interface CircularLineInterface<T> {
	/**
	 *  This method adds a new data to the waiting line. 
	 *	If the waiting line is full, it will double the size to accommodate the new data.
	 *  @param newData data to store in array
	 */
	public void insert(T newData);

	/**
	 *  This method removes and returns the first data in the waiting line.
	 *  @return returns removed element (element that has been in line longest)
	 */
	public T remove();

	/**
	 *  This method removes all elements in the waiting line. 
	 *  It throws NoElementException, if the waiting line is empty.
	 */
	public void removeAll();

	/**
	 *  This method returns the first element currently waiting in the waiting line (without removing the element).
	 *  If nothing is in the waiting line it will throw NoElementException.
	 *  @return returns the front-of-the-line element
	 */
	public T getFront();

	/**
	 *  This method returns the last element currently waiting in the line(without removing the element). 
	 *  If nothing is in the waiting line it will throw NoElementException.	 *  If nothing is in the waiting line it will throw NoElementException.
	 *  @return returns the back-of-the-line element
	 */
	public T getBack();

	/**
	 *  This method returns the maximum storage capacity.
	 *  @return returns max that the array can hold (not length) as an int
	 */
	public int getCapacity();

	/**
	 *  This method returns the number of data/elements at the time.
	 *  @return returns the size as an int
	 */
	public int size();

	/**
	 *  This method checks if the waiting line is empty.
	 *  @return returns true if line is empty, and false if not
	 */
	public boolean isEmpty();

	/**
	 *  This method checks if the waiting line is full.
	 *  @return returns true if line is full, and false if it is not
	 */
	public boolean isFull();
}