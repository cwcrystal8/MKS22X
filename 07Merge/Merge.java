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
        for (int i = lo; i <= hi; i++){
          temp[i] = data[i];
        }
        int mid = (lo + hi) / 2;
        msort(temp, data, lo, mid);
        msort(temp, data, mid + 1, hi);
        merge(data, temp, lo, mid, mid + 1, hi);
      }
  }

  public static void mergesort(int[] data){
    int[] temp = new int[data.length];
    msort(data,temp,0,data.length - 1);
  }

  public static void main(String[] args){
    int[] a = new int[1000000];
    for (int i = 0; i < 1000000; i++){
      int temp = (int)(Math.random() * 1000000);
      a[i] = temp;
    }
    mergesort(a);

    /*Arrays.sort(b);

    for (int i = 0; i < 1000000; i++){
      if (a[i] != b[i]){
        System.out.println("There is a mismatch");
      }
    }*/
  }

}
