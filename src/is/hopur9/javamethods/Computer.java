package is.hopur9.javamethods;

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
	
	private class BackgroundTask extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog myProgressDialog = new ProgressDialog(context);
		
		@Override
		protected void onPreExecute()
		{
			myProgressDialog.setMessage("I'm thinking...");
			myProgressDialog.show();
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... arg)
		{
			try
			{
				Thread.sleep(2000);
			}
			catch (InterruptedException e)
			{
				// Nothing
			}
				
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
			
			int xy[] = ArrayProjection.IntegerProjection(pick);
			isReversi.placeDisks(xy[0], xy[1]);
			
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
