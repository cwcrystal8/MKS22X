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
 public static int partition ( int [] data, int start, int end){
    Random a = new Random();
    int indRandom = start + a.nextInt(end - start + 1);
    int pivot = data[indRandom];
    data[indRandom] = data[start];
    data[start] = data[indRandom];
    int saved = start;
    start++;

    while(start <= end){
      if (data[start] > pivot){
        int temp = data[end];
        data[end] = data[start];
        data[start] = temp;
        end--;
      }
      else{
        start++;
      }
    }

    /*System.out.print("Array: [");
    for (int i = 0; i < data.length; i++){
      System.out.print(data[i] + ", ");
    }
    System.out.print("]");*/

    int b = data[end];
    data[end] = pivot;
    data[saved] = b;

    /*System.out.println("Pivot: " + pivot);
    System.out.print("Array: [");
    for (int i = 0; i < data.length; i++){
      System.out.print(data[i] + ", ");
    }
    System.out.print("]");*/

    return end;
 }

 public static int quickselect(int[] ary, int pos){
   int i = partition(ary, 0, ary.length - 1), start = 0, end = ary.length - 1;
   while(i != pos){
     if (i < pos){
       start = i + 1;
     }
     else{
       end = i - 1;
     }
     i = partition(ary, start, end);
   }
   return ary[i];
 }

 public static void main(String[] args){

   for (int i = 0; i < 6; i++){
     int[] ary = {2,10,15,23,0,5};
     System.out.println(Quick.quickselect(ary,i));
   }
 }

}
