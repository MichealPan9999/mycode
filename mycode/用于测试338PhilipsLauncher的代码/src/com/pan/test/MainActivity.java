package com.pan.test;

import com.pan.test.packages.PackageInfoActivity;
import com.pan.test.time.ShowTimeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{

	Intent intent = null;
	private Context mContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = MainActivity.this;
	}

	/**
	 * 显示打开的应用的信息apk名，包名，版本信息等
	 */
	public void getAllPackageInfo(View view)
	{

		intent = new Intent(mContext, PackageInfoActivity.class);
		startActivity(intent);

	}

	/**
	 * 显示系统时间
	 */
	public void showTimeInfo(View view)
	{

		intent = new Intent(mContext, ShowTimeActivity.class);
		startActivity(intent);

	}
}
