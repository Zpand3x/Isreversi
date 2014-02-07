package is.hopur9.isreversi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{	
	Button buttonStart1V1; // Takki til a� velja a� spila 1 vs 1 � sama s�ma

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonStart1V1 = (Button) findViewById(R.id.button1V1);
        
        buttonStart1V1.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View arg0) 
            {
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(myIntent);
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
