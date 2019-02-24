import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Screen if lives reach zero.  Shows score.
 */
public class EndScreenLost extends World {
    private GreenfootSound sound3 = new GreenfootSound("defeat.mp3");
    private int x = 0;
    private int y = 0;
    
    
    /**
     * Constructor for objects of class EndScreenLost.
     * 
     */
    public EndScreenLost()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(550, 600, 1); 
        prepare();
    }
    
    
    private void prepare() {
        addObject(new Ending(), 235, 550);
    }
    
    
    public void act() {
        sound3.play();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            x = mouse.getX();
            y = mouse.getY();
            if (Greenfoot.mouseClicked(null)) {
                if (x > 120 && x < 425 && y > 270 && y < 345) {
                    PackLives.lives = 5;
                    Score.score = 0;
                    Timer.timer = 0;
                    sound3.stop();
                    Greenfoot.setWorld(new Level1());
                } else if (x > 120 && x < 430 && y > 360 && y < 470) {
                    sound3.stop();
                    Greenfoot.setWorld(new MainMenu());
                }
            }
        }
    }
}
