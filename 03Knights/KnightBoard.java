public class KnightBoard{
  private int[][] board;

  public KnightBoard(int startingRows, int startingCols){
      if (startingCols < 0 || startingRows < 0){
        throw new IllegalArgumentException();
      }
      board = new int[startingRows][startingCols];
  }

  public String toString(){
    String ans = "";
    for (int i = 0; i < board.length; i ++){
      for (int j = 0; j < board[i].length; j++){
        if (board.length * board[0].length >= 10 && board[i][j] < 10){
            ans += "_";
        }
        if(board[i][j] != 0){
          ans += board[i][j] + " ";
        }else{
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }

  public boolean solve(int startingRow, int startingCol){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException();
        }
      }
    }

    if (startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException();
    }
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int row, int col, int level){
    int[][] coordinates = {{2,1},{1,2},{-2,1},{-1,2},
                           {2,-1},{1,-2},{-1,-2},{-2,-1}};
    if (level == board.length * board[0].length){
      board[row][col] = level;
      return true;
    }
    for(int[] x: coordinates){
      try{
        if (board[row + x[0]][col + x[1]] == 0){
          board[row][col] = level;
          if(solveH(row + x[0], col + x[1], level + 1)){
            return true;
          }
          else{
            board[row][col] = 0;
          }
        }
      }catch(Exception e){

      }
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException();
        }
      }
    }

    if (startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException();
    }
    return countHelp(startingRow, startingCol, 1);

  }

  public int countHelp(int row, int col, int level){
    int sol = 0;
    int[][] coordinates = {{2,1},{1,2},{-2,1},{-1,2},
                           {2,-1},{1,-2},{-1,-2},{-2,-1}};
    if (level == board.length * board[0].length){
      return 1;
    }
    for(int[] x: coordinates){
      if(row + x[0] < board.length && row + x[0] >= 0 &&
         col + x[1] < board[0].length && col + x[1] >= 0){
        if (board[row + x[0]][col + x[1]] == 0){
          board[row][col] = level;
          sol += countHelp(row + x[0], col + x[1], level + 1);
          board[row][col] = 0;
          }
      }
    //  System.out.println(sol);
    }
    return sol;
  }

  public static void main(String[] args){
    KnightBoard a = new KnightBoard(3,3);

    System.out.println(a);
    /* Prints
      _ _ _
      _ _ _
      _ _ _
    */

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (a.solve(i,j)){
          System.out.println("There is an error with your solve method");
        }
      }
    } //prints nothing

    System.out.println(a.countSolutions(0,0)); //prints 0



    KnightBoard b = new KnightBoard(5,5);
    System.out.println(b.solve(0,0)); //prints true
    System.out.println(b); //prints a valid solution

    try{
      b.solve(0,0);
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    try{
      b.countSolutions(0,0);
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    try{
      KnightBoard b1 = new KnightBoard(-1,0);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(-1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(0,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(-1,0);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(-1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"



    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        KnightBoard abc = new KnightBoard(5,5);
        System.out.println(abc.solve(i,j)); //prints alternating lines of true/false starting with true
      }
    }
    KnightBoard c = new KnightBoard(5,5);

    int totalSol = 0;
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        totalSol += c.countSolutions(i,j);
      }
    }

    System.out.println(totalSol); //prints 1728

    KnightBoard d = new KnightBoard(5,5);
    System.out.println(d.countSolutions(0,0)); //prints 304

  }
}
