/**
 * this class represents the bucket
 */

import bagel.Window;

public class Bucket extends Sprite {
    private int direction = -1;
    private static final double SPEED = 4;
    private static final double originX = 512;
    private static final double originY = 744;

    public Bucket(){
        super(originX, originY, "res/bucket.png");
    }

    /**
     * update the moving direction of the bucket when the sides of the window are hit
     */
    public void update(){
        super.move(direction * SPEED, 0);
        if (super.bounding().left() == 0 || super.bounding().right() > Window.getWidth()){
            direction *= -1;
        }
        super.render();
    }
}
