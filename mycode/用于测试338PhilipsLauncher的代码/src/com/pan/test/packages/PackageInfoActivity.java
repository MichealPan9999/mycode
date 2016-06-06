package com.pan.test.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.pan.test.R;

public class PackageInfoActivity extends Activity
{

	private ListView packageList;
	private PackageManager mPackageManager = null;
	private Intent mIntent = null;
	private List<MPackageInfo> allPackages;
	private PackageAdapter mPackageAdapter;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_packages);
		context = PackageInfoActivity.this;
		init();
		packageList.setAdapter(mPackageAdapter);
		
		packageList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = allPackages.get(position).getIntent();
				startActivity(intent);
			}
		});
	}

	private void init()
	{
		packageList = (ListView) findViewById(R.id.packagelist);
		mPackageManager = getPackageManager();
		//allPackages = getAllApps();
		allPackages = getAllSystemApps();
		mPackageAdapter = new PackageAdapter(context, allPackages);
	}
	private List<MPackageInfo> getAllSystemApps()
	{
		List<MPackageInfo> allApps = new ArrayList<MPackageInfo>();
		mIntent = new Intent(Intent.ACTION_MAIN,null);
		mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfos = mPackageManager.queryIntentActivities(mIntent, 0);
		//调用系统排序，根据名字排序
		Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(mPackageManager));
		if (allApps != null)
		{
			allApps.clear();
			for (ResolveInfo resolveInfo : resolveInfos)
			{
				String pkgName = resolveInfo.activityInfo.packageName;
				String activityName = resolveInfo.activityInfo.name;
				String appLabel = (String) resolveInfo.loadLabel(mPackageManager);
				Drawable icon = resolveInfo.loadIcon(mPackageManager);
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName,activityName));
				MPackageInfo mPackageInfo = new MPackageInfo(appLabel, pkgName, icon);
				mPackageInfo.setIntent(launchIntent);
				allApps.add(mPackageInfo);
			}
		}
		return allApps;
	}
	private List<MPackageInfo> getAllApps()
	{
		List<MPackageInfo> allPackages = new ArrayList<MPackageInfo>();
		// GET_ACTIVITIES, GET_GIDS, GET_CONFIGURATIONS, GET_INSTRUMENTATION, GET_PERMISSIONS, GET_PROVIDERS, GET_RECEIVERS, GET_SERVICES, GET_SIGNATURES, GET_UNINSTALLED_PACKAGES
		List<PackageInfo> packageinfo = mPackageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		for (PackageInfo Info2 : packageinfo)
		{
			String pkgName = Info2.packageName;
			String appLabel = (String) Info2.applicationInfo.loadLabel(mPackageManager);
			Drawable icon = Info2.applicationInfo.loadIcon(mPackageManager);
			MPackageInfo info = new MPackageInfo(appLabel, pkgName, icon);
			Intent launchIntent = mPackageManager.getLaunchIntentForPackage(pkgName);
			info.setIntent(launchIntent);
			allPackages.add(info);
		}
		return allPackages;
	}
	private List<String> queryAllSystemApp() {
		List<String> sysapplst;
		List<PackageInfo> packList = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		sysapplst = new ArrayList<String>(); 
		for (PackageInfo pack : packList) {
		 // 把系统的应用分离出来 
			if ((pack.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {    
				continue;//第三方app
			}else if ((pack.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				continue;//系统app升级后成第三方app
			}
			else {
				//系统app
				sysapplst.add(pack.packageName);
			}
		}
		return sysapplst;
	} 
}
