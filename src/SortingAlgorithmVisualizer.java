import processing.core.PApplet;
import controlP5.*;
import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    final int arr_len = 100;
    Rectangles [] rectanglesArray = new Rectangles[arr_len];
    final int screenHeight = 1000;
    final int screenWidth = 1000;
    final int recWidth = (screenWidth / arr_len);
    int recHeight;
    int recHeightFactor = (int)((screenHeight/ arr_len) * 0.75);
    int [] randomArray = new int[arr_len];
    int backGroundColour = 255;
    int counter = 0;


    public void settings(){
        size(screenWidth, screenHeight);
    }

    public void setup(){
        randomArray = randomArrayGenerator(arr_len);
        ControlP5 cp5 = new ControlP5( this);
        cp5.addButton("Randomise")
                .setValue(0)
                .setPosition(500 ,200)
                .setSize(200, 19);
        cp5.addButton("Solve")
                .setValue(0)
                .setPosition(500 ,219)
                .setSize(200, 19);
        createRectangles();
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
                    int [] test = {36,8,2,1};
                    SortingAlgorithms solver = new SortingAlgorithms();
                    ArrayList <int []> ins = solver.selectionSort(test);
                    for (int [] list : ins){
                        for(int nums : list){
                            System.out.print(Integer.toString(nums) + " ");
                        }
                        System.out.println();
                    }
            }
        }
        counter++;

    }

    public int [] randomArrayGenerator(int size){
        int [] randArr = new int[size];
        LinkedHashSet<Integer> noRepetitionSet = new LinkedHashSet<>();
        int index = 0;
        while (noRepetitionSet.size() < size){
            int randInt = (int) (Math.random()*size);
            noRepetitionSet.add(randInt);
        }
        Iterator<Integer> it = noRepetitionSet.iterator();
        while (it.hasNext()){
            randArr[index] = it.next();
            index++;
        }
        return randArr;
    }

    public static void main(String[] args) {
        String [] processing  = {"SortingAlgorithmVisualizer"};
        PApplet.main(processing);
    }
}
