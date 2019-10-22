/**
 * this class represents the pegs
 */

public class Peg extends Sprite{
    private String color = "blue";
    private String shape = "circle";
    private boolean destructible = true;

    public Peg(double dx, double dy, String type){
        super(dx, dy, "res/peg.png");
        if (type.equals("blue_peg_horizontal")){
            super.setImage("res/horizontal-peg.png");
            shape = "horizontal";
        }
        else if (type.equals("blue_peg_vertical")){
            super.setImage("res/vertical-peg.png");
            shape = "vertical";
        }
    }

    /**
     * get the shape of the peg
     * @return shape type
     */
    public String getShape(){
        return shape;
    }

    /**
     * set the shape of the peg
     * @param str shape type
     */
    public void setShape(String str){
        shape = str;
    }

    /**
     * get the color of the peg
     * @return color of the peg
     */
    public String getColor(){
        return color;
    }

    /**
     * set the color of the peg
     * @param string color type
     */
    public void setColor(String string){
        color = string;
    }

    /**
     * set destructibility(?)
     */
    public void beIndestructible(){
        destructible = false;
    }

    /**
     * check if the peg is destructible
     * @return true if it is destructible, false otherwise
     */
    public boolean isDestructible(){
        return destructible;
    }

    /**
     * draw the peg
     */
    public void update(){
        super.render();
    }
}