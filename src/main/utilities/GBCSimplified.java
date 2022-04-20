package main.utilities;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBCSimplified extends GridBagConstraints {

	private static final long serialVersionUID = 3822190102776934506L;

	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, Insets insets) {
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.insets = insets;
	}

	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, Insets insets, int anchor) {
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.insets = insets;
		this.anchor = anchor;
	}

	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady) {
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
	}
}
