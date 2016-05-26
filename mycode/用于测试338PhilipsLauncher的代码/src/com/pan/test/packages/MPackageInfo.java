package com.pan.test.packages;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class MPackageInfo implements Comparable<MPackageInfo>
{

	private String title;
	private String name;
	private Drawable icon;
	private Intent intent;
	public MPackageInfo(String title, String name, Drawable icon)
	{
		super();
		this.title = title;
		this.name = name;
		this.icon = icon;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Drawable getIcon()
	{
		return icon;
	}
	public void setIcon(Drawable icon)
	{
		this.icon = icon;
	}
	
	@Override
	public String toString()
	{
		return "MPackageInfo [title=" + title + ", name=" + name + ", icon=" + icon + "]";
	}
	@Override
	public int compareTo(MPackageInfo another)
	{
		//return 0;
		if (this.title != null && another.getTitle() != null)
		{
			return this.title.compareTo(another.getTitle());
		}
		else 
		{
			throw new IllegalArgumentException(this.title+" 不存在");
		}
	}
	public Intent getIntent()
	{
		return intent;
	}
	public void setIntent(Intent intent)
	{
		this.intent = intent;
	}
	
}
