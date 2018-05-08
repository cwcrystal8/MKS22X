public class Maze{
  private Location start, end;
  private char[][] board;

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
    board = new char[row][col];
    index = 0;

    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        board[i][j] = mazeText.charAt(index);
        if(board[i][j] == 'S'){
          start = new Location(j,i,null);
        }
        if(board[i][j] == 'E'){
          end = new Location(j,i,null);
        }
        index++;
      }
      index++;
    }
  }

  public String toString(){
    String ans = "";
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        ans += board[i][j];
      }
      ans+='\n';
    }
    return ans;
  }

  public String toStringColor(){
    return "";
  }

  public Location[] getNeighbors(Location n){
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    Location[] ans = new Location[4];
    for(int i = 0; i < 4; i++){
      int x = directions[i][0], y = directions[i][1];
      if(
         n.getY() + y >= 0 &&
         n.getY() + y < board.length &&
         n.getX() + x >= 0 &&
         n.getX() + x < board[0].length){
        if(board[n.getY() + y][n.getX() + x] == ' ' ||
           board[n.getY() + y][n.getX() + x] == 'E'){
            ans[i] = new Location(n.getX() + x, n.getY() + y, n);
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
    return board;
  }

  public static void main(String[] args) {
    String mazetext = "# #\n#  \nS E";
    Maze a = new Maze(mazetext);
    System.out.println(a);
    Location[] b = a.getNeighbors(new Location(1,1,null));
    for(int i = 0; i < 4; i++){
      if(b[i] != null){
        System.out.println(b[i].getPrev());
      }
    }
  }

}
