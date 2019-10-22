/**
 * this class represents the power up
 */

import bagel.Window;

public class Power_up extends Sprite{
    private double destinationX;
    private double destinationY;
    private double directionX;
    private double directionY;
    private static final double SPEED = 3;


    public Power_up(){
        super(Math.random() * Window.getWidth(), Math.random() * Window.getHeight(), "res/powerup.png");
        this.setUp();
    }

    /**
     * update power up, change its movement and destination according to requirements
     */
    public void update(){
        super.render();
        if (Math.sqrt(Math.pow(super.getX() - destinationX, 2) + Math.pow(super.getY() - destinationY, 2)) < 5){
            this.setUp();
        }
        else{
            super.move(directionX * SPEED, directionY * SPEED);
        }
    }

    /**
     * set up the basic info needed for the power up to move
     */
    public void setUp(){
        destinationX = Math.random() * Window.getWidth();
        destinationY = Math.random() * Window.getHeight();
        directionX = (destinationX - super.getX())
                / Math.sqrt(Math.pow(super.getX() - destinationX, 2) + Math.pow(super.getY() - destinationY, 2));
        directionY = (destinationY - super.getY())
                / Math.sqrt(Math.pow(super.getX() - destinationX, 2) + Math.pow(super.getY() - destinationY, 2));
    }

}
