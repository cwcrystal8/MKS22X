import java.util.*;
import java.util.Arrays;

public class Merge{
  public static void merge(int[] data, int[] temp, int lo1, int hi1, int lo2, int hi2){
    for (int i = lo1; i <= hi2; i++){
      if (lo1 <= hi1 && (lo2 > hi2 || temp[lo1] < temp[lo2])){
        data[i] = temp[lo1];
        lo1++;
      }
      else {
        data[i] = temp[lo2];
        lo2++;
      }
    }
  }

  public static void msort(int[] data, int[] temp, int lo, int hi){
      if (lo < hi){

        int mid = (lo + hi) / 2;
        msort(temp, data, lo, mid);
        msort(temp, data, mid + 1, hi);
        merge(data, temp, lo, mid, mid + 1, hi);
      }
  }

  public static void mergesort(int[] data){
    int[] temp = new int[data.length];
    for (int i = 0; i <= data.length - 1; i++){
      temp[i] = data[i];
    }
    msort(data,temp,0,data.length - 1);
  }

  public static void main(String[] args){
    /*int[] a = new int[100000], b = new int[1000000];
    for (int i = 0; i < 1000000; i++){
      int temp = (int)(Math.random() * 1000000);
      a[i] = temp;
      b[i] = temp;
    }
    mergesort(a);

    Arrays.sort(b);

    for (int i = 0; i < 1000000; i++){
      if (a[i] != b[i]){
        System.out.println("There is a mismatch");
      }
    }*/



    for (int x = 0; x < 1000; x++){
      int[] a = new int[x], b = new int[x];
      for (int i = 0; i < x; i++){
        int temp = (int)(Math.random() * x);
        a[i] = temp;
        b[i] = temp;
      }

      mergesort(a);
      Arrays.sort(b);

      for (int i = 0; i < x; i++){
        if (a[i] != b[i]){
          System.out.println("There is a mismatch");
        }
      }
    }




    for (int x = 0; x < 1000; x++){
      int[] a = new int[x], b = new int[x];
      for (int i = 0; i < x; i++){
        int temp = i;
        a[i] = temp;
        b[i] = temp;
      }

      mergesort(a);
      Arrays.sort(b);

      for (int i = 0; i < x; i++){
        if (a[i] != b[i]){
          System.out.println("There is a mismatch");
        }
      }
    }


    for (int x = 0; x < 1000; x++){
      int[] a = new int[x], b = new int[x];
      for (int i = 0; i < x; i++){
        int temp = x - i;
        a[i] = temp;
        b[i] = temp;
      }

      mergesort(a);
      Arrays.sort(b);

      for (int i = 0; i < x; i++){
        if (a[i] != b[i]){
          System.out.println("There is a mismatch");
        }
      }
    }
  }

}
