import java.util.Arrays;
import java.util.*;

public class MyHeap{
  private int size;
  private String[] data;
  private boolean isMin;

  public MyHeap(){
    isMin = false;
    data = new String[8];
  }

  public MyHeap(boolean b){
    if(b){
      isMin = false;
    }
    else{
      isMin = true;
    }
    data = new String[8];
  }

  public void add(String s){
    if(size == data.length){
      resize();
    }
    data[size] = s;
    int pos = size;
    while( ((pos - 1) / 2 >= 0) && ((data[pos].compareTo(data[(pos - 1) / 2]) > 0 && !(isMin)) ||
          (data[pos].compareTo(data[(pos - 1) / 2]) < 0 && isMin)) ){
            /*System.out.println("there needs to be a swap");
            System.out.println("pos: " + (pos - 1) / 2);
            System.out.println("parent: " + data[(pos - 1) / 2]);
            System.out.println("self: " + data[pos]);
            System.out.println("isMax? " + (data[pos].compareTo(data[(pos - 1) / 2]) > 0 && !(isMin)));
            System.out.println("comparison: " + data[pos].compareTo(data[(pos - 1) / 2]));*/
      String temp = data[(pos - 1) / 2];
      data[pos] = temp;
      data[(pos - 1) / 2] = s;
      pos = (pos - 1) / 2;
    }
    size++;
  }

  private void resize(){
    String[] temp = new String[data.length * 2];
    for(int i = 0; i < size; i++){
      temp[i] = data[i];
    }
    data = temp;
  }

  public String remove(){
    String ans = data[0];
    data[0] = data[size - 1];
    data[size] = null;
    int pos = 0;
    size--;
    while( ((2 * pos + 1 < size) &&
          ((data[pos].compareTo(data[2 * pos + 1]) < 0 ||
          ( (2 * pos + 2 < size) && data[pos].compareTo(data[2 * pos + 2]) < 0) && !(isMin))) ) ||

          ( (2 * pos + 1 < size) &&
          ((data[pos].compareTo(data[2 * pos + 1]) > 0 ||
          ((2 * pos + 2 < size) && data[pos].compareTo(data[2 * pos + 2]) > 0) && isMin)) ) ) {

            /*System.out.println("pos: " + pos + " " + data[pos]);
            System.out.println("next move: " + (2 * pos + 1) + " " + data[2 * pos + 1]);
            System.out.println("size: " + size);
            System.out.println(Arrays.toString(data));
            System.out.println(data[pos].compareTo(data[2 * pos + 1]));*/

            System.out.println("\n");
            System.out.println(this);
            System.out.println("pos: " + pos);
            System.out.println("comparing left and right: " + data[2 * pos + 1].compareTo(data[2 * pos + 2]));

            if((2 * pos + 2 == size) || ((data[2 * pos + 1].compareTo(data[2 * pos + 2]) > 0 && !(isMin) && (data[pos].compareTo(data[2 * pos + 1]) < 0)) ||
               (data[2 * pos + 1].compareTo(data[2 * pos + 2]) < 0 && isMin && (data[pos].compareTo(data[2 * pos + 1]) > 0 )))) {
              String temp = data[2 * pos + 1];
              data[2 * pos + 1] = data[pos];
              data[pos] = temp;
              pos = 2 * pos + 1;
            }else{
              String temp = data[2 * pos + 2];
              data[2 * pos + 2] = data[pos];
              data[pos] = temp;
              pos = 2 * pos + 2;
            }
            // if((data[2 * pos + 1].compareTo(data[2 * pos + 2]) < 0 && !(isMin)) ||
                     //(data[2 * pos + 1].compareTo(data[2 * pos + 2]) > 0 && isMin))
    }
    System.out.println("\n");
    System.out.println("final version:" + this);
    return ans;
  }

  public String peek(){
    return data[0];
  }

  public int size(){
    return size;
  }

  public String toString(){
    String ans = "[";
    for(int i = 0; i < size; i++){
      ans += data[i] + ", ";
    }
    return ans.substring(0, ans.length() - 2) + "]";
  }

  public static void main(String[] args) {
    MyHeap a = new MyHeap(false);
    String[] b = new String[20];
    for(int i = 0; i < 20; i++){
      int temp = (int)(Math.random() * 26) + 97;
      char value = (char)temp;
      a.add("" + value);
      b[i] = "" + value;
    }

    Arrays.sort(b);

    System.out.println("MyHeap: " + a);
    System.out.println("Arrays: "+ Arrays.toString(b));

    for(int i = 0; i < 20; i++){
      String temp = a.remove();
      System.out.println("size: " + a.size());
      if(!(temp.equals(b[i]))){
        System.out.println("there is an error");
        System.out.println(temp);
        System.out.println(b[i]);
        System.out.println(a);
      }
    }

  }
}
