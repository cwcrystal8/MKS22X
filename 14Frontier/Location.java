public class Location implements Comparable<Location>{
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

  public int compareTo(Location other){
    return (int) Math.sqrt( (x * other.getX()) * (x * other.getX()) +
                      (y * other.getY()) * (y * other.getY()));
  }
}
