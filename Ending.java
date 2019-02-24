import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Hmm.
 */
public class Ending extends Actor {
    private int score = Score.score;
    
    
    public void act() {
        setImage(new GreenfootImage("Score:     " + 
                                    score, 28, Color.WHITE, Color.BLACK));
    }
}
