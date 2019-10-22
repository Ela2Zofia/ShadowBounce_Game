/**
 * this class represents the game board
 */

import bagel.util.Side;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private String line;
    private ArrayList<Peg> pegs = new ArrayList<>();

    /**
     * read from file and create the board filled with pegs
     * @param num the number of the level currently at
     */
    public Board(int num){
        try(BufferedReader br = new BufferedReader(new FileReader("res/"+ num +".csv"))){

            // read in all the data while constructing all the pegs accordingly
            int blue_num = 0;
            while ((line = br.readLine()) != null){
                String[] array = line.split(",");
                double x = Double.parseDouble(array[1]);
                double y = Double.parseDouble(array[2]);

                if (array[0].contains("blue")){
                    pegs.add(new Peg(x, y, array[0]));
                    blue_num++;
                }
                else if (array[0].contains("grey")){
                    pegs.add(new Grey_peg(x, y, array[0]));
                }
            }

            // pick 1/5 of random blue pegs and convert them into red pegs
            int red_num = blue_num / 5;
            Random rand = new Random();

            while (red_num > 0){
                int i = rand.nextInt(pegs.size());
                double pegX = pegs.get(i).getX();
                double pegY = pegs.get(i).getY();
                if (pegs.get(i).getColor().equals("blue")){
                    if (pegs.get(i).getShape().equals("horizontal")){
                        pegs.remove(i);
                        pegs.add(new Red_peg(pegX, pegY, "red_horizontal_peg"));
                    }
                    else if (pegs.get(i).getShape().equals("vertical")){
                        pegs.remove(i);
                        pegs.add(new Red_peg(pegX, pegY, "red_vertical_peg"));
                    }
                    else{
                        pegs.remove(i);
                        pegs.add(new Red_peg(pegX, pegY, "red_peg"));
                    }
                    red_num--;
                }
            }


        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * update the status of the board according to requirements and changes eg. collisions
     * @param balls the list of all the balls
     */
    public void update(ArrayList<Ball> balls){

        for (int k = 0; k < balls.size(); k++) {
            Ball ball = balls.get(k);

            // upon collision with the ball, peg gets deleted, and bounce back when hit
            for (int i = 0; i < pegs.size(); i++) {
                Side collisionSide = pegs.get(i).bounding().intersectedAt(ball.bounding().centre(), ball.getVelocity());
                if (collisionSide != Side.NONE) {

                    // generate new balls if hit green peg
                    if (pegs.get(i) instanceof Green_peg) {
                        ((Green_peg)pegs.get(i)).createNewBalls(balls, ball, pegs, i);
                    }

                    ball.bounce(collisionSide);

                    if (pegs.get(i).isDestructible()) {
                        pegs.remove(i);
                    }

                    // if a fireball strikes a peg, check all other pegs within the radius of destruction and remove them
                    if (ball instanceof Fire_ball) {
                        for (int j = 0; j < pegs.size(); j++) {
                            if (((Fire_ball) ball).detonate(pegs, j, balls, ball)){
                                j--;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * get the pegs
     * @return all the pegs
     */
    public ArrayList<Peg> getPegs(){
        return pegs;
    }

    /**
     * check if all the red pegs are eliminated
     */
    public boolean finished(){
        boolean isFinished = true;
        for (Peg peg : pegs){
            if (peg instanceof Red_peg){
                isFinished = false;
                break;
            }
        }
        return isFinished;
    }

    /**
     * draw the board
     */
    public void render(){
        for (Peg peg : pegs){
            peg.render();
        }
    }
}
