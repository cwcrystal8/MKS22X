import java.util.*;
import java.io.*;
public class Maze{

    private char[][] maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then: print a meaningful error and exit the program.
    */
    public Maze(String filename) throws FileNotFoundException{
      animate = false;
      int numE = 0, numS = 0;
          File f = new File(filename);
          Scanner inf = new Scanner(f);
          int index = 0;
          ArrayList<String> text = new ArrayList<String>();

          while (inf.hasNextLine()){
            index++;
            text.add(inf.nextLine());
          }

          Scanner s = new Scanner(f);
          maze = new char[index][text.get(1).length()];
          for (int i = 0; i < text.size(); i++){
            for (int j = 0; j < text.get(i).length(); j++){
              maze[i][j] = text.get(i).charAt(j);
              if (maze[i][j] == 'E'){
                numE++;
              }
              if (maze[i][j] == 'S'){
                numS++;
              }
            }
          }
        if (numE != 1 || numS != 1){
          throw new IllegalStateException("You are missing an E or an S");
        }
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      for (int i = 0; i < maze.length; i++){
        for (int j = 0; j < maze[i].length; j++){
          if(maze[i][j] == 'S'){
            maze[i][j] = '@';
            return solve(i,j,1);
          }
        }
      }
      return -1;
            //find the location of the S.

            //erase the S

            //and start solving at the location of the s.
            //return solve(???,???);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
            Note: This is not required based on the algorithm, it is just nice visually to see.
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int numSteps){ //you can add more parameters since this is private

        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(200);
        }

        //COMPLETE SOLVE
        int[][] paths = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] x : paths){
          char value = maze[row + x[0]][col + x[1]];
          if (maze[row + x[0]][col + x[1]] == 'E'){
            return numSteps;
          }
        }
        for (int[] x : paths){
          char value = maze[row + x[0]][col + x[1]];
            if(value == ' '){
              maze[row + x[0]][col + x[1]] = '@';
              int temp = solve(row + x[0], col + x[1], numSteps + 1);
              if(temp != -1){
                return temp;
              }
              else{
                maze[row + x[0]][col + x[1]] = '.';
              }
            }
          }

        return -1; //so it compiles
    }

    public String toString(){
      String ans = "";
      for (int i = 0; i < maze.length; i++){
        for (int j = 0; j < maze[i].length; j++){
          ans += maze[i][j];
        }
        ans += "\n";
      }
    return ans;
  }
}
