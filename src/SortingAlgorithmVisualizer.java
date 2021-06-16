import processing.core.PApplet;
public class SortingAlgorithmVisualizer extends  PApplet{
    final int arr_len = 20;
    Rectangles [] arr = new Rectangles[arr_len];

    public void settings(){
        size(500, 500);
        for (int i = 0 ; i < arr_len ; i++){
            arr[i] = new Rectangles(10 * i , (i * 60), 400 , this );
        }
    }

    public void draw(){
        for(Rectangles rec : arr){
            rec.render();
        }
    }

    public static void main(String[] args) {
        String [] processing  = {"SortingAlgorithmVisualizer"};
        SortingAlgorithmVisualizer myS = new SortingAlgorithmVisualizer();
        PApplet.runSketch(processing,myS);
    }
}
