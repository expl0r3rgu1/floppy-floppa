package main.model.utilities;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * A class that simplifies the usage of the GridBagConstraints used to create
 * the different panels of the game
 *
 */
public class GBCSimplified extends GridBagConstraints {

	private static final long serialVersionUID = 3822190102776934506L;

	/**
	 * @param gridx  specifies the cell containing the leading edge of the
	 *               component's display area, where the first cell in a row has
	 *               gridx=0
	 * @param gridy  specifies the cell at the top of the component's display area,
	 *               where the topmost cell has gridy=0
	 * @param ipadx  this field specifies the internal padding of the component, how
	 *               much space to add to the minimum width of the component
	 * @param ipady  this field specifies the internal padding, that is, how much
	 *               space to add to the minimum height of the component
	 * @param insets this field specifies the external padding of the component, the
	 *               minimum amount of space between the component and the edges of
	 *               its display area
	 * @param anchor this field is used when the component is smaller than its
	 *               display area
	 */
	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, Insets insets, int anchor) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.insets = insets;
		this.anchor = anchor;
	}

	/**
	 * @param gridx  specifies the cell containing the leading edge of the
	 *               component's display area, where the first cell in a row has
	 *               gridx=0
	 * @param gridy  specifies the cell at the top of the component's display area,
	 *               where the topmost cell has gridy=0
	 * @param ipadx  this field specifies the internal padding of the component, how
	 *               much space to add to the minimum width of the component
	 * @param ipady  this field specifies the internal padding, that is, how much
	 *               space to add to the minimum height of the component
	 * @param anchor this field is used when the component is smaller than its
	 *               display area
	 */
	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, int anchor) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.anchor = anchor;
	}

	/**
	 * @param gridx  specifies the cell containing the leading edge of the
	 *               component's display area, where the first cell in a row has
	 *               gridx=0
	 * @param gridy  specifies the cell at the top of the component's display area,
	 *               where the topmost cell has gridy=0
	 * @param ipadx  this field specifies the internal padding of the component, how
	 *               much space to add to the minimum width of the component
	 * @param ipady  this field specifies the internal padding, that is, how much
	 *               space to add to the minimum height of the component
	 * @param insets this field specifies the external padding of the component, the
	 *               minimum amount of space between the component and the edges of
	 *               its display area
	 */
	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady, Insets insets) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
		this.insets = insets;
	}

	/**
	 * @param gridx specifies the cell containing the leading edge of the
	 *              component's display area, where the first cell in a row has
	 *              gridx=0
	 * @param gridy specifies the cell at the top of the component's display area,
	 *              where the topmost cell has gridy=0
	 * @param ipadx this field specifies the internal padding of the component, how
	 *              much space to add to the minimum width of the component
	 * @param ipady this field specifies the internal padding, that is, how much
	 *              space to add to the minimum height of the component
	 */
	public GBCSimplified(int gridx, int gridy, int ipadx, int ipady) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.ipadx = ipadx;
		this.ipady = ipady;
	}

	/**
	 * @param anchor this field is used when the component is smaller than its
	 *               display area
	 */
	public GBCSimplified(int anchor) {
		this.anchor = anchor;
	}
}
