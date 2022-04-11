package main.utilities;

public class CommonMethods {
	public static int getPixelsFromPercentage(int percentage) {
		return (int) Constants.SCREEN_SIZE.getWidth() * (percentage / 100);
	}
}
