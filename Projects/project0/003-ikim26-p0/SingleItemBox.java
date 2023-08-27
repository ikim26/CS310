/**
 *  This is my submission of the SingleItemBox class.
 *  @author Isaac Kim
 *  @param <T> a generic type variable for storage
 */
public class SingleItemBox<T> {
	/**
	 * initialize generic variable to store.
	 */
	protected T element;

	/**
	 * Default constructor for SingleItemBox.
	 * @param element generic variable stored in SingleItemBox
 	 */
	public SingleItemBox(T element){
		this.element = element;
	}
	/**
	 * Method for getting the item stored in SingleItemBox.
	 * @return the generic variable stored in SingleItemBox
	 */
	public T getItem(){
		return this.element;
	}
	/**
	 * Method for setting the item for SingleItemBox to store.
	 * @param element generic variable to store in SingleItemBox
	 */
	public void setItem(T element){
		this.element = element;
	}
}

