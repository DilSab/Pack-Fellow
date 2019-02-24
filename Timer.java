import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Used for tracking 'GhostBusters' mode.
 */
public class Timer extends Actor {
    public static int timer = 0;
    
    
    public void act() {
        setImage(new GreenfootImage("Ghost Busters:   " + 
                                    timer, 28, Color.WHITE, Color.BLACK));
    }
}
