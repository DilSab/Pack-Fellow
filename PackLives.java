import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class for lives.
 */
public class PackLives extends OnScreen {
    public static int lives = 5;
    
    
    public void act() {
        setImage(new GreenfootImage("Lives:  " + 
                                    lives, 28, Color.DARK_GRAY, null));
    }
}
