/**
 *  This is my submission of the PriorityCircularLine.
 *  @author Isaac Kim G01201648
 *  @param <T> a generic type variable for storage
 */
public class PriorityCircularLine<T extends Comparable<T>> implements CircularLineInterface<T> {

	//initializing class variables
	/**the line that holds people.*/
	protected T[] line;
	/**int to keep track of where to insert people.*/
	protected int lineIndex;
	/**int to keep track of capacity of line.*/
	protected int max;
	/**int to keep track of where the line starts.*/
	protected int start;
	/**int to keep track of where the line ends.*/
	protected int end;
	/**int to keep track of the number of items in the line(array).*/
	protected int numOfItems = 0;
	/**int to keep track of actual space in line (max - 1).*/
	protected int space;
	/**data to store for compareTo method.*/
	protected T newData = null;

	/**
	 *  Default constructor.
	 */
	@SuppressWarnings("unchecked")
	public PriorityCircularLine(){
		this.line = (T[]) new Comparable[50];
		this.space = 49;
		this.lineIndex = 0;
		this.max = 50;
		this.start = 0;
		this.end = 49;
	}
	/**
	 *  Default constructor with specified length.
	 *  @param capacity length of array
	 */
	@SuppressWarnings("unchecked")
	public PriorityCircularLine(int capacity){
		this.line = (T[]) new Comparable[capacity];
		this.space = capacity-1;
		this.lineIndex = 0;
		this.max = capacity;
		this.start = 0;
		this.end = capacity-1;
	}

	/**
	 *  This method doubles the length of the array.
	 */
	@SuppressWarnings("unchecked")
	public void doubleCapacity(){
		int count = 0;
		int index = this.start;
		T[] temp = (T[]) new Comparable[max*2];
		while(count != max){
			if(index >= max){
				index = 0;
			}
			temp[count] = line[index];
			index++;
			count++;
		}
		this.line = temp;
		this.start = 0;
		this.end = max-1;
		this.max *= 2;
		this.space = (this.space * 2) + 1;
	}
	/**
	 *  This method returns the start of the line.
	 * @return returns the index of the start of the line
	 */
	public int getStart(){
		return this.start;
	}
	/**
	 *  This method returns the end of the line.
	 * @return returns the index of the end of the line
	 */
	public int getEnd(){
		return this.end;
	}
	/**
	 *  This method returns the string representation of the data in the waiting line.
	 * @return returns a String representation of the line seperated by commas
	 */
	public String toString(){
		String s = "[";
		int i = this.start;
		while(line[i] != null){
			s = s + "" + line[i];
			i++;
			//account for wrap around
			if(i >= max){
				i = 0;
			}
			if(line[i] != null){
				s = s + ",";
			}
		}
		s = s + "]";
		//System.out.print(s);
		return s;
	}
	/**
	 *  Overriding for compareTo method in comparable interface.
	 *  @param element data to compare to
	 *  @return returns -1, 0, or 1 depending if the data is less than, equal, or greater than
	 */
	public int compareTo(T element){
		int ans;
		if(element == null){
			return 0;
		}
		ans = this.newData.compareTo(element);
		return ans;
	}
	/**
	 *  This method adds a new data to the waiting line. 
	 *	If the waiting line is full, it will double the size to accommodate the new data.
	 *  @param newData data to store in array
	 */
	@Override
	public void insert(T newData){
		//System.out.print(numOfItems);
		this.newData = newData;
		line[lineIndex] = newData;
		this.numOfItems++;
		//System.out.print(numOfItems);
		if(numOfItems >= max){
			doubleCapacity();
			lineIndex = (max/2)-1;
		}
		if(numOfItems > 1){
			int index = lineIndex-1;
			if(index < 0){
				index = this.end;
			}
			//System.out.print(index);
			int count = 1;
			while(compareTo(line[index]) < 0 && count < numOfItems){
				T temp = line[index];
				//System.out.print(index);
				if(index + 1 >= max){
					line[index] = line[0];
					line[0] = temp;
				}
				else{
					line[index] = line[index + 1];
					line[index + 1] = temp;
				}
				index--;
				if(index < 0){
					index = lineIndex;
				}
				count++;
			}
		}
		toString();
		lineIndex++;
		this.end = lineIndex-1;
		//if lineIndex wraps around, wrap around to 0
		if(lineIndex >= max){
			lineIndex = 0;
		}
		//System.out.print(this.end);
	}

	/**
	 *  This method removes and returns the first data in the waiting line.
	 *  @exception NoElementException exception to handle empty line
	 *  @return returns removed element (element that has been in line longest)
	 */
	@Override
	public T remove(){
		if(numOfItems == 0){
			throw new NoElementException("No element to process");
		}
		this.numOfItems--;
		//System.out.print(numOfItems);
		//temp variable for return val
		T temp = line[this.start];

		//remove the element
		line[this.start] = null;
		this.start++;
		//if start wraps around
		if(this.start >= max){
			this.start = 0;
		}
		return temp;
	}

	/**
	 *  This method removes all elements in the waiting line. 
	 *  It throws NoElementException, if the waiting line is empty.
	 *  @exception NoElementException exception to handle empty line
	 */
	@Override
	public void removeAll(){
		if(numOfItems == 0){
			throw new NoElementException("No element to process");
		}
		int i;
		int temp = numOfItems;
		for(i = 0; i < temp; i++){
			remove();
		}
		this.start = 0;
		this.end = max-1;
		this.lineIndex = 0;
	}

	/**
	 *  This method returns the first element currently waiting in the waiting line (without removing the element).
	 *  If nothing is in the waiting line it will throw NoElementException.
	 *  @exception NoElementException exception to handle empty line  
	 *  @return returns the front-of-the-line element
	 */
	@Override
	public T getFront(){
		if(numOfItems == 0){
			throw new NoElementException("No element to process");
		}
		return line[this.start];
	}

	/**
	 *  This method returns the last element currently waiting in the line(without removing the element). 
	 *  If nothing is in the waiting line it will throw NoElementException.	 *  If nothing is in the waiting line it will throw NoElementException.
	 *  @exception NoElementException exception to handle empty line
	 *  @return returns the back-of-the-line element
	 */
	@Override
	public T getBack(){
		if(numOfItems == 0){
			throw new NoElementException("No element to process");
		}
		return line[this.end];
	}

	/**
	 *  This method returns the maximum storage capacity.
	 *  @exception NoElementException exception to handle empty line
	 *  @return returns max that the array can hold (not length) as an int
	 */
	@Override
	public int getCapacity(){
		return this.space;
	}

	/**
	 *  This method returns the number of data/elements at the time.
	 *  @return returns the size as an int
	 */
	@Override
	public int size(){
		return numOfItems;
	}

	/**
	 *  This method checks if the waiting line is empty.
	 *  @return returns true if line is empty, and false if not
	 */
	@Override
	public boolean isEmpty(){
		if(numOfItems == 0){
			return true;
		}
		return false;
	}

	/**
	 *  This method checks if the waiting line is full.
	 *  @return returns true if line is full, and false if it is not
	 */
	@Override
	public boolean isFull(){
		//System.out.print(numOfItems);
		if(numOfItems == max-1){
			return true;
		}
		return false;
	}
}