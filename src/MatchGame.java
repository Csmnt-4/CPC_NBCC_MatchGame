import java.awt.EventQueue;

import javax.swing.UIManager;

import manager.GameDialogue;

/**
 * @author csmnt
 */
public class MatchGame {

    /**
     * @param args Launch the application.
     */
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try 
    			{
    				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    				GameDialogue dialog = new GameDialogue();
    				dialog.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
       });
    }
}
