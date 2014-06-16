package source.beacon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/**
 * Displays basic information about beacon.
 *
 *
 */
public class LeDeviceListAdapter extends BaseAdapter {

  private ArrayList<Beacon> beacons;
  private LayoutInflater inflater;
  /**
   * Constructor
   *  creates inflater and beacon list (ArrayList<Beacons>)
   * @param context
   */
  public LeDeviceListAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
    this.beacons = new ArrayList<Beacon>();
  }
  /**
   * Replaces current beacons 
   * @param newBeacons
   */
  public void replaceWith(Collection<Beacon> newBeacons) {
    this.beacons.clear();
    this.beacons.addAll(newBeacons);
    notifyDataSetChanged();
  }
  /**
   * Returns number of beacons
   */
  @Override
  public int getCount() {
    return beacons.size();
  }
  /**
   * Returns beacon based on position
   * @param position
   */
  @Override
  public Beacon getItem(int position) {
    return beacons.get(position);
  }
  /**
   * Returns item ID
   * @param position
   */
  @Override
  public long getItemId(int position) {
    return position;
  }
  /**
   * Creates, binds and return view
   * @param position <int>
   * @param view <View>
   * @param parent <ViewGroup>
   */
  @Override
  public View getView(int position, View view, ViewGroup parent) {
    view = inflateIfRequired(view, position, parent);
    bind(getItem(position), view);
    return view;
  }
  /**
   * Inflates holder with beacon properties
   * @param beacon <Beacon>
   * @param view <View>
   */
  private void bind(Beacon beacon, View view) {
    ViewHolder holder = (ViewHolder) view.getTag();
    holder.macTextView.setText(String.format("MAC: %s (%.2fm)", beacon.getMacAddress(), Utils.computeAccuracy(beacon)));
    holder.majorTextView.setText("Major: " + beacon.getMajor());
    holder.minorTextView.setText("Minor: " + beacon.getMinor());
    holder.measuredPowerTextView.setText("MPower: " + beacon.getMeasuredPower());
    holder.rssiTextView.setText("RSSI: " + beacon.getRssi());
  }
  /**
   * Returns view
   * @param view <View>
   * @param position <Int>
   * @param parent <ViewGroup>
   * @return
   */
  private View inflateIfRequired(View view, int position, ViewGroup parent) {
    if (view == null) {
      //view = inflater.inflate(R.layout.device_item, null);
      //view.setTag(new ViewHolder(view));
    }
    return view;
  }
  /**
   *  class ViewHolder
   *  
   *  View class for Beacons
   *  so their properties may be easily viewed
   * @author Adam, Antek, Rafał
   */
  static class ViewHolder {
    final TextView macTextView;
    final TextView majorTextView;
    final TextView minorTextView;
    final TextView measuredPowerTextView;
    final TextView rssiTextView;
    /**
     * Pupulates holder with data
     * @param view <View>
     */
    ViewHolder(View view) {
      macTextView = (TextView) view.findViewWithTag("mac");
      majorTextView = (TextView) view.findViewWithTag("major");
      minorTextView = (TextView) view.findViewWithTag("minor");
      measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
      rssiTextView = (TextView) view.findViewWithTag("rssi");
    }
  }
}
