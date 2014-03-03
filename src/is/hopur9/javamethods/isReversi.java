package is.hopur9.javamethods;

import is.hopur9.userinterface.GameActivity;

public class isReversi {
	
	public static int hasNoMoves = 0;
	public static boolean gameOver = false;

		
	public static void initBoard(){
		//In the array representing the board, 0 is empty space, 1 is a disk belonging to player 1 and 2 is player 2's disk.
		Globals.board[3][3]=1;
		Globals.board[4][4]=1;
		Globals.board[3][4]=2;
		Globals.board[4][3]=2;
	}
	
	public static void checkStatus(){
		IsReversiCheckMoves.LegalMoves();
		if (Globals.isLegal) {
			hasNoMoves = 0;
		} /*else {
			System.out.println("No legal moves!");
			hasNoMoves++;
		
			if (hasNoMoves > 1) {
				gameOver = true;
				System.out.println("GameOver!");
				newGame();
			}
			if(Globals.player == 1) {
				Globals.player++;
			} else {
				Globals.player--;
			}
		}*/
			
	}
	
	public static void newGame() {
		Globals.board = new int[8][8];
		initBoard();
        GameActivity.drawBoard();
	}
	
	public static void placeDisks(int x, int y){
	
		if (Globals.legalmoves[x][y] == Globals.player) {
			Globals.board[x][y] = Globals.player;
			FlipCoins.FlipIt(x, y);
			if(Globals.player == 1) {
				Globals.player++;
			} else {
				Globals.player--;
			}
		
		}
		checkStatus();
	}
	
	public static int rateMove(int m, int p)
	{
		int[][] boardCopy = new int[8][8];
		boardCopy = copy(Globals.board);
		
		int playerCopy = Globals.player;
		int[] move = ArrayProjection.IntegerProjection(m);
		int x = move[0];
		int y = move[1];
		
		Globals.board[x][y]	= p;
		FlipCoins.FlipIt(x, y);
		
		int rating = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (Globals.board[i][j] == p) rating++;
			}
		}
		
		Globals.board = boardCopy;
		Globals.player = playerCopy;
		
		return rating;
	}
	
	private static int[][] copy(int[][] a)
	{
		int[][] ret = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[0].length; j++)
			{
				ret[i][j] = a[i][j];
			}
		}
		return ret;
	}
	
	public static int[] score(){
		int result[] = new int[2];
		
		int scorePlayer1 = 0;
		int scorePlayer2 = 0;
		
		for(int x = 0; x < Globals.board.length; x++){
			for(int y = 0; y < Globals.board[x].length; y++){
				if (Globals.board[x][y] == 1){
					scorePlayer1++;
				}
				if (Globals.board[x][y] == 2){
					scorePlayer2++;
				}
			}
		}
		result[0] = scorePlayer1;
		result[1] = scorePlayer2;
	
		return result;
	}
	
	public static void printScore(){
		int score[] = new int[2];
		
		score = score();
		System.out.print("Player1: "+ score[0]+ "\t");
		System.out.println("Player2: "+ score[1]);
	}
	
	public static void showBoard() {
		System.out.println("\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7");
		for(int row = 0; row < 8; row++ ){
			System.out.print((row)+"");
			for(int column = 0; column < 8; column++ ){
				System.out.print("\t"+ Globals.board[column][row]);
			}
			System.out.println();
		}
	}
}