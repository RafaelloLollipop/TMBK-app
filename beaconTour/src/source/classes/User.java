package source.classes;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.estimote.sdk.Beacon;
import com.example.source.beacon.ListBeaconsActivity;

public class User {

	private int x,y,z;
	List<Place> Places;
	ArrayList<Beacon> Beacons = new ArrayList<Beacon>();
	ArrayList<String> BeaconsString = new ArrayList<String>();


	public User() {
		super();
		Places = new ArrayList<Place>();
		
		Place tmp = new Place("raf","wixa");
		this.Places.add(tmp);
	}
	
	
	public void LoadBeacons()
	{
	
	}
	public void LoadPlacesFromDatabase(List<Place> list)
	{
		Place tmp = new Place("raf","wixa");
		
		Places = list;
		this.Places.add(tmp);
	}
	
	public List<Place> getPlaces() {
		return Places;
	}

	public void setPlaces(List<Place> places) {
		Places = places;
	}
}
