import java.util.*;

public class FrontierQueue implements Frontier{
  private ArrayList<Location> frontier;

  public FrontierQueue(){
    frontier = new ArrayList<>();
  }

  public Location next(){
    if(frontier.size() == 0){
      throw new NoSuchElementException();
    }
    return frontier.remove(0);
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
