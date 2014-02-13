package is.hopur9.isreversi;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		int width = 100;
		int height = 200;
		
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		Canvas canvas = new Canvas(bitmap);
		
		canvas.drawColor(Color.RED);
		
		canvas.drawRect(25,  50, 75, 150, paint);
		
		ImageView imageView = new ImageView(this);
		
		imageView.setImageBitmap(bitmap);
		
		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout.addView(imageView, params);
		layout.setBackgroundColor(Color.BLACK);
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
