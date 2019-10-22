import bagel.Window;
import bagel.util.Point;
import bagel.util.Side;
import bagel.util.Vector2;

public class Ball extends Sprite{
    private double mouseX = 0;
    private double mouseY = 0;
    private double directionX;
    private double directionY;

    private double gravSpeed = 0;
    private static final double SPEED = 10;
    private static final double GRAVITY = 0.15;
    private static final int originX = 512;
    private static final int originY = 32;

    public Ball(){
        super(originX, originY,"res/ball.png");
    }

    public Ball(double x, double y){
        super(x, y, "res/ball.png");
    }

    public void update(){
        // represent the increase in speed due to gravity
        gravSpeed += GRAVITY;
        super.move(SPEED * directionX,  gravSpeed + SPEED * directionY);
        super.render();
        this.bounce();
    }

    public void reset(double mx, double my){
        gravSpeed = 0;
        mouseX = mx;
        mouseY = my;
        super.moveTo(originX, originY);
        directionX = (mouseX - originX) / Math.sqrt(Math.pow((mouseX - originX), 2) + Math.pow((mouseY - originY), 2));
        directionY = (mouseY- originY) / Math.sqrt(Math.pow((mouseX - originX), 2) + Math.pow((mouseY - originY), 2));
    }



    public Vector2 getVelocity(){
        Point p = new Point(SPEED * directionX, gravSpeed + SPEED * directionY);
        return p.asVector();
    }

    public void horizontalBounce(){
        directionX *= -1;
    }

    public void verticalBounce(){
        if ((gravSpeed + SPEED * directionY) > 0 && directionY < 0 ){
            gravSpeed = 0;
        }
        else{
            directionY *= -1;
            gravSpeed = 0;
        }
    }

    // bounce back when it hits the wall
    public void bounce(){
        if (super.bounding().centre().x <= 0 || super.bounding().centre().x >= Window.getWidth()) {
            this.horizontalBounce();
        }
    }

    // bounce when it hits a peg
    public void bounce(Side collisionSide){
        // determine how the ball will bounce
        if (collisionSide == Side.TOP || collisionSide == Side.BOTTOM) {
            this.verticalBounce();

        } else if (collisionSide == Side.LEFT || collisionSide == Side.RIGHT) {
            this.horizontalBounce();
        }
    }

    public double getDirectionX(){
        return directionX;
    }

    public double getDirectionY(){
        return directionY;
    }

    public void setDirection(double dx, double dy){
        directionX = dx;
        directionY = dy;
    }

    public double getGravSpeed(){
        return gravSpeed;
    }



    public void fullcopy(Ball ball){
        gravSpeed = ball.getGravSpeed();
        directionX = ball.getDirectionX();
        directionY = ball.getDirectionY();
        super.moveTo(ball.getX(), ball.getY());
    }

    public boolean isOut(){
        return(super.bounding().top() > Window.getHeight());
    }
}
