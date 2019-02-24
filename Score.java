import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class for score.
 */
public class Score extends OnScreen {
    public static int score = 0;
    
    
    public void act() {
        setImage(new GreenfootImage("Score:     " + 
                                    score, 28, Color.DARK_GRAY, null));
    }
}