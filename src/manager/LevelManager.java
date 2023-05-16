package manager;

import java.util.Random;

public class LevelManager
{
	/** 
	 * LevelManager class is intended for calculating points and adjusting them and the level on
	 * progressing in the game. Assigning points relies on the current level which is treated as
	 * an indicator of the difficulty. The functionality is intended to be simple as the class 
	 * serves the purpose of making the code more readable and modular.
     *
     * @author Victor Anisimov
     * @since 5/10/2023         
	 */
	
    private int points;
    private int level;
    
    private int successfulCompletions;
    private int pointsForCorrect = 5;
    private int pointsForWrong = 10;

	private int buttonSequence[];
	private int inputSequence[];
	private int position;
	private Boolean isShowingColors = false;
	
	/**
	 * Creates the manager and sets the parameters to their default value.
	 * 
	 * @author Victor Anisimov
	 * @since 5/10/2023    
	 */
	
    public LevelManager() 
    {
        this.points = 0;
        this.level = 1;
        this.successfulCompletions = 0;

		this.position = 0;
    }
    
	/**
	 * Creates and sets (updates) the 'color' sequence for the game, based on the current level.
	 * 'Resets' the position parameter, which serves as an iterator for the sequence; resets the
	 * indicator of a new sequence, isShowingColors, that is needed to be true in order to display
	 * the sequence of colors to the player.
	 * 
	 * @author Victor Anisimov
	 * @since 5/12/2023         
	 */
    
    public void createInputSequence() 
    {
    	inputSequence = new int[level + 2];
		buttonSequence = new int[level + 2];

		position = 0;
		isShowingColors = true;

		Random rand = new Random();
		for (int counter = 0; counter < level + 2; counter++) 
		{
			buttonSequence[counter] = rand.nextInt(4);
		}
    }

    public int getPoints() 
    {
        return points;
    }

    public void setPoints(int points) 
    {
        this.points = points;
    }
    
	/**
	 * Used inside the checking function and based on successfulness of an input assigns or deducts 
	 * points to or from the user, using the level parameter to calculate the amount of points.
	 * The formula for amount points is described in the description of assignment and can be written as
	 * following - for correct points: 5 * 2 ^ [level], for incorrect points: 10 * 2 ^ [level]. 
	 * On four successful completions, 'proceeds to the level'.
	 * 
	 * @author Victor Anisimov
	 * @param isInputSuccessful succesfullness of an input (or series of).
	 * @since 5/12/2023         
	 */
    
    public void updatePoints(boolean isInputSuccessful)
    {
        if (isInputSuccessful)
        {
			this.points += pointsForCorrect * Math.pow(2, level);
            this.successfulCompletions++;
            if (successfulCompletions % 4 == 0)
            {
                this.level++;
                successfulCompletions = 0;
            }
        }
        else
        {
            this.points -= pointsForWrong * Math.pow(2, level);
        }
    }

    public int getLevel() 
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

	public int[] getButtonSequence() 
	{
		return buttonSequence;
	}
	
	public int getButtonAtCurrentPosition() 
	{
		return buttonSequence[position];
	}

	public void setButtonSequence(int buttonSequence[]) 
	{
		this.buttonSequence = buttonSequence;
	}

	public int[] getInputSequence() 
	{
		return inputSequence;
	}

	public void setInputSequence(int inputSequence[]) 
	{
		this.inputSequence = inputSequence;
	}
	
	public void setInputSequenceAtCurrentPosition(int input) 
	{
		this.inputSequence[position] = input;
	}

	public int getPosition() 
	{
		return position;
	}

	public void setPosition(int position) 
	{
		this.position = position;
	}

	public Boolean isShowingColors() 
	{
		return isShowingColors;
	}

	public void setShowingColors(Boolean isShowingColors) 
	{
		this.isShowingColors = isShowingColors;
	}
}
