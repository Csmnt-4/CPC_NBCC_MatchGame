import java.awt.EventQueue;

import javax.swing.UIManager;

import manager.GameDialogue;

/** 
 * MatchGame class is intended for running the game by creating an instance of GameDialogue class.
 *
 * @author Victor Anisimov
 * @since 5/7/2023        
 */

public class MatchGame 
{
	/**
	 * Runs the application and sets the form appearance as the the unified Windows LookAndFeel.
	 * 
     * @author Victor Anisimov
     * @param args arguments for main method.
     * @since 5/7/2023   
     */
	
    public static void main(String[] args) 
    {
    	EventQueue.invokeLater(new Runnable() 
    	{
    		public void run() 
    		{
    			try 
    			{
    				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    				GameDialogue dialog = new GameDialogue();
    				dialog.setVisible(true);
    			} 
    			catch (Exception e) 
    			{
    				e.printStackTrace();
    			}
    		}
       });
    }
}
