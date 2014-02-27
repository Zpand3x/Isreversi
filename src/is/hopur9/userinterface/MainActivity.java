package is.hopur9.userinterface;

import is.hopur9.isreversi.R;
import is.hopur9.isreversi.R.id;
import is.hopur9.isreversi.R.layout;
import is.hopur9.isreversi.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{	
	Button buttonStart1V1; // Takki til að velja að spila 1 vs 1 á sama síma
	Button buttonStartAI;  // Takki til að velja að spila á móti tölvu 

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonStart1V1 = (Button) findViewById(R.id.button1V1);
        buttonStartAI = (Button) findViewById(R.id.buttonAI);
        
        buttonStart1V1.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View arg0) 
            {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        
        buttonStartAI.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	{
        		Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
