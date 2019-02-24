import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level1: Short speedrun.
 */
public class Level1 extends Scrolling {
    //Creates String representing the map layout.
    String[] textMap = {
        "**********************************",
        "*.eeeeeeeeeeeeeebropeeeeeeeeeeeee*",
        "********************************e*",
        "*meeeeeeeeeeeeeeeeeeeeeeeeeeeeeee*",    
        "**********************************"
    };
    
    
    public Level1() {
            super(550, 600, 1, 2250, 1200);
            setScrollingBackground(new GreenfootImage("road.jpg"));
            stopSounds();
            prepare();
            drawMap();
    }
       
    
    /**
     * Creates Level1 world.
     */
    private void prepare() {
        Timer.timer = 0;
        stopSounds();
        addObject(new Score(), 75, 575);
        addObject(new PackLives(), 480, 575);
        addObject(new Pack(), 0, 0);
    }
    
    
    /**
     * Draws map using string with case switch.
     */
    private void drawMap() {
        for (int i = 0; i < textMap.length; i++) {
            String mapLine = textMap[i];
            for(int j = 0; j < mapLine.length(); j++) {
                char mapChar = mapLine.charAt(j);
                int y = 150 + i * 50;
                int x = j * 50;
                switch(mapChar) {
                    case '*':
                        addObject(new Wall(), x, y);
                        break;
                    case '.':
                        addObject(new SmallYummy(), x, y);
                        break;
                    case 'b':
                        addObject(new BlueGhost(), x, y);
                        break;
                    case 'r':
                        addObject(new RedGhost(), x, y);
                        break;
                    case 'o':
                        addObject(new OrangeGhost(), x, y);
                        break;
                    case 'p':
                        addObject(new PinkGhost(), x, y);
                        break;
                    case 'm':
                        addObject(new BigYummy(), x, y);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    
    public void act() {
        checkLives();
        checkSounds();
        nextLevel();
        checkNoPack();
    }
    
    
    /**
     * Checks if Pack is alive or level reset button pressed.
     */
    private void checkNoPack() {
        if (getObjects(Pack.class).isEmpty() || Greenfoot.isKeyDown("r")) {
            stopSounds();
            PackLives.lives--;
            Greenfoot.setWorld(new Level1());
            Greenfoot.delay(5);
        }
    }
    
    
    /**
     * If SmallYummy empty goes to next world.
     */
    private void nextLevel() {
        if (getObjects(SmallYummy.class).isEmpty()) {
            stopSounds();
            Greenfoot.setWorld(new Level2());
        }
    }
}
