package source.classes;
/**
 * 
 * @author Adam, Antek, Rafał
 *
 */

public class Place {
	private long id;
	private String mac;
	private String name;

	private int x,y,z;
	int distance;
	String photo;
	private String text;
	Boolean photoWorth;
	int beaconID;
	/**
	 * Creates a new place object, defines a real life location
	 * 
	 * @param name <String>
	 * @param text <String>
	 * @param distance <Int>
	 * @param photo <String>
	 * @param mac <String>
	 */
	public Place(String name,String text,int distance,String photo,String mac)
	{
		this.mac=mac;
		this.name=name;
		this.text=text;
		this.distance=distance;
		this.photo=photo;
	}

	/**
	 * Mac getter
	 * @return
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Mac setter
	 * @param mac <String>
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Photo getter
	 * @return
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Photo setter
	 * @param photo <String>
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Distanse getter
	 * @return
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * Distance setter
	 * @param distance <Int>
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * Id getter
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * Id setter
	 * @param id <long>
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Name getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Name setter
	 * @param name <String>
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * to String method
	 * Will be used by the ArrayAdapter in the ListView
	 * @return
	 */
	@Override
	public String toString() {
		return name;
	}
} 
