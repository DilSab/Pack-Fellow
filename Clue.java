import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Class that removes itself when touched.
 */
public class Clue extends GameObjects {
    
    
    public void act() {
        if (isTouching(Pack.class)) {
            getWorld().removeObject(this);
        }
    }    
}
