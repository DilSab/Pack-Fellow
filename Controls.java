import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Shows controls.  Checks for mouse input.
 */
public class Controls extends SoundManager {
    private int x = 0;
    private int y = 0;

    
    public Controls() {    
        super(550, 600, 1); 
    }
  
    
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            x = mouse.getX();
            y = mouse.getY();
            if (Greenfoot.mouseClicked(null)) {
                if (x > 410 && x < 549 && y > 552 && y < 599) {
                    Greenfoot.setWorld(new MainMenu());
                }
            }
        }
    }
}
