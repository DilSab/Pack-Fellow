import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is just an example. You can delete it or change the code.
 * It's not necessary for the scrolling system.
 */
public class Pack extends ScrollingActor {
    private int multiplier = 200;  //increases when combo eating ghosts
    private GreenfootImage[] imagesPackEaten = new GreenfootImage[11];
    private GreenfootImage[] imagesPackEat = new GreenfootImage[3];
    private GreenfootSound sound2 = new GreenfootSound("popSound.mp3");
    private int mouthState = 0;
    private int mouthState2 = 0;
    private boolean backflip = false;
    private boolean airtime = true;
    private int airtim = 0;
    private int prevRotation = 0;
    private int rotation = 0;
    private int dashing = 15;
    private int cooldown = 0;
    
    
    /**
     * Puts Pack's images for mouth and death animations into array
     */
    public Pack() {
        for (int i = 0; i < 11; i++) {
            imagesPackEaten[i] = new GreenfootImage("pacRip" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            imagesPackEat[i] = new GreenfootImage("pac" + i + ".png");
        }
    }
    
    
    public void act() {
        move(3);
        pacMouth();
        dashPack();
        doABackflip();
        checkRotation();
        checkIfGhostBusters();
        checkIfTouched();
    }
    
    
    /**
     * Changes Pack image to move his mouth with slight delay.
     */
    private void pacMouth() {
        if (mouthState2 == 4) {
            setImage(imagesPackEat[mouthState]);
            mouthState++;
            mouthState2 = 0;
            if (mouthState == 3) {
                mouthState = 0;
            }
        } else {
            mouthState2++;
        }
    }
    
    
    /**
     * Checks for keyboard input and changes rotation accordingly
     */
    private void checkRotation() {
        if (Greenfoot.isKeyDown("Up")) {
            setRotation(270);
        } else if (Greenfoot.isKeyDown("Down")) {
            setRotation(90);
        } else if (Greenfoot.isKeyDown("Left")) {
            setRotation(180);
        } else if (Greenfoot.isKeyDown("Right")) {
            setRotation(0);
        }
    }
    
    
    /**
     * Checks if Pack touched BigYummy and
     * adds time to allow Pack eat Ghosts
     */
    private void checkIfGhostBusters() {
        if (isTouching(BigYummy.class)) {
            Timer.timer = 800;
            removeTouching(BigYummy.class);
        }
    }
    
    
    /**
     * Checks if Actor touched SmallYummy and if he should eat or 
     * be eaten by Ghosts
     */
    private void checkIfTouched() {
        if (isTouching(SmallYummy.class)) {
            Score.score += 3546;
            removeTouching(SmallYummy.class);
        }
        if (Timer.timer > 0) {
            Timer.timer--;
            checkIfTouchedGhostBusters();
        } else if (Timer.timer == 0) {
            multiplier = 200;
            if (isTouching(BlueGhost.class) || isTouching(RedGhost.class) ||
                isTouching(OrangeGhost.class) || isTouching(PinkGhost.class)) {
                sound2.play();
                for (int i = 0; i < 11; i++) {
                    setRotation(0);
                    setImage(imagesPackEaten[i]);
                    Greenfoot.delay(3);
                }
                getWorld().removeObject(this);
            }
        }
    }
    
    
    /**
     * Checks for all the Ghosts during 'GhostBusters'
     */
    private void checkIfTouchedGhostBusters() {
        if (isTouching(BlueGhost.class)) {
            Score.score += multiplier;
            multiplier = multiplier * 2;
            removeTouching(BlueGhost.class);
        }
        if (isTouching(RedGhost.class)) {
            Score.score += multiplier;
            multiplier = multiplier * 2;
            removeTouching(RedGhost.class);
        }
        if (isTouching(OrangeGhost.class)) {
            Score.score += multiplier;
            multiplier = multiplier * 2;
            removeTouching(OrangeGhost.class);
        }
        if (isTouching(PinkGhost.class)) {
            Score.score += multiplier;
            multiplier = multiplier * 2;
            removeTouching(PinkGhost.class);
        }
    }
    
    
    /**
     * Checks for walls.  Acts as collision.
     */
    private void CheckWall() {
        if (isTouching(Wall.class) || isTouching(CodeDoor.class)) {
            move(-3);
        }
    }
    
    
    /**
     * Checks if Pack wants to do a backflip (gracefully).
     */
    private void doABackflip() {
        if (Greenfoot.isKeyDown("b")) {
            backflip = true;
            if (airtime) {
                prevRotation = getRotation();
                airtime = false;
            }
        }
        if (backflip) {
            if (airtim < 15) {
                int prevRot = getRotation();
                setRotation(270);
                move(7);
                setRotation(prevRot);
                airtim++;
            } else if (airtim >= 15 && airtim < 30) {
                int prevRot = getRotation();
                setRotation(270);
                move(-7);
                setRotation(prevRot);
                airtim++;
            } else {
                airtim = 0;
            }
            turn(-12);
            move(-3);
            rotation++;
        }
        if (rotation >= 30) {
            rotation = 0;
            backflip = false;
            airtime = true;
            setRotation(prevRotation);
        } 
    }
    
    
    /**
     * Pack dashes like a mad lad (works only during 'GhostBusters').
     * With collision.
     */
    private void dashPack() {
        if (Timer.timer > 0) {
            if (Greenfoot.isKeyDown("v")) {
                if (cooldown == 0) {
                    move(dashing);
                    if (isTouching(Wall.class) || isTouching(CodeDoor.class)) {
                        move(-(dashing + 3));
                    }
                    dashing--;
                    if (dashing == 0) {
                        dashing = 15;
                        cooldown = 100;
                    }
                }
            }
            if (cooldown > 0) {
                cooldown--;
            }
            CheckWall();
        } else {
            CheckWall();
        }
    }
}