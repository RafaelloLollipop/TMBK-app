package source.classes;


public class Place {
	private long id;
	private String name;
	private int x,y,z;
	float distance;
	int photo;
	private String text;
	Boolean photoWorth;
	int beaconID;

	public Place(String name,String text)
	{
		this.name=name;
		this.text=text;
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
