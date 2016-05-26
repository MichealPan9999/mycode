package com.pan.test.time;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pan.test.R;

public class TimeFrag extends Fragment
{

	View content;
	private TextView date;
	private TextView time;
	private final int REFRESH_TIME = 0x001;
	private String lastTime = new String();
	private static final String TAG = "TimeFrag";
	Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			if (msg.what == REFRESH_TIME)
			{
				if (lastTime == null)
				{
					time.setText(getStringTime(":"));
					date.setText(getStringData());
				} else
				{
					if (!lastTime.equals(getStringTime(":")))
					{
						time.setText(getStringTime(":"));
						date.setText(getStringData());
					}
				}
				handler.sendEmptyMessageDelayed(REFRESH_TIME, 1000);
			}
		};
	};

	/**
	 * 获取当前时间
	 * 
	 * @return 时间字符串 24小时制
	 * @author drowtram
	 */
	public static String getStringTime(String type)
	{
		Time t = new Time();
		t.setToNow(); // 取得系统时间。
		String hour = t.hour < 10 ? "0" + (t.hour) : t.hour + ""; // 默认24小时制
		String minute = t.minute < 10 ? "0" + (t.minute) : t.minute + "";
		String second = t.second < 10 ? "0" + (t.second) : t.second + "";
		return hour + type + minute + type + second;
	}

	/**
	 * 获取当前日期，包含星期几
	 * 
	 * @return 日期字符串 xx月xx号 星期x
	 * @author drowtram
	 */
	public static String getStringData()
	{
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay))
		{
			mWay = "日";
		} else if ("2".equals(mWay))
		{
			mWay = "一";
		} else if ("3".equals(mWay))
		{
			mWay = "二";
		} else if ("4".equals(mWay))
		{
			mWay = "三";
		} else if ("5".equals(mWay))
		{
			mWay = "四";
		} else if ("6".equals(mWay))
		{
			mWay = "五";
		} else if ("7".equals(mWay))
		{
			mWay = "六";
		}
		return mMonth + "月" + mDay + "日\n" + "星期" + mWay + "\n";
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "====TimeFrag onCreate====");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.d(TAG, "====TimeFrag onCreateView====");
		content = inflater.inflate(R.layout.time, container, false);
		date = (TextView) content.findViewById(R.id.date);
		time = (TextView) content.findViewById(R.id.time);
		return content;

	}

	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(TAG, "====TimeFrag onResume====");
		handler.sendEmptyMessageDelayed(REFRESH_TIME, 1000);
	}

}
