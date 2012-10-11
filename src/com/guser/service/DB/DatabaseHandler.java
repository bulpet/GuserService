package com.guser.service.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_GUSER_MESSAGES = "messages";
 
    // Messages Table Columns names
    private static final String KEY_MESSAGE = "msg";
    private static final String KEY_ID = "id";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_GUSER_MESSAGES + "(" + KEY_MESSAGE + " TEXT," + KEY_ID + " INTEGER PRIMARY KEY)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUSER_MESSAGES);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void addMessage(String message) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, message); // Contact Name
 
        // Inserting Row
        db.insert(TABLE_GUSER_MESSAGES, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    String getMessage(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_GUSER_MESSAGES, new String[] { KEY_ID,
                KEY_MESSAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        String message = cursor.getString(0);

        cursor.close();
        
        // return contact
        return message;
    }
 
    // Getting All Contacts
    public List<String> getAllMessages() {
        
    	List<String> messageList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GUSER_MESSAGES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding message to list
            	messageList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return messageList;
    }
 
    // Updating single contact
    public int updateMessage(String message, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, message);
 
        // updating row
        return db.update(TABLE_GUSER_MESSAGES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
 
    // Deleting single contact
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GUSER_MESSAGES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
 
    // Getting contacts Count
    public int getMessagesCount() {
        String countQuery = "SELECT * FROM " + TABLE_GUSER_MESSAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}