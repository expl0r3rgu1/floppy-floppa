package main.menu;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.utilities.GraphicJLabel;
import main.utilities.Constants;
import main.utilities.Constants.PANEL;
import main.utilities.GBCSimplified;
import main.utilities.GraphicJButton;
import main.utilities.CommonMethods;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

/**
 * Menu class implements the panel for the menu
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7631305128085484196L;
	private GridBagLayout grid = new GridBagLayout();
	JPanel panel = new JPanel(new GridBagLayout());
	private MainMenu mainMenu;

	/**
	 * @param mainMenu - used to show cardlayouts
	 * 
	 */
	public MenuPanel(MainMenu mainMenu) {

		this.mainMenu = mainMenu;
		this.setLayout(grid);

		this.setPreferredSize(Constants.SCREEN_SIZE);
		this.setOpaque(false);

		addTitle();

		addJPanel();

		addButton("Leaderboard", 1, 1, 7, 5, 0, 0, 2, 0);
		addButton("Clear Savings", 3, 1, 7, 5, 0, 0, 2, 0);
		addButton("Shop", 3, 3, 7, 5, 2, 0, 0, 0);
		addButton("Tutorial", 1, 3, 7, 5, 2, 0, 0, 0);

		addImage("Floppa.png", 1, 2, 0, 0, 0, 0, 0, 2);
		addImage("Bingus.png", 3, 2, 0, 0, 0, 2, 0, 0);

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
				new GBCSimplified(2, 0, 0, 0, new Insets(0, 0, CommonMethods.getPixelsFromPercentageWidth(3), 0)));
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
	 * class method that creates and adds the images show in the menu
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
		this.add(this.getImageResource(name),
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

	/**
	 * The method reads an image file and creates the corresponding Image object
	 * which gets also scaled
	 * 
	 * @param fileName
	 * @return a JLabel containing the newly created Image
	 */
	private JLabel getImageResource(String fileName) {
		JLabel label = null;
		try {
			Image image = ImageIO.read(getClass().getResource("/resources/images/" + fileName));
			ImageIcon imageIcon = new ImageIcon(this.scaleImage(image, new Dimension(
					CommonMethods.getPixelsFromPercentageWidth(20), CommonMethods.getPixelsFromPercentageWidth(20))));
			label = new JLabel(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return label;
	}

	/**
	 * Returns a scaled version of the image parameter
	 * 
	 * @param image - the image to scale
	 * @param dim   - the Dimension to which to scale the image
	 * @return a scaled version of the image parameter
	 */
	private Image scaleImage(Image image, Dimension dim) {
		return image.getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_DEFAULT);
	}

}