package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameDialogue extends JFrame implements ActionListener
{
	/** 
	 * GameDialogue class is intended for creating the window for a game and includes the logic
     * of a color-matching game. It relies on icon and audio managers for getting access to
     * respectful resources and on level manager to keep track of the variables, such as  current 
     * amount of points, level, rewards and penalties.
     *
     * @author Victor Anisimov
     * @since 5/7/2023         
	 */

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private static Boolean isButtonToBeLighted = false;
	private static final Integer FRAMETIME = 500;

	private JButton redButton;
	private JButton greenButton;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton playButton;

	private JPanel buttonPane;
	private JLabel pointsLabel;
	private JLabel levelLabel;

	private IconManager  iconManager;
	private AudioManager audioManager;
	private LevelManager levelManager;

	private JPanel contentPanel = new JPanel();

	/**
	 * Creates the dialog, instantiates the managers, sets the content and action performed
	 * on interaction with the buttons.
	 * 
     * @author Victor Anisimov
     * @since 5/7/2023         
	 */

	public GameDialogue() 
	{

		iconManager  = new IconManager();
		audioManager = new AudioManager();
		levelManager = new LevelManager();

		timer = new Timer(FRAMETIME, this);

		setBounds(100, 100, 280, 300);
		setMinimumSize(new Dimension(280, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));

		{
			redButton = new JButton("");
			redButton.setEnabled(false);
			redButton.setIcon(iconManager.getRedIcon(false));
			redButton.setDisabledIcon(iconManager.getRedIcon(false));
			redButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event) 
				{
					checkInput(0);
				}
			});
			contentPanel.add(redButton);
		}
		{
			greenButton = new JButton("");
			greenButton.setEnabled(false);
			greenButton.setIcon(iconManager.getGreenIcon(false));
			greenButton.setDisabledIcon(iconManager.getGreenIcon(false));
			greenButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event) 
				{
					checkInput(1);
				}
			});
			contentPanel.add(greenButton);
		}
		{
			yellowButton = new JButton("");
			yellowButton.setEnabled(false);
			yellowButton.setIcon(iconManager.getYellowIcon(false));
			yellowButton.setDisabledIcon(iconManager.getYellowIcon(false));
			yellowButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event) 
				{
					checkInput(2);
				}
			});
			contentPanel.add(yellowButton);
		}
		{
			blueButton = new JButton("");
			blueButton.setEnabled(false);
			blueButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent event) 
				{
					checkInput(3);
				}
			});
			contentPanel.add(blueButton);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				pointsLabel = new JLabel("Points: " + levelManager.getPoints());
				buttonPane.add(pointsLabel);
			}
			{
				levelLabel = new JLabel("Level: " + levelManager.getLevel());
				buttonPane.add(levelLabel);
			}
			{
				playButton = new JButton("Play");
				buttonPane.add(playButton);
				getRootPane().setDefaultButton(playButton);
				playButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent event) 
{
						levelManager.createInputSequence();
						isButtonToBeLighted = true;
						timer.restart();
					}
				});
			}
		}
		timer.start();
	}
	
	/**
	 * Acts like an update or a new frame; while being executed changes buttons icons in defined order,
	 * one by one. On finishing the sequence, nullifies the 'position' that was used to iterate through
	 * the sequence. 
	 *
	 * @author Victor Anisimov
	 * @param actionEvent 'indicator' for an event.
	 * @since 5/7/2023         
	 */
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		if (levelManager.getButtonSequence() != null) {
			if (levelManager.isShowingColors() && levelManager.getButtonSequence().length > 0) 
			{
				switch (levelManager.getButtonAtCurrentPosition()) 
				{
				case 0:
					redButton.setIcon(iconManager.getRedIcon(isButtonToBeLighted));
					redButton.setDisabledIcon(iconManager.getRedIcon(isButtonToBeLighted));
					break;
				case 1:
					greenButton.setIcon(iconManager.getGreenIcon(isButtonToBeLighted));
					greenButton.setDisabledIcon(iconManager.getGreenIcon(isButtonToBeLighted));
					break;
				case 2:
					yellowButton.setIcon(iconManager.getYellowIcon(isButtonToBeLighted));
					yellowButton.setDisabledIcon(iconManager.getYellowIcon(isButtonToBeLighted));
					break;
				case 3:
					blueButton.setIcon(iconManager.getBlueIcon(isButtonToBeLighted));
					blueButton.setDisabledIcon(iconManager.getBlueIcon(isButtonToBeLighted));
					break;
				}

				if (!isButtonToBeLighted)
					levelManager.setPosition(levelManager.getPosition() + 1);
			}
			
			if (levelManager.getPosition() >= levelManager.getButtonSequence().length && levelManager.isShowingColors())
			{
				levelManager.setPosition(0);
				enableGameButtons();
				levelManager.setShowingColors(false);
			}
		}
		
		isButtonToBeLighted = !isButtonToBeLighted;
	}

	/**
	 * Being used in the button call, receives the respective number on the press of the button 
	 * and compares that with the 'next' number in the sequence. On use increases the 'position' in the
	 * sequence, updates the labels in the form, on successful/unsuccessful attempt updates the amount
	 * of points and calls for message and audio identification.
	 * 
	 * @author Victor Anisimov
	 * @param freshInput number of the color of the button that's being pressed.
	 * @since 5/8/2023         
	 */
	
	public boolean checkInput(int freshInput) {
		levelManager.setInputSequenceAtCurrentPosition(freshInput);
		if (levelManager.getButtonAtCurrentPosition() != freshInput) 
		{
			disableGameButtons();

			levelManager.updatePoints(false);
			updateLabels();

			try 
			{
				audioManager.playIncorrectAnswerSound();
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Problem playing sound");
			}
			JOptionPane.showMessageDialog(null, "Wrong color", "Uh-oh!", 2);
			if (levelManager.getPoints() < 0)
			{
				disableGameButtons();
				playButton.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Seems like you lost", "Well...", 0);
			}
			return false;
		}
		
		levelManager.setPosition(levelManager.getPosition() + 1);
		
		if (levelManager.getPosition() >= levelManager.getButtonSequence().length) 
		{
			disableGameButtons();

			levelManager.updatePoints(true);
			updateLabels();

			try {
				audioManager.playCorrectAnswerSound();
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Problem playing sound");
			}
			JOptionPane.showMessageDialog(null, "Correct!", "Yay!", 1);
		}

		return true;
	}
	
	/**
	 * Enables all color buttons, disables the 'play' button.
	 * 
	 * @author Victor Anisimov
	 * @since 5/8/2023         
	 */
	
	public void enableGameButtons() 
	{
		redButton.setEnabled(true);
		greenButton.setEnabled(true);
		yellowButton.setEnabled(true);
		blueButton.setEnabled(true);
		playButton.setEnabled(false);
	}
	
	/**
	 * Disables the color buttons, enables the 'play' button.
	 * 
	 * @author Victor Anisimov
	 * @since 5/8/2023         
	 */
	
	public void disableGameButtons() 
	{
		redButton.setEnabled(false);
		greenButton.setEnabled(false);
		yellowButton.setEnabled(false);
		blueButton.setEnabled(false);
		playButton.setEnabled(true);
	}
	
	/**
	 * Updates the two labels to match current amount of points and the level the player is on.
	 * 
	 * @author Victor Anisimov
	 * @since 5/14/2023         
	 */
	
	public void updateLabels() 
	{
		pointsLabel.setText("Points: " + levelManager.getPoints());
		levelLabel.setText("Level: " + levelManager.getLevel());
	}
}