package main.view.menu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.controller.menu.MainMenu;
import main.model.utilities.CommonMethods;
import main.model.utilities.Constants;
import main.model.utilities.Constants.PANEL;
import main.model.utilities.GBCSimplified;
import main.model.utilities.GraphicJButton;
import main.model.utilities.GraphicJLabel;

/**
 * Menu class implements the panel for the menu
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private final GridBagLayout grid;
	private final JPanel panel;
	private final MainMenu mainMenu;

	/**
	 * @param mainMenu - used to show cardlayouts
	 * 
	 */
	public MenuPanel(MainMenu mainMenu) {

		this.mainMenu = mainMenu;
		grid = new GridBagLayout();
		this.setLayout(grid);
		panel = new JPanel(grid);

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		addTitle();

		addJPanel();

		addButton("Leaderboard", 1, 1, 7, 5, 0, 0, 2, 0);
		addButton("Clear Savings", 3, 1, 7, 5, 0, 0, 2, 0);
		addButton("Shop", 3, 3, 7, 5, 2, 0, 0, 0);
		addButton("Tutorial", 1, 3, 7, 5, 2, 0, 0, 0);

		addImage("Floppa", 1, 2, 0, 0, 0, 0, 0, 2);
		addImage("Bingus", 3, 2, 0, 0, 0, 2, 0, 0);

		this.setVisible(true);
	}

	/**
	 * addTitle is a class method that creates and adds the title label of the menu
	 */
	private void addTitle() {
		GraphicJLabel title = new GraphicJLabel("Floppy Floppa", Color.decode("#8EDCFB"), Color.decode("#3288FE"),
				"pixel", (float) Constants.SCREEN_SIZE.getWidth() / 25);

		title.setOpaque(false);

		this.add(title,
				new GBCSimplified(2, 0, 0, 0, new Insets(0, 0, CommonMethods.getPixelsFromPercentageHeight(3), 0)));
	}

	/**
	 * addJPanel is a method that creates a JPanel that places all the central
	 * components of the menu, apart from the title, so that everything is visible
	 * on different screen resolution, in particular the ultra-wide
	 */
	private void addJPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.ipadx = CommonMethods.getPixelsFromPercentageWidth(15) / 2;
		c.ipady = 0;

		addPlayButton();
		addButton("Select", 1, 1, 7, 5, 0, 0, 13, 0);
		addButton("Quit", 1, 3, 6, 4, 13, 0, 0, 0);

		panel.setBackground(Color.red);
		panel.setOpaque(false);

		this.add(panel, c);
	}

	/**
	 * addPlayButton is a class method that creates and adds the play button
	 */
	private void addPlayButton() {

		GraphicJButton jb = new GraphicJButton("Play", "fipps", 90, "#77DD77", "#007542");

		jb.addActionListener(adHocActionListener("PLAY"));

		panel.add(jb, new GBCSimplified(1, 2, CommonMethods.getPixelsFromPercentageWidth(15),
				CommonMethods.getPixelsFromPercentageHeight(10)));
	}

	/**
	 * addButton is a class method that creates and adds the various buttons of the
	 * menu
	 * 
	 * @param name   - The text of the JButton
	 * 
	 * @param gridx  - int for setDimensionObj
	 * 
	 * @param gridy  - int for setDimensionObj
	 * 
	 * @param ipadx  - int for setDimensionObj
	 * 
	 * @param ipady  - int for setDimensionObj
	 * 
	 * @param top    - int inset from the top for setDimensionObj
	 * 
	 * @param left   - int inset from the left for setDimensionObj
	 * 
	 * @param bottom - int inset from the bottom for setDimensionObj
	 * 
	 * @param right  - int inset from the right for setDimensionObj
	 */
	private void addButton(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		String actionListenerName = name.toUpperCase();

		GraphicJButton jb;

		if (name.equals("Quit")) {
			jb = new GraphicJButton(name, "fipps", 110, "#FF0000", "#8B0000");
		} else {
			jb = new GraphicJButton(name, "fipps", 120, "#FFDD62", "#FF971A");
		}

		jb.addActionListener(adHocActionListener(actionListenerName));

		if (name.equals("Quit") || name.equals("Select")) {
			panel.add(jb,
					new GBCSimplified(gridx, gridy, CommonMethods.getPixelsFromPercentageWidth(ipadx),
							(int) CommonMethods.getPixelsFromPercentageHeight(ipady),
							new Insets(CommonMethods.getPixelsFromPercentageHeight(top),
									CommonMethods.getPixelsFromPercentageWidth(left),
									CommonMethods.getPixelsFromPercentageHeight(bottom),
									CommonMethods.getPixelsFromPercentageWidth(right))));
		} else {
			this.add(jb,
					new GBCSimplified(gridx, gridy, CommonMethods.getPixelsFromPercentageWidth(ipadx),
							(int) CommonMethods.getPixelsFromPercentageHeight(ipady),
							new Insets(CommonMethods.getPixelsFromPercentageHeight(top),
									CommonMethods.getPixelsFromPercentageWidth(left),
									CommonMethods.getPixelsFromPercentageHeight(bottom),
									CommonMethods.getPixelsFromPercentageWidth(right))));
		}
	}

	/**
	 * addImage is a class method that creates and adds the images show in the menu
	 * 
	 * @param name   - The name for getImageResource
	 * 
	 * @param gridx  - int for setDimensionObj
	 * 
	 * @param gridy  - int for setDimensionObj
	 * 
	 * @param ipadx  - int for setDimensionObj
	 * 
	 * @param ipady  - int for setDimensionObj
	 * 
	 * @param top    - int inset from the top for setDimensionObj
	 * 
	 * @param left   - int inset from the left for setDimensionObj
	 * 
	 * @param bottom - int inset from the bottom for setDimensionObj
	 * 
	 * @param right  - int inset from the right for setDimensionObj
	 */
	private void addImage(String name, int gridx, int gridy, int ipadx, int ipady, int top, int left, int bottom,
			int right) {
		this.add(CommonMethods.getJLabelImage(name, 20),
				new GBCSimplified(gridx, gridy, CommonMethods.getPixelsFromPercentageWidth(ipadx),
						CommonMethods.getPixelsFromPercentageHeight(ipady),
						new Insets(CommonMethods.getPixelsFromPercentageHeight(top),
								CommonMethods.getPixelsFromPercentageWidth(left),
								CommonMethods.getPixelsFromPercentageHeight(bottom),
								CommonMethods.getPixelsFromPercentageWidth(right))));
	}

	/**
	 * adHocActionListener method includes the actionListener of all JButtons in the
	 * menu, depending by the given String panelName
	 * 
	 * @param panelName - Used to open the jpanel needed by the player
	 * @return ActionListener
	 */
	private ActionListener adHocActionListener(String panelName) {

		if (panelName.equals("PLAY")) {
			return e -> {
				mainMenu.showCard(PANEL.PLAY);
			};
		} else if (panelName.equals("QUIT")) {
			return e -> {
				mainMenu.updateSavings();
				System.exit(0);
			};
		} else if (panelName.equals("CLEAR SAVINGS")) {
			return e -> {
				mainMenu.clearSavings();
			};
		} else {
			return e -> {
				mainMenu.showCard(PANEL.valueOf(panelName));
			};
		}
	}

}