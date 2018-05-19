public class Location implements Comparable<Location>{
  private int x,y;
  private Location previous;
  private int distance, stepsSoFar;
  private boolean isAStar;


  public Location(int _x, int _y, Location prev, Location end, int steps){
    x = _x;
    y = _y;
    previous = prev;
    if(end != null){
      distance = Math.abs(end.getX() - x) + Math.abs(end.getY() - y);
    }
    else{
      distance = 0;
    }
    stepsSoFar = steps;
    isAStar = false;
  }

  public Location(int _x, int _y, Location prev, Location end, int steps, boolean isA){
    x = _x;
    y = _y;
    previous = prev;
    if(end != null){
      distance = Math.abs(end.getX() - x) + Math.abs(end.getY() - y);
    }
    else{
      distance = 0;
    }
    stepsSoFar = steps;
    isAStar = isA;
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

  public int getDistance(){
    return distance;
  }

  public int getAStarDistance(){
    return distance + stepsSoFar;
  }

  public int compareTo(Location other){
    if(isAStar){
      return (stepsSoFar + distance) - (other.getDistance() + other.getAStarDistance());
    }
    return distance - other.getDistance();
  }
}
