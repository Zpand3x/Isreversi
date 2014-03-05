package Objects;

import is.hopur9.javamethods.ArrayProjection;
import is.hopur9.javamethods.Globals;
import is.hopur9.javamethods.isReversi;
import is.hopur9.userinterface.GameActivity;

import java.util.ArrayList;
import java.util.Random;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Computer 
{
	private final Context context;
	private GameActivity activity;
	
	public Computer(Context c, GameActivity a)
	{
		this.context = c;
		this.activity = a;
	}
	
	public void makeMove()
	{
		BackgroundTask task = new BackgroundTask();
		task.execute();
	}
	
	private int minimax(Board node, int depth, int player)
	{	
		ArrayList<Cell> legalMoves = node.getLegalMoves(player);
		
		if (legalMoves.isEmpty() || depth == 0)
		{
			return node.getScoreDifferential();
		}
		
		if (player == 2) // Maximizing player
		{
			int bestValue = Integer.MIN_VALUE;
			for (int i = 0; i < legalMoves.size(); i++ )
			{
				Cell move = legalMoves.get(i);
				Board child = new Board(node.getState());
				child.placeDisk(move.x, move.y, player);
				
				int val = minimax(child, depth - 1, 2);
				bestValue = Math.max(bestValue, val);
			}
			return bestValue;
		}
		else // Minimizing player
		{
			int bestValue = Integer.MAX_VALUE;
			for (int i = 0; i < legalMoves.size(); i++)
			{
				Cell move = legalMoves.get(i);
				Board child = new Board(node.getState());
				child.placeDisk(move.x, move.y, player);
				
				int val = minimax(child, depth - 1, 1);
				bestValue = Math.min(bestValue, val);
			}
			return bestValue;
		}
	}
	
	private int alphabeta(Board node, int depth, int alpha, int beta, int player)
	{
		ArrayList<Cell> legalMoves = node.getLegalMoves(player);
		
		if (legalMoves.isEmpty() || depth == 0)
		{
			return node.getScoreDifferential();
		}
		
		if (player == 2) // Maximizing player
		{
			for (int i = 0; i < legalMoves.size(); i++)
			{
				Cell move = legalMoves.get(i);
				Board child = new Board(node.getState());
				child.placeDisk(move.x, move.y, player);
				
				alpha = Math.max(alpha, alphabeta(child, depth - 1, alpha, beta, 1));
			
				if (beta <= alpha)
				{
					break;
				}
			}
			return alpha;
		}
		else
		{
			for (int i = 0; i < legalMoves.size(); i++)
			{
				Cell move = legalMoves.get(i);
				Board child = new Board(node.getState());
				child.placeDisk(move.x, move.y, player);
				
				beta = Math.min(beta, alphabeta(child, depth - 1, alpha, beta, 2));
			
				if (beta <= alpha)
				{
					break;
				}
			}
			return beta;
		}
	}
	
	private class BackgroundTask extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog myProgressDialog = new ProgressDialog(context);
		
		@Override
		protected void onPreExecute()
		{
			myProgressDialog.setMessage("I'm thinking...");
			myProgressDialog.setCancelable(false);
			myProgressDialog.show();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... arg)
		{		
			long startTime = System.currentTimeMillis();
				
			ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
			
			int cell = 0;
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (Globals.legalmoves[i][j] == 1 || Globals.legalmoves[i][j] == 2)
					{
						possibleMoves.add(cell);
					}
					
					cell++;
				}
			}
				
			/* Heimsk gervigreind */
			/*
			Random rand = new Random();
			int n = rand.nextInt(possibleMoves.size());
			
			int pick = possibleMoves.get(n);
			
			int xy[] = ArrayProjection.IntegerProjection(pick);
			
			isReversi.placeDisks(xy[0], xy[1]);
			System.out.println("COMPUTER PLACES DISK AT: " + xy[0] + " " + xy[1]);
			*/
			
			/* Gervigreind sem velur leik sem snýr við flestum skífum */
			/*
			int max = 0;
			int pick = 0;
			
			for (int i = 0; i < possibleMoves.size(); i++)
			{
				int score = isReversi.rateMove(possibleMoves.get(i), Globals.player);
				if (score > max)
				{
					max = score;
					pick = possibleMoves.get(i);
				}
			}
			*/
			
			/* Minimax gervigreind */
			int max = Integer.MIN_VALUE;
			int pick = 0;
			
			for (int i = 0; i < possibleMoves.size(); i++)
			{
				int[] move = ArrayProjection.IntegerProjection(possibleMoves.get(i));
				Board currBoard = new Board(Globals.board);
				currBoard.placeDisk(move[0], move[1], 2);
				
				int score = alphabeta(currBoard, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, 2); //minimax(currBoard, 3, 2);
				System.out.println("MINIMAX: " + score);
				
				if (score > max)
				{
					max = score;
					pick = possibleMoves.get(i);
				}
			}
			
			int xy[] = ArrayProjection.IntegerProjection(pick);
			isReversi.placeDisks(xy[0], xy[1]);
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			
			// Bíðum í smá stund ef tölvan var fljót að hugsa 
			// svo að notandi sjái að hún sé að gera.
			if (elapsedTime < 100)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// Nothing
				}
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result)
		{
			myProgressDialog.dismiss();
			activity.update();
			super.onPostExecute(result);
		}
		
	}
	
}
