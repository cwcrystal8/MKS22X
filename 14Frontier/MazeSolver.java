import java.util.*;
import java.io.*;

public class MazeSolver{
  private Maze maze;
  private Frontier frontier;

  public MazeSolver(String mazeText){
    maze = new Maze(mazeText);
  }

  //Default to BFS
  public boolean solve(){
    return solve(0);
  }

  //mode: required to allow for alternate solve modes
  //0: BFS
  //1: DFS
  public boolean solve(int mode){
    if(mode == 0){
      frontier = new FrontierQueue();
    }
    else if(mode == 1){
      frontier = new FrontierStack();
    }
    else if(mode == 2){
      frontier = new FrontierPriorityQueue();
    }
    else if(mode == 3){
      frontier = new FrontierPriorityQueue();
    }

    frontier.add(maze.getStart());
    //System.out.println("end: " + maze.getEnd());
    //System.out.println(frontier);
    //System.out.println(frontier);
    while(frontier.hasNext()){
      System.out.println(maze.toStringColor(100));
    //  System.out.println("frontier: " + frontier);
      Location temp = frontier.next();
      char[][] board = maze.getBoard();
      Location[] nextOnes = maze.getNeighborsAStar(temp);
      if(mode == 3){
        nextOnes = maze.getNeighbors(temp);
      }

      for(int i = 0; i < 4; i++){
        //System.out.println("1");
        if(nextOnes[i] != null){
          //System.out.println("error here");
          /*System.out.println("1: " + nextOnes[i].getX());
          System.out.println("2: " + nextOnes[i].getY());
          System.out.println("3: " + maze.getEnd().getX());
          System.out.println("4: " + maze.getEnd().getY());*/
          if(nextOnes[i].getX() == maze.getEnd().getX() &&
            nextOnes[i].getY() == maze.getEnd().getY()){
            //  System.out.println("error here");
              Location current = nextOnes[i];
              int x = current.getY(), j = current.getX();
              while(board[x][j] != 'S'){
                if(board[x][j] != 'E'){
                  board[x][j] = '@';
                }
                current = current.getPrev();
                x = current.getY();
                j = current.getX();

              }
            //  System.out.println(maze);
            return true;
          }
          else{
            //System.out.println("error here0");
            frontier.add(nextOnes[i]);
            //System.out.println("error here");
            Location current = nextOnes[i];
            //System.out.println("error here2");
            int x = current.getY(), j = current.getX();
            //System.out.println("error here3");
            board[x][j] = '?';
          }
        }
      }
      //System.out.println("1");
      if(board[temp.getY()][temp.getX()] != 'S'){
        board[temp.getY()][temp.getX()] = '.';
      }
    //  System.out.println(frontier);
    }

    //initialize your Frontier
    //while there is stuff in the frontier:
        //get the next Location
        //process the location to find the locations(use the maze to do this)
        //check if any locations are the end, if you found the end just return true!
    //when there are no more values in the frontier return false
    return false;
  }

  public String toString(){
    return maze.toString();
  }

  public static void main(String[] args) throws FileNotFoundException {
    /*File f = new File("input.txt");
    Scanner br = new Scanner(f);
    String text = "";
    while(br.hasNext()){
      String s = br.next();
      text += s;
      System.out.println(s);
    }
    System.out.println(text);*/
    String s = "";
    try{
     Scanner in = new Scanner(new File("input.txt"));
     while(in.hasNext()){
       s+= in.nextLine() + "\n";
     }
  //   System.out.println(s);
   }catch(FileNotFoundException e){
     System.out.println("File not found");
     System.exit(1);
   }
    MazeSolver a  = new MazeSolver(s), b = new MazeSolver(s), c = new MazeSolver(s);
    System.out.println(a.solve(3));
  //  System.out.println(b.solve(1));
  //  System.out.println(c.solve(0));

  }

}
