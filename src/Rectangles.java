import processing.core.PApplet;

import java.awt.*;

public class Rectangles extends Canvas{
    int x;
    int  y;
    int height;
    int width;
    PApplet visualiser;


    public Rectangles(int height, int x , int y, PApplet visualiser){
        this.x = x;
        this.y =y;
        this.height = height;
        this.visualiser = visualiser;
        this.width = 60;
    }

    public void render(){
        visualiser.rect(x , y, width , height);
    }
}
