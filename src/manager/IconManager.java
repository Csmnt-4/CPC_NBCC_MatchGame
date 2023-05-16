package manager;

import javax.swing.ImageIcon;

public class IconManager
{
	/** 
	 * IconManager class is intended for for loading the icons needed for the color buttons.
	 * The get[Color]Icon methods rely on passed parameters to either pass highlighted or regular
	 * button icon. The functionality is intended to be simple as the class serves the purpose of 
	 * making the code more readable and modular.
     *
     * @author Victor Anisimov
     * @since 5/7/2023         
	 */
	
	private ImageIcon redLo;
	private ImageIcon greenLo;
	private ImageIcon yellowLo;
	private ImageIcon blueLo;
	
	private ImageIcon redHi;
	private ImageIcon greenHi;
	private ImageIcon yellowHi;
	private ImageIcon blueHi;
	
    /**
	 * Creates the icon manager and loads the icons.
	 * 
     * @author Victor Anisimov
     * @since 5/13/2023         
	 */
	
	public IconManager() 
	{
		 this.redLo = new ImageIcon("resources/images/redLo.png");
		 this.greenLo = new ImageIcon("resources/images/greenLo.png");
		 this.yellowLo = new ImageIcon("resources/images/yellowLo.png");
		 this.blueLo = new ImageIcon("resources/images/blueLo.png");

		 this.redHi = new ImageIcon("resources/images/redHi.png");
		 this.greenHi = new ImageIcon("resources/images/greenHi.png");
		 this.yellowHi = new ImageIcon("resources/images/yellowHi.png");
		 this.blueHi = new ImageIcon("resources/images/blueHi.png");
	}
	
    /**
	 * Based on the input, returns the regular or "highlighted" icon for red button.
	 * 
     * @author Victor Anisimov
     * @param isHighlighted indicates whether the button icon is supposed to appear
     * "highlighted".
     * @since 5/13/2023         
	 */
	
	public ImageIcon getRedIcon(Boolean isHighlighted) 
	{
		if (isHighlighted)
			return redHi;
		return redLo;
	}
	
    /**
	 * Based on the input, returns the regular or "highlighted" icon for green button.
	 * 
     * @author Victor Anisimov
     * @param isHighlighted indicates whether the button icon is supposed to appear
     * "highlighted".
     * @since 5/13/2023         
	 */
	
	public ImageIcon getGreenIcon(Boolean isHighlighted) 
	{
		if (isHighlighted)
			return greenHi;
		return greenLo;
	}
	
    /**
	 * Based on the input, returns the regular or "highlighted" icon for yellow button.
	 * 
     * @author Victor Anisimov
     * @param isHighlighted indicates whether the button icon is supposed to appear
     * "highlighted".
     * @since 5/13/2023         
	 */
	
	public ImageIcon getYellowIcon(Boolean isHighlighted) 
	{
		if (isHighlighted)
			return yellowHi;
		return yellowLo;
	}
	
    /**
	 * Based on the input, returns the regular or "highlighted" icon for blue button.
	 * 
     * @author Victor Anisimov
     * @param isHighlighted indicates whether the button icon is supposed to appear
     * "highlighted".
     * @since 5/13/2023         
	 */
	
	public ImageIcon getBlueIcon(Boolean isHighlighted) 
	{
		if (isHighlighted)
			return blueHi;
		return blueLo;
	}
}
