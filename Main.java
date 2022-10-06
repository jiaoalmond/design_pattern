import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static MarketSpace mk=MarketSpace.getInstance();
	private List<Computer> cart;
	static String PC=null;
	static double PCPrice=0;
	static String path="src/products.txt";
	
	public static void mainMenu() throws FileNotFoundException{
		System.out.println("Hi, what would you like to do?");
		System.out.println("1: Buy a computer");
		System.out.println("2: See my shoppoing cart");
		System.out.println("3: Sort by order ID (Descending order)");
		System.out.println("4: Sort by order price (Descending order)");
		System.out.println("5: Quit");
		
		Random r = new Random();
		int id= r.nextInt(101) + 1;
		Computer defModel= new defaultComputer("Default Computer", 700.0, id); //create defaultComputer
		Map<String, Double> cp = mk.loadProducts(path);//load the text file
		
		Scanner userInput= new Scanner(System.in);
		int input;
		try {
			input= userInput.nextInt();
			switch(input) {
			case 1:
	//build dynamic buy Computer menu	
				Integer[] arrList= new Integer[cp.size()];
				int count = 0;
				System.out.println(
						"\nCurrent Build: " + defModel.getDescription() + ",and total price is " + defModel.getPrice());
				System.out.println("What component would you like to add?");
				for (int i = 0; i < cp.size(); i++) {
					arrList[i] = i;
					count = i;
					System.out.println((i + 1) + ": " + cp.keySet().toArray()[i] + " $" + cp.values().toArray()[i]);
				}
				System.out.println((cp.size() + 1) + ": " + "Done");
				input=userInput.nextInt();
//		loop
				while(true) {
					if(input==0 || input>(cp.size()+1)){
						System.out.println("\nPlease enter a number from 1-"+cp.size());
						input = userInput.nextInt();
					}
					if(Arrays.asList(arrList).contains(input) || input==count+1 ) {// if input is between 1-9
						String comName = (String) cp.keySet().toArray()[input - 1];
						String comStrP = cp.values().toArray()[input - 1].toString();
						double price = Double.parseDouble(comStrP);
						double comPrice = Math.round(price*100) / 100.0;
						double newPrice = Math.round((defModel.getPrice()+ comPrice)*100) / 100.0;
						
						System.out.println("\nCurrent Build: " + defModel.getDescription() + " + " + comName
								+ " ,and total price is " + newPrice);
						
						PC=defModel.getDescription() + " + " + comName;
						PCPrice=defModel.getPrice() + comPrice;
						defModel = new defaultComputer(defModel.getDescription() + " + " + comName,
								defModel.getPrice() + comPrice, id);//update the defaultComputer description and price
						
						System.out.println("What component would you like to add?");
						for (int i = 0; i < cp.size(); i++) {
							arrList[i] = i;
							System.out.println((i + 1) + ": " + cp.keySet().toArray()[i] + " $" + cp.values().toArray()[i]);
						}
						System.out.println((cp.size() + 1) + ": " + "Done");
						input = userInput.nextInt();
					}
					if (input == cp.size() + 1) {//when the purchase is done, save the info into the cart()
						mk.cart(PC, PCPrice, id);
						System.out.println();
						mainMenu();
						input = userInput.nextInt();
					}
				}
			case 2:
				if(mk.cart.size()==0) {
					System.out.println("\nNo Item");
					mainMenu();
					input = userInput.nextInt();
				}else {
					mk.cartPrint();
					mainMenu();
					input = userInput.nextInt();
				}
			case 3:
				if(mk.cart.size()==0) {
					System.out.println("\nNo Item");
					mainMenu();
					input = userInput.nextInt();
				}else {
	//sort by orderID
//					mk.cartPrint(); //print out the sorted list, just for TEST
					mk.sort(mk.cart, new SortByOrderID());
					mainMenu();
					input = userInput.nextInt();
				}
			case 4:
				if(mk.cart.size()==0) {
					System.out.println("\nNo Item");
					mainMenu();
					input = userInput.nextInt();
				}else {
	//sort by Price
					mk.sort(mk.cart, new SortByPrice());
//					mk.cartPrint();//print out the sorted array, just for TEST
					mainMenu();
					input = userInput.nextInt();
				}
			case 5:
				System.out.println("ByeBye");
				System.exit(0);
				
			default:
				System.out.println("\nPlease enter a number from 1-5");//handle out of range input
				mainMenu();
				input = userInput.nextInt();
			}
		}catch(InputMismatchException e) {
			System.out.println("\nPlease enter valid integer");//handle exception of input value other then Integer
			mainMenu();
		}
		userInput.close();
	}
	
	
	public static void main(String[] args) {
		try {
			mainMenu();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	

	}

}
