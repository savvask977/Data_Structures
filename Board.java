/* 
Author: Karampelas Savvas
AEM: 9005
email: savvaska@ece.auth.gr
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Board extends Move{
    
    public int N; //dimension factor
    public char[][] board;
    public char name; //keeps the player's name

    //default constructor
    Board(){
        this.N = 0;
        this.board = null;
        this.name = ' ';
    }
    
    //constructor
    Board(int N){
        this.N = N;
        this.board = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                board[i][j] = '.';
            }
        }
        this.board[N / 2][N / 2] = 'X';
        this.board[N / 2 - 1][N / 2] = 'O';
        this.board[N / 2][N / 2 - 1] = 'O';
        this.board[N / 2 - 1][N / 2 - 1] = 'X';
        this.name = ' ';
    }

    //constuctor that copies the board
    Board(Board b) {
        this.N = b.N;
        this.name = b.name;
        this.board = new char[this.N][this.N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                this.board[i][j] = b.board[i][j];
            }
        }
    }

    //prints the board
    void printBoard(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }    

    //returns the name of the player playing next
    char getWhosePiece(){
        return this.name;
    }    

    //returns the name of the opponent playing
    char getOpponentPiece(){
        if (getWhosePiece() == 'X'){
            return 'O';
        }else{
            return 'X';
        }
    }

    //sets who plays next move
    void setCurrentPlayer(char player){
        this.name = player;
    }
 
    //checks a direction from x, y to see if we can make a move
    boolean checkFlip(int x, int y, int dX, int dY){
        char opp = getOpponentPiece();
        if((x >= 0 && x < N) && (y >= 0 && y < N)){
            if(board[x][y] == opp){
                while((x >= 0 && x < N) && (y >= 0 && y < N)){
                    
                    //check if there is a vacant position between
                    if(board[x][y] == '.'){
                        return false;
                    }
                    if(board[x][y] == this.name){
                        return true;
                    }else{
                        x += dX;
                        y += dY;
                    }
                }
            }
        }
        //if we reach this point we havent found any opponenent pieces
        //or we are at the end of the board
        return false;
    }

    //flips the pieces from [x, y] with direction to x += dX and y += dY
    void flipPieces(int x, int y, int dX, int dY){
        char opp = getOpponentPiece();
        while(board[x][y] == opp){
            board[x][y] = this.name;
            x += dX;
            y += dY;
        }
    }

    //checks if the move [x, y] is valid
    boolean validMove(int x, int y){
        //if the position is not empty we return false
        if(board[x][y] != '.'){
            // System.out.println("The cell is not empty!");
            return false;           
        }else{
            //check the 8 possible directions 

            //check Up
            if((x != 0) && checkFlip(x - 1, y, -1, 0)){
                return true;
            }
            //check Down
            if((x != N-1) && checkFlip(x + 1, y, 1, 0)){
                return true;
            } 
            //check Left
            if((y != 0) && checkFlip(x, y - 1, 0, -1)){
                return true;
            }   
            //check Right
            if((y != N-1) && checkFlip(x, y + 1, 0, 1)){
                return true;
            }        
            //check Up and Left
            if((y != 0 && x != 0) && checkFlip(x - 1, y - 1, -1, -1)){
                return true;
            }     
            //check Up and Right
            if((x != 0 && y != N-1) && checkFlip(x - 1, y + 1, -1, 1)){
                return true;
            }      
            //check Down and Left
            if((y != 0 && x != N-1) && checkFlip(x + 1, y - 1, 1, -1)){
                return true;
            }     
            //check Down and Right
            if((y != N-1 && x != N-1) && checkFlip(x + 1, y + 1, 1, 1)){
                return true;
            }     
        }
        //if we reach this point, the move is not valid!
        return false;
    }

    //plays the piece in position [x, y] assuming it's valid
    void makeMove(int x, int y){
        //play the move
        board[x][y] = this.name;
            //check Up
            if(checkFlip(x - 1, y, -1, 0)){
                flipPieces(x - 1, y, -1, 0);
            }
            //check Down
            if(checkFlip(x + 1, y, 1, 0)){
                flipPieces(x + 1, y, 1, 0);
            } 
            //check Left
            if(checkFlip(x, y - 1, 0, -1)){
                flipPieces(x, y - 1, 0, -1);
            }   
            //check Right
            if(checkFlip(x, y + 1, 0, 1)){
                flipPieces(x, y + 1, 0, 1);
            }
            //check Up and Left
            if(checkFlip(x - 1, y - 1, -1, -1)){
                flipPieces(x - 1, y - 1, -1, -1);
            }
            //check Up and Right
            if(checkFlip(x - 1, y + 1, -1, 1)){
                flipPieces(x - 1, y + 1, -1, 1);
            }
            //check Down and Left
            if(checkFlip(x + 1, y - 1, 1, -1)){
                flipPieces(x + 1, y - 1, 1, -1);
            }
            //check Down and Right
            if(checkFlip(x + 1, y + 1, 1, 1)){
                flipPieces(x + 1, y + 1, 1, 1);
            }  
    }

    //returns an array list with the valid moves for the piece
    ArrayList<Move> getMoveList(char piece){
        char temp = this.name;
        if (piece != getWhosePiece()){
            this.name = piece;
        }
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(validMove(i, j)){
                    Move p = new Move(i, j);
                    moves.add(p);
                }
            }
        }
        this.name = temp;
        return moves;
    }

    //returns true if there is not a valid move for any player
    boolean gameOver(){
        ArrayList<Move> x = getMoveList('X');
        ArrayList<Move> o = getMoveList('O');
        if((x.isEmpty()) && (o.isEmpty())){
            // System.out.println("The game is over!");
            System.out.println("Player X has no other moves.");
            System.out.println("Player O has no other moves.");
            return true;
        }
        // System.out.println("The game is not over!");
        return false;
    }

    //returns the number of pieces of type piece
    int score(char piece){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] == piece){
                    count++;
                }
            }
        }
        return count;
    }    

    //using the move list, gets a random move out of the list
    Move getFirstMove(ArrayList<Move> possibleMoves){
       return possibleMoves.get(0);
    }

    // The simple heuristic is simply the number of our pieces minus the number of opponent pieces.
    int heuristic(char name){
        char opp = getOpponentPiece();
        int nameScore = score(name);
        int oppScore = score(opp);
        return (nameScore - oppScore);
    }

    // This is the minimax decision function. It calls minimaxValue for each position
    // on the board and returns the best move (largest value returned) in x and y.
    Move minimaxDecision(Board board){
        char opp = getOpponentPiece();
        char whoseTurn = getWhosePiece();
        ArrayList<Move> whoseTurnMoves = new ArrayList<Move>();
        whoseTurnMoves = getMoveList(whoseTurn);
        if(whoseTurnMoves.isEmpty()){
            //System.out.println("Player " + whoseTurn + " has no other moves.");
            Move wrong = new Move(-1, -1);
            return wrong;
        }else{
            int bestMove = -1000;
            int bestX = whoseTurnMoves.get(0).x;
            int bestY = whoseTurnMoves.get(0).y;
            //try all possible moves
            for(int i = 0; i < whoseTurnMoves.size(); i++){
                //copy the board
                Board optionBoard = new Board(board);
                //make move
                optionBoard.makeMove(whoseTurnMoves.get(i).x, whoseTurnMoves.get(i).y);
                //recursive call to minimaxValue
                int value = minimaxValue(optionBoard, whoseTurn, opp, 1);
                //check for best move
                if(value > bestMove){
                    bestMove = value;
                    bestX = whoseTurnMoves.get(i).x;
                    bestY = whoseTurnMoves.get(i).y;
                }
            }
            Move best = new Move(bestX, bestY);
            return best;
        }
    }


    // minimaxValue makes a recursive call to itself to search another ply in the tree.
    // It should look 3 ply ahead.  myTurn is the original player piece
    // which is needed to determine if this is a MIN or a MAX move.  It is also needed to 
    // calculate the heuristic. currentTurn flips between X and O.
    //We pass in the board, whose turn it is and whose
    //original turn it was that started the move. 
    int minimaxValue(Board board, char myTurn,char currentTurn, int depth){
        
        if((depth == 3) || this.gameOver()){
            return heuristic(myTurn);
        }
        char opp = 'X';
        if(currentTurn == 'X'){
            opp = 'O';
        }
        ArrayList<Move> moves = new ArrayList<Move>();
        moves = getMoveList(currentTurn);
        if(moves.isEmpty()){
            return minimaxValue(board, myTurn, opp, depth + 1);
        }else{
            int bestMove = -1000;
            if(myTurn != currentTurn){
                bestMove = 1000;
            }
            //try all possible moves
            for(int i = 0; i < moves.size(); i++){
                //copy the board
                Board optionBoard = new Board(board);
                //make move
                optionBoard.makeMove(moves.get(i).x, moves.get(i).y);
                //recursive call to minimaxValue
                int value = minimaxValue(optionBoard, myTurn, opp, depth + 1);
                //check for best move
                if(myTurn == currentTurn){
                    //max case
                    if(value > bestMove){
                        bestMove = value;
                    }
                }else{
                    //min case
                    if(value < bestMove){
                        bestMove = value;
                    }
                }
            }
            return bestMove;
        }
    }

    
    public static void main(String[] args){
        Scanner s1 = new Scanner(System.in);
        Board gameBoard = new Board(6);
        gameBoard.setCurrentPlayer('X');
        while(!gameBoard.gameOver()){
            gameBoard.printBoard();
            System.out.println("It is player " + gameBoard.getWhosePiece() + "'s turn.");
            System.out.println("Enter move.");
            if(gameBoard.getMoveList(gameBoard.getWhosePiece()).isEmpty()){
                System.out.println("Player " + gameBoard.getWhosePiece() + " has no other moves.");
                gameBoard.setCurrentPlayer(gameBoard.getOpponentPiece());
                continue;
            }
            int x, y;
            if(gameBoard.getWhosePiece() == 'X'){
                ArrayList<Move> possibleX = new ArrayList<Move>();
                possibleX = gameBoard.getMoveList('X');
                Move firstX = gameBoard.getFirstMove(possibleX);
                x = firstX.x;
                y = firstX.y;
            }else{
                Move moveO = new Move();
                moveO = gameBoard.minimaxDecision(gameBoard);
                x = moveO.x;
                y = moveO.y;
            }
            if(gameBoard.validMove(x, y)){
                System.out.println("The move is [" + x + ", " + y +"].");
                gameBoard.makeMove(x, y);
                gameBoard.setCurrentPlayer(gameBoard.getOpponentPiece());
            }else{
                System.out.println("Invalid move. Enter move again.");
            }
        }
        gameBoard.printBoard();
        System.out.println("The game is over!");
        System.out.println("X's score: " + gameBoard.score('X'));
        System.out.println("O's score: " + gameBoard.score('O'));
        s1.close();
    }
}
