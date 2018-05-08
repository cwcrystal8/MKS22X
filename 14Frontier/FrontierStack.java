import java.util.*;

public class FrontierStack implements Frontier{
  private ArrayList<Location> frontier;

  public FrontierStack(){
    frontier = new ArrayList<>();
  }

  public Location next(){
    if(frontier.size() == 0){
      throw new NoSuchElementException();
    }
    return frontier.remove(frontier.size() - 1);
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
