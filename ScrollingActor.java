import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


/**
 * Contains the scrolling actor. 
 * This actor will always be in the middle of the screen 
 * and the objects around him will be moved.
 */
public abstract class ScrollingActor extends GameObjects {
    private boolean scrollingCenter;
    
    
    public ScrollingActor() {
        scrollingCenter = true;
    }
    
    
    public ScrollingActor(boolean scrollingCenter) {
        this.scrollingCenter = scrollingCenter;
    }
    
    
    public final void addedToWorld(World world) {
        if (scrollingCenter) {
            List<ScrollingActor> scrollingActors = getWorld().getObjects(ScrollingActor.class);
            if (scrollingActors != null && !scrollingActors.isEmpty()) {
                for (ScrollingActor scrollingActor : scrollingActors) {
                    if (!scrollingActor.equals(this)) {
                        scrollingActor.setScrollingCenter(false);
                    }
                }
            }
        }
    }
    
    
    /**
     * SetLocation method uses integer values.  x - new x location; y - new y location.
     */
    public final void setLocation(int x, int y) {
        super.setLocation(x, y);
        if (scrollingCenter) {
            getWorld().resetPlayersPosition(this);
        }
    }
    
    
    /**
     * SetLocation method uses double values.  x - new x location; y - new y location.
     */
    public final void setLocation(double x, double y) {
        super.setLocation(x, y);
        if (scrollingCenter) {
            getWorld().resetPlayersPosition(this);
        }
    }
    
    
    /**
     * SetLocation method which uses double values.
     * Boolean = true -> reset player's position.  Boolean = false -> don't reset player's position.
     */
    public final void setLocation(double x, double y, boolean resetPosition) {
        super.setLocation(x, y);
        if (scrollingCenter && resetPosition) {
            getWorld().resetPlayersPosition(this);
        }
    }

    
    public final boolean isScrollingCenter() {
        return scrollingCenter;
    }
    
    
    public final void setScrollingCenter(boolean scrollingCenter) {
        this.scrollingCenter = scrollingCenter;
    }
}