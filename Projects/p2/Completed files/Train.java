import java.util.Iterator;
/**
 *  This is my submission of the Train class.
 *  @author Isaac Kim G01201648
 */
class Train implements Iterable<Car> {

	//initializing class variables
	/**The name of the train.*/
	protected String name;
	/**The head of the train.*/
	protected Car head;
	/**The tail of the train.*/
	protected Car tail;

	/**
	 *  Default Constructor.
	 *  @param name name of the train
	 */
	public Train(String name) {
		this.name = name;
	}
	/**
	 *  Getter method for name.
	 *  @return returns name of the train
	 */
	public String getName() {
		//returns the train's name
		//O(1)
		return this.name;
	}
	/**
	 *  Iterator method.
	 *  @return returns iterator that traverses the train
	 */
	public Iterator<Car> iterator() {
		//returns an iterator which traverses
		//the train from the first car (the one closest
		//to the front of the train) to the last car
		//use an anonymous class here
		Car temp = this.head;
		Iterator<Car> carIterator = new Iterator<Car>() {
			private Car currentCar = temp;

			@Override
			public Car next(){
				Car t = currentCar;
				currentCar = currentCar.getNext();
				return t;
			}
			@Override
			public boolean hasNext(){
				if(currentCar != null){
					return true;
				}
				return false;
			}
		};

		//required iterator methods: next() and hasNext()
		//both methods are required to work in O(1) time

		return carIterator;
	}
	/**
	 *  This method determines if 2 trains are the same or not.
	 *  @param o the object we are comparing this train to
	 *  @return returns true if trains have same name, and false otherwise
	 */
	public boolean equals(Object o) {
		//two trains are equal if they have the same name
		//O(1)
		Train t = (Train)o;
		if(this.name == t.getName()){
			return true;
		}
		return false;
	}
	/**
	 *  This method connects the car in param to the train.
	 *  @param c the car we will connect to the end of the train
	 */
	public void connectCar(Car c) {
		//connects the car to the end of the cars for this train
		//requied Big-O: O(n) where n=the length of the linked list
		//of cars starting at c, NOT n=the number of cars already 
		//connected to this train.

		Car currentCar;
		Car prev;
		currentCar = this.head;

		//if train is empty
		if(this.head == null){
			this.head = c;
			currentCar = this.head;
			while(currentCar != null){
				this.tail = currentCar;
				currentCar = currentCar.getNext();
			}
		}
		//if train is not empty
		else{
			this.tail.setNext(c);

			//setting previous for joined car
			prev = this.tail;
			this.tail = this.tail.getNext();
			this.tail.setPrevious(prev);
			while(this.tail.getNext() != null){
				this.tail = this.tail.getNext();
			}
		}
		//System.out.println(this.tail);
		currentCar = this.head;
	}
	/**
	 *  This method disconnects the car in param from the train.
	 *  @param c car we are trying to disconnect from the train
	 *  @return returns the Car that we disconnect
	 */
	public Car disconnectCar(Car c) {
		//returns the car disconnected from the train
		//should throw the following exception if the car isn't on
		//the train: RuntimeException("Can not disconnect a car that doesn't exist");
		//required Big-O: O(n) where n=the number of cars on this train

		//setting currentCar to head initially
		Car currentCar;
		Car temp;
		currentCar = this.head;

		//traverse the train until we find the car
		while(!currentCar.equals(c)){
			if(currentCar.getNext() == null){
				throw new RuntimeException("Can not disconnect a car that doesn't exist");
			}
			else{
				currentCar = currentCar.getNext();
			}
		}
		//at this point, currentCar is the car we are looking for
		//setting temp to currentCar
		temp = currentCar;

		//set currentCar to previous car, then remove next car (set to null)
		currentCar = currentCar.getPrevious();
		//if train is empty after disconnect
		if(currentCar == null){
			this.head = null;
			this.tail = null;
			return currentCar;
		}
		else{
			currentCar.setNext(null);
			temp.setPrevious(null);
		}

		//set tail to currentCar
		this.tail = currentCar;

		return temp;
	}
	/**
	 *  This method reverses the order of the cars connected to the train.
	 */
	public void reverseTrain() {
		//reconnects all the cars on the train in the reverse order
		//that they currently are (e.g. changes C1->C2->C3 to
		//C3->C2->C1). You may find using a second train to do this useful.
		//required Big-O: O(n) where n=the number of cars on this train
		//careful not to end up with O(n^2) which is easy to do by calling O(n)
		//methods in a loop

		this.head = this.tail;
		//debugging
		//System.out.print("head:");
		//System.out.println(this.head);

		Car currentCar = head;
		Car c = currentCar;

		//set all next nodes to the previous initially
		while(currentCar.getPrevious() != null){
			//debugging
			//System.out.println(c);

			c.setNext(currentCar.getPrevious());
			currentCar = currentCar.getPrevious();

			//debugging
			//System.out.print("Current Car:");
			//System.out.println(currentCar);
			//System.out.print("Current Car prev:");
			//System.out.println(currentCar.getPrevious());

			c = c.getNext();
		}
		//set next of tail to null
		c.setNext(null);
		this.tail = c;

		//cutting ties with back half of list
		currentCar = this.head;
		currentCar.setPrevious(null);

		//setting correct previous pointers
		while(currentCar != null){
			Car prev = currentCar;
			//debugging
			//System.out.println(prev);
			currentCar = currentCar.getNext();
			if(currentCar == null){
				break;
			}
			currentCar.setPrevious(prev);
		}

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
		
		Train t1 = new Train("T1");
		Train t1b = new Train("T1");
		
		if(t1.getName().equals("T1") && t1.equals(t1b)) {
			System.out.println("Yay 1");
		}
		
		t1.printAscii();
		
		t1.connectCar(c1);
		t1.printAscii();
		
		Car c3 = new Car("C3");
		Car c4 = new Car("C4");
		
		c3.setNext(c4);
		c4.setPrevious(c3);
		
		t1.connectCar(c3);
		t1.printAscii();
		
		t1.reverseTrain();
		t1.disconnectCar(c1);
		t1.printAscii();
	}
	
	//****************************************************************/
	//***************** DO NOT EDIT BELOW THIS LINE ******************/
	//****************************************************************/
	/**
	 *  toString method for Train.
	 *  @return returns a string of the train
	 */
	public String toString() {
		String s = getName();
		for(Car c : this) {
			s += " " + c;
		}
		return s;
	}
	/**
	 *  This method prints the trains.
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		    o O___ _________
		  _][__|o| |O O O O|
		 <_______|-|_______|
		  /O-O-O     o   o  
		*/
		
		System.out.print(String.format("%-4s",getName())+"o O___");
		for(Car c : this) {
			System.out.print(" _________");
		}
		System.out.println();
		
		System.out.print("  _][__|o|");
		for(Car c : this) {
			System.out.print(" | "+String.format("%-5s",c.getName())+" |");
		}
		System.out.println();
		
		System.out.print(" |_______|");
		for(Car c : this) {
			System.out.print("-|_______|");
		}
		System.out.println();
		
		System.out.print("  /O-O-O  ");
		for(Car c : this) {
			System.out.print("   o   o  ");
		}
		System.out.println();
	}
}