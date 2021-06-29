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
    int heightFactor;


    public Rectangles(int x , int y,int value,int heightFactor, int width, PApplet visualiser){
        this.heightFactor  = heightFactor;
        this.x = x;
        this.y =y;
        this.value = value;
        this.height = -(heightFactor + (heightFactor*value));
        this.visualiser = visualiser;
        this.width = width;
        this.colour = 0;
    }
    public void set_colour(int newColour){
        this.colour = newColour;
    }

    public void swapHeights(Rectangles other){
        int tempHeight = other.height;
        int tempValue = other.value;
        other.value = this.value;
        this.value = tempValue;
        other.height = this.height;
        this.height  = tempHeight;
    }

    public void setHeight(int new_value){
        this.value = new_value;
        this.height = -(heightFactor + (heightFactor*value));

    }

    public void stop(long milli){
        try
        {
            Thread.sleep(milli);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void tempColorChange(){
        Thread t = new Thread(()->{
            this.set_colour(255);
            stop(60);
            this.set_colour(0);
        });
        t.start();
    }

    public void render(){
        visualiser.rect(x , y, width , height);
        visualiser.fill(colour);
//        visualiser.stroke(255);
    }
}
