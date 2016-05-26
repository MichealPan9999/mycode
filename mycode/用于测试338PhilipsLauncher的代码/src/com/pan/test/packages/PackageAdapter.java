package com.pan.test.packages;

import java.util.ArrayList;
import java.util.List;

import com.pan.test.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PackageAdapter extends BaseAdapter
{

	private Context context;
	List<MPackageInfo> listPackage = new ArrayList<MPackageInfo>();
	
	public PackageAdapter(Context context)
	{
		super();
		this.context = context;
	}

	
	public PackageAdapter(Context context, List<MPackageInfo> listPackage)
	{
		super();
		this.context = context;
		this.listPackage = listPackage;
	}


	@Override
	public int getCount()
	{
		return listPackage.size();
	}

	@Override
	public Object getItem(int position)
	{
		return listPackage.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder ;
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		if (listPackage != null)
		{
			MPackageInfo packageInfo = listPackage.get(position);
			if (convertView == null)
			{
				holder = new ViewHolder();
				convertView = layoutInflater.inflate(R.layout.item_packages, null);
				holder.pName = (TextView) convertView.findViewById(R.id.tv_pname);
				holder.pTitle = (TextView) convertView.findViewById(R.id.tv_ptitle);
				holder.pIcon = (ImageView) convertView.findViewById(R.id.iv_ficon);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.pName.setText(packageInfo.getName());
			holder.pTitle.setText(packageInfo.getTitle());
			holder.pIcon.setImageDrawable(packageInfo.getIcon());
		}
		return convertView;
	}

	class ViewHolder
	{
		TextView pName;
		TextView pTitle;
		ImageView pIcon;
	}
}
