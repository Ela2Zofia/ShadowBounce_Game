/**
 * this class represents the green pegs
 */

import java.util.ArrayList;

public class Green_peg extends Peg{

    /**
     * create green peg as instructed with its x coordinate at dx and y coordinate at dy
     */
    public Green_peg(double dx, double dy, String type){
        super(dx, dy, "green_peg");
        if (type.equals("green_vertical_peg")){
            super.setImage("res/green-vertical-peg.png");
            super.setShape("vertical");
        }
        else if (type.equals("green_horizontal_peg")){
            super.setImage("res/green-horizontal-peg.png");
            super.setShape("horizontal");
        }
        else {
                super.setImage("res/green-peg.png");
        }
        super.setColor("green");
    }

    /**
     * create new balls when the green peg is struck
     * @param balls the list of all the balls
     * @param ball the ball that makes contact
     * @param pegs the list of all the pegs
     * @param i the index of the peg that makes contact
     */

    public void createNewBalls(ArrayList<Ball> balls, Ball ball, ArrayList<Peg> pegs, int i){
        Ball newBall_1;
        Ball newBall_2;
        if (ball instanceof Fire_ball) {
            newBall_1 = new Fire_ball(pegs.get(i).getX(), pegs.get(i).getY());
            newBall_2 = new Fire_ball(pegs.get(i).getX(), pegs.get(i).getY());
        }else {
            newBall_1 = new Ball(pegs.get(i).getX(), pegs.get(i).getY());
            newBall_2 = new Ball(pegs.get(i).getX(), pegs.get(i).getY());
        }
        newBall_1.setDirection(-1 / Math.sqrt(2), -1 / Math.sqrt(2));
        newBall_2.setDirection(1 / Math.sqrt(2), -1 / Math.sqrt(2));
        balls.add(newBall_1);
        balls.add(newBall_2);
    }
}
