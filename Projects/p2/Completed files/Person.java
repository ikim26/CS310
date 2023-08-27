/**
 *  This is my submission of the Person class.
 *  @author Isaac Kim G01201648
 */
class Person {

	//initializing class variables
	/**The name of the person.*/
	protected String name;
	/**The name of the car the person is in.*/
	protected Car currentCar;

	/**
	 *  Default constructor for person class.
	 *  @param name The name of this person
	 *  @param currentCar The name of the car the person is in
	 */
	public Person(String name, Car currentCar) {
		this.name = name;
		this.currentCar = currentCar;
	}
	
	/**
	 *  This method gets the name of the person.
	 *  @return returns name of person
	 */
	public String getName() {
		//returns the name set in the constructor
		//O(1)
		return this.name;
	}
	
	/**
	 *  This method gets the car the person is in.
	 *  @return returns the car the person is in
	 */
	public Car getCurrentCar() {
		//returns the current car
		//O(1)
		return this.currentCar;
	}
	
	/**
	 *  This method moves the person to an adjavent car.
	 *  @param c the car we are trying to move to
	 *  @return returns true if car is adjacent and the person moved, and false otherwise
	 */
	public boolean moveToCar(Car c) {
		//Tries to move the person from their current car
		//to the car passed in as a parameter. If the two
		//cars are not adjacent, returns false (and the
		//person remains in their current car). Returns
		//true if the person was able to move.
		//O(1)
		if(this.currentCar.getNext() == (c) || this.currentCar.getPrevious() == (c)){
			this.currentCar = c;
			return true;
		}
		return false;
	}
	
	/**
	 *  This method determines if 2 people are the same (name).
	 *  @param o the person of comparison
	 *  @return returns true if names are same, false otherwise
	 */
	public boolean equals(Object o) {
		//two people are "equal" if they have the same name
		//O(1)
		Person p = (Person)o;
		if(this.name == p.getName()){
			return true;
		}
		return false;
	}
	/**
	 *  toString method for person.
	 *  @return returns a string of the person's name
	 */
	public String toString() {
		//returns the person's name
		//O(1)
		return this.name;
	}
	
	//example test code... edit this as much as you want!
	/**
	 *  Main method.
	 *  @param args command line arguments
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		Car c3 = new Car("C3");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		Person p1 = new Person("P1", c1);
		
		if(p1.getCurrentCar().equals(c1) && p1.getName().equals("P1")) {
			System.out.println("Yay 1");
		}
		
		if(p1.moveToCar(c2) && p1.getCurrentCar().equals(c2) && p1.moveToCar(c1) && p1.getCurrentCar().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Person p1b = new Person("P1", c1);
		if(p1.equals(p1b) && !p1.equals(new Person("P2", c1))) {
			System.out.println("Yay 3");
		}
	}
}