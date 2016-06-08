package com.pan.test.dialogtest;

import com.pan.test.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class ShowDialogActivity extends Activity
{

	private AutoTuningInitDialog progressDialog = null;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		setDtvConfigTask task = new setDtvConfigTask();
		task.execute();
	}

	/**
	 * 异步处理
	 * 
	 * @author panzq
	 *
	 */
	class setDtvConfigTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			progressDialog = new AutoTuningInitDialog(mContext, R.style.dialog);
			progressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0)
		{
			// TODO Auto-generated method stub
			try
			{
				Thread.sleep(5000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			// TODO Auto-generated method stub
			if (progressDialog != null && progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
			super.onPostExecute(result);
		}
	}

}
