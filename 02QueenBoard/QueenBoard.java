public class QueenBoard{
  private int[][] board;

  public QueenBoard(int n){
    board = new int[n][n];
  }

  private boolean addQueen(int r, int c){
    if (board[r][c] == 0){
      board[r][c] = -1;
      for (int i = c + 1; i < board.length; i++){
        board[r][i]++;
      }
      for(int i = 1; r - i >= 0 && i + c < board.length; i++){
        board[r - i][c + i]++;
      }
      for(int i = 1; i + r < board.length && i + c < board.length; i++){
        board[r + i][c + i]++;
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c){
    if (board[r][c] == -1){
      board[r][c] = 0;
      for (int i = c + 1; i < board.length; i++){
        board[r][i]--;
      }
      for(int i = 1; r - i >= 0 && i + c < board.length; i++){
        board[r - i][c + i]--;
      }
      for(int i = 1; i + r < board.length && i + c < board.length; i++){
        board[r + i][c + i]--;
      }
      return true;
    }
    return false;
  }

  public String toString(){
    String ans = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if(board[i][j] == -1){
          ans += "Q ";
        }
        else{
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }

  public boolean solve(){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return solveHelp(0,0);
  }

  private boolean solveHelp(int c, int numQueens){
    if (numQueens == board.length){
      return true;
    }
    if (c == board.length){
      return false;
    }
    for (int i = 0; i < board.length; i++){
      if(addQueen(i,c)){
        if(!(solveHelp(c + 1, numQueens + 1))){
          removeQueen(i,c);
        }
        else{
          return true;
        }
      }
    }
    return false;
  }

  public int countSolutions(){
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board.length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return countSolutionsHelp(0,0);
  }

  public int countSolutionsHelp(int c, int numQueens){
    int sol = 0;
    if (numQueens == board.length){
      return 1;
    }
    if (c == board.length){
      return 0;
    }
    for (int i = 0; i < board.length; i++){
        if(addQueen(i,c)){
          sol += countSolutionsHelp(c + 1, numQueens + 1);
          removeQueen(i,c);
        }

    }
    return sol;
  }

  public static void main(String[] ans){
    QueenBoard b = new QueenBoard(4);

    System.out.println(b.solve()); //prints true
    System.out.println(b); //prints a valid solution

    try{
      b.solve();
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    try{
      b.countSolutions();
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    for (int i = 0; i < 12; i++){
      QueenBoard a = new QueenBoard(i);
      System.out.println("# of Solutions for " + i + ": " + a.countSolutions());
      /*          Expected Values
      i --> # of Solutions   i --> # of Solutions
      0 --> 1                6 --> 4
      1 --> 1                7 --> 40
      2 --> 0                8 --> 92
      3 --> 0                9 --> 352
      4 --> 2               10 --> 724
      5 --> 10              11 --> 2680
      */
      System.out.println(a); //Should print out an empty i by i grid of underscores
    }
  }

}
