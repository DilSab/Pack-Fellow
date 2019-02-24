import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueGhost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedGhost extends Ghosts {
    /**
     * Images for every rotation
     */
    private GreenfootImage imageUp = new GreenfootImage ("redUp.png"); 
    private GreenfootImage imageDown = new GreenfootImage ("redDown.png");
    private GreenfootImage imageLeft = new GreenfootImage ("redLeft.png"); 
    private GreenfootImage imageRight = new GreenfootImage ("redRight.png");
    
    
    public void act() {
        //turnRandom();
        CheckWall();
        checkRotationAndImage();
        setMovementSpeed();
    }
    
    
    /**
     * Adds some random movement by changing direction with about 
     * 10% probability every frame
     */
    private void turnRandom() {
        if (Greenfoot.getRandomNumber(100) > 90) {
            checkRotation();
        }
    }
    
    
    /**
     * Checks which images should be set according to the 'GhostBusters'
     * state
     */
    private void checkRotationAndImage() {
        if (Timer.timer == 0) {
            rotationNormal();
        } else {
            rotationScared();
        }
    }
    
    
    /**
     * Sets random rotation and sets image for normal Ghost
     */
    private void turnRotationNormal() {
        int turnDirection = Greenfoot.getRandomNumber(4);
        if (turnDirection == 0 && Timer.timer == 0) {
            setRotation(270);
            setImage(imageUp);
        } else if (turnDirection == 1) {
            setRotation(90);
            setImage(imageDown);
        } else if (turnDirection == 2) {
            setRotation(180);
            setImage(imageLeft);
        } else if (turnDirection == 3) {
            setRotation(0); 
            setImage(imageRight);
        }
    }
    
    
    /**
     * Sets random rotation and sets image for scared Ghost
     */
    private void turnRotationScared() {
        int turnDirection = Greenfoot.getRandomNumber(4);
        if (turnDirection == 0) {
            setRotation(270);
            setImage(imageScaredUp);
        } else if (turnDirection == 1) {
            setRotation(90);
            setImage(imageScaredDown);
        } else if (turnDirection == 2) {
            setRotation(180);
            setImage(imageScaredLeft);
        } else if (turnDirection == 3) {
            setRotation(0); 
            setImage(imageScaredRight);
        }
    }
    
    
    /**
     * Checks normal Ghost rotation and sets image
     * (for accurate image switch)
     */
    private void rotationNormal() {
        int rotation = getRotation();
        if (rotation == 270) {
            setImage(imageUp);
        } else if (rotation == 90) {
            setImage(imageDown);
        } else if (rotation == 180) {
            setImage(imageLeft);
        } else if (rotation == 0) {
            setImage(imageRight);
        }
    }
    
    
    /**
     * Checks scared Ghost rotation and sets image
     * (for accurate image switch)
     */
    private void rotationScared() {
        int rotation = getRotation();
        if (rotation == 270) {
            setImage(imageScaredUp);
        } else if (rotation == 90) {
            setImage(imageScaredDown);
        } else if (rotation == 180) {
            setImage(imageScaredLeft);
        } else if (rotation == 0) {
            setImage(imageScaredRight);
        }
    }
    
    
    /**
     * Combines both random rotation switch methods
     */
    private void checkRotation() {
        if (Timer.timer == 0) {
            turnRotationNormal();
        } else {
            turnRotationScared();
        }
    }
    
    
    /**
     * Sets Ghost's movement speed according to 'GhostBusters'
     */
    private void setMovementSpeed() {
        if (Timer.timer == 0) {
            move(4);
        } else {
            move(2);
        }
    }
    
    
    /**
     * Checks for walls.  Acts as collision
     */
    private void CheckWall() {
        if (isTouching(Wall.class) || isTouching(CodeDoor.class)) {
            if (Timer.timer == 0) {
                move(-4);
            } else {
                move(-2);
            }
            checkRotation();
        }
    }
}
