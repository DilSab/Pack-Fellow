import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.util.List;

/**
 * Subclass for all game objects in the world. 
 */
public abstract class GameObjects extends Actor
{
    protected double exactX;
    protected double exactY;
    protected double velX;
    protected double velY;
    
    
    /**
     * Sets the players location.  Takes integer value.
     */
    public void setLocation(int x, int y) {
        if (getDistanceToScrollingActor('x') - getWorld().getTotalXMovement() > Scrolling.WORLD_WIDTH/2) {
            x = (int) (getStartingPoint().getX() + Scrolling.WORLD_WIDTH/2);
        }
        else if (getDistanceToScrollingActor('x') - getWorld().getTotalXMovement() < -Scrolling.WORLD_WIDTH/2) {
            x = (int) (getStartingPoint().getX() - Scrolling.WORLD_WIDTH/2);
        }
        if(getDistanceToScrollingActor('y') - getWorld().getTotalYMovement() > Scrolling.WORLD_HEIGHT/2) {
            y = (int) (getStartingPoint().getY() + Scrolling.WORLD_HEIGHT/2);
        }
        else if(getDistanceToScrollingActor('y') - getWorld().getTotalYMovement() < -Scrolling.WORLD_HEIGHT/2) {
            y = (int) (getStartingPoint().getY() - Scrolling.WORLD_HEIGHT/2);
        }
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }
    
    
    /**
     * Sets the players location.  Takes double value.
     */
    public void setLocation(double x, double y) {
        if (getDistanceToScrollingActor('x') - getWorld().getTotalXMovement() > Scrolling.WORLD_WIDTH/2) {
            x = getStartingPoint().getX() + Scrolling.WORLD_WIDTH/2;
        }
        else if (getDistanceToScrollingActor('x') - getWorld().getTotalXMovement() < -Scrolling.WORLD_WIDTH/2) {
            x = getStartingPoint().getX() - Scrolling.WORLD_WIDTH/2;
        }
        if(getDistanceToScrollingActor('y') - getWorld().getTotalYMovement() > Scrolling.WORLD_HEIGHT/2) {
            y = getStartingPoint().getY() + Scrolling.WORLD_HEIGHT/2;
        }
        else if(getDistanceToScrollingActor('y') - getWorld().getTotalYMovement() < -Scrolling.WORLD_HEIGHT/2) {
            y = getStartingPoint().getY() - Scrolling.WORLD_HEIGHT/2;
        }
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }
    
    
    /**
     * Gets the distance to the ScrollingActor.
     */
    public double getDistanceToScrollingActor() {
        List<ScrollingActor> actors = getWorld().getObjects(ScrollingActor.class);
        if (actors != null && !actors.isEmpty()) {
            ScrollingActor actor = null;
            for (ScrollingActor scrollingActor : actors) {
                if (scrollingActor.isScrollingCenter()) {
                    actor = scrollingActor;
                }
            }
            if (actor == null) {
                System.err.println("No scrollingActor in the world.");
                return 0;
            }
            return Math.hypot(getExactX() - actor.getExactX(), getExactY() - actor.getExactY());
        }
        return 0;
    }
    

    /**
     * Gets one component of the distance to the ScrollingActor.
     */
    public double getDistanceToScrollingActor(char component) throws IllegalArgumentException {
        if (component == 'x') {
            List<ScrollingActor> actors = getWorld().getObjects(ScrollingActor.class);
            if (actors != null && !actors.isEmpty()) {
                ScrollingActor actor = null;
                for (ScrollingActor scrollingActor : actors) {
                    if (scrollingActor.isScrollingCenter()) {
                        actor = scrollingActor;
                    }
                }
                if (actor == null) {
                    System.err.println("No scrollingActor in the world.");
                    return 0;
                }
                return getExactX() - actor.getExactX();
            }
        }
        else if (component == 'y') {
            List<ScrollingActor> actors = getWorld().getObjects(ScrollingActor.class);
            if (actors != null && !actors.isEmpty()) {
                ScrollingActor actor = null;
                for (ScrollingActor scrollingActor : actors) {
                    if (scrollingActor.isScrollingCenter()) {
                        actor = scrollingActor;
                    }
                }
                if (actor == null) {
                    System.err.println("No scrollingActor in the world.");
                    return 0;
                }
                return getExactY() - actor.getExactY();
            }
        }
        else {
            throw new IllegalArgumentException("component (" + component + ") must be either 'x' or 'y'");
        }
        return 0;
    }
    
    
    /**
     * Gets the location of the starting point.
     */
    public java.awt.Point getStartingPoint() {
        List<ScrollingActor> actors = getWorld().getObjects(ScrollingActor.class);
        if (actors != null && !actors.isEmpty()) {
            ScrollingActor actor = null;
            for (ScrollingActor scrollingActor : actors) {
                if (scrollingActor.isScrollingCenter()) {
                    actor = scrollingActor;
                }
            }
            if (actor == null) {
                System.err.println("No scrollingActor in the world.");
                return null;
            }
            int x = actor.getX() + getWorld().getTotalXMovement();
            int y = actor.getY() + getWorld().getTotalYMovement();
            return new Point(x, y);
        }
        return null;
    }
    
    
    /**
     * Gets the players exact x location.
     */
    public double getExactX(){
        return exactX;
    }
    
    
    /**
     * Gets the players exact y location.
     */
    public double getExactY(){
        return exactY;
    }
    
    
    /**
     * Gets the players exact x location in the scrolling world.
     */
    public double getScrollingX() {
        return getDistanceToScrollingActor('x') - getWorld().getTotalXMovement();
    }
    
    
    /**
     * Gets the players exact y location in the scrolling world.
     */
    public double getScrollingY() {
        return getDistanceToScrollingActor('y') - getWorld().getTotalYMovement();
    }
    
    
    /**
     * Gets the current world casted.
     */
    public Scrolling getWorld() {
        return ((Scrolling) super.getWorld());
    }
}