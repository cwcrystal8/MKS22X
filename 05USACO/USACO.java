import java.util.*;
import java.io.*;

public class USACO{
  public static int bronze(String filename){
    int row, col, elevation, numSteps;
    ArrayList<String> info = new ArrayList<String>();
    try{
      Scanner inf = new Scanner(new File(filename));
      while(inf.hasNextLine()){
        String temp = inf.nextLine();
        if(temp.length() > 0){
          info.add(temp);
        }
      }

      ArrayList<String> dimensions = new ArrayList<String>();
      String s = "";
      for (int i = 0; i < info.get(0).length(); i++){
        if (info.get(0).charAt(i) == ' '){
          dimensions.add(s);
          s = "";
        }
        else{
          s += info.get(0).charAt(i);
        }
      }

      dimensions.add(s);


      row = Integer.parseInt(dimensions.get(0));
      col = Integer.parseInt(dimensions.get(1));
      elevation = Integer.parseInt(dimensions.get(2));
      numSteps = Integer.parseInt(dimensions.get(3));

      int[][] lake = new int[row][col];
      for (int i = 1; i < row + 1; i++){
        int y = 0;
        String str = "";
        for (int j = 0; j < info.get(i).length(); j++){
          if (info.get(i).charAt(j) == ' '){
            lake[i-1][y] = Integer.parseInt(str);
            str = "";
            y++;
          }
          else{
            str += info.get(i).charAt(j);
          }
        }
        lake[i-1][y] = Integer.parseInt(str);
      }

      for (int i = row + 1; i < row + 1 + numSteps; i++){
        ArrayList<String> moves = new ArrayList<String>();
        String sterr = "";
        for (int j = 0; j < info.get(i).length(); j++){
          if (info.get(i).charAt(j) == ' '){
            moves.add(sterr);
            sterr = "";
          }
          else{
            sterr += info.get(i).charAt(j);
          }
        }
        moves.add(sterr);

        int r = Integer.parseInt(moves.get(0)) - 1,
            c = Integer.parseInt(moves.get(1)) - 1,
            numIter = Integer.parseInt(moves.get(2));
        int biggest = lake[r][c];
        for (int x = r; x < r + 3; x++){
          for (int y = c; y < c + 3; y++){
            if(lake[x][y] > biggest){
              biggest = lake[x][y];
            }
          }
        }

          biggest -= numIter;

          for (int x = r; x < r + 3; x++){
            for (int y = c; y < c + 3; y++){
              if(lake[x][y] > biggest){
                lake[x][y] = biggest;
              }
            }
          }
        }
      for (int x = 0; x < row; x++){
        for (int y = 0; y < col; y++){
          if(lake[x][y] >= elevation){
            lake[x][y] = 0;
          }
          else{
            lake[x][y] = elevation - lake[x][y];
          }
        }
      }

      int total = 0;
      for (int x = 0; x < row; x++){
        for (int y = 0; y < col; y++){
          total += lake[x][y];
        }
      }

      return total * 72 * 72;

      }catch(FileNotFoundException e){
        System.out.println("File Not Found");
        return -1;
      }
    }

  private static String stringer(int[][] ary){
    String ans = "";
    for (int i = 0; i < ary.length; i++){
      for (int j = 0; j < ary[i].length; j++){
        ans += ary[i][j] + " ";
      }
      ans += "\n";
    }
    /*
    String ans = "";
    for (int i = 0; i < map.length; i++){
      for (int j = 0; j < map[i].length; j++){
        ans += map[i][j] + " ";
      }
      ans += "\n";
    }
    System.out.println(ans);
    */
    return ans;
  }

  public static int silver(String filename){
    int numMoves;
    int[] startCor = new int[2], endCor = new int[2];

    ArrayList<String> info = new ArrayList<String>();

    try{
      Scanner inf = new Scanner(new File(filename));
      while(inf.hasNextLine()){
        String temp = inf.nextLine();
        if(temp.length() > 0){
          info.add(temp);
        }
      }
    }
      catch(FileNotFoundException e){
        System.out.println("File not found");

      }
      String firstLine = info.get(0);
      String temporary = "";
      ArrayList<Integer> dimensions = new ArrayList<Integer>(3);
      for (int i = 0; i < firstLine.length(); i++){
        if(firstLine.charAt(i) == ' '){
          dimensions.add(Integer.parseInt(temporary));
          temporary = "";
        }
        else{
          temporary += firstLine.charAt(i);
        }
      }
      dimensions.add(Integer.parseInt(temporary));

      char[][] map = new char[dimensions.get(0)][dimensions.get(1)];
      numMoves = dimensions.get(2);

      for (int i = 1; i < map.length + 1; i++){
        for (int j = 0; j < map[i-1].length; j++){
          map[i-1][j] = info.get(i).charAt(j);
        }
      }

      int[][] board1 = new int[map.length][map[0].length],
              board2 = new int[map.length][map[0].length];

      String s = "";
      ArrayList<Integer> a = new ArrayList<Integer>(4);
      for (int i = 0; i < info.get(map.length + 1).length(); i++){
        if (info.get(map.length + 1).charAt(i) == ' '){
          a.add(Integer.parseInt(s));
          s = "";
        }
        else{
          s += info.get(map.length + 1).charAt(i);
        }
      }
      a.add(Integer.parseInt(s));

      startCor[0] = a.get(0) - 1;
      startCor[1] = a.get(1) - 1;
      endCor[0] = a.get(2) - 1;
      endCor[1] = a.get(3) - 1;

      int row = map.length, col = map[0].length;
      boolean useBoard1 = false;

      board1[startCor[0]][startCor[1]] = 1;
      for (int x = 0; x < numMoves; x++){
        if (useBoard1){
          board1 = new int[board1.length][board1[0].length];
          for (int i = 0; i < board1.length; i++){
            for (int j = 0; j < board1[i].length; j++){
              int[][] b = {{1,0},{0,1},{-1,0},{0,-1}};
              for(int k = 0; k < 4; k++){
                try{
                  if(map[i+b[k][0]][j+b[k][1]] != '*'){
                    board1[i][j] += board2[i+b[k][0]][j+b[k][1]];
                  }
                }catch(ArrayIndexOutOfBoundsException e){

                }
              }
            }
          }
          useBoard1 = false;
        }
        else{
          board2 = new int[board2.length][board2[0].length];
          for (int i = 0; i < board2.length; i++){
            for (int j = 0; j < board2[i].length; j++){
              int[][] b = {{1,0},{0,1},{-1,0},{0,-1}};
              for(int k = 0; k < 4; k++){
                try{
                  if(map[i+b[k][0]][j+b[k][1]] != '*'){
                    board2[i][j] += board1[i+b[k][0]][j+b[k][1]];
                  }
                }catch(ArrayIndexOutOfBoundsException e){

                }
              }
            }
          }
          useBoard1 = true;
        }
      }
      if (useBoard1){
        return board2[endCor[0]][endCor[1]];
      }else{
        return board1[endCor[0]][endCor[1]];
      }
  }

  public static void main(String[] args){
    System.out.println(USACO.bronze("info.txt") == 342144);
    System.out.println(USACO.bronze("info1.txt") == 102762432);
    System.out.println(USACO.bronze("info2.txt") == 1058992704);
    System.out.println(USACO.silver("silver1.txt") == 1);
    System.out.println(USACO.silver("silver2.txt") == 74);
    System.out.println(USACO.silver("silver3.txt") == 6435);
  }
}
