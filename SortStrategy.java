import java.util.Collections;
import java.util.Comparator;
import java.util.List;

interface sortingStrategy{
	void sort(List<Computer> cart);
}

//sorted the cart item by orderID 
class SortByOrderID implements sortingStrategy{
	@Override
	public void sort(List<Computer> cart) {
		Collections.sort(cart, new Comparator<Computer>() {
			@Override
			public int compare(Computer o1, Computer o2) {
				return o2.getOrderID().compareTo(o1.getOrderID());//in descending order
			}
		});
	}

}

//sorted the cart item by price
class SortByPrice implements sortingStrategy{
	@Override
	public void sort(List<Computer> cart) {
		Collections.sort(cart, new Comparator<Computer>() {
			@Override
			public int compare(Computer o1, Computer o2) {
				Integer o1P=(int) o1.getPrice();
				Integer o2P=(int) o2.getPrice();
				return o2P.compareTo(o1P);////in descending order
			}
		});
	}

}


