package model;

/**
 * Class handling the scoring element of the game
 */

public class Score {
    private static Score score = null;

    private int totalScore;
    private final int SCORE_AMOUNT = 10;

    private Score(){
        totalScore = 0;
    }

    /**
     * This method creates a single instance of the class.
     * if the instance is null, the instance is created then returned
     * @return the only instance of the class.
     */
    public static Score getInstance(){
        if (score == null)
            score = new Score();
        return score;

    }

    /**
     * Increments the score by SCORE_AMOUNT;
     */
    public void incrementScore(){
        totalScore += SCORE_AMOUNT;
    }

    /**
     * Method returning the total score when called.
     * @return the total score.
     */
    public int getTotalScore(){
        return totalScore;
    }

    public void resetScore(){
        this.totalScore = 0;
    }

}
