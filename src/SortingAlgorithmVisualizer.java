import processing.core.PApplet;
import controlP5.*;

import java.awt.*;
import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    final int arr_len = 200;
    final int screenHeight = 1000;
    final int screenWidth = 1000;
    final int recWidth = (screenWidth / arr_len);
    final int recHeightFactor = (int)((screenHeight/ arr_len) * 1);
    Rectangles [] rectanglesArray = new Rectangles[arr_len];
    int [] randomArray = new int[arr_len];
    int recHeight;
    int backGroundColour = 255;
    int counter = 0;
    final int delay = 60;
    float alpha = 1.5F;




    public void settings(){
        size(screenWidth, screenHeight);
    }

    public void setup(){
        randomArray = randomArrayGenerator(arr_len);
        ControlP5 cp5 = new ControlP5( this);
        cp5.addButton("Randomise")
                .setValue(0)
                .setPosition(250 ,200)
                .setSize(100, 20);
        cp5.addButton("Solve")
                .setValue(0)
                .setPosition(250 ,240)
                .setSize(100, 20);
        createRectangles();
        smooth();
        frameRate(200);
    }
    public void createRectangles(){
        for (int i = 0 ; i < arr_len ; i++){
            recHeight = - ( recHeightFactor + ( recHeightFactor * randomArray[i] ) );
            rectanglesArray[i] = new Rectangles((i * recWidth), screenHeight ,recHeight, recWidth, this );
        }
    }

    public void draw(){
        background(backGroundColour);
        for(Rectangles rec : rectanglesArray){
            rec.render();
        }
    }


    public void controlEvent(ControlEvent theEvent) {
        String button = theEvent.getController().getName();
        if (counter > 1){
            switch (button){
                case "Randomise":
                    for (Rectangles recs : rectanglesArray){
                        if (recs != null) {
                            recs.set_colour(backGroundColour);
                        }
                    }
                    randomArray = randomArrayGenerator(arr_len);
                    createRectangles();
                    draw();
                    break;
                case "Solve":
                    SortingAlgorithms solver = new SortingAlgorithms();
                    ArrayList <int []> ins = solver.selectionSort(randomArray);
                    swapHeights(ins);
            }
        }
        counter++;

    }
    public void swapHeights(ArrayList <int []> instructions){
        Thread t = new Thread(()->{
            for (int [] instruction : instructions){
                int recBiggerIndex = instruction[0];
                int recSmallerIndex = instruction[1];
                Rectangles biggerRec = rectanglesArray[recBiggerIndex];
                Rectangles smallerRec = rectanglesArray[recSmallerIndex];
                int bigHeight = biggerRec.height;
                int smallHeight = smallerRec.height;
                biggerRec.set_height(smallHeight);
                smallerRec.set_height(bigHeight);
                stop(delay);

            }
        });
        t.start();
    }

    public int [] randomArrayGenerator(int size){
        int [] randArr = new int[size];
        LinkedHashSet<Integer> noRepetitionSet = new LinkedHashSet<>();
        int index = 0;
        while (noRepetitionSet.size() < size){
            int randInt = (int) (Math.random()*size);
            noRepetitionSet.add(randInt);
        }
        for (Integer integer : noRepetitionSet) {
            randArr[index] = integer;
            index++;
        }
        return randArr;
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

    public static void main(String[] args) {
        String [] processing  = {"SortingAlgorithmVisualizer"};
        PApplet.main(processing);
    }
}
