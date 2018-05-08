public class Location{
  private int x,y;
  private Location previous;

  public Location(int _x, int _y, Location prev){
    x = _x;
    y = _y;
    previous = prev;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public Location getPrev(){
    return previous;
  }

  public void setPrev(Location n){
    previous = n;
  }

  public String toString(){
    return "[" + x + ", " + y + "]";
  }
}
