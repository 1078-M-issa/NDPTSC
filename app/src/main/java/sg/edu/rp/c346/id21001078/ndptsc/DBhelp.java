package sg.edu.rp.c346.id21001078.ndptsc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NDSong.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONGS = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "artist";
    private static final String COLUMN_RATING = "ratings";

    public DBhelp(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SONG_TABLE = "CREATE TABLE " + TABLE_SONGS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT, " + COLUMN_YEAR + " INTEGER, " + COLUMN_RATING
                + " INTEGER " + ")";
        db.execSQL(CREATE_SONG_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        //db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN  module_name TEXT ");
        onCreate(db);

    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_SONGS, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int rating = cursor.getInt(4);
                Song song = new Song(id, title, singer, year, rating);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Integer> getSongList() {
        ArrayList<Integer> list = new ArrayList<>();

        String Selectquery = "SELECT " + " * " + " FROM " + TABLE_SONGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Selectquery,null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public long insertSong(String TITLE, String SINGER, int YEAR, int RATING ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, TITLE);
        values.put(COLUMN_SINGER, SINGER);
        values.put(COLUMN_YEAR, YEAR);
        values.put(COLUMN_RATING, RATING);
        long result = db.insert(TABLE_SONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;
    }
    public Song getSongDetails(String keyword) {
        ArrayList<Song> songDetails = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_TITLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_SONGS, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int rating = cursor.getInt(4);
                Song song = new Song(id ,title, singer, year, rating);
                songDetails.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Song j = songDetails.get(0);
        return j;
    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGER, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();
        return result;
    }

}
