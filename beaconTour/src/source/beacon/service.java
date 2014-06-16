package source.beacon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.utils.L;
import com.example.beacontour.R;

import source.beacon.LeDeviceListAdapter;
import android.app.Activity;
import android.app.IntentService;
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
/**
 * Service which dynamically provides list of beacons
 * @author Adam
 *
 */
public class service extends IntentService {
    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
		setContentView(R.layout.activity_second);

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
						int i = 0 ;
						ArrayList<Beacon> beaconArr = new ArrayList<Beacon>(beacons);
						
						intent.putParcelableArrayListExtra("beacons", beaconArr);
						
						setResult(RESULT_OK, intent);
						finish();
					}
				});
			}
		});

		if (!beaconManager.hasBluetooth()) {
			Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
			return;
		}
		if (!beaconManager.isBluetoothEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			connectToService();
		}
    }
    private static final String TAG = ListBeaconsActivity.class.getSimpleName();

	public static final String EXTRAS_TARGET_ACTIVITY = "extrasTargetActivity";
	public static final String EXTRAS_BEACON = "extrasBeacon";

	private static final int REQUEST_ENABLE_BT = 1234;
	private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

	private BeaconManager beaconManager;
	private LeDeviceListAdapter adapter;
	/**
	 * Handlers for data returning with Intent
	 * @param requestCode <Int>
	 * @param resultCode <Int>
	 * @param data <Intent>
	 */
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

	/**
	 * connects to bluetooth scaning service
	 */
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
	/**
	 * Listens to click event, when clicked starts Activity for Intent
	 * @return
	 */
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
	/**
	 * Sends Intent with specefied message
	 * @param message <String>
	 */
	  private void sendIntentWithMessage(String message){
		  Intent intent = new Intent();
		  intent.putExtra("problem", message);
		  setResult(RESULT_CANCELED, intent);
		  finish();
	  }
}