package source.classes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import source.classes.Place;
import source.classes.MySQLiteHelper;


public class PlacesDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.COLUMN_PLACE,MySQLiteHelper.COLUMN_IMAGENAME,MySQLiteHelper.COLUMN_DISTANCE,MySQLiteHelper.COLUMN_TEXT};
  /**
   * sets Data source
   * @param context <Context>
   */
  public PlacesDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }
  /**
   * opens new database
   * @throws SQLException
   */
  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }
  /**
   * closes database
   */
  public void close() {
    dbHelper.close();
  }
	/**
	 * creates new place based on params 
	 * @param place <String>
	 * @param text <String>
	 * @param distance <Int>
	 * @param imagename <String>
	 * @return
	 */
  public Place createPlace(String place,String text, int distance, String imagename) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_PLACE, place);
    values.put(MySQLiteHelper.COLUMN_IMAGENAME, imagename);
    values.put(MySQLiteHelper.COLUMN_DISTANCE, distance);
    values.put(MySQLiteHelper.COLUMN_TEXT, text);


    long insertId = database.insert(MySQLiteHelper.TABLE_PLACES, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_PLACES,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Place newPlace = cursorToPlace(cursor);
    cursor.close();
    return newPlace;
  }
  /**
   * Deletes comment
   * @param place <Place>
   */
  public void deleteComment(Place place) {
    long id = place.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_PLACES, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }
  /**
   * returns list (List<Place>) of all places in database
   * @return
   */
  public List<Place> getAllPlaces() {
    List<Place> places = new ArrayList<Place>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_PLACES,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Place place = cursorToPlace(cursor);
      places.add(place);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return places;
  }
  /**
   * 
   * @param cursor <Cursor>
   * @return
   */
  private Place cursorToPlace(Cursor cursor) {
	  Place place = new Place("RAF","wixSSSa",10,"beacontour","1");
	  place.setId(cursor.getLong(0));
	  place.setName(cursor.getString(1));
    return place;
  }

} 