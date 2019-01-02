import java.util.Scanner;
import java.util.ArrayList;

public class TicTacToe {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean playState = true;
    boolean playLoop = true;
    int playTurn = (int)(Math.random() * 2);
    int newX = 0;
    int newY = 0;
    
    System.out.println();
    while(playLoop) {
      String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
      printBoard(board);
    while(playState) {
      if (playTurn == 0) {
        System.out.println("Your turn");
        System.out.print("Row: ");
        newX = sc.nextInt();
        System.out.print("Column: ");
        newY = sc.nextInt();
      }
      else {
        //Minimax object !
        Move cMove = minMax(board, "O");
        newX = cMove.getXCoor();
        newY = cMove.getYCoor();
      }
      //checkFull() !
      if (checkFull(newX, newY, board)) 
        System.out.println("This square is already full. Please choose another square.");
      else {
        if (playTurn == 0) {
          board[newX][newY] = "X";
          playTurn = 1;
        }
        else {
          board[newX][newY] = "O";
          playTurn = 0;
        }
        //printBoard() !
        printBoard(board);
        System.out.println();
        //checkWin() !
        if (checkWin(board) == 1) {
          System.out.println("You won!");
          playState = false;
        }
        else if (checkWin(board) == -1) {
          System.out.println("Computer won");
          playState = false;
        }
        else if (checkWin(board) == 0) {
          System.out.println("Draw");
          playState = false;
        }
      }
    }
    System.out.println("Play again? ");
    String answer = sc.next();
    if (!(answer.toLowerCase().equals("yes")))
      playLoop = false;
    playState = true;
  }
    sc.close();
  }
  
  public static void printBoard(String[][] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(" " + board[i][j] + " ");
        if (j < 2)
          System.out.print("|");
      }
      System.out.println();
      if (i < 2)
        System.out.println("-----------");
    }
  }
  
  public static boolean checkFull(int x, int y, String[][] board) {
    if (!(board[x][y].equals(" ")))
      return true;
    return false;
  }
  
  public static int checkWin(String[][] board) {
    int count = 0;
    if((board[0][0] == "X" && board[0][1] == "X" && board[0][2] == "X") || (board[1][0] == "X" && board[1][1] == "X" && board[1][2] == "X") || (board[2][0] == "X" && board[2][1] == "X" && board[2][2] == "X") || (board[0][0]== "X" && board[1][0]== "X" && board[2][0]== "X") || (board[0][1]== "X" && board[1][1]== "X" && board[2][1]== "X") || (board[0][2]== "X" && board[1][2]== "X" && board[2][2]== "X") || (board[0][0]== "X" && board[1][1]== "X" && board[2][2]== "X") || (board[0][2]== "X" && board[1][1]== "X" && board[2][0]== "X"))
      return 1;
    else if ((board[0][0] == "O" && board[0][1] == "O" && board[0][2] == "O") || (board[1][0] == "O" && board[1][1] == "O" && board[1][2] == "O") || (board[2][0] == "O" && board[2][1] == "O" && board[2][2] == "O") || (board[0][0]== "O" && board[1][0]== "O" && board[2][0]== "O") || (board[0][1]== "O" && board[1][1]== "O" && board[2][1]== "O") || (board[0][2]== "O" && board[1][2]== "O" && board[2][2]== "O") || (board[0][0]== "O" && board[1][1]== "O" && board[2][2]== "O") || (board[0][2]== "O" && board[1][1]== "O" && board[2][0]== "O"))
      return -1;
    else 
      for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
      if (board[i][j].equals(" "))
            count++;
      if (count == 0) 
            return 0;
      return 2;
  }
  
  public static Move minMax(String[][] newBoard, String player) {
    String hPlayer = "X";
    String cPlayer = "O";
    if (checkWin(newBoard) == -1) {
        Move m = new Move(-1,-1,10);
        return m;
      }
      else if (checkWin(newBoard) == 1) {
        Move m = new Move(-1,-1,-10);
        return m;
      }
      else if (checkWin(newBoard) == 0) {
        Move m = new Move(-1,-1,0);
        return m;
      }
      ArrayList<Move> moves = new ArrayList<Move>();
      //Looping through possible moves in each space
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (!(checkFull(i,j,newBoard))) {
              Move move = new Move(i,j,-1);
              newBoard[i][j] = player;
              if (player.equals(cPlayer)) {
                //Recursion to check all possible moves
                Move result = new Move(-1,-1,-1);
                result = minMax(newBoard,hPlayer);
                move.setScore(result.getScore());
              }
              else {
                Move result = new Move(-1,-1,-1);
                result = minMax(newBoard,cPlayer);
                move.setScore(result.getScore());
              }
              //Replacing lost element and adding potential move to list
              newBoard[i][j] = " ";
              moves.add(move);
            }
          }
        }
      int bestMove = 0;
      //Checking best possible move from move list
      if (player.equals(cPlayer)) {
        int bestScore = -2;
        for(int i = 0; i < moves.size(); i++) {
          if (moves.get(i).getScore() > bestScore) {
            bestScore = moves.get(i).getScore();
            bestMove = i;
          }
        }
      }
      else {
        int bestScore = 2;
        for (int i = 0; i < moves.size(); i++) {
          if (moves.get(i).getScore() < bestScore) {
            bestScore = moves.get(i).getScore();
            bestMove = i;
          }
        }
      }
      return moves.get(bestMove);
   }
}
