import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * @author Dilanas Sabaliauskas 1 Grupe.
 */


/**
 * MainMenu checks for mouse input to show controls, credits or start game.
 */
public class MainMenu extends SoundManager {
    Quit quit = new Quit();
    private int x = 0;
    private int y = 0;
    
    
    /**
     * Constructor for objects of class MainMenu.
     */
    public MainMenu() {    
        super(550, 600, 1); 
        EndScreenWon.soundWon.stop();
    }
  
    
    public void act() {
        SoundManager.soundMenu.play();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            x = mouse.getX();
            y = mouse.getY();
            if (Greenfoot.mouseClicked(null)) {
                if (x > 350 && x < 510 && y > 400 && y < 530) {
                    PackLives.lives = 5;
                    Score.score = 0;
                    Timer.timer = 0;
                    SoundManager.soundMenu.stop();
                    Greenfoot.setWorld(new Level1());
                } else if (x > 350 && x < 549 && y > 545 && y < 599) {
                    Greenfoot.setWorld(new Credits());
                } else if (x > 0 && x < 238 && y > 545 && y < 599) {
                    Greenfoot.setWorld(new Controls());
                } else if (x > 520 && x < 550 && y > 0 && y < 30) {
                    addObject(quit, getWidth()/2, getHeight()/2);
                }
            }
        }
    }
}
