package manager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.*;

import java.io.File;

public class AudioManager
{
	/** 
	 * AudioManager class is intended for loading and playing the sounds for the match game,
	 * that will serve as signs of correct or incorrect input. The functionality is intended
	 * to be simple as the class serves the purpose of making the code more readable and modular.
     *
     * @author Victor Anisimov
     * @since 5/13/2023         
	 */
	
    private AudioInputStream correctSoundAudioFile;
    private AudioInputStream wrongSoundAudioFile;
    private Clip correctSound;
    private Clip wrongSound;
	
    /**
	 * Creates the audio manager and tries to load the sounds.
	 * 
     * @author Victor Anisimov
     * @since 5/13/2023         
	 */
    
    public AudioManager()
    {
        try
        {
            correctSoundAudioFile = AudioSystem.getAudioInputStream(new File("resources/audio/correct.wav"));
            correctSound = AudioSystem.getClip();
            correctSound.open(correctSoundAudioFile);

            wrongSoundAudioFile = AudioSystem.getAudioInputStream(new File("resources/audio/wrong.wav"));
            wrongSound = AudioSystem.getClip();
            wrongSound.open(wrongSoundAudioFile);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Problem playing sound");
        }
    }
    
	/**
	 * Plays the sound for 'correct' attempt.
	 *  
	 * @author Victor Anisimov
	 * @since 5/13/2023         
	 */
    
    public void playCorrectAnswerSound() 
    {
    	correctSound.setFramePosition(0);
    	correctSound.start();
    }
    
	/**
	 * Plays the sound for 'incorrect' attempt.
	 * 
	 * @author Victor Anisimov
	 * @since 5/13/2023         
	 */
    public void playIncorrectAnswerSound() 
    {
		wrongSound.setFramePosition(0);
		wrongSound.start();
    }
}
