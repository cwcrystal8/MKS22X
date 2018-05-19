import java.util.Arrays;
import java.util.*;

public class MyHeap<T extends Comparable<T>> {
  private int size;
  private T[] data;
  private boolean isMin;

  @SuppressWarnings("unchecked")
  public MyHeap(){
    isMin = false;
    data = (T[]) new Comparable[8];
  }

  @SuppressWarnings("unchecked")
  public MyHeap(boolean b){
    isMin = !(b);
    data = (T[]) new Comparable[8];
  }

  public void add(T s){
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
      T temp = data[(pos - 1) / 2];
      data[pos] = temp;
      data[(pos - 1) / 2] = s;
      pos = (pos - 1) / 2;
    }
    size++;
  }

  @SuppressWarnings("unchecked")
  private void resize(){
    T[] temp = (T[])new Comparable[data.length * 2];
    for(int i = 0; i < size; i++){
      temp[i] = data[i];
    }
    data = temp;
  }

  public T remove(){
    T ans = data[0];
    data[0] = data[size - 1];
    data[size - 1] = null;
    int pos = 0;
    size--;
    while( ((2 * pos + 1 < size) &&
          ((data[pos].compareTo(data[2 * pos + 1]) < 0 && !(isMin)) ||
          (( (2 * pos + 2 < size) && data[pos].compareTo(data[2 * pos + 2]) < 0) && !(isMin)) ) ) ||

          ( (2 * pos + 1 < size) &&
          ((data[pos].compareTo(data[2 * pos + 1]) > 0 && isMin) ||
          (((2 * pos + 2 < size) && data[pos].compareTo(data[2 * pos + 2]) > 0) && isMin)) ) ) {

            /*System.out.println("pos: " + pos + " " + data[pos]);
            System.out.println("next move: " + (2 * pos + 1) + " " + data[2 * pos + 1]);
            System.out.println("size: " + size);
            System.out.println(Arrays.toT(data));
            System.out.println(data[pos].compareTo(data[2 * pos + 1]));

            System.out.println("data: " + this);
            System.out.println("pos: " + pos);*/
            //System.out.println("comparing left and right: " + data[2 * pos + 1].compareTo(data[2 * pos + 2]));
            if(2 * pos + 2 == size && ( !(isMin) && (data[pos].compareTo(data[2 * pos + 1]) < 0) ||  (isMin && (data[pos].compareTo(data[2 * pos + 1]) > 0 )))) {
              T temp = data[2 * pos + 1];
              data[2 * pos + 1] = data[pos];
              data[pos] = temp;
              pos = 2 * pos + 1;
            }
            else if((2 * pos + 2 < size) &&
              (data[2 * pos + 1].compareTo(data[2 * pos + 2]) >= 0 && !(isMin) && (data[pos].compareTo(data[2 * pos + 1]) < 0)) ||
              (data[2 * pos + 1].compareTo(data[2 * pos + 2]) <= 0 && isMin && (data[pos].compareTo(data[2 * pos + 1]) > 0 )) )  {
              T temp = data[2 * pos + 1];
              data[2 * pos + 1] = data[pos];
              data[pos] = temp;
              pos = 2 * pos + 1;
            }else if( (!(isMin)  && data[pos].compareTo(data[2 * pos + 2]) < 0 ) ||
                      (data[pos].compareTo(data[2 * pos + 2]) > 0  && isMin)) {
              T temp = data[2 * pos + 2];
              data[2 * pos + 2] = data[pos];
              data[pos] = temp;
              pos = 2 * pos + 2;
            }
            // if((data[2 * pos + 1].compareTo(data[2 * pos + 2]) < 0 && !(isMin)) ||
                     //(data[2 * pos + 1].compareTo(data[2 * pos + 2]) > 0 && isMin))
    }
    //System.out.println("\n");
    //System.out.println("final version:" + this);
    return ans;
  }

  public T peek(){
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
    if(size > 0){
      return ans.substring(0, ans.length() - 2) + "]";
    }
    else{
      return "[]";
    }
  }

  public static void main(String[] args) {
    MyHeap<String> a = new MyHeap<>(false);
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

    boolean isCorrect = true;
    for(int i = 0; i < 20; i++){
      //System.out.println("size: " + a.size());
      //System.out.println("heap before: " + a.toT());
      String temp = a.remove();
      if(!(temp.equals(b[i]))){
        System.out.println("there is an error");
        System.out.println(temp);
        System.out.println(b[i]);
        System.out.println(a);
        isCorrect = false;
      }
    }

    if(isCorrect){
      System.out.println("Your heap is correct!");
    }
    else{
      System.out.println("There are error(s)");
    }

  }
}
