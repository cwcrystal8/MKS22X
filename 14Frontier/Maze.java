import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
public class Maze{
  private Location start, end;
  private char[][] maze;
  private static final String CLEAR_SCREEN =  "\033[2J";
  private static final String HIDE_CURSOR =  "\033[?25l";
  private static final String SHOW_CURSOR =  "\033[?25h";

  public Maze(String mazeText){
    int index = 0, row = 0, col = 0, finalCol = 0;

    while(index < mazeText.length()){
      if(mazeText.charAt(index) == '\n'){
        row++;
        finalCol = col;
        col = 0;
      }
      else{
        col++;
      }
      index++;
    }

    col = finalCol;
    maze = new char[row][col];
    index = 0;

    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        maze[i][j] = mazeText.charAt(index);
        if(maze[i][j] == 'S'){
          start = new Location(j,i,null,null,1);
        }
        if(maze[i][j] == 'E'){
          end = new Location(j,i,null,null,0);
        }
        index++;
      }
      index++;
    }
  }
/*
  public String toString(){
    String ans = "";
    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze[i].length; j++){
        ans += maze[i][j];
      }
      ans+='\n';
    }
    return ans;
  }
*/

  public Location[] getNeighbors(Location n){
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    Location[] ans = new Location[4];
    for(int i = 0; i < 4; i++){
      int x = directions[i][0], y = directions[i][1];
      if(
         n.getY() + y >= 0 &&
         n.getY() + y < maze.length &&
         n.getX() + x >= 0 &&
         n.getX() + x < maze[0].length){
        if(maze[n.getY() + y][n.getX() + x] == ' ' ||
           maze[n.getY() + y][n.getX() + x] == 'E'){
            ans[i] = new Location(n.getX() + x, n.getY() + y, n, end, n.getAStarDistance() + 1);
        }
      }
    }
    return ans;
  }

  public Location[] getNeighborsAStar(Location n){
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    Location[] ans = new Location[4];
    for(int i = 0; i < 4; i++){
      int x = directions[i][0], y = directions[i][1];
      if(
         n.getY() + y >= 0 &&
         n.getY() + y < maze.length &&
         n.getX() + x >= 0 &&
         n.getX() + x < maze[0].length){
        if(maze[n.getY() + y][n.getX() + x] == ' ' ||
           maze[n.getY() + y][n.getX() + x] == 'E'){
            ans[i] = new Location(n.getX() + x, n.getY() + y, n, end, n.getAStarDistance() + 1, true);
        }
      }
    }
    return ans;
  }

  public Location getStart(){
    return start;
  }

  public Location getEnd(){
    return end;
  }

  public char[][] getBoard(){
    return maze;
  }

  /*public static void main(String[] args) {
    String mazetext = "# #\n#  \nS E";
    Maze a = new Maze(mazetext);
    System.out.println(a);
    Location[] b = a.getNeighbors(new Location(1,1,null, null));
    for(int i = 0; i < 4; i++){
      if(b[i] != null){
        System.out.println(b[i].getPrev());
      }
    }
  }
/* CHANGES BEGIN HERE

*/
private static String go(int x,int y){
    return ("\033[" + x + ";" + y + "H");
  }
  private static String color(int foreground,int background){
    return ("\033[0;" + foreground + ";" + background + "m");
  }

  public void clearTerminal(){
    System.out.println(CLEAR_SCREEN+"\033[1;1H");
  }


  public String toStringColor(){
    return toStringColor(50);
  }

  public String toStringColor(int delay){
    try{
      Thread.sleep(delay);
    }catch(InterruptedException e){

    }
    return HIDE_CURSOR+CLEAR_SCREEN+go(1,1)+colorize(toString())+SHOW_CURSOR;
  }

  public String toString(){
    int maxr = maze.length;
    int maxc = maze[0].length;
    String ans = "";
    for(int i = 0; i < maxr * maxc; i++){
      int row = i/maxc;
      int col = i%maxc;

      char c =  maze[row][col];
      ans+=c;
      if( col == maxc-1 ){
        ans += "\n";
      }

    }
    return ans + "\n";
  }

  public char get(int row,int col){
    return maze[row][col];
  }
  public void set(int row,int col, char n){
    maze[row][col] = n;
  }
  public static String colorize(String s){
    String ans = "";
    Scanner in = new Scanner(s);
    while(in.hasNext()){
      String line ="";
      for(char c : in.nextLine().toCharArray()){
        if(c == '#'){
          line+= color(37,47)+c;
        }
        else if(c == '@'){
          line+= color(33,40)+c;
        }
        else if(c == '?'){
          line+= color(37,42)+c;
        }
        else if(c == '.'){
          line+= color(36,40)+c;
        }
        else if(c == ' '){
          line+= color(35,40)+c;
        }else{
          line+=color(37,40)+c;
        }

      }
      ans += line+color(37,40)+"\n";
    }
    return ans;
  }
}



/*


CHANGES END HERE
*/
