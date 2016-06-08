package com.pan.test.animphoto;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.pan.test.R;

public class ShowPhotoAnimActivity2 extends Activity implements OnGestureListener
{

	private static final int FLING_MIN_SITANCE = 100;
	private static final int FLING_MAX_DURATION = 1000;
	private ViewFlipper flipper;
	private GestureDetector detector;
	private long startT = 0;
	private long endT = 0;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewflipper);
		detector = new GestureDetector(this);
		flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startT = SystemClock.elapsedRealtime();
			break;
		case MotionEvent.ACTION_UP:
			endT = SystemClock.elapsedRealtime();
			break;

		default:
			break;
		}
		// 触屏事件交给收拾识别类处理
		// return super.onTouchEvent(event);
		return this.detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{

		if ((endT - startT) < 0 || (endT - startT) > FLING_MAX_DURATION)
		{
			return false;
		}
		if (e1.getX() - e2.getX() > FLING_MIN_SITANCE)
		{
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
			this.flipper.showNext();
			return true;
		} else if (e1.getX() - e2.getX() < -FLING_MIN_SITANCE)
		{
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.right_out));
			this.flipper.showPrevious();
			return true;
		}

		return false;
	}

}
