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

    interface quickSorting{

        int partition(int [] unsortedArray , int low , int high);
        void qSort(int [] unsortedArray , int low , int high);
    }

    interface heapSortInter{
        void sort(int [] unsortedArray);
        void heap(int [] unsortedArray , int size , int rootNode);
    }

    public void mergeSort(int [] unsortedList){
        t = new Thread(()->{
            SortingAlgorithms.mergeSortInter solve = new SortingAlgorithms.mergeSortInter() {
                @Override
                public void merge(int [] unsortedArray , int middleIndex , int leftIndex , int rightIndex)
                {
                    // calculate size of each subArrays

                    int listSize1 = middleIndex - leftIndex + 1;
                    int listSize2 = rightIndex - middleIndex;


                    visualizer.updateCounter(2);


                    // create sub_arrays

                    int [] subArray1 = new int[listSize1];
                    int [] subArray2 = new int[listSize2];

                    visualizer.updateCounter(2);

                    // fill subArrays with data

                    for (int i = 0 ; i < listSize1; i++){
                        subArray1[i] = unsortedArray[leftIndex + i];
                        visualizer.updateCounter(1);
                    }


                    for (int x = 0 ; x < listSize2; x++){
                        subArray2[x] = unsortedArray[middleIndex + 1 + x];
                        visualizer.updateCounter(1);
                    }


                    // merge subArrays

                    int subArrayIndex1 = 0;
                    int subArrayIndex2 = 0;

                    int mergedIndex = leftIndex;

                    visualizer.updateCounter(3);

                    while (subArrayIndex1 < listSize1 && subArrayIndex2 < listSize2){
                        visualizer.opCounter++;
                        if (subArray1[subArrayIndex1] <= subArray2[subArrayIndex2]){
                            visualizer.rectanglesArray[visualizer.indexArray[subArray1[subArrayIndex1]]].tempColorChange(delay);
                            visualizer.rectanglesArray[visualizer.indexArray[subArray2[subArrayIndex2]]].tempColorChange(delay);
                            unsortedArray[mergedIndex] = subArray1[subArrayIndex1];
                            visualizer.updateCounter(1);
                            visualizer.rectanglesArray[mergedIndex].setHeight(subArray1[subArrayIndex1]);
                            stop(delay/3);
                            subArrayIndex1++;
                        }
                        else{
                            unsortedArray[mergedIndex] = subArray2[subArrayIndex2];
                            visualizer.updateCounter(1);
                            visualizer.rectanglesArray[mergedIndex].setHeight(subArray2[subArrayIndex2]);
                            stop(delay/3);
                            subArrayIndex2++;
                        }
                        mergedIndex++;

                    }

                    // writing whatever left of subArray1 if any

                    while(subArrayIndex1 < listSize1){
                        unsortedArray[mergedIndex] = subArray1[subArrayIndex1];
                        visualizer.rectanglesArray[visualizer.indexArray[subArray1[subArrayIndex1]]].tempColorChange(delay);
                        visualizer.rectanglesArray[mergedIndex].setHeight(subArray1[subArrayIndex1]);
                        stop(delay/3);
                        subArrayIndex1++;
                        mergedIndex++;
                        visualizer.updateCounter(2);
                    }

                    // writing whatever left of subArray2 if any

                    while (subArrayIndex2 < listSize2){
                        unsortedArray[mergedIndex] = subArray2[subArrayIndex2];
                        visualizer.rectanglesArray[visualizer.indexArray[subArray2[subArrayIndex2]]].tempColorChange(delay);
                        visualizer.rectanglesArray[mergedIndex].setHeight(subArray2[subArrayIndex2]);
                        stop(delay/3);
                        visualizer.updateCounter(2);
                        subArrayIndex2++;
                        mergedIndex++;
                    }


                }

                @Override
                public void sort(int [] unsortedArray , int leftIndex, int rightIndex)
                {
                    if (leftIndex < rightIndex){
                        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;// middle point
                        visualizer.updateCounter(3);
                        sort(unsortedArray , leftIndex , middleIndex); // sort first and second half
                        sort(unsortedArray , middleIndex + 1 , rightIndex);
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
            visualizer.updateCounter(1);
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
                visualizer.updateCounter(size);
                for (int i = 0; i < size ; i++){
                    visualizer.updateCounter(1);
                    if (list[i] == maximum){
                        visualizer.updateCounter(1);
                        swap(list , size -1  , i , delay , delay);
                        break;
                    }
                }
                size--;
                visualizer.updateCounter(1);
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
            boolean swapped;

            for (index = 0; index < size - 1; index++){
                swapped = false;
                for (index2 = 0; index2 < size - index - 1; index2++){
                    visualizer.updateCounter(1);
                    if (unsortedArray[index2] > unsortedArray[index2 + 1]){
                        swap(unsortedArray, index2, index2 + 1 , delay/60, delay/60);
                        swapped = true;
                    }
                }if (!swapped){
                    break;  
                }
            }
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        visualizer.setGUIInvisible();
        t.start();
    }



    public void quickSort(int [] list){
        t = new Thread (() ->{
            SortingAlgorithms.quickSorting solve = new quickSorting() {

                @Override
                public int partition(int[] unsortedArray, int low, int high) {

                    int pivot = unsortedArray[high];


                    int smallIndex = (low - 1);


                    for (int i = low ; i <= high -1; i++ )
                    {
                        if (unsortedArray[i] < pivot){
                            smallIndex++;
                            swap(unsortedArray, smallIndex, i , delay/ 2 , delay /3);
                        }
                    }
                    swap(unsortedArray, smallIndex + 1 , high , delay/ 2 , delay / 3);

                    return (smallIndex + 1);
                }

                @Override
                public void qSort(int[] unsortedArray, int low, int high) {
                    if (low < high )
                    {
                        int partitioningIndex = partition(unsortedArray, low, high);
                        qSort(unsortedArray, low , partitioningIndex - 1);
                        qSort(unsortedArray, partitioningIndex + 1 , high);
                    }

                }
            };
            solve.qSort(list, 0 , list.length -1);
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        visualizer.setGUIInvisible();
        t.start();
    }


    public void heapSort(int [] list){
        t = new Thread (() -> {
            SortingAlgorithms.heapSortInter solve = new heapSortInter() {
                @Override
                public void sort(int[] unsortedArray) {

                    int size = unsortedArray.length;

                    for (int rootNode = size / 2 - 1; rootNode >= 0; rootNode--) {
                        heap(unsortedArray, size, rootNode);
                    }

                    for (int i = size - 1; i > 0; i--) {
                        swap(unsortedArray, 0, i, delay/ 3, delay /3);
                        heap(unsortedArray,i , 0);
                    }


                }

                @Override
                public void heap(int[] unsortedArray, int size, int rootNode) {
                    int biggest = rootNode;
                    int left = 2 * rootNode + 1;
                    int right = 2 * rootNode + 2;

                    if (left < size && unsortedArray[left] > unsortedArray[biggest]) {
                        biggest = left;
                    }

                    if (right < size && unsortedArray[right] > unsortedArray[biggest]) {
                        biggest = right;
                    }

                    if (biggest != rootNode) {
                        swap(unsortedArray, rootNode, biggest, delay / 3, delay / 3);
                        heap(unsortedArray,size, biggest);
                    }


                }
            };
            solve.sort(list);
            visualizer.solving = false;
            visualizer.setGUIVisible();
        });
        t.start();
        visualizer.setGUIInvisible();
    }


    public void swap(int[] array, int index1, int index2, int d , int d2) {
        int tempVal = array[index1];
        array[index1] = array[index2];
        array[index2] = tempVal;
        Rectangles rec1 = visualizer.rectanglesArray[index1];
        Rectangles rec2 = visualizer.rectanglesArray[index2];
        rec1.tempColorChange(d2);
        rec2.tempColorChange(d2);
        stop(d);
        rec1.swapHeights(rec2);
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