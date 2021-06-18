import processing.core.PApplet;
import java.awt.*;

public class Rectangles extends Canvas{
    int x;
    int  y;
    int height;
    int width;
    PApplet visualiser;
    int colour;


    public Rectangles(int x , int y,int height, int width, PApplet visualiser){
        this.x = x;
        this.y =y;
        this.height = height;
        this.visualiser = visualiser;
        this.width = width;
        int color = 0;

    }
    public void set_colour(int value){
        this.colour = value;
        this.render();
    }

    public void render(){
        visualiser.rect(x , y, width , height);
        visualiser.fill(colour);
        visualiser.stroke(255);
    }
}
