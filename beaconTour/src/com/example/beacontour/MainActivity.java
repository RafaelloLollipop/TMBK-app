package com.example.beacontour;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import com.estimote.sdk.Beacon;


import source.beacon.ListBeaconsActivity;
import source.classes.Place;
import source.classes.User;
import source.classes.PlacesDataSource;
import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {
	private User user;
	private PlacesDataSource datasource;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Map", "Beacons", "Details" };



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);	
		// Initilization
		//new
		user = new User();
		datasource = new PlacesDataSource(this);
		datasource.open();
		user.LoadPlacesFromDatabase(datasource.getAllPlaces());
		Log.e("TAG", "RAFAL");
		// use the SimpleCursorAdapter to show the
		// elements in a ListView

		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
				populateListView();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if (requestCode==1){
			if(resultCode==RESULT_OK){
				this.user.setBeacons(data.<Beacon>getParcelableArrayListExtra("beacons"));
				this.user.updateAvailablePlaces();
			}
			}
		
	}

	public void ChangeCurrentPlace(int positionClicked)
	{
		    Place currentPlace= user.getPlaces().get(positionClicked);
		    TextView t = (TextView)findViewById(R.id.details_textView);
			t.setText(currentPlace.getText());	
			
			t = (TextView)findViewById(R.id.details_nameView);
			t.setText(currentPlace.getName());	
			
			t = (TextView)findViewById(R.id.details_distanceView);
			t.setText("Odleglość: "+Integer.toString(currentPlace.getDistance())+" m");	
			
			
			Resources res = getResources();
			int id = res.getIdentifier("rect_"+currentPlace.getPhoto(), "drawable", getBaseContext().getPackageName());
			ImageView imageView = (ImageView) findViewById(R.id.details_image);
			imageView.setImageResource(id);
			imageView = (ImageView) findViewById(R.id.details_image);
	}
	
	public void populateListView(){
		ArrayAdapter<Place> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.beaconList);
		list.setClickable(true);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
				   TextView v=(TextView) view.findViewById(R.id.beaconList);
				   ChangeCurrentPlace(position);
				   viewPager.setCurrentItem(2);				 				    
			   } 
			});
		
		list.setAdapter(adapter);
	}


	private class MyListAdapter extends ArrayAdapter<Place> {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView= convertView;
			if (itemView == null)
			{	
				itemView = getLayoutInflater().inflate(R.layout.place_view, parent,false);
			}
			Place currentPlace= user.getPlaces().get(position);
			TextView makeText = (TextView) itemView.findViewById(R.id.item_txtName);
			makeText.setText(currentPlace.getName());
			
			Resources res = getResources();
			int id = res.getIdentifier("sq_"+currentPlace.getPhoto(), "drawable", getBaseContext().getPackageName());
			ImageView imageView = (ImageView) itemView.findViewById(R.id.item_image);
			imageView.setImageResource(id);
			
			
			return itemView;
		}

		public MyListAdapter(){
			super(MainActivity.this,R.layout.place_view, user.getAvaiblePlaces());
			   		 				    

		}
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		
		viewPager.setCurrentItem(tab.getPosition());
		if(tab.getPosition()==1) {
			
			
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void onClick(View view) {

		Intent intent = new Intent(this,ListBeaconsActivity.class);
		startActivityForResult(intent,1);
		populateListView();		    
	
	}

}