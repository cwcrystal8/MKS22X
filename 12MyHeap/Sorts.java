import java.util.Arrays;

public class Sorts{

  public static void heapsort(int[] data){
    heapify(data,data.length - 1);
  }

  private static void swap(int[] data, int pos1, int pos2){
    int temp = data[pos1];
    data[pos1] = data[pos2];
    data[pos2] = temp;
  }

  public static void heapify(int[] data, int pos){
    if(pos == 0){
      while(( 2 * pos + 1 < data.length && data[pos] < data[2 * pos + 1]) || (2 * pos + 2 < data.length && data[pos] < data[2 * pos + 2])) {
        if(2 * pos + 2 >= data.length || data[2 * pos + 1] > data[2 * pos + 2]){
          swap(data, 2 * pos + 1, pos);
          pos = 2 * pos + 1;
        }
        else{
          swap(data, 2 * pos + 2, pos);
          pos = 2 * pos + 2;
        }
      }
  //    System.out.println("Heaped version: " + Arrays.toString(data));
      for(int i = data.length - 1; i > 0; i--){
          //remove and put at the end
        //  System.out.println("pos: " + i + " " + Arrays.toString(data));
          swap(data, 0, i);
          int index = 0;
          while(2 * index + 2 < i && (data[index] < data[2 * index + 1] || data[index] < data[2 * index + 2])) {
        //    System.out.println("before: " + Arrays.toString(data));
        //    System.out.println("index: " + index);
            if(2 * index + 2 == data.length || data[2 * index + 1] > data[2 * index + 2]){
              swap(data, 2 * index + 1, index);
              index = 2 * index + 1;
            }
            else{
              swap(data, 2 * index + 2, index);
              index = 2 * index + 2;
            }
          }
      //    System.out.println("after: " + Arrays.toString(data));
      //    System.out.println("\n");
      }
      if(data[0] > data[1]){
        swap(data, 0, 1);
      }
    }
    else{
      int index = pos;
      while(( 2 * pos + 1 < data.length && data[pos] < data[2 * pos + 1]) ||
      (2 * pos + 2 < data.length && data[pos] < data[2 * pos + 2])) {
        if(2 * pos + 2 >= data.length || data[2 * pos + 1] > data[2 * pos + 2]){
          swap(data, 2 * pos + 1, pos);
          pos = 2 * pos + 1;
        }
        else{
          swap(data, 2 * pos + 2, pos);
          pos = 2 * pos + 2;
        }
      }
    //  System.out.println("next level heap: " + Arrays.toString(data));
      heapify(data, index - 1);
    }
  }

  public static void main(String[] args){
    int[] reg = new int[25];
    int[] heap = new int[25];

    for(int i = 0; i < 25; i ++){
      int temp = (int)(Math.random() * 1000);
      reg[i] = temp;
      heap[i] = temp;
    }

    Arrays.sort(reg);
    Sorts.heapsort(heap);

    for(int i = 0; i < 25; i++){
      if(reg[i] != heap[i]){
        System.out.println("There is an error at index " + i);
        System.out.println("reg: " + Arrays.toString(reg));
        System.out.println("hea: " + Arrays.toString(heap));
      }
    }
  }
}
