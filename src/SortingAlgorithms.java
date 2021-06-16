import java.util.Arrays;


public class SortingAlgorithms {

    public int[] merge(int [] list1 , int [] list2){
        int size1 = list1.length;
        int size2 = list2.length;
        int list1Index = 0;
        int list2Index = 0;
        int sizeSorted = size1 + size2;
        int [] mergedList  = new int[sizeSorted];
        while (true){
            if (list1Index == size1){
                System.arraycopy(
                        list2,
                        list2Index,
                        mergedList,
                        list1Index+list2Index,
                        size2-list2Index
                );
                break;
            }
            if(list2Index == size2){
                System.arraycopy(
                        list1,
                        list1Index,
                        mergedList,
                        list1Index+list2Index,
                        size1-list1Index
                );
                break;
            }
            int min1 = list1[list1Index];
            int min2 = list2[list2Index];
            if (min1 < min2){
                mergedList[list1Index+list2Index] = min1;
                list1Index++;
            }else {
                mergedList[list1Index+list2Index] = min2;
                list2Index++;
            }
        }
        return mergedList;
    }

    public int [] mergeSort(int [] unsortedList){
        int size = unsortedList.length;
        if (size == 1){
            return unsortedList;
        }else{
            int halfSize = size / 2;
            int [] list1  = new int[halfSize];
            int [] list2  = new int[size - halfSize];
            System.arraycopy(unsortedList,0,list1,0,halfSize);
            System.arraycopy(unsortedList,halfSize,list2,0,size -halfSize);
            list1 = mergeSort(list1);
            list2 = mergeSort(list2);
            return merge(list1 , list2);
        }
    }
    public int [] selectionSort(int [] list){
        int size = list.length;
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
            for (int i = 0; i < size ; i++){
                if (list[i] == maximum){
                    int temp = list[i];
                    list[i] = list[size-1];
                    list[size-1] = temp;
                }
            }
            size--;
        }
        return list;
    }

}
