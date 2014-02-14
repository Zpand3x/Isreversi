package is.hopur9.isreversi;

import android.app.Activity;
import android.media.MediaPlayer;
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
	private Integer[] mThumbIds;
	private ImageAdapter adapter;
	private char nextPlayer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nextPlayer = 'w';
        
        mThumbIds = new Integer[64];
        for (int i = 0; i < 64; i++)
        {
        	mThumbIds[i] = R.drawable.empty;
        }
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ImageAdapter(this, mThumbIds);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {
            	if (nextPlayer == 'w')
            	{
            		mThumbIds[position] = R.drawable.white;
            		nextPlayer = 'b';
            	}
            	else
            	{
            		mThumbIds[position] = R.drawable.black;
            		nextPlayer = 'w';
            	}
            	v.playSoundEffect(SoundEffectConstants.CLICK);
            	updateScores();
            	adapter.notifyDataSetChanged();
            }
        });
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