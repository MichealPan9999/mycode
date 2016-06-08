package com.pan.test.dialogtest;

import com.pan.test.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

public class AutoTuningInitDialog extends ProgressDialog
{

	public AutoTuningInitDialog(Context context, int theme)
	{
		super(context, theme);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autotuingdialog);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		return true;
	}
}
