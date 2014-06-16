package source.classes;

import java.util.ArrayList;
import java.util.List;

import com.example.beacontour.R;

import source.beacon.ListBeaconsActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.estimote.sdk.Beacon;

public class User {

	private int x,y,z;
	List<Place> Places;
	ArrayList<Beacon> Beacons = new ArrayList<Beacon>();
	List<Place> availablePlaces;
	private Context context;

	public User() {
		super();
		Places = new ArrayList<Place>();
	}
	
	
	public ArrayList<Beacon> getBeacons() {
		return Beacons;
	}


	public void setBeacons(ArrayList<Beacon> beacons) {
		Beacons = beacons; 
	}


	public List<Place> getAvailablePlaces() {
		return availablePlaces;
	}


	public void LoadBeacons()
	{
	
	}
	public void LoadPlacesFromDatabase(List<Place> list)
	{	
		// not sure if it works
		Places = list;
		context = null;
		this.Places.add(new Place(context.getString(R.string.kosciol_name),context.getString(R.string.kosciol_opis),10,"kosciol","D4:50:FF:BE:5F:81"));
		this.Places.add(new Place(context.getString(R.string.adas_name),context.getString(R.string.adas_opis),3,"adas","F2:EC:8E:BD:F1:70"));
		this.Places.add(new Place(context.getString(R.string.suk_name),context.getString(R.string.suk_opis),20,"suk","D9:23:78:85:58:30"));
		this.Places.add(new Place(context.getString(R.string.glowa_name),context.getString(R.string.glowa_opis),1,"glowa","DONT HAVE XD"));

	}
	
	public List<Place> getPlaces() {
		return Places;
	}
	
	
	public List<String> GetMacList()
	{
		List<String> list = new ArrayList<String>();
		for (Beacon beacon : Beacons)
		{
		list.add(beacon.getMacAddress());	
		}
		return list;
	}
	
	public List<Place> getAvaiblePlaces() {
		List<Place> list = new ArrayList<Place>();
		List<String> macList = GetMacList();
		for (Place place : Places)
		{
		  if(macList.contains( place.getMac())) list.add(place);
		}
		return list;
	}
	public void updateAvailablePlaces(){
		this.availablePlaces = this.getAvaiblePlaces();
	}

	public void setPlaces(List<Place> places) {
		Places = places;
	}
}
