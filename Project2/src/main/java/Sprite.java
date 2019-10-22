import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;


public abstract class Sprite {
    private Image image;
    private double x;
    private double y;

    public Sprite(double dx, double dy, String imageSrc) {
        image = new Image(imageSrc);
        x = dx;
        y = dy;
    }

    /**
     * get the bounding rectangle of the image
     * @return rectangle of the image
     */
    public Rectangle bounding(){
        Point centre = new Point(x, y);
        return image.getBoundingBoxAt(centre);
    }

    /**
     * get x value
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * get y value
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * set the image of the sprite
     * @param path image file path
     */
    public void setImage(String path){
        image = new Image(path);
    }

    /**
     * move the object to (dx, dy)
     * @param dx destination x coordinate
     * @param dy destination y coordinate
     */
    public void moveTo(double dx, double dy){
        x = dx;
        y = dy;
    }

    /**
     * move the object by (dx, dy)
     * @param dx x coordinate movement
     * @param dy y coordinate movement
     */
    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }

    /**
     * draw the object
     */
    public void render() {
        image.draw(x, y);
    }

    public abstract void update();

}
