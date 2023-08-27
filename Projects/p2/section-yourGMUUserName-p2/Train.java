import java.util.Iterator;

class Train implements Iterable<Car> {
	public Train(String name) {
		
	}
	
	public String getName() {
		//returns the train's name
		//O(1)
		return null;
	}
	
	public Iterator<Car> iterator() {
		//returns an iterator which traverses
		//the train from the first car (the one closest
		//to the front of the train) to the last car
		//use an anonymous class here
		//required iterator methods: next() and hasNext()
		//both methods are required to work in O(1) time
		return null;
	}
	
	public boolean equals(Object o) {
		//two trains are equal if they have the same name
		//O(1)
		return false;
	}
	
	public void connectCar(Car c) {
		//connects the car to the end of the cars for this train
		//requied Big-O: O(n) where n=the length of the linked list
		//of cars starting at c, NOT n=the number of cars already 
		//connected to this train.
	}
	
	public Car disconnectCar(Car c) {
		//returns the car disconnected from the train
		//should throw the following exception if the car isn't on
		//the train: RuntimeException("Can not disconnect a car that doesn't exist");
		//required Big-O: O(n) where n=the number of cars on this train
		return null;
	}
	
	public void reverseTrain() {
		//reconnects all the cars on the train in the reverse order
		//that they currently are (e.g. changes C1->C2->C3 to
		//C3->C2->C1). You may find using a second train to do this useful.
		//required Big-O: O(n) where n=the number of cars on this train
		//careful not to end up with O(n^2) which is easy to do by calling O(n)
		//methods in a loop
	}
	
	//example test code... edit this as much as you want!
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
		t1.printAscii();
	}
	
	/*****************************************************************/
	/****************** DO NOT EDIT BELOW THIS LINE ******************/
	/*****************************************************************/
	
	public String toString() {
		String s = getName();
		for(Car c : this) {
			s += " " + c;
		}
		return s;
	}
	
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