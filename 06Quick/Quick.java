import java.util.*;
import java.io.*;

public class Quick{

  /*Choose a random pivot element, and modify the array such that:
 *1. Only the indices from start to end inclusive are considered in range
 *2. A random index from start to end inclusive is chosen, the corresponding
 *   element is designated the pivot element.
 *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
 *4. all elements in range that are larger than the pivot element are placed after the pivot element.
 *@return the index of the final position of the pivot element.
 */

 public static int[] partition (int[] data, int start, int end){
    Random a = new Random();
    int indRandom = start + a.nextInt(end - start + 1);




    int pivot = data[indRandom];
    data[indRandom] = data[start];
    data[start] = pivot;

    int lt = start, i = start+1, gt = end;





    while(i <= gt){
      if (data[i] > pivot){
        int temp = data[gt];
        data[gt] = data[i];
        data[i] = temp;
        gt--;
      }
      else if(data[i] == pivot){
        i++;
      }
      else{
        int temp = data[lt];
        data[lt] = data[i];
        data[i] = temp;
        i++;
        lt++;
      }
/*      System.out.print("i: " + i + " lt: " + lt + " gt " + gt);
      System.out.print(" Array: [");
      for (int j = 0; j < data.length; j++){
        System.out.print(data[j] + ", ");
      }
      System.out.print("]\n");*/


    }

    /*System.out.print("Array: [");
    for (int i = 0; i < data.length; i++){
      System.out.print(data[i] + ", ");
    }
    System.out.print("]");*/


  /*  System.out.println("Pivot: " + pivot);
    System.out.print("Array: [");
    for (int k = 0; k < data.length; k++){
      System.out.print(data[k] + ", ");
    }
    System.out.print("]\n");*/

    int[] ans = {lt, gt};

    return ans;
 }

 public static int quickselect(int[] ary, int pos){
   int[] i = partition(ary, 0, ary.length - 1);
   int start = 0, end = ary.length - 1;
   while(pos < i[0] || pos > i[1]){

     if (i[1] < pos){
       start = i[1];
     }
     else if (pos < i[0]){
       end = i[0];
     }

     i = partition(ary, start, end);
   }

   return ary[pos];
 }



 public static void partitionH(int[] data, int start, int end){
   if (start < end){
     int[] temp = partition(data,start, end);
     partitionH(data,start, temp[0] - 1);
     partitionH(data,temp[1] + 1,end);
   }
 }

 public static void quicksort(int[] data){
   partitionH(data,0,data.length -1);
 }


 public static void main(String[] args){

   /*for (int i = 0; i < 12; i++){
     int[] ary = {2,10,15,23,0,5,5,5,5,5,5,5};
     System.out.println(Quick.quickselect(ary,i));
   }

   int[] ary = {2,10,15,23,0,5,5,5,5,5,5,5};
   Quick.quicksort(ary);
   for (int i = 0; i < ary.length; i++){
     System.out.print(ary[i] + " ");
   }*/

   int[] a = new int[1000000], b = new int[1000000];
   for (int i = 0; i < 1000000; i++){
     int temp = (int)(Math.random() * 1000000);
     a[i] = temp;
     b[i] = temp;
   }

   Quick.quicksort(a);
   Arrays.sort(b);

   for (int i = 0; i < 1000000; i++){
     if (a[i] != b[i]){
       System.out.println("There is a mismatch");
     }
   }



 }

}
