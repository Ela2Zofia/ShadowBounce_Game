/**
 * this class represents player's turn in-game
 */

import java.util.ArrayList;
import java.util.Random;

public class Turn {

    /**
     * number of shots the player has
     */
    private int shots = 20 + 1;
    /**
     * a power up
     */
    private Power_up powerUp;

    public Turn(){
        this.generatePowerUp();
    }

    /**
     * proceed to the next turn
     * @param board the current board the game is running on
     */
    public void nextTurn(Board board){
        shots--;
        System.out.printf("%d shots left\n", shots);
        this.revertGreenPeg(board.getPegs());
        this.generatePowerUp();
        this.generateGreenPeg(board.getPegs());
    }

    /**
     * give an extra shot to the player
     */
    public void extraShot(){
        shots++;
    }

    /**
     * get shots number
     * @return number of shots the player had left
     */
    public int getShots(){
        return shots;
    }

    /**
     * randomly decide if a power up should be created
     */
    public void generatePowerUp(){
        Random rand = new Random();
        if (rand.nextInt(5) == 1){
            powerUp = new Power_up();
        }
        else {
            powerUp = null;
        }
    }

    /**
     * check if the ball has hit the power up
     */
    public boolean isActivated(Ball ball){
        return powerUp != null && powerUp.bounding().intersects(ball.bounding());
    }

    /**
     * fire up the ball (power up the ball)
     */
    public Ball activate(Ball ball){
        powerUp = null;
        return new Fire_ball(ball);
    }

    /**
     * update the status of the power up
     */
    public void update(){
        if (powerUp != null){
            powerUp.render();
            powerUp.update();
        }
    }

    /**
     * randomly choose one of the blue pegs to become a green peg
     * @param pegs the list of all the pegs
     */
    private void generateGreenPeg(ArrayList<Peg> pegs){
        Random rand = new Random();
        int pegNum;
        boolean hasBlue = false;
        for (Peg peg: pegs){
            if (peg.getColor().equals("blue")){
                hasBlue = true;
                break;
            }
        }
        if (hasBlue) {
            do {
                pegNum = rand.nextInt(pegs.size());
            } while (!pegs.get(pegNum).getColor().equals("blue"));

            double x = pegs.get(pegNum).getX();
            double y = pegs.get(pegNum).getY();
            pegs.add(new Green_peg(x, y, "green_" + pegs.get(pegNum).getShape() + "_peg"));
            pegs.remove(pegNum);
        }
    }

    /**
     * switch the green peg back to normal if not activated
     */
    private void revertGreenPeg(ArrayList<Peg> pegs){
        if (pegs.get(pegs.size() - 1) instanceof Green_peg){
            double x = pegs.get(pegs.size() - 1).getX();
            double y = pegs.get(pegs.size() - 1).getY();
            pegs.add(new Peg(x, y, "blue_peg_"+pegs.get(pegs.size() - 1).getShape()));
            pegs.remove(pegs.size() - 2);
        }
    }
}
