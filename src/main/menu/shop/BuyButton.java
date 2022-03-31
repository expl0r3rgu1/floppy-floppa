package main.menu.shop;

import javax.swing.JButton;

public class BuyButton extends JButton {

	private static final long serialVersionUID = 3550487230752126610L;
	private Object object;

	public BuyButton(String name, Object object) {
		super(name);
		this.object = object;
	}

	public Object getIndex() {
		return this.object;
	}

}
