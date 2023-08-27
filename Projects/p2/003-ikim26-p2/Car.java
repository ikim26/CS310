/**
 *  This is my submission of the Car class.
 *  @author Isaac Kim G01201648
 */
class Car {

	//initializing class variables
	/**The name of the car.*/
	protected String name;
	/**The name of the next car.*/
	protected Car next;
	/**The name of the prev car.*/
	protected Car prev;

	/**
	 *  Default constructor for car class.
	 *  @param name The name of this car
	 */
	public Car(String name) {
		this.name = name;
		this.next = null;
		this.prev = null;
	}
	/**
	 *  This method gets the next car in the train.
	 *  @return returns the next car in the train (linked list)
	 */
	public Car getNext() {
		//returns the next car after this one
		//O(1)
		return this.next;
	}
	/**
	 *  This method gets the previous car in the train.
	 *  @return returns the previous car in the train (linked list)
	 */
	public Car getPrevious() {
		//returns the car before this one
		//O(1)
		return this.prev;
	}
	/**
	 *  This method sets the next car in the train.
	 *  @param next the car that will come after current car
	 */
	public void setNext(Car next) {
		//sets the car after this one to next (the parameter)
		//O(1)
		this.next = next;
	}
	/**
	 *  This method sets the previous car in the train.
	 *  @param previous the car that will come before current car
	 */
	public void setPrevious(Car previous) {
		//sets the car before this one to previous (the parameter)
		//O(1)
		this.prev = previous;
	}
	/**
	 *  This method gets the name of the car.
	 *  @return returns the name of this car
	 */
	public String getName() {
		//return's the car's name
		//O(1)
		return this.name;
	}
	/**
	 *  This method sees if this car and the object in the parameter is the same.
	 *  @param o the object that we are comparing this car to
	 *  @return returns true if they are the same, and false otherwise
	 */
	public boolean equals(Object o) {
		//two cars are equal if they have the same name
		//O(1)
		Car c = (Car)o;
		if(this.name == c.getName()){
			return true;
		}
		return false;
	}
	/**
	 *  This method gets the name of the car.
	 *  @return returns the name of this car
	 */
	public String toString() {
		//return's the car's name
		//O(1)
		return this.name.toString();
	}
	
	//example test code... edit this as much as you want!
	/**
	 *  Main method.
	 *  @param args command line arguments
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		if(c1.getName().equals("C1")) {
			System.out.println("Yay 1");
		}
		
		if(c1.getNext().equals(c2) && c2.getPrevious().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Car c1b = new Car("C1");
		if(c1.equals(c1b)) {
			System.out.println("Yay 3");
		}
		
		c1.printAscii();
	}
	
	//****************************************************************/
	//***************** DO NOT EDIT BELOW THIS LINE ******************/
	//****************************************************************/
	/**
	 *  This method prints the trains.
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		 _________
		 |O O O O|
		-|_______|
		   o   o  
		*/
		
		Car current = this;
		while(current != null) {
			System.out.print(" _________");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print(" | "+String.format("%-5s",current.getName())+" |");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("-|_______|");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("   o   o  ");
			current = current.getNext();
		}
		System.out.println();
	}
}