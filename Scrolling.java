import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


/**
 * Scrolling system.
 * Creates a scrolling texture of the given backround image.
 * Constantly checks movement and moves that texture accordingly.
 */
public abstract class Scrolling extends World {
    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;

    protected int totalXMovement = 0;
    protected int totalYMovement = 0;
    
    private GreenfootSound sound = new GreenfootSound("ready.mp3");
    private GreenfootSound sound2 = new GreenfootSound("normal.mp3");
    
    protected GreenfootImage texture;

    
    public Scrolling(int screenWidth, int screenHeight, int cellSize, int scrollingWidth, int scrollingHeight) {
        super(screenWidth, screenHeight, cellSize, false);
        WORLD_WIDTH = scrollingWidth;
        WORLD_HEIGHT = scrollingHeight;
    }
    
    
    /**
     * Reset the position of the ScrollingActor 
     * and set the position of all other objects in the world.
     */
    public final void resetPlayersPosition(ScrollingActor scrollingActor) {
        int xMovement = (int) ((double) getWidth()/2 - scrollingActor.getExactX());
        int yMovement = (int) ((double) getHeight()/2 - scrollingActor.getExactY());
        
        totalXMovement += xMovement;
        totalYMovement += yMovement;
        List<Actor> actors = getObjects(Actor.class);
        
        
        for (Actor actor : actors) {
            if (actor instanceof ScrollingActor) {
                ((ScrollingActor) actor).setLocation(actor.getX() + xMovement, actor.getY() + yMovement, false);
            }
            else if (actor instanceof OnScreen) {
                ;
            }
            else {
                actor.setLocation(actor.getX() + xMovement, actor.getY() + yMovement);
            }
        }
        createTexture();
    }
    
    
    /**
     * Creates a texture on a background image that is moving accordingly.
     */
    protected final void createTexture() {
        int x;
        int y;
        
        
        if (totalXMovement > 0) {
            for (x = totalXMovement; x > 0; x -= texture.getWidth()) {
                ;
            }
        }
        else {
            for (x = totalXMovement; x < 0; x += texture.getWidth()) {
                ;
            }
            x -= texture.getWidth();
        }
        if (totalYMovement > 0) {
            for (y = totalYMovement; y > 0; y -= texture.getHeight()) {
                ;
            }
        }
        else {
            for (y = totalYMovement; y < 0; y += texture.getHeight()) {
                ;
            }
            y -= texture.getHeight();
        }
        getBackground().clear();
        for (int i = x; i < getWidth(); i += texture.getWidth()) {
            for (int j = y; j < getHeight(); j += texture.getHeight()) {
                getBackground().drawImage(texture, i, j);
            }
        }
    }
    

    /**
     * Change the background image of the scrolling world to the given image.
     */
    public void setScrollingBackground(GreenfootImage bgImage) {
        texture = bgImage;
    }
    

    /**
     * Get the total movement in x direction.
     */
    public int getTotalXMovement() {
        return totalXMovement;
    }
    
    
    /**
     * Get the total movement in y direction.
     */
    public int getTotalYMovement() {
        return totalYMovement;
    }
    
    
    /**
     * Checks which sounds to play according to game state
     */
    public void checkSounds() {
        if (Timer.timer == 0) {
            sound.stop();
            sound2.play();
        } else if (Timer.timer > 0){
            sound2.stop();
            sound.play();
        }
    }
    
    
    /**
     * Stops sounds
     */
    public void stopSounds() {
            sound.stop();
            sound2.stop();
    }
    
    
    /**
     * Checks lives.  If equal 0 stops sounds and shows EndScreenLost.
     */
    public void checkLives() {
        if (PackLives.lives == 0) {
            Timer.timer = -1;
            stopSounds();
            Greenfoot.setWorld(new EndScreenLost());
        }
    }
}