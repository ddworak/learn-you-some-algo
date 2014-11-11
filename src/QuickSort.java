import java.util.Random;

/**
 * learning
 * Dawid Dworak
 * Date: 03/11/2014
 * Time: 09:57
 */
public class QuickSort {
    //for easier reading
    private static void swap(Comparable[] arr, int i1, int i2){
        Comparable temp = arr[i1];
        arr[i1]=arr[i2];
        arr[i2]=temp;
    }
    //lomuto partition (random pivot)
    private static int lomutoPartition(Comparable[] arr, int l, int r){
        int r_ind = new Random().nextInt(r-l+1)+l;
        swap(arr,r_ind,r);
        int i = l-1; //last index smaller than pivot
        for(int j=l;j<r;j++){
            if(arr[j].compareTo(arr[r])<=0){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,r);
        return i+1;
    }
    private static int hoarePartition(Comparable[] arr, int l, int r){
        int r_ind = new Random(0).nextInt(r-l+1)+l;
        System.out.println("Selected pivot "+arr[r_ind]);
        swap(arr, r_ind, r);
        Comparable pivot=arr[r];
        while(true){
            while(arr[l].compareTo(pivot) < 0)l++;
            while(arr[r].compareTo(pivot) > 0)r--;
            if(l<r){
                swap(arr,l,r);
            }
            else return r;
        }
    }

    //regular version with lomuto
    public static void qSortLomuto(Comparable[] arr,int l, int r){
        if(l<r){
            int pivot = lomutoPartition(arr,l,r);
            qSortLomuto(arr, l, pivot - 1);
            qSortLomuto(arr, pivot + 1, r);
        }
    }
    public static void qSortHoare(Comparable[] arr,int l, int r){
        if(l<r){
            int pivot = hoarePartition(arr, l, r);
            System.out.println();
            for(Comparable i : arr)System.out.print(i + " ");
            System.out.println("Got pivot "+arr[pivot]);
            qSortHoare(arr, l, pivot - 1);
            qSortHoare(arr, pivot + 1, r);
        }
    }

    public static void main(String[] args){

        int k = 10;
        Random r = new Random(0);
        Integer[] testarray = new Integer[k];
        for(int i=0; i<k;i++)testarray[i]=r.nextInt(100)+1;
        for(int i : testarray)System.out.print(i + " ");
        qSortHoare(testarray,0,k-1);
        for(int i : testarray)System.out.print(i + " ");
    }
}
