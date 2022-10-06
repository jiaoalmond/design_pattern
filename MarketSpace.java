import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class MarketSpace {
	static List<Computer> cart = new ArrayList<Computer>();
	private Map<String, Double> productSort = new TreeMap<String, Double>();
	private static MarketSpace marketInstance = null;

//	private MarketSpace() {
//
//	}

	Map<String, Double> loadProducts(String FilePath) throws FileNotFoundException {
		File input = new File(FilePath);
		Scanner reader = new Scanner(input);
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			String[] dataList = data.split(",");
			productSort.put(dataList[0], Double.parseDouble(dataList[1]));
		}
		reader.close();
		return productSort;
	}

	//sort strategy
	public void sort(List<Computer> cart, sortingStrategy strategy) {
		strategy.sort(cart);
	}

	//create a Computer list to store all the value
	static List<Computer> cart(String name, double p, int id) {
		Computer pc = new defaultComputer(name, p, id);
		cart.add(pc);
		return cart;
	}
	
	//print out the cart item
	void cartPrint() {
		System.out.print("\n[");
		for(int i=0; i<cart.size();i++) {
			if(i==cart.size()-1) {
				System.out.print(cart.get(i).getOrderID()+": "+cart.get(i).getDescription()
						+" $"+cart.get(i).getPrice());	
			}else {
				System.out.println(cart.get(i).getOrderID()+": "+cart.get(i).getDescription()
						+" $"+cart.get(i).getPrice()+", ");	
			}

		}
		System.out.print("]\n");
	}

	//create instance for Market space
	public static synchronized MarketSpace getInstance() {
		if (marketInstance == null) {
			marketInstance = new MarketSpace();
		}
		return marketInstance;
	}
}
