import processing.core.PApplet;
import controlP5.*;
import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    ControlP5 cp5;
    final int arr_len = 100;
    final int screenHeight = 1000;
    final int screenWidth = 1000;
    final int recWidth = (screenWidth / arr_len);
    final int recHeightFactor = (screenHeight/ arr_len);
    Rectangles [] rectanglesArray = new Rectangles[arr_len];
    int [] randomArray = new int[arr_len];
    int recHeight;
    int backGroundColour = 255;
    final int delay = 60;
    int opCounter =  0;
    int buttonPressCounter = 0;




    public void settings(){
        size(screenWidth, screenHeight);
    }

    public void setup(){
        randomArray = randomArrayGenerator(arr_len);
        cp5 = new ControlP5( this);
        cp5.addButton("Randomise")
                .setValue(0)
                .setPosition(250 ,200)
                .setSize(100, 20);
        cp5.addButton("Solve")
                .setValue(0)
                .setPosition(250 ,240)
                .setSize(100, 20);
        cp5.addTextlabel("counter" , Integer.toString(opCounter) , 30, 30 )
                .setColor(69);
        createRectangles();
    }
    public void createRectangles(){
        for (int i = 0 ; i < arr_len ; i++){
            recHeight = - ( recHeightFactor + ( recHeightFactor * randomArray[i] ) );
            rectanglesArray[i] = new Rectangles((i * recWidth), screenHeight ,randomArray[i],recHeight, recWidth, this );
        }
    }

    public void draw(){
        background(backGroundColour);
        for(Rectangles rec : rectanglesArray){
            rec.render();
        }
        smooth();
        frameRate(60);
    }

    public void controlEvent(ControlEvent theEvent) {
        String button = theEvent.getController().getName();
        if (buttonPressCounter > 1){
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
                    opCounter = 0;
                    SortingAlgorithms solver = new SortingAlgorithms(this);
                    ArrayList <int []> ins = solver.selectionSort(randomArray);
                    swapHeights(ins);
            }
        }
        buttonPressCounter++;

    }

    public void swapHeights(ArrayList <int []> instructions){
        Thread t = new Thread(()->{
            for (int [] instruction : instructions){
                int recBiggerIndex = instruction[0];
                int recSmallerIndex = instruction[1];
                int opNum = instruction[2];
                Rectangles biggerRec = rectanglesArray[recBiggerIndex];
                Rectangles smallerRec = rectanglesArray[recSmallerIndex];
                int bigHeight = biggerRec.height;
                int smallHeight = smallerRec.height;
                biggerRec.set_height(smallHeight);
                smallerRec.set_height(bigHeight);
                biggerRec.set_colour(200);
                smallerRec.set_colour(200);
                opCounter = opCounter + opNum;
                cp5.getController("counter").setValueLabel(Integer.toString(opCounter));
                stop(delay );

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
