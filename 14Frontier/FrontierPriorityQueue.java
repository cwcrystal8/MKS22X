import java.util.*;

public class FrontierPriorityQueue implements Frontier{
  private MyHeap<Location> frontier;

  public FrontierPriorityQueue(){
    frontier = new MyHeap<>(false);
  }

  public Location next(){
    if(frontier.size() == 0){
      throw new NoSuchElementException();
    }
    return frontier.remove();
  }

  public void add(Location n){
    frontier.add(n);
  }

  public boolean hasNext(){
    return frontier.size() != 0;
  }

  public String toString(){
    return frontier.toString();
  }
}
