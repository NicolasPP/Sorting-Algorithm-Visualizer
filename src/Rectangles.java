import processing.core.PApplet;
import java.awt.*;

public class Rectangles extends Canvas{
    float x;
    int  y;
    float height;
    float width;
    PApplet visualiser;
    int value;
    float heightFactor;
    int colorV1 = 255;
    int colorV2 = 255;
    int colorV3 = 255;


    public Rectangles(float x , int y,int value,float heightFactor, float width, PApplet visualiser){
        this.heightFactor  = heightFactor;
        this.x = x;
        this.y =y;
        this.value = value;
        this.height = -(heightFactor + (heightFactor*(float)value));
        this.visualiser = visualiser;
        this.width = width;
    }

    public void colorChange(int v1 , int v2 , int v3){
        this.colorV1 = v1;
        this.colorV2 = v2;
        this.colorV3 = v3;
    }

    public void swapHeights(Rectangles other){
        float tempHeight = other.height;
        int tempValue = other.value;
        other.value = this.value;
        this.value = tempValue;
        other.height = this.height;
        this.height  = tempHeight;
    }

    public void setHeight(int new_value){
        this.value = new_value;
        this.height =  -(heightFactor + (heightFactor*(float)value));

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

    public void tempColorChange(int delay){
        Thread t = new Thread(()->{
            this.colorChange(118,183,247);
            stop(delay);
            this.colorChange(255, 255 , 255);
        });
        t.start();
    }

    public void render(){
        visualiser.quad( x ,  y , x +  width , y  , x + width , y + height  , x , y + height + heightFactor);
        visualiser.fill(colorV1, colorV2, colorV3);
        visualiser.stroke(colorV1, colorV2, colorV3);
    }
}
