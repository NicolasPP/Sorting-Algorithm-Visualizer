import processing.core.PApplet;
import controlP5.*;
import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    ControlP5 cp5;
    int arr_len = 100;
    final int screenHeight = 1000;
    final int screenWidth = 1000;
    float recWidth = ((float) screenWidth / (float) arr_len);
    float recHeightFactor = ((float)screenHeight/ (float)arr_len);
    Rectangles [] rectanglesArray = new Rectangles[arr_len];
    int [] randomArray = new int[arr_len];
    int backGroundColour = 0;
    int opCounter =  0;
    String [] algorithmList = {"Selection Sort" , "Merge Sort", "Bubble Sort" , "Quick Sort", "Heap Sort" , "Gnome Sort" , "Cocktail Sort"};
    ScrollableList scrollList;
    int [] indexArray = new int[arr_len];
    SortingAlgorithms solver = new SortingAlgorithms(this);
    boolean solving = false;
    boolean choose = false;
    boolean solved = false;
    Textlabel opCounterLabel;
    Slider arrLenSlider;
    Textlabel arrLenLabel;




    public void settings(){
        size(screenWidth, screenHeight);
    }



    public void setup(){
        randomArray = randomArrayGenerator(arr_len);
        cp5 = new ControlP5( this);
        cp5.addButton("Randomise")
                .setColorLabel(color(0))
                .setPosition(30 ,50)
                .setColorForeground(color(200,100))
                .setColorActive(color(100,100))
                .setColorBackground(color(200,100))
                .setSize(100, 20);
        cp5.addButton("Solve")
                .setColorLabel(color(0))
                .setPosition(30 ,80)
                .setColorForeground(color(200,100))
                .setColorActive(color(100,100))
                .setColorBackground(color(200,100))
                .setSize(100, 20);


        opCounterLabel = cp5
                .addTextlabel("counter" , "Comparisons: " + opCounter , 30, 30 )
                .setColor(69);

        arrLenLabel = cp5
                .addTextlabel("arrLen","Array length: " + arr_len , 250 ,30)
                .setColor(69);



        int num = cp5.getController("counter").getWidth();
        arrLenSlider = cp5
                .addSlider("array Length")
                .setRange(50 , 500)
                .setValue(100)
                .setNumberOfTickMarks(451)
                .showTickMarks(false)
                .setPosition( num - 75 , 30)
                .setLabelVisible(false)
                .setTriggerEvent(2);

        scrollList = cp5.addScrollableList("Algorithm")
                .setPosition(30 , 110)
                .setBarHeight(20)
                .setItemHeight(20)
                .setColorForeground(color(200,100))
                .setColorActive(color(100,100))
                .setColorBackground(color(200,100))
                .setValue(0)
                .setOpen(false);
        customizeScrollableList(scrollList);
        arr_len = (int) arrLenSlider.getValue();
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
            cp5.getController("counter").setValueLabel("Comparisons: " + opCounter);
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
                if (!algorithmName.equals("Algorithm")){
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
                break;
            case "array Length":
                if (!solving && arrLenSlider != null)
                {
                    solved = false;
                    arr_len = (int) arrLenSlider.getValue();
                    indexArray = new int[arr_len];
                    recWidth = ((float) screenWidth / (float) arr_len);
                    recHeightFactor = ((float)screenHeight/ (float)arr_len);
                    rectanglesArray = new Rectangles[arr_len];
                    randomArray = new int[arr_len];
                    randomArray = randomArrayGenerator(arr_len);
                    arrLenLabel.setValue("Array length: " + arr_len);
                    createRectangles();
                }
                else
                {
                    System.out.println("already solving why");
                }
                break;
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
            case"Gnome Sort":
                solver.gnomeSort(randomArray, arr_len);
                break;
            case"Cocktail Sort":
                solver.cocktailSort(randomArray);
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
        cp5.getController("Algorithm").setVisible(true);
    }

    public void setGUIInvisible(){
        cp5.getController("Solve").setVisible(false);
        cp5.getController("Randomise").setVisible(false);
        cp5.getController("Algorithm").setVisible(false);
    }

    public static void main(String[] args) {
        String [] processing  = {"SortingAlgorithmVisualizer"};
        PApplet.main(processing);
    }
}