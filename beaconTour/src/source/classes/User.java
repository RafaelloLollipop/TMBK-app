package source.classes;

import java.util.ArrayList;
import java.util.List;

import source.beacon.ListBeaconsActivity;
import android.content.Intent;

import com.estimote.sdk.Beacon;

public class User {

	private int x,y,z;
	List<Place> Places;
	ArrayList<Beacon> Beacons = new ArrayList<Beacon>();
	ArrayList<String> BeaconsString = new ArrayList<String>();


	public User() {
		super();
		Places = new ArrayList<Place>();
	}
	
	
	public ArrayList<String> getBeaconsString() {
		return BeaconsString;
	}


	public void setBeaconsString(ArrayList<String> beaconsString) {
		BeaconsString = beaconsString;
	}


	public void LoadBeacons()
	{
	
	}
	public void LoadPlacesFromDatabase(List<Place> list)
	{	
		Places = list;
		this.Places.add(new Place("Kościół Mariacki w Krakowie","Według Jana Długosza pierwszy murowany kościół w stylu romańskim został ufundowany przez biskupa krakowskiego Iwo Odrowąża w latach 1221-1222 na miejscu pierwotnej drewnianej świątyni. Wkrótce jednak budowlę zniszczono podczas najazdów tatarskich. W latach 1290-1300 wzniesiono, częściowo na poprzednich fundamentach, wczesnogotycki kościół halowy, który konsekrowano około roku 1320-1321. Prace jednak kontynuowane były jeszcze w trzeciej dekadzie czternastego stulecia.",10,"kosciol"));
		this.Places.add(new Place("Pomnik Adama Mickiewicza w Krakowie","Pomnik powstał według projektu Teodora Rygiera, uroczyste odsłonięcie nastąpiło 16 czerwca 1898 r, czyli w setnym roku urodzin Adama Mickiewicza. Projekt pomnika został przedstawiony przez Rygiera podczas konkursu na monument Wieszcza, zorganizowanego w Krakowie w latach 1880-1890. Swoje wizje pomnika zaprezentowali wówczas m.in. następujący artyści: Tomasz Dykas, Walery Gadomski, Antoni Kurzawa, Jan Matejko i Pius Weloński. Wybór do realizacji projektu Rygiera spotkał się z krytyką, czego wyrazem były liczne polemiki prasowe oraz osobne publikacje poświęcone temu zagadnieniu. Surowemu osądowi poddawano zwłaszcza wartość artystyczną dzieła. Koszt budowy monumentu wyniósł 164 tys. złr. Większość tej kwoty pochodziła ze składek publicznych. Pomnik został odlany w firmie Nelli w Rzymie[1].",3,"adas"));
		this.Places.add(new Place("Sukiennice w Krakowie","Sukiennice podlegały przez wieki wielu przemianom i ich obecny kształt w niczym nie przypomina dawnych sukiennic. Już w roku 1257 książę Bolesław Wstydliwy przy lokacji Krakowa zobowiązał się postawić kramy sukienne. Stanowiły one podwójny rząd kramów kamiennych, tworzących jakby uliczkę pośrodku Rynku. Sukiennice w tej postaci przetrwały do połowy XIV stulecia",20,"suk"));
	}
	
	public List<Place> getPlaces() {
		return Places;
	}

	public void setPlaces(List<Place> places) {
		Places = places;
	}
}
