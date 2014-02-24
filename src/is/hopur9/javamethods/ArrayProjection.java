package is.hopur9.javamethods;

public class ArrayProjection {
	
	public static int PairProjection(int x, int y)
	{
		int z = x*8 + y;
		return z;
	}
	
	public static int[] IntegerProjection(int z)
	{
		int y = z % 8;
		int x = (z-y)/8;
		int[] loc = new int[2];
		loc[0] = x;
		loc[1] = y;
		return loc;
	}

	public static Integer[] MatrixProjection()
	{
		
		Integer[] arrayboard = new Integer[64];
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				arrayboard[PairProjection(i,j)] = Globals.board[i][j];
			}
		}
		return arrayboard;
	}
}
