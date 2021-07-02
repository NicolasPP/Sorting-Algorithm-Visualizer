import processing.core.PApplet;
import controlP5.*;
import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    ControlP5 cp5;
    final int arr_len = 100;
    final int screenHeight = 1000;
    final int screenWidth = 1000;
    final float recWidth = ((float) screenWidth / (float) arr_len);
    final float recHeightFactor = ((float)screenHeight/ (float)arr_len);
    Rectangles [] rectanglesArray = new Rectangles[arr_len];
    int [] randomArray = new int[arr_len];
    int backGroundColour = 0;
    int opCounter =  0;
    String [] algorithmList = {"Selection Sort" , "Merge Sort", "Bubble Sort" , "Quick Sort", "Heap Sort"};
    ScrollableList scrollList;
    int [] indexArray = new int[arr_len];
    SortingAlgorithms solver = new SortingAlgorithms(this);
    boolean solving = false;
    boolean choose = false;
    boolean solved = false;
    Textlabel opCounterLabel;




    public void settings(){
        size(screenWidth, screenHeight);
    }



    public void setup(){
        randomArray = randomArrayGenerator(arr_len);
        cp5 = new ControlP5( this);
        cp5.addButton("Randomise")
                .setPosition(250 ,200)
                .setSize(100, 20);
        cp5.addButton("Solve")
                .setPosition(250 ,240)
                .setSize(100, 20);



        opCounterLabel = cp5.addTextlabel("counter" , Integer.toString(opCounter) , 30, 30 )
                .setColor(69);

        scrollList = cp5.addScrollableList("dropdown")
                .setPosition(250 , 280)
                .setBarHeight(20)
                .setItemHeight(20)
                .setValue(0)
                .setOpen(false);
        customizeScrollableList(scrollList);


        createRectangles();
    }
    public void createRectangles(){
        for (int i = 0 ; i < arr_len ; i++){
            rectanglesArray[i] = new Rectangles(
                    (i * recWidth),
                    screenHeight,
                    randomArray[i],
                    recHeightFactor,
                    recWidth,
                    this );
            indexArray[randomArray[i]] = i;
        }
    }

    public void updateCounter(int increment){
        if(cp5.getController("counter") != null){
            opCounter += increment;
            cp5.getController("counter").setValueLabel(Integer.toString(opCounter));
        }
    }



    public void customizeScrollableList(ScrollableList ddl){
        ddl.setBackgroundColor(color(190));
        ddl.setItemHeight(20);
        ddl.setBarHeight(15);
        ddl.addItems(algorithmList);
        ddl.setColorBackground(color(60));
        ddl.setColorActive(color(255, 128));
    }

    public void draw(){
        background(backGroundColour);
        for(Rectangles rec : rectanglesArray){
            rec.render();

        }
    }

    public void controlEvent(ControlEvent theEvent) {
        String button = theEvent.getController().getName();
        switch (button){
            case "Randomise":
                if (solving){
                    System.out.println("solving");
                        break;
                }
                for (Rectangles recs : rectanglesArray){
                    if (recs != null) {
                        recs.set_colour(backGroundColour);
                    }
                }
                solved = false;
                opCounter = 0;
                updateCounter(0);
                randomArray = randomArrayGenerator(arr_len);
                createRectangles();
                break;
            case "Solve" :
                String algorithmName = scrollList.getLabel();
                if (!algorithmName.equals("dropdown")){
                       choose = true;
                }
                if (solved){
                    System.out.println("already solved, randomize");
                    break;
                }
                if (solving){
                    System.out.println("already solving");
                    break;
                }else if(!choose){
                    System.out.println("Choose Algorithm");
                    break;
                }
                opCounter = 0;
                setAlgorithm(algorithmName);
                solving = true;
                solved = true;
            }
    }

    public void setAlgorithm (String algorithm){
        switch (algorithm){
            case "Selection Sort":
                randomArray = solver.selectionSort(randomArray);
                break;
            case "Merge Sort":
                solver.mergeSort(randomArray);
                break;
            case "Bubble Sort":
                solver.bubbleSort(randomArray, arr_len);
                break;
            case "Quick Sort":
                solver.quickSort(randomArray);
                break;
            case"Heap Sort":
                solver.heapSort(randomArray);
                break;
        }
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


    public void setGUIVisible(){
        cp5.getController("Solve").setVisible(true);
        cp5.getController("Randomise").setVisible(true);
        cp5.getController("dropdown").setVisible(true);
    }

    public void setGUIInvisible(){
        cp5.getController("Solve").setVisible(false);
        cp5.getController("Randomise").setVisible(false);
        cp5.getController("dropdown").setVisible(false);
    }

    public static void main(String[] args) {
        String [] processing  = {"SortingAlgorithmVisualizer"};
        PApplet.main(processing);
    }
}