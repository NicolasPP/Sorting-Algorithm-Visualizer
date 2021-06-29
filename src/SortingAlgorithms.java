import java.util.Arrays;

public class SortingAlgorithms {

    // Sorting algorithms changed for visualisation.

    SortingAlgorithmVisualizer visualizer;
    final int delay = 60;
    Thread t;

    public SortingAlgorithms(SortingAlgorithmVisualizer visualizer){
        this.visualizer = visualizer;

    }

    interface mergeSortInter{
        void merge(int [] unsortedArray , int middleIndex , int leftIndex , int rightIndex);
        void sort(int [] unsortedArray , int leftIndex, int rightIndex);
    }

    public void mergeSort(int [] unsortedList){
        t = new Thread(()->{
            SortingAlgorithms.mergeSortInter solve = new SortingAlgorithms.mergeSortInter() {
                @Override
                public void merge(int [] unsortedArray , int middleIndex , int leftIndex , int rightIndex)
                {
                    visualizer.cp5.getController("counter").setValueLabel(Integer.toString(visualizer.opCounter));
                    // calculate size of each subArrays

                    int listSize1 = middleIndex - leftIndex + 1;
                    int listSize2 = rightIndex - middleIndex;

                    visualizer.opCounter += 2;


                    // create sub_arrays

                    int [] subArray1 = new int[listSize1];
                    int [] subArray2 = new int[listSize2];

                    visualizer.opCounter += 2;

                    // fill subArrays with data

                    for (int i = 0 ; i < listSize1; i++){
                        subArray1[i] = unsortedArray[leftIndex + i];
                    }
                    visualizer.opCounter += listSize1;

                    for (int x = 0 ; x < listSize2; x++){
                        subArray2[x] = unsortedArray[middleIndex + 1 + x];
                    }

                    visualizer.opCounter += listSize2;

                    // merge subArrays

                    int subArrayIndex1 = 0;
                    int subArrayIndex2 = 0;

                    int mergedIndex = leftIndex;

                    visualizer.opCounter += 3;

                    while (subArrayIndex1 < listSize1 && subArrayIndex2 < listSize2){
                        visualizer.opCounter++;
                        if (subArray1[subArrayIndex1] <= subArray2[subArrayIndex2]){
                            visualizer.rectanglesArray[visualizer.indexArray[subArray1[subArrayIndex1]]].tempColorChange();
                            visualizer.rectanglesArray[visualizer.indexArray[subArray2[subArrayIndex2]]].tempColorChange();
                            unsortedArray[mergedIndex] = subArray1[subArrayIndex1];
                            visualizer.opCounter++;
                            visualizer.rectanglesArray[mergedIndex].setHeight(subArray1[subArrayIndex1]);
                            stop(delay/3);
                            subArrayIndex1++;
                        }
                        else{
                            unsortedArray[mergedIndex] = subArray2[subArrayIndex2];
                            visualizer.opCounter++;
                            visualizer.rectanglesArray[mergedIndex].setHeight(subArray2[subArrayIndex2]);
                            stop(delay/3);
                            subArrayIndex2++;
                        }
                        mergedIndex++;

                    }

                    // writing whatever left of subArray1 if any

                    while(subArrayIndex1 < listSize1){
                        unsortedArray[mergedIndex] = subArray1[subArrayIndex1];
                        visualizer.rectanglesArray[visualizer.indexArray[subArray1[subArrayIndex1]]].tempColorChange();
                        visualizer.rectanglesArray[mergedIndex].setHeight(subArray1[subArrayIndex1]);
                        stop(delay/3);
                        subArrayIndex1++;
                        mergedIndex++;
                        visualizer.opCounter+= 2;
                    }

                    // writing whatever left of subArray2 if any

                    while (subArrayIndex2 < listSize2){
                        unsortedArray[mergedIndex] = subArray2[subArrayIndex2];
                        visualizer.rectanglesArray[visualizer.indexArray[subArray2[subArrayIndex2]]].tempColorChange();
                        visualizer.rectanglesArray[mergedIndex].setHeight(subArray2[subArrayIndex2]);
                        stop(delay/3);
                        visualizer.opCounter+= 2;
                        subArrayIndex2++;
                        mergedIndex++;
                    }
                    visualizer.cp5.getController("counter").setValueLabel(Integer.toString(visualizer.opCounter));


                }

                @Override
                public void sort(int [] unsortedArray , int leftIndex, int rightIndex)
                {
                    if (leftIndex < rightIndex){
                        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;// middle point
                        visualizer.opCounter += 3;
                        sort(unsortedArray , leftIndex , middleIndex); // sort first and second half
                        sort(unsortedArray , middleIndex + 1 , rightIndex);
                        visualizer.cp5.getController("counter").setValueLabel(Integer.toString(visualizer.opCounter));
                        merge(unsortedArray,middleIndex, leftIndex, rightIndex); //merging sorted list

                    }
                }

            };
            solve.sort(unsortedList, 0, unsortedList.length - 1);
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        visualizer.setGUIInvisible();
        t.start();
    }


    public int [] selectionSort(int [] list ){
        t = new Thread(()->{
            int size = list.length;
            visualizer.opCounter++;
            while(size > 1){
                int [] mutatedList = new int[size];
                System.arraycopy(
                        list,
                        0,
                        mutatedList,
                        0,
                        size
                );
                int maximum = Arrays.stream(mutatedList).max().getAsInt();
                visualizer.opCounter = visualizer.opCounter  + size ;
                for (int i = 0; i < size ; i++){
                    visualizer.opCounter++;
                    if (list[i] == maximum){
                        visualizer.opCounter++;
                        int temp = list[i];
                        visualizer.opCounter++;
                        list[i] = list[size-1];
                        visualizer.opCounter++;
                        list[size-1] = temp;
                        visualizer.opCounter++;
                        Rectangles max = visualizer.rectanglesArray[i];
                        Rectangles other = visualizer.rectanglesArray[size-1];
                        max.tempColorChange();
                        other.tempColorChange();
                        stop(delay);
                        max.swapHeights(other);

                        break;
                    }
                }
                size--;
                visualizer.opCounter++;
                visualizer.cp5.getController("counter").setValueLabel(Integer.toString(visualizer.opCounter));
            }
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        visualizer.setGUIInvisible();
        t.start();
        return list;
    }

    public void bubbleSort(int [] unsortedArray, int size){
        t = new Thread(()->{
            int index;
            int index2;
            int tempNum;
            boolean swap;

            for (index = 0; index < size - 1; index++){
                swap = false;
                for (index2 = 0; index2 < size - index - 1; index2++){
                    if (unsortedArray[index2] > unsortedArray[index2 + 1]){
                        tempNum = unsortedArray[index2];
                        visualizer.rectanglesArray[index2].swapHeights(visualizer.rectanglesArray[index2 + 1]);
                        stop(delay/60);
                        unsortedArray[index2] = unsortedArray[index2 + 1 ];
                        unsortedArray[index2 + 1] = tempNum;
                        swap = true;
                    }
                }if (swap == false){
                    break;
                }
            }
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        visualizer.setGUIInvisible();
        t.start();
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

}
