import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manages sound between menu switching.  Keeps reference.
 */
public abstract class SoundManager extends World {
    public static GreenfootSound soundMenu = new GreenfootSound("mainMenu.mp3");
    
    
    /**
     * Constructor for objects of class SoundManager.
     * 
     */
    public SoundManager(int screenWidth, int screenHeight, int cellSize) {
        super(screenWidth, screenHeight, cellSize);
    }
    
    
    public void soundPlaying() {
        if (!soundIsPlaying()) {
            soundMenu.play();
        }
    }
    
    
    public void soundStop() {
        soundMenu.stop();
    }
    
    
    public boolean soundIsPlaying() {
        if (soundMenu.isPlaying()) {
            return true;
        } else {
            return false;
        }
    }
}
