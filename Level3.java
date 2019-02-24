import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Level3: Backflip and dash combo.
 */
public class Level3 extends Scrolling {
    //Creates String representing the map layout.
    private String[] code = { "3", "3", "0" };
    private int codeSymbol = 0;
    private boolean doorOpen = false;
    String[] textMap = {
        "eeeeee*****eeeeeeeeeeee",
        "eeeeee*ece*eeeeee***eee",
        "eeeeee*eee*eeeeee*m*eee",
        "*******eee**********eee",
        "*mkeeeee*eeeeeeeeee*eee",
        "****************eee*eee",
        "*bropeeeeeeeeeeeeee*eee",
        "*k******************eee",
        "*e*eeeeeeeeeeeeeeeeeeee",
        "*e*eeeeeeeeeeeeeeeeeeee",
        "*e****eeeeeeeeeeeeeeeee",
        "*eeee*eeeeeeeeeeeeeeeee",
        "****e*eeeeeeeeeeeeeeeee",
        "eee*e*************eeeee",
        "eee*eebropeeeeee.*eeeee",
        "eee***************eeeee",
    };
    
    
    public Level3() {
            super(550, 600, 1, 1100, 1200);
            setScrollingBackground(new GreenfootImage("sand2.jpg"));
            prepare();
            drawMap();
    }
        
    
    /**
     * Prepare method.
     */
    public void prepare() {
        Timer.timer = 0;
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
                int y = 100 + i * 50;
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
                    case 'c':
                        addObject(new Clue(), x, y);
                        break;
                    case 'k':
                        addObject(new CodeDoor(), x, y);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    
    public void act() {
        if (!doorOpen) {
            checkDoorCode();
        }
        checkLives();
        checkSounds();
        nextLevel();
        checkNoPack();
        clueTaken();
    }
    
    
    /**
     * Checks if Pack is alive or level reset button pressed.
     */
    private void checkNoPack() {
        if (getObjects(Pack.class).isEmpty() || Greenfoot.isKeyDown("r")) {
            stopSounds();
            PackLives.lives--;
            Greenfoot.setWorld(new Level3());
            Greenfoot.delay(5);
        }
    }
    
    
    /**
     * If SmallYummy empty goes to EndScreenWon.
     */
    public void nextLevel() {
        if (getObjects(SmallYummy.class).isEmpty()) {
            Score.score += (PackLives.lives * 1000);
            stopSounds();
            Greenfoot.setWorld(new EndScreenWon());
        }
    }
    
    
    /**
     * Shows a little message with code inside when Clue is taken by Pack.
     */
    private void clueTaken() {
        if (getObjects(Clue.class).isEmpty()) {
            showText("Oh, I think we found a clue!\n15 + 15 * 15 + 10 * 10 - 10", getWidth()/2, getHeight() - 50);
        }
    }
    
    
    /**
     * Checks if pressed key equals to the code's first symbol.
     * If it does -> checks the next one with next pressed key.
     * Else -> starts checking keys all over again.
     * If they mach -> removes CodeDoor from the world.
     */
    private void checkDoorCode() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (code[codeSymbol].equals(key)) {
                codeSymbol++;
                if (codeSymbol == code.length) {
                    removeObjects(getObjects(CodeDoor.class));
                    doorOpen = true;
                    return;
                }
            } else codeSymbol = 0;
        }
    }
}
