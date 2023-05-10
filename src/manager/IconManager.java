package manager;

import javax.swing.ImageIcon;
public class IconManager {
	private static ImageIcon redLo = new ImageIcon("images/redLo.png");	
	private static ImageIcon greenLo = new ImageIcon("images/greenLo.png");
	private static ImageIcon yellowLo = new ImageIcon("images/yellowLo.png");	
	private static ImageIcon blueLo = new ImageIcon("images/blueLo.png");
	
	private static ImageIcon redHi = new ImageIcon("images/redHi.png");
	private static ImageIcon greenHi = new ImageIcon("images/greenHi.png");
	private static ImageIcon yellowHi = new ImageIcon("images/yellowHi.png");
	private static ImageIcon blueHi = new ImageIcon("images/blueHi.png");
	
	public static ImageIcon getRedIcon(Boolean isHighlighted) {
		if (isHighlighted)
			return redHi;
		return redLo;
	}
	
	public static ImageIcon getGreenIcon(Boolean isHighlighted) {
		if (isHighlighted)
			return greenHi;
		return greenLo;
	}
	
	public static ImageIcon getYellowIcon(Boolean isHighlighted) {
		if (isHighlighted)
			return yellowHi;
		return yellowLo;
	}
	
	public static ImageIcon getBlueIcon(Boolean isHighlighted) {
		if (isHighlighted)
			return blueHi;
		return blueLo;
	}
}
