package source.classes;

import java.util.ArrayList;
import java.util.List;

import source.beacon.ListBeaconsActivity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.estimote.sdk.Beacon;

public class User {

	private int x,y,z;
	List<Place> Places;
	ArrayList<Beacon> Beacons = new ArrayList<Beacon>();

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


	public void LoadBeacons()
	{
	
	}
	public void LoadPlacesFromDatabase(List<Place> list)
	{	
		Places = list;
		this.Places.add(new Place("KoÅ›ciÃ³Å‚ Mariacki w Krakowie","WedÅ‚ug Jana DÅ‚ugosza pierwszy murowany koÅ›ciÃ³Å‚ w stylu romaÅ„skim zostaÅ‚ ufundowany przez biskupa krakowskiego Iwo OdrowÄ…Å¼a w latach 1221-1222 na miejscu pierwotnej drewnianej Å›wiÄ…tyni. WkrÃ³tce jednak budowlÄ™ zniszczono podczas najazdÃ³w tatarskich. W latach 1290-1300 wzniesiono, czÄ™Å›ciowo na poprzednich fundamentach, wczesnogotycki koÅ›ciÃ³Å‚ halowy, ktÃ³ry konsekrowano okoÅ‚o roku 1320-1321. Prace jednak kontynuowane byÅ‚y jeszcze w trzeciej dekadzie czternastego stulecia.",10,"kosciol"));
		this.Places.add(new Place("Pomnik Adama Mickiewicza w Krakowie","Pomnik powstaÅ‚ wedÅ‚ug projektu Teodora Rygiera, uroczyste odsÅ‚oniÄ™cie nastÄ…piÅ‚o 16 czerwca 1898 r, czyli w setnym roku urodzin Adama Mickiewicza. Projekt pomnika zostaÅ‚ przedstawiony przez Rygiera podczas konkursu na monument Wieszcza, zorganizowanego w Krakowie w latach 1880-1890. Swoje wizje pomnika zaprezentowali wÃ³wczas m.in. nastÄ™pujÄ…cy artyÅ›ci: Tomasz Dykas, Walery Gadomski, Antoni Kurzawa, Jan Matejko i Pius WeloÅ„ski. WybÃ³r do realizacji projektu Rygiera spotkaÅ‚ siÄ™ z krytykÄ…, czego wyrazem byÅ‚y liczne polemiki prasowe oraz osobne publikacje poÅ›wiÄ™cone temu zagadnieniu. Surowemu osÄ…dowi poddawano zwÅ‚aszcza wartoÅ›Ä‡ artystycznÄ… dzieÅ‚a. Koszt budowy monumentu wyniÃ³sÅ‚ 164 tys. zÅ‚r. WiÄ™kszoÅ›Ä‡ tej kwoty pochodziÅ‚a ze skÅ‚adek publicznych. Pomnik zostaÅ‚ odlany w firmie Nelli w Rzymie[1].",3,"adas"));
		this.Places.add(new Place("Sukiennice w Krakowie","Sukiennice podlegaÅ‚y przez wieki wielu przemianom i ich obecny ksztaÅ‚t w niczym nie przypomina dawnych sukiennic. JuÅ¼ w roku 1257 ksiÄ…Å¼Ä™ BolesÅ‚aw Wstydliwy przy lokacji Krakowa zobowiÄ…zaÅ‚ siÄ™ postawiÄ‡ kramy sukienne. StanowiÅ‚y one podwÃ³jny rzÄ…d kramÃ³w kamiennych, tworzÄ…cych jakby uliczkÄ™ poÅ›rodku Rynku. Sukiennice w tej postaci przetrwaÅ‚y do poÅ‚owy XIV stulecia",20,"suk"));
		this.Places.add(new Place("Pomnik gÅ‚owy","RzeÅºba ta przedstawia spÄ™tanÄ… twarz mÅ‚odzieÅ„ca o pustych oczodoÅ‚ach i nosi nazwÄ™ ?Eros bendato?, co po polsku oznacza â€žEros spÄ™tanyâ€�. PowstaÅ‚a ona w 1999 roku przez znanego polskiego rzeÅºbiarza Igora Mitoraja, ktÃ³rego rzeÅºby moÅ¼na oglÄ…daÄ‡ miÄ™dzy innymi  w takich miastach jak ParyÅ¼, Rzym, Mediolan, Lozanna. Ta warta okoÅ‚o pÃ³Å‚ miliona euro rzeÅºba z brÄ…zu zostaÅ‚a podarowana miastu KrakÃ³w za darmo. W 2005 roku zostaÅ‚a ustawiona pod WieÅ¼Ä… RatuszowÄ… i w tym miejscu stoi do dziÅ›. Popularna GÅ‚owa od poczÄ…tku jest postawienia na pÅ‚ycie Rynku GÅ‚Ã³wnego budziÅ‚a w Krakowie skrajne emocje. Jedni uwaÅ¼ali jÄ… za element szpecÄ…cy Rynek GÅ‚Ã³wny w Krakowie, natomiast inni sÄ…dzili, Å¼e dobrze siÄ™ w niego wpasowuje.   Obecnie wiÄ™kszoÅ›Ä‡ przeciwnikÃ³w â€žGÅ‚owyâ€� pogodziÅ‚a siÄ™ z jej obecnoÅ›ciÄ… i nie sÅ‚ychaÄ‡ juÅ¼ tak zdecydowanych gÅ‚osÃ³w za usuniÄ™ciem jej. Warto podkreÅ›liÄ‡, Å¼e â€žEros spÄ™tanyâ€� jest jednÄ… z atrakcji Rynku GÅ‚Ã³wnego, przy ktÃ³rej turyÅ›ci chÄ™tnie robiÄ… sobie zdjÄ™cia. LokalizacjÄ™ â€žErosa spÄ™tanegoâ€� zaznaczyliÅ›my na poniÅ¼szej mapce:",1,"glowa"));

	}
	
	public List<Place> getPlaces() {
		return Places;
	}

	public void setPlaces(List<Place> places) {
		Places = places;
	}
}
