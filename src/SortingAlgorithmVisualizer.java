import processing.core.PApplet;

import java.util.*;

public class SortingAlgorithmVisualizer extends  PApplet{
    final int arr_len = 100;
    Rectangles [] arr = new Rectangles[arr_len];
    final int screenHeight = 1200;
    final int screenWidth = 1000;
    final int recWidth = (int)(screenWidth / arr_len);
    int recHeight;
    int recHeightFactor = (int)(screenHeight/ arr_len);

    public void settings(){
        int [] test = randomArray(arr_len);

        size(screenWidth, screenHeight);
        for (int i = 0 ; i < arr_len ; i++){
            recHeight = - ( recHeightFactor + ( recHeightFactor * test[i] ) );
            arr[i] = new Rectangles((i * recWidth), screenHeight ,recHeight, recWidth, this );
        }
    }

    public void draw(){
        for(Rectangles rec : arr){
            rec.render();
        }
    }
    public int [] randomArray(int size){
        int [] randArr = new int[size];
        LinkedHashSet<Integer> noRepetitionSet = new LinkedHashSet<>();
        Random rand = new Random();
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
        SortingAlgorithmVisualizer myS = new SortingAlgorithmVisualizer();
        PApplet.runSketch(processing,myS);
    }
}
