package Objects;

import is.hopur9.javamethods.Globals;

import java.util.ArrayList;

public class Board 
{
	private int[][] state;
	
	public Board()
	{
		state = new int[8][8];
		boardInit();
	}
	
	public Board(int[][] boardState)
	{
		state = new int[8][8];
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				state[i][j] = boardState[i][j];
			}
		}
	}
	
	public void boardInit()
	{
		state[3][3]=1;
		state[4][4]=1;
		state[3][4]=2;
		state[4][3]=2;
	}
	
	public int[][] getState()
	{
		return state;
	}
	
	public void placeDisk(int x, int y, int p)
	{
		state[x][y] = p;
		flipDisks(x, y, p);
	}
	
	public int getScoreDifferential()
	{
		int score1 = 0, score2 = 0;
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (state[i][j] == 1)		score1++;
				else if (state[i][j] == 2)	score2++;
			}
		}
		
		return score2 - score1;
	}
	
	public ArrayList<Cell> getLegalMoves(int player)
	{
		ArrayList<Cell> legalMoves = new ArrayList<Cell>();
		int[] row;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (state[i][j] == player)
				{
					row = checkRow(i, j, -1, -1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, -1, 0, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, -1, 1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, 0, -1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, 0, 1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, 1, -1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, 1, 0, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
					
					row = checkRow(i, j, 1, 1, player);
					if (row[0] >= 0)
					{
						legalMoves.add(new Cell(row[0], row[1]));
					}
				}
			}
		}
		
		return legalMoves;
	}
	
	private int[] checkRow(int x, int y, int deltaX, int deltaY, int player)
	{
		int[] legalMove = new int[2];
		int[] notLegal = new int[2];
		notLegal[1] = -1;
		notLegal[0] = -1;
		
		if(x+deltaX <0 | y+deltaY <0 | x+deltaX > 7 | y+deltaY > 7)
		{
			return notLegal;
		}
		else if (state[x+deltaX][y+deltaY] == 0 | state[x+deltaX][y+deltaY] == player)
		{
			return notLegal;
		}
		
		boolean isLegal = false;
		while(x+deltaX >= 0 && y+deltaY >= 0 && x+deltaX <= 7 && y+deltaY <= 7)
		{
			x = x+deltaX;
			y = y+deltaY;
			
			if(state[x][y] == 0)
			{
				isLegal = true;
				legalMove[0] = x;
				legalMove[1] = y;
				break;
			}
			
			if(state[x][y] == player)
			{
				break;
			}
		}
		
		if(isLegal)
		{
			return legalMove;
		}
		else
		{
			return notLegal;
		}
	}
	
	private void flipDisks(int x, int y, int p)
	{
		flipRow(x, y, -1, -1, p);
		flipRow(x, y, -1, -0, p);
		flipRow(x, y, -1,  1, p);
		flipRow(x, y,  0, -1, p);
		flipRow(x, y,  0,  1, p);
		flipRow(x, y,  1, -1, p);
		flipRow(x, y,  1,  0, p);
		flipRow(x, y,  1,  1, p);
	}
	
	private void flip(int x, int y)
	{
		if (state[x][y] == 1)
		{
			state[x][y] = 2;
		}
		else
		{
			state[x][y] = 1;
		}
	}
	
	private void flipRow(int x, int y, int dx, int dy, int p)
	{
		int opponent;
		
		if (p == 1)
		{
			opponent = 2;
		}
		else 
		{
			opponent = 1;
		}
		
		boolean weNeedFlippin = false;
		
		if(x+dx <0 | y+dy <0 | x+dx > 7 | y+dy > 7)
		{
			/* Ekkert */
		}
		else if (state[x+dx][y+dy] == 0 | state[x+dx][y+dy] == p)
		{
			/* Ekkert */
		}
		else
		{
			while(x+dx >= 0 && y+dy >= 0 && x+dx <= 7 && y+dy <= 7)
			{
				x = x+dx;
				y = y+dy;
				
				if(state[x][y] == p)
				{
					weNeedFlippin = true;
					break;
				}
				if(state[x][y] == 0)
				{
					break;
				}
			}
			
			if(weNeedFlippin == true)
			{
				x = x-dx;
				y = y-dy;
				
				while(state[x][y] == opponent)
				{
					flip(x, y);
					x = x-dx;
					y = y-dy;	
				}
			}
		}
		
	}
}
