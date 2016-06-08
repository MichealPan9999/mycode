package com.pan.test.animphoto;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.pan.test.R;

public class ShowPhotoAnimActivity extends Activity
{
	private static final float FLING_MIN_SITANCE = 150;
	private static final float FLING_MAX_DURATION = 1000;
	private ViewFlipper viewFlipper = null;
	private float startX = 0;
	private long startT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewflipper);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startT = SystemClock.elapsedRealtime();
			break;
		case MotionEvent.ACTION_UP:
			if (SystemClock.elapsedRealtime() - startT < FLING_MAX_DURATION)
			{
				if (event.getX() - startX > FLING_MIN_SITANCE)
				{
					// 往右滑动
					viewFlipper.setInAnimation(this, R.anim.right_in);
					viewFlipper.setOutAnimation(this, R.anim.right_out);
					viewFlipper.showNext();
				}
				if (event.getX() - startX < -FLING_MIN_SITANCE)
				{
					// 往左滑动
					viewFlipper.setInAnimation(this, R.anim.left_in);
					viewFlipper.setOutAnimation(this, R.anim.left_out);
					viewFlipper.showPrevious();
				}
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
