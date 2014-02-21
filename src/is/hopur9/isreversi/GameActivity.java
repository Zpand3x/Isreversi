package is.hopur9.isreversi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class GameActivity extends Activity 
{
	public static Integer[] mThumbIds;
	private ImageAdapter adapter;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        mThumbIds = new Integer[64];
        
        
        isReversi.initBoard();
        drawBoard();
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ImageAdapter(this, mThumbIds);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {
            	int tempPlayer = Globals.player;
            	int[] xy = ArrayProjection.IntegerProjection(position);
            	
            	
            	isReversi.placeDisks(xy[0], xy[1]);
            	System.out.println(""+Globals.player);
            	System.out.println("x "+xy[0]+" y " + xy[1]);

            	
            	if (Globals.player != tempPlayer) {
            		drawBoard();
                	adapter.notifyDataSetChanged();
                	updateScores();
                	v.playSoundEffect(SoundEffectConstants.CLICK);
            	}
            	
            	/*
            	if (nextPlayer == 1)
            	{
            		mThumbIds[position] = R.drawable.white;
            		nextPlayer = 2;
            	}
            	else
            	{
            		mThumbIds[position] = R.drawable.black;
            		nextPlayer = 1;
            	}
            	v.playSoundEffect(SoundEffectConstants.CLICK);
            	updateScores();
            	adapter.notifyDataSetChanged();
            	*/
            }
        });
    }
    
    public static void drawBoard() {
    	
    	Integer[] board = ArrayProjection.MatrixProjection();
        
        for (int i = 0; i < 64; i++)
        {
        	if (board[i] == 1) {
        		mThumbIds[i] = R.drawable.white;
        	} else if (board[i] == 2) {
        		mThumbIds[i] = R.drawable.black;
        	} else {
        		mThumbIds[i] = R.drawable.empty;
        	}
        }
        
        IsReversiCheckMoves.LegalMoves();
        
        int cell = 0;
        for (int i = 0; i < 8; i++)
        {
        	for (int j = 0; j < 8; j++)
        	{
        		if (Globals.legalmoves[i][j] == 1 || Globals.legalmoves[i][j] == 2)
        		{
        			mThumbIds[cell] = R.drawable.legal;
        		}
        		
        		cell++;
        	}
        }
    }
    
    
    private void updateScores()
    {
    	int w = 0;
    	int b = 0;
    	for (int i = 0; i < mThumbIds.length; i++)
    	{
    		if (mThumbIds[i] == R.drawable.white) w++;
    		else if (mThumbIds[i] == R.drawable.black) b++;
    	}
    	TextView wScore = (TextView) findViewById(R.id.whiteCnt);
    	wScore.setText(w + "");
    	
    	TextView bScore = (TextView) findViewById(R.id.blackCnt);
    	bScore.setText(b + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}