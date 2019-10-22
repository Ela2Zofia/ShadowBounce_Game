/**
 * this class represents the fire balls
 */

import java.util.ArrayList;

public class Fire_ball extends Ball {
    private static final int effectRadius = 70;

    /**
     * substitute the current ball with a fire ball
     * @param ball the ball
     */
    public Fire_ball(Ball ball){
        super();
        super.fullcopy(ball);
        super.setImage("res/fireball.png");
    }

    /**
     * create new fire ball at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     */
    public Fire_ball(double x, double y){
        super(x, y);
        super.setImage("res/fireball.png");
    }


    /**
     * destroy all the pegs within the effect radius of fire ball when it touches a peg
     * @param pegs the list of all the pegs
     * @param i the index of the peg that makes contact
     * @param balls the list of all the balls
     * @param ball the ball that makes contact
     */
    public boolean detonate(ArrayList<Peg> pegs, int i, ArrayList<Ball> balls, Ball ball){
        if (Math.sqrt(Math.pow(pegs.get(i).getX() - super.getX(), 2) + Math.pow(pegs.get(i).getY() - super.getY(), 2)) < effectRadius && pegs.get(i).isDestructible()){
            if (pegs.get(i) instanceof  Green_peg) {
                // create new balls if a green peg is destroyed by fire ball's effect
                ((Green_peg) pegs.get(i)).createNewBalls(balls, ball, pegs, i);
            }
            pegs.remove(i);
            return true;
        }else {
            return false;
        }
    }

}
