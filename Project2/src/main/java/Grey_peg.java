/**
 * this class represents the grey pegs
 */

public class Grey_peg extends Peg{

    /**
     * create grey peg as instructed with its x coordinate at dx and y coordinate at dy
     */
    public Grey_peg(double dx, double dy, String type){

        super(dx, dy, "grey_peg");

        super.beIndestructible();

        if (type.equals("grey_peg_vertical")){
            super.setImage("res/grey-vertical-peg.png");
            super.setShape("vertical");
        }
        else if (type.equals("grey_peg_horizontal")){
            super.setImage("res/grey-horizontal-peg.png");
            super.setShape("horizontal");
        }
        else {
            super.setImage("res/grey-peg.png");
        }
        super.setColor("grey");
    }
}
