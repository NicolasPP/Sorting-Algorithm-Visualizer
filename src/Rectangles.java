import processing.core.PApplet;
import java.awt.*;

public class Rectangles extends Canvas{
    int x;
    int  y;
    int height;
    int width;
    PApplet visualiser;
    int colour;
    int value;


    public Rectangles(int x , int y,int value,int height, int width, PApplet visualiser){
        this.x = x;
        this.y =y;
        this.value = value;
        this.height = height;
        this.visualiser = visualiser;
        this.width = width;
        this.colour = 0;

    }
    public void set_colour(int value){
        this.colour = value;
    }

    public void set_height(int new_height){
        this.height = new_height;
    }

    public void render(){
        visualiser.rect(x , y, width , height);
        visualiser.fill(colour);
        visualiser.stroke(255);
    }
}
