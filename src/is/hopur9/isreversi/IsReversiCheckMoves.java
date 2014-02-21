package is.hopur9.isreversi;

public class IsReversiCheckMoves {

	public  static int[][] LegalMoves()
	{	
		int[] row;
		
		Globals.legalmoves = new int[8][8];
		Globals.isLegal = false;
		/*for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				Globals.legalmoves[i][j] = 0;
			}
		}*/
		
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				if(Globals.board[i][j] == Globals.player)
				{
					row= CheckRow(i,j, -1,-1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j, -1,0);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j, -1,1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j, 0,-1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j,0,1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j,1,-1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j, 1,0);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
					row= CheckRow(i,j,1,1);
					if(row[0] >= 0)
					{
						Globals.legalmoves[row[0]][row[1]] = Globals.player;
					}
				}
			}
		}
		return Globals.legalmoves;
	}
	
	public static int[] CheckRow(int x, int y, int deltaX, int deltaY)
	{
		int[] legalMove = new int[2];
		int[] notLegal = new int[2];
		notLegal[1] = -1;
		notLegal[0] = -1;
		
		if(x+deltaX <0 | y+deltaY <0 | x+deltaX > 7 | y+deltaY > 7)
		{
			return notLegal;
		}
		else if (Globals.board[x+deltaX][y+deltaY] == 0 | Globals.board[x+deltaX][y+deltaY] == Globals.player)
		{
			return notLegal;
		}
		
		
		while(x+deltaX >= 0 && y+deltaY >= 0 && x+deltaX <= 7 && y+deltaY <= 7)
		{
			x = x+deltaX;
			y = y+deltaY;
			
			if(Globals.board[x][y] == 0)
			{
				Globals.isLegal = true;
				legalMove[0] = x;
				legalMove[1] = y;
				break;
			}
			
			if(Globals.board[x][y] == Globals.player)
			{
				break;
			}
		}
		
		if(Globals.isLegal)
		{
			return legalMove;
		}
		else
		{
			return notLegal;
		}
	}	
}
