package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameDialogue extends JFrame implements ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private static Boolean hiIntensity = false;
	private static final Integer FRAMETIME = 1000;

	private JButton redButton;
	private JButton greenButton;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton playButton;

	private JPanel buttonPane;
	private JLabel pointsLabel;
	private JLabel levelLabel;

	private int points = 0;
	private int pointsForCorrect = 10;
	private int pointsForWrong = 5;

	private IconManager iconManager;

	private JPanel contentPanel = new JPanel();

	private int order[];
	private int inputOrder[];
	private int position;
	private Boolean isShowningColors = false;;

	private AudioInputStream correctSoundAudioFile;
	private AudioInputStream wrongSoundAudioFile;
	private Clip correctSound;
	private Clip wrongSound;

	/**
	 * Create the dialog.
	 */

	public GameDialogue() {

		try {
			correctSoundAudioFile = AudioSystem.getAudioInputStream(new File("audio/correct.wav"));
			correctSound = AudioSystem.getClip();
			correctSound.open(correctSoundAudioFile);

			wrongSoundAudioFile = AudioSystem.getAudioInputStream(new File("audio/wrong.wav"));
			wrongSound = AudioSystem.getClip();
			wrongSound.open(wrongSoundAudioFile);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem playing sound");
		}

		timer = new Timer(FRAMETIME, this);

		setBounds(100, 100, 280, 300);
		setMinimumSize(new Dimension(280, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));

		position = 0;

		{
			redButton = new JButton("");
			redButton.setEnabled(false);
			redButton.setIcon(IconManager.getRedIcon(false));
			redButton.setDisabledIcon(IconManager.getRedIcon(false));
			redButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					checkInput(0);
				}
			});
			contentPanel.add(redButton);
		}
		{
			greenButton = new JButton("");
			greenButton.setEnabled(false);
			greenButton.setIcon(IconManager.getGreenIcon(false));
			greenButton.setDisabledIcon(IconManager.getGreenIcon(false));
			greenButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					checkInput(1);
				}
			});
			contentPanel.add(greenButton);
		}
		{
			yellowButton = new JButton("");
			yellowButton.setEnabled(false);
			yellowButton.setIcon(IconManager.getYellowIcon(false));
			yellowButton.setDisabledIcon(IconManager.getYellowIcon(false));
			yellowButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					checkInput(2);
				}
			});
			contentPanel.add(yellowButton);
		}
		{
			blueButton = new JButton("");
			blueButton.setEnabled(false);
			blueButton.setIcon(IconManager.getBlueIcon(false));
			blueButton.setDisabledIcon(IconManager.getBlueIcon(false));
			blueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
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
				pointsLabel = new JLabel("Points: 0");
				buttonPane.add(pointsLabel);
			}
			{
				levelLabel = new JLabel("Level: 1");
				buttonPane.add(levelLabel);
			}
			{
				playButton = new JButton("Play");
				buttonPane.add(playButton);
				getRootPane().setDefaultButton(playButton);
				playButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						inputOrder = new int[4];
						order = new int[4];

						position = 0;
						isShowningColors = true;
						hiIntensity = true;

						Random rand = new Random();
						for (int counter = 0; counter < 4; counter++) {
							order[counter] = rand.nextInt(3);
						}

						for (int counter = 0; counter < 4; counter++) {
							System.out.println(order[counter]);
						}

						timer.restart();
					}
				});
			}
		}
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (order != null) {
			if (isShowningColors && order.length > 0) {
				switch (order[position]) {
				case 0:
					redButton.setIcon(IconManager.getRedIcon(hiIntensity));
					redButton.setDisabledIcon(IconManager.getRedIcon(hiIntensity));
					break;
				case 1:
					greenButton.setIcon(IconManager.getGreenIcon(hiIntensity));
					greenButton.setDisabledIcon(IconManager.getGreenIcon(hiIntensity));
					break;
				case 2:
					yellowButton.setIcon(IconManager.getYellowIcon(hiIntensity));
					yellowButton.setDisabledIcon(IconManager.getYellowIcon(hiIntensity));
					break;
				case 3:
					blueButton.setIcon(IconManager.getBlueIcon(hiIntensity));
					blueButton.setDisabledIcon(IconManager.getBlueIcon(hiIntensity));
					break;
				}

				if (!hiIntensity)
					position++;
			}

			if (position >= order.length && isShowningColors) {
				position = 0;
				enableGameButtons();
				isShowningColors = false;

			}
		}
		hiIntensity = !hiIntensity;
	}

	public boolean checkInput(int freshInput) {
		inputOrder[position] = freshInput;
		for (int counter = 0; counter < position + 1; counter++) {
			if (order[position] != inputOrder[position]) {
				disableGameButtons();

				points -= pointsForWrong;
				pointsLabel.setText("Points: " + points);

				try {
					wrongSound.setFramePosition(0);
					wrongSound.start();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Problem playing sound");
				}
				JOptionPane.showMessageDialog(null, "Wrong color!");
				return false;
			}
		}
		position++;

		if (position >= order.length) {
			disableGameButtons();

			points += pointsForCorrect;
			pointsLabel.setText("Points: " + points);

			try {
				correctSound.setFramePosition(0);
				correctSound.start();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problem playing sound");
			}
			JOptionPane.showMessageDialog(null, "Correct!");
		}

		return true;
	}

	public void enableGameButtons() {
		redButton.setEnabled(true);
		greenButton.setEnabled(true);
		yellowButton.setEnabled(true);
		blueButton.setEnabled(true);
		playButton.setEnabled(false);
	}

	public void disableGameButtons() {
		redButton.setEnabled(false);
		greenButton.setEnabled(false);
		yellowButton.setEnabled(false);
		blueButton.setEnabled(false);
		playButton.setEnabled(true);
	}
}
