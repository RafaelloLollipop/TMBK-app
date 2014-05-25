package source.classes;


public class Place {
	private long id;
	private String name;

	private int x,y,z;
	int distance;
	String photo;
	private String text;
	Boolean photoWorth;
	int beaconID;

	public Place(String name,String text,int distance,String photo)
	{
		this.name=name;
		this.text=text;
		this.distance=distance;
		this.photo=photo;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name;
	}
} 
