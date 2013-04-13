package com.guser.service.DB;

import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserMessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class ArtistDBHandler extends SQLiteOpenHelper {

	

	public ArtistDBHandler(Context context) {
		super(context, GlobalVariables.DB_ARTIST_DATABASENAME, null, GlobalVariables.DB_ARTIST_DATABASEVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_CONTACTS_TABLE = "CREATE TABLE [" + GlobalVariables.DB_ARTIST_TABLENAME + "] (" +
	        		"[" + GlobalVariables.DB_ARTIST_FIELD_artistID + "] INTEGER  PRIMARY KEY NULL," + 
	        		"[" + GlobalVariables.DB_ARTIST_FIELD_artistImageUrl + "] TEXT  NULL," +
	        		"[" + GlobalVariables.DB_ARTIST_FIELD_artistPail + "] BOOLEAN  true," +
	        		"[" + GlobalVariables.DB_ARTIST_FIELD_artistName + "] NVARCHAR(150)  NULL)";


		 db.execSQL(CREATE_CONTACTS_TABLE);
		
	}

	//Add new Artist
	public void addArtist(String artistName, String artistImageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
               
        values.put(GlobalVariables.DB_ARTIST_FIELD_artistName, artistName.toLowerCase());
        values.put(GlobalVariables.DB_ARTIST_FIELD_artistImageUrl, artistImageUrl);
        values.put(GlobalVariables.DB_ARTIST_FIELD_artistPail, true);
                
         // Inserting Row
        db.insert(GlobalVariables.DB_ARTIST_TABLENAME, null, values);
        db.close(); // Closing database connection
    }
	
	 public Bundle GetRandomArtist()
	    {
	    	 String countQuery = "SELECT * FROM " + GlobalVariables.DB_ARTIST_TABLENAME + 
	    			 					" WHERE " + GlobalVariables.DB_ARTIST_FIELD_artistPail + " = 1 ORDER BY RANDOM() LIMIT 1";
	    	 Bundle data = new Bundle();
	    	 
	    	 
	         SQLiteDatabase db = this.getReadableDatabase();
	         Cursor cursor = db.rawQuery(countQuery, null);
	         
	         if (cursor.moveToFirst()) {
       	
	        	 
	         	data.putString(GlobalVariables.DB_ARTIST_FIELD_artistName,
	         				cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_ARTIST_FIELD_artistName)));
	         	
	         	data.putString(GlobalVariables.DB_ARTIST_FIELD_artistImageUrl,
         				cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_ARTIST_FIELD_artistImageUrl)));
	         }
	         cursor.close();
	         db.close(); // Closing database connection
	         
	         return data;
	    }
	 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + GlobalVariables.DB_ARTIST_TABLENAME);
 
        // Create tables again
        onCreate(db);
	}

}
