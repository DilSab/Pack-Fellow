import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Used when exiting the game.
 */
public class Quit extends Actor {
    private int x;
    private int y;
    
    
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            x = mouse.getX();
            y = mouse.getY();
            if (Greenfoot.mouseClicked(null)) {
                if (x > 140 && x < 220 && y > 320 && y < 360) {
                    getWorld().removeObject(this);
                    SoundManager.soundMenu.stop();
                    Greenfoot.stop();
                } else if (x > 325 && x < 405 && y > 320 && y < 360) {
                    getWorld().removeObject(this);
                }
            }
        }
    }    
}
