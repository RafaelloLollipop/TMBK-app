package com.example.beacontour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.utils.L;
import com.example.source.beacon.LeDeviceListAdapter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class ListBeaconsActivity extends Activity {

	private static final String TAG = ListBeaconsActivity2.class.getSimpleName();

	public static final String EXTRAS_TARGET_ACTIVITY = "extrasTargetActivity";
	public static final String EXTRAS_BEACON = "extrasBeacon";

	private static final int REQUEST_ENABLE_BT = 1234;
	private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

	private BeaconManager beaconManager;
	private LeDeviceListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		simulateBeacons();

		adapter = new LeDeviceListAdapter(this);

		L.enableDebugLogging(true);

		beaconManager = new BeaconManager(this);
		beaconManager.setRangingListener(new BeaconManager.RangingListener() {
			@Override
			public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent();
						ArrayList<String> beaconStringList = new ArrayList<String>();
						for(Beacon beacon : beacons){ 
							beaconStringList.add(beacon.toString());
						}
						intent.putStringArrayListExtra("beacons", beaconStringList);
						setResult(RESULT_OK, intent);
						finish();
					}
				});
			}
		});

	}

	@Override
	protected void onDestroy() {
		beaconManager.disconnect();

		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!beaconManager.hasBluetooth()) {
			Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
			return;
		}
		if (!beaconManager.isBluetoothEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			//startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			connectToService();
		}
	}

	@Override
	protected void onStop() {
		try {
			beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
		} catch (RemoteException e) {
			Log.d(TAG, "Error while stopping ranging", e);
		}

		super.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ENABLE_BT) {
			if (resultCode == Activity.RESULT_OK) {
				connectToService();
			} else {
				sendIntentWithMessage("Bluetooth not enabled");
				getActionBar().setSubtitle("Bluetooth not enabled");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	private void connectToService() {
		getActionBar().setSubtitle("Scanning...");
		adapter.replaceWith(Collections.<Beacon>emptyList());
		beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
			@Override
			public void onServiceReady() {
				try {
					beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
				} catch (RemoteException e) {
					sendIntentWithMessage("Cannot start ranging, something terrible happened");
					Log.e(TAG, "Cannot start ranging", e);
				}
			}
		});
	}

	private AdapterView.OnItemClickListener createOnItemClickListener() {
		return new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (getIntent().getStringExtra(EXTRAS_TARGET_ACTIVITY) != null) {
					try {
						Class<?> clazz = Class.forName(getIntent().getStringExtra(EXTRAS_TARGET_ACTIVITY));
						Intent intent = new Intent(ListBeaconsActivity.this, clazz);
						intent.putExtra(EXTRAS_BEACON, adapter.getItem(position));
						startActivity(intent);
					} catch (ClassNotFoundException e) {
						Log.e(TAG, "Finding class by name failed", e);
					}
				}
			}
		};
	}

	  private void sendIntentWithMessage(String message){
		  Intent intent = new Intent();
		  intent.putExtra("problem", message);
		  setResult(RESULT_CANCELED, intent);
		  finish();
	  }
	
	  private ArrayList<Beacon> createSimulatedBeacons(int beaconCount){
		  ArrayList<Beacon> beaconList = new ArrayList<Beacon>();
		 for(int i = 0; i < beaconCount;i++){

		  beaconList.add(new Beacon("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","beacon"+Integer.toString(i),"12121212",i,i,10*i,i*2));
		  }
		  return beaconList;
	  }
	  
	  private void simulateBeacons(){
		  Intent intent = new Intent();
		  ArrayList<String> beaconStringList = new ArrayList<String>();
		  List<Beacon> simulatedBeacons = createSimulatedBeacons(5);
		  
		  for(Beacon beacon : simulatedBeacons){ 
			  beaconStringList.add(beacon.toString());
		  }
		  
		  intent.putStringArrayListExtra("beacons", beaconStringList);
		  
		  setResult(RESULT_OK, intent);
		  finish();
		  
	  }

}
