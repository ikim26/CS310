/**
 *  This is my submission of the AppleOrange class.
 *  @author Isaac Kim
 */
public class AppleOrange{
	/**
	 *  This is the main method that prints integers up to user input.
	 *  @param args command line args are user input
	 */
	public static void main(String[] args) {
		//Assuming user input is in correct format.


		//initialize a var for max value to print up to
		int max = 0;
		try{
			//if there are multiple user inputs
			if(args.length > 1){
				throw new Exception();
			}

			max = Integer.parseInt(args[0]);

			int count = 1;
			int yet = 0;
			
			//if the user input is negative or 0
			if(max <= 0){
				throw new Exception();
			}

			else{
				//loop until count reaches user input
				while(count <= max){
					yet = 0;

					//for multiples of 3, print apple
					if(count % 3 == 0){
						System.out.print("apple");
						yet = 1;
					}
					//for multiples of 7, print orange
					if(count % 7 == 0){
						System.out.print("orange");
						yet = 1;
					}

					//if the number is not a multiple of 3 or 7, print the number
					if(yet == 0){
						System.out.print(count);
					}
					System.out.print(" ");
					count++;
				}
			}
		}
		//When user input is not in correct format.
		catch(Exception e){
			System.err.println("One positive number required as a command line argument.\nExample Usage: java AppleOrange [number]");
		}
		
	}
}