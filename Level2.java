import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Short maze.  Introduces backflip usage in game.
 */
public class Level2 extends Scrolling {
    //Creates String representing the map layout.
    String[] textMap = {
        "*******************",
        "*eeeeeeeeeeeeeee*.*",
        "*e*************e*e*",
        "*e*eeeeeeeeeee*e*e*",
        "*e*eeeeeeeeeee*e*e*",
        "*e*eeeeeeeeeee*b*e*",
        "*e*eeeeeeeee***r*e*",
        "*e*eeeeeeeee*m*o*e*",
        "*e*************p*e*",
        "*eeeeeeeeeeeee*eee*",
        "*******************"
    };
    
    
    public Level2() {
            super(550, 600, 1, 1100, 1200);
            setScrollingBackground(new GreenfootImage("bricks2.jpg"));
            prepare();//this method just adds some objects to the world.
            drawMap();
    }
       
    
    /**
     * Prepare method.
     */
    public void prepare() {
        Timer.timer = 0;
        stopSounds();
        addObject(new Score(), 75, 575);
        addObject(new PackLives(), 480, 575);
        addObject(new Pack(), getWidth()/2, getHeight()/2);
    }
    
    /**
     * Draws map using string with case switch.
     */
    private void drawMap() {
        for (int i = 0; i < textMap.length; i++) {
            String mapLine = textMap[i];
            for(int j = 0; j < mapLine.length(); j++) {
                char mapChar = mapLine.charAt(j);
                int y = -150 + i * 50;
                int x = -275 + j * 50;
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
            Greenfoot.setWorld(new Level2());
            Greenfoot.delay(5);
        }
    }
    
    
    /**
     * If SmallYummy empty goes to next world.
     */
    public void nextLevel() {
        if (getObjects(SmallYummy.class).isEmpty()) {
            stopSounds();
            Greenfoot.setWorld(new Level3());
        }
    }
}
