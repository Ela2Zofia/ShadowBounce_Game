/**
 * the game itself is represented by this class
 */


import bagel.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ShadowBounce extends AbstractGame {
    private ArrayList<Ball> balls;
    private Board board;
    private Bucket bucket;
    private Turn turn;
    private int boardNum = 0;

    public ShadowBounce(int width, int height) {
        super(width, height, "Shadow Bounce");
        bucket = new Bucket();
        board = new Board(boardNum);
        turn = new Turn();
        turn.nextTurn(board);
    }

    /**
     * main function of the game, starting point
     */
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce(1024, 768);
        game.run();
    }

    /**
     * updates the game 60 times per second
     */
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE) || boardNum > 4 ||turn.getShots() == 0){
            System.exit(0);
        }

        // if all red pegs are destroyed, advance to the next board
        if (board.finished() && balls == null){

                boardNum++;
                board = new Board(boardNum);
        }

        // B A L L'S O U T
        if (input.wasPressed(MouseButtons.LEFT) && balls == null) {
            balls = new ArrayList<>();
            balls.add(new Ball());
            balls.get(0).reset(input.getMouseX(), input.getMouseY());
        }

        if (balls != null) {
            board.update(balls);

            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);

                ball.update();

                // check if all the balls are out of window, if yes, start a new turn
                if (ball.isOut()) {
                    balls.remove(i);
                    i--;
                }
                if (balls.isEmpty()){
                    turn.nextTurn(board);
                    balls = null;
                    break;
                }

                // activate power up if struck
                if (turn.isActivated(ball)){
                    balls.add(turn.activate(ball));
                    balls.remove(ball);
                }

                // detect if the ball activates the bucket, if so, extra shot given
                if (ball.bounding().centre().y > Window.getHeight() && ball.bounding().intersects(bucket.bounding())){
                    turn.extraShot();
                }

            }

        }

        board.render();
        turn.update();
        bucket.update();

    }
}