/**
 * this class represents the red pegs
 */

public class Red_peg extends Peg{
    public Red_peg(double dx, double dy, String type){
        super(dx, dy, "red_peg");
        if (type.equals("red_vertical_peg")){
            super.setImage("res/red-vertical-peg.png");
            super.setShape("vertical");
        }
        else if (type.equals("red_horizontal_peg")){
            super.setImage("res/red-horizontal-peg.png");
            super.setShape("horizontal");
        }
        else {
            super.setImage("res/red-peg.png");
        }
        super.setColor("red");
    }
}