import java.util.*;
import java.io.*;

public class MyLinkedListImproved<T extends Comparable<T>> implements Iterable<T>{
  private Node start, end;
  private int size;


  public LinkedIterator iterator(){
    return new LinkedIterator(start);
  }
  private Node getNode(int index){
    if(index >= size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Node ans = start;
    for (int i = 0; i < index; i++){
      ans = ans.getNextNode();
    }
    return ans;
  }

  public String toString(){
    String ans = "{ ";
    Node temp = start;
    for (int i = 0; i < size; i++){
      ans += temp.toString() + " ";
      temp = temp.getNextNode();
    }
    return ans + "}";
  }

  public void clear(){
    start = null;
    end = null;
    size = 0;
  }

  public int size(){
    return size;
  }

  public T get(int index){
    if(index >= size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    return getNode(index).getData();
  }

  public T set(int index, T value){
    if(index >= size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    T temp = getNode(index).getData();
    getNode(index).setData(value);
    return temp;
  }

  public int indexOf(T value){
    for (int i = 0; i < size; i++){
      if(getNode(i).getData().equals(value)){
        return i;
      }
    }
    return -1;
  }

  public boolean add(T newData){
    if (size == 0){
      start = new Node(newData);
      end = start;
      size++;
      return true;
    }
    getNode(size - 1).setNextNode(new Node(newData));
    size++;
    Node temp = end;
    end = getNode(size - 1);
    end.setPrevNode(temp);
    return true;
  }

  public void add(int index, T value){
    if(index > size || index < 0){
      throw new IndexOutOfBoundsException();
    }
    if(index == size){
      add(value);
    }
    else if(index == 0){
      Node temp = start;
      start = new Node(value);
      start.setNextNode(temp);
      size++;
    }
    else{
      Node temp = getNode(index);
      getNode(index - 1).setNextNode(new Node(value));
      Node newer = getNode(index);
      newer.setNextNode(temp);
      temp.setPrevNode(newer);
      newer.setPrevNode(getNode(index - 1));
      size++;
    }
  }//exceptions!

  //The remove methods can cause a problem, this is why we shouldn't
  //use an int as the data, we need objects to distinguish between index and data
  public boolean remove(T value){
    int index = indexOf(value);
    if(index != -1){
      remove(index);
      return true;
    }
    return false;
  }

  public T remove(int index){
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException();
    }
    T ans = get(index);
    if(size == 1){
      start = null;
      end = null;
    }
    else if(index == 0){
      start = getNode(1);
      start.setPrevNode(null);
    }
    else if(index == size - 1){
      end = getNode(size - 2);
      end.setNextNode(null);
    }
    else{
      Node previous = getNode(index - 1), next = getNode(index + 1);
      previous.setNextNode(next);
      next.setPrevNode(previous);
    }
    size--;
    return ans;
  }//exceptions!

  public int max(){
      T maxSoFar = start.getData();
      int index = 0, i = 0;
      for(T x: this){
        if(x.compareTo(maxSoFar) > 0){
          maxSoFar = x;
          index = i;
        }
        i++;
      }
      return index;
  }

  public int min(){
    T maxSoFar = start.getData();
    int index = 0, i = 0;
    for(T x: this){
      if(x.compareTo(maxSoFar) < 0){
        maxSoFar = x;
        index = i;
      }
      i++;
    }
    return index;
  }

  public void extend(MyLinkedListImproved<T> other){
    if(other.size() != 0){
      if(this.size() == 0){
        start = other.start;
        end = other.end;
        size += other.size();
        other.clear();
      }
      else{
        this.end.setNextNode(other.start);
        other.start.setPrevNode(this.end);
        end = other.end;
        size += other.size();
        other.clear();
      }
    }
  }

  public class Node{
    private T data;
    private Node next;
    private Node prev;

    public Node(T value){
      data = value;
    }
    public Node(T value, Node nextNode){
      this(value);
      next = nextNode;
    }
    public T getData(){
      return data;
    }
    public boolean setData(T a){
      data = a;
      return true;
    }
    public Node getNextNode(){
      if(next != null){
         return next;
       }
       return null;
     }

     public Node getPrevNode(){
        if(prev != null){
          return prev;
        }
        return null;
     }

     public boolean setNextNode(Node a){
       next = a;
       return true;
     }

     public boolean setPrevNode(Node a){
       prev = a;
       return true;
     }

     public String toString(){
       return "[" + data + "]";
     }

   }


   public class LinkedIterator implements Iterator<T>{
     Node current;

     public LinkedIterator(Node start){
         current = start;
     }

     public boolean hasNext(){
       return current != null;
     }

     public T next(){
       if(!(hasNext())){
         throw new NoSuchElementException();
       }
       T ans = current.getData();
       current = current.getNextNode();
       return ans;
     }

   }


   public static void main(String[] args){
     MyLinkedListImproved<Integer> a = new MyLinkedListImproved<>();

     System.out.println("Testing add(Integer value)");
     for (int i = 0; i < 10; i++){
       a.add(new Integer(i));
       System.out.println("size: " + a.size() + " LinkedList: " + a.toString());
     } //adds the integers from 0 to 9 inclusive and prints out the LinkedList

     System.out.println("\nTesting get(int index)");
     for (int i = 0; i < 10; i++){
       System.out.println("index: " + i + " data: " + a.get(i));
     } //prints the integers from 0 to 9 inclusive

     System.out.println("\nTesting exception for get(int index)");
     try{
       System.out.println(a.get(10));
       System.out.println(a.size());
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"
     try{
       System.out.println(a.get(-1));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"

     for (int i = 0; i < 10; i++){
       a.add(new Integer(i));
     }

     System.out.println("\nTesting indexOf(Integer value)");
     for (int i = 0; i < 10; i++){
       System.out.println("Value: " + i + " Index: " + a.indexOf(i));
     } //prints 0 to 9 inclusive

     System.out.println("\nTesting remove(Integer value)");
     for(int i = 0; i < 10; i++){
       a.remove(new Integer(i));
       System.out.println(a);
     } //removes first half of the LinkedList

     System.out.println("\nTesting set(int index, Integer value)");
     for (int i = 0; i < 10; i++){
       a.set(i, new Integer(i * 10));
       System.out.println(a);
     } //sets the data of each node to 10 * index

     System.out.println("\nTesting exceptions for set(int index, Integer value)");
     try{
       System.out.println(a.set(-1, new Integer(1)));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"
     try{
       System.out.println(a.set(10, new Integer(1)));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"

     System.out.println("\nTesing add(int index, Integer value)");
     for (int i = 0; i < 9; i++){
       a.add(i, new Integer(i * 3));
       System.out.println("index added: " + i + " LinkedList: " + a.toString());
     } //adds multiples of 3 up to 24 to the LinkedList at the beginning
     a.add(19, new Integer(100)); //adds 100 to the LinkedList at the end
     System.out.println("index added: " + 19 + " LinkedList: " + a.toString());

     System.out.println("\nTesting exceptions for add(int index, Integer value)");
     try{
       a.add(-1, new Integer(5));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"
     try{
       a.add(21, new Integer(5));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"

     System.out.println("\nTesting remove(int index)");
     System.out.println("Original LinkedList: " + a.toString());
     System.out.println("data removed: " + a.remove(0) + " index removed: 0"); //removes 0
     System.out.println("LinkedList: " + a.toString());
     System.out.println("data removed: " + a.remove(a.size() - 1) + " index removed: 18"); //removes 100
     System.out.println("LinkedList: " + a.toString());
     System.out.println("data removed: " + a.remove(2) + " index removed: 2 "); //removes 9
     System.out.println("LinkedList: " + a.toString());

     System.out.println("\nTesting exceptions for remove(int index)");
     try{
       System.out.println(a.remove(-1));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"
     try{
       System.out.println(a.remove(17));
     }catch(IndexOutOfBoundsException e){
       System.out.println("This size is out of bounds");
     } //prints "This size is out of bounds"

     for(Integer x: a){
       System.out.print(x + " ");
     }

     System.out.println("\n" + a.max());
     System.out.println(a.min());

     MyLinkedListImproved<Integer> b = new MyLinkedListImproved<>();
     b.add(5);
     b.add(2);
     b.add(100);
     a.extend(b);

     System.out.println(a);
     System.out.println(b);

     b.extend(a);
     System.out.println(a);
     System.out.println(b);

     System.out.println("\nTesting clear()");
     a.clear();
     System.out.println("LinkedList: " + a.toString()); //prints an empty LinkedList
   }
}
