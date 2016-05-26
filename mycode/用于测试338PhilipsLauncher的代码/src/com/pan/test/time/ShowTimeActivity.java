package com.pan.test.time;

import com.pan.test.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class ShowTimeActivity extends Activity
{

	private DisplayManager mDisplayManager;
	private RelativeLayout mcontent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
		setContentView(R.layout.activity_time);
		mcontent = (RelativeLayout) findViewById(R.id.main_layout);
		//mcontent.setVisibility(View.INVISIBLE);
	}

}
