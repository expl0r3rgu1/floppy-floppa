package main.menu.shop;

public class PurchaseStatus<X> {
	private X x;
	private boolean purchased;
	
	public PurchaseStatus(X x, boolean purchased) {
		this.x = x;
		this.purchased = purchased;
	}

	public X getX() {
		return this.x;
	}

	public void setX(X x) {
		this.x = x;
	}

	public boolean isPurchased() {
		return this.purchased;
	}

	public void purchase(boolean purchased) {
		this.purchased = true;
	}
	
}
