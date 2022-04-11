package main.utilities;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBCSimplified extends GridBagConstraints {
	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, Insets insets) {
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.insets = insets;
	}
}
