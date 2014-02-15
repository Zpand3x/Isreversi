package is.hopur9.isreversi;

public class FlipCoins {

	public static void FlipIt(int x, int y)
	{
		Globals.player = Globals.board[x][y];
		FlipRow(x, y, -1, -1);
		FlipRow(x, y, -1, -0);
		FlipRow(x, y, -1,  1);
		FlipRow(x, y,  0, -1);
		FlipRow(x, y,  0,  1);
		FlipRow(x, y,  1, -1);
		FlipRow(x, y,  1,  0);
		FlipRow(x, y,  1,  1);
	}
	
	public static void Flip(int x, int y)
	{
		if( Globals.board[x][y] == 1)
		{
			Globals.board[x][y] = 2;
		}
		else
		{
			Globals.board[x][y] = 1;
		}
	}
	
	public static void FlipRow(int x, int y, int dx, int dy)
	{
		int opponent;
		if (Globals.player == 1)
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
		}
		else if (Globals.board[x+dx][y+dy] == 0 | Globals.board[x+dx][y+dy] == Globals.player)
		{
		}
		else
		{
			while(x+dx >= 0 && y+dy >= 0 && x+dx <= 7 && y+dy <= 7)
			{
				x = x+dx;
				y = y+dy;
				
				if(Globals.board[x][y] == Globals.player)
				{
					weNeedFlippin = true;
					break;
				}
				if(Globals.board[x][y] == 0)
				{
					break;
				}
			}
			
			if(weNeedFlippin == true)
			{
				x = x-dx;
				y = y-dy;
				
				while(Globals.board[x][y] == opponent)
				{
					Flip(x, y);
					x = x-dx;
					y = y-dy;	
				}
			}
		}
		
	}
}
