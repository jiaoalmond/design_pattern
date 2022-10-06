import java.util.Random;

interface Computer {
	public String getDescription();
	public double getPrice();
	public String getOrderID();
}

class defaultComputer implements Computer {
	String name;
	double price;
	int id;

	public defaultComputer(String name, double p, int id) {
		this.name = name;
		this.price = Math.round(p*100) / 100.0;
		this.id=id;
	}

	@Override
	public String getDescription() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public String getOrderID() {
		return "OrderID@" + this.id;
	}

}

//	decorating

class ComputerDecorator implements Computer {
	Computer defaultComp;
	public String name;
	public double price;

//	build a constructor to store the previous value
	public ComputerDecorator(Computer c) {
		defaultComp = c;
	}

	public ComputerDecorator(String name, double price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String getOrderID() {
		return defaultComp.getOrderID();
	}

	@Override
	public String getDescription() {
		return defaultComp.getDescription();
	}

	@Override
	public double getPrice() {
		return defaultComp.getPrice();
	}
}

//	varies component
class Component extends ComputerDecorator {
		String name;
		double p;
	public Component(Computer c,String name, double p) {
		super(c);
		this.name=name;
		this.p=p;
		
	}

	public String getDescription() {
		return super.getDescription() + " + " + this.name ;
	}

	public double getPrice() {
		return super.getPrice() + this.p;
	}

	public String getOrderID() {
		return super.getOrderID();
	}

}
