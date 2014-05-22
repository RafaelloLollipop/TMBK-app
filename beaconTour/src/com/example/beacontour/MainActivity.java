package com.example.beacontour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.estimote.sdk.Beacon;
import com.example.source.beacon.*;

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
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
	private List<Beacon> beacons;



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
		Intent intent = new Intent(MainActivity.this,ListBeaconsActivity.class);
		//startActivityForResult(intent,1);
		this.beacons = this.createBeacons(5);

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
				ArrayList<String> list= data.getStringArrayListExtra("beacons");
			}else if(resultCode == RESULT_CANCELED) {
				String message = data.getStringExtra("problem");
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}





	public void ChangeCurrentPlace(int positionClicked)
	{
		
		  TextView t = (TextView)findViewById(R.id.details_textView);			 
		    t.setText("Step One: blast egg");

		
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
				 
				 				    
					//makeText.setText(currentPlace.getName());
				   
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

			TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
			makeText.setText(currentPlace.getName());
			return itemView;
		}

		public MyListAdapter(){
			super(MainActivity.this,R.layout.place_view, user.getPlaces());
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

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Place> adapter = new MyListAdapter();
		Place place = null;
		switch (view.getId()) {
		case R.id.add:
			String[] places = new String[] { "Cool", "Very nice", "Hate it" };
			int nextInt = new Random().nextInt(3);
			// save the new comment to the database
			place = datasource.createPlace(places[nextInt],"content");
			adapter.add(place);
			break;

		}
		adapter.notifyDataSetChanged();
	}
	  private List<Beacon> createBeacons(int beaconCount){
		  List<Beacon> beaconList = new ArrayList<Beacon>();
		  for(int i = 0; i < beaconCount;i++){
			  beaconList.add(new Beacon(Integer.toString(i),"beacon"+i,"12121212",i,i,10*i,i*2));
		  }
		  return beaconList;
	  }


}