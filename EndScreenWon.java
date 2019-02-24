import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * End screen and score.  Checks mouse input.
 */
public class EndScreenWon extends World {
    public static GreenfootSound soundWon = new GreenfootSound("victory.mp3");
    private int x = 0;
    private int y = 0;

    
    public EndScreenWon() {    
        super(550, 600, 1); 
        prepare();
    }
    
    
    public void prepare() {
        Timer.timer = 0;
        addObject(new Ending(), 400, 200);
    }
    
    
    public void act() {
        soundWon.play();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            x = mouse.getX();
            y = mouse.getY();
            if (Greenfoot.mouseClicked(null)) {
                if (x > 390 && x < 550 && y > 515 && y < 550) {
                    Greenfoot.setWorld(new MainMenu());
                } else if (x > 400 && x < 550 && y > 555 && y < 600) {
                    Greenfoot.setWorld(new Credits());
                }
            }
        }
    }
}
