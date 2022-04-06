package main.utilities;

public class PricedItem {
	
	protected int price;
	
	
	public PricedItem(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
