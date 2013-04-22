package com.guser.service.DB;

import java.util.ArrayList;
import java.util.List;

import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserMessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

 
    public DatabaseHandler(Context context) {
        super(context, GlobalVariables.DB_NAME, null, GlobalVariables.DB_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE [" + GlobalVariables.DB_TABLE_GUSER_MESSAGES + "] (" +
										        		"[" + GlobalVariables.DB_FIELD_msg_id + "] INTEGER  PRIMARY KEY NULL," + 
										        		"[" + GlobalVariables.DB_FIELD_msg_description + "] TEXT  NULL," +
										        		"[" + GlobalVariables.DB_FIELD_msg_name + "] NVARCHAR(250)  NULL," +
										        		"[" + GlobalVariables.DB_FIELD_msg_caption + "] NVARCHAR(500)  NULL," +
										        		"[" + GlobalVariables.DB_FIELD_msg_link + "] NVARCHAR(1000)  NULL," +
										        		"[" + GlobalVariables.DB_FIELD_msg_picture + "] NVARCHAR(1000)  NULL," +
										        		"[" + GlobalVariables.DB_FIELD_msg_call_dir + "] BOOLEAN  NULL)";
        
        
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + GlobalVariables.DB_TABLE_GUSER_MESSAGES);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void addMessage(GuserMessage message) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
               
        values.put(GlobalVariables.DB_FIELD_msg_description, message.getMsg_description());
        values.put(GlobalVariables.DB_FIELD_msg_name, message.getMsg_name());
        values.put(GlobalVariables.DB_FIELD_msg_caption, message.getMsg_caption());
        values.put(GlobalVariables.DB_FIELD_msg_link, message.getMsg_link());
        values.put(GlobalVariables.DB_FIELD_msg_picture, message.getMsg_picture());
        values.put(GlobalVariables.DB_FIELD_msg_call_dir, message.getMsg_call_dir());
        
         // Inserting Row
        db.insert(GlobalVariables.DB_TABLE_GUSER_MESSAGES, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public GuserMessage getMessage(int id) {
        
    	SQLiteDatabase db = this.getReadableDatabase();
        GuserMessage message = null;
        
        Cursor cursor = db.query(GlobalVariables.DB_TABLE_GUSER_MESSAGES, 
				        			new String[] { 	GlobalVariables.DB_FIELD_msg_id,
				        							GlobalVariables.DB_FIELD_msg_description,
				        							GlobalVariables.DB_FIELD_msg_name,
				        							GlobalVariables.DB_FIELD_msg_caption,
				        							GlobalVariables.DB_FIELD_msg_link,
				        							GlobalVariables.DB_FIELD_msg_picture,
				        							}, 
									GlobalVariables.DB_FIELD_msg_id + "=?",
				    				new String[] { String.valueOf(id) }, null, null, null, null);
        
        if (cursor != null)
        {    cursor.moveToFirst();
 
        	
        	message = new GuserMessage();
        	
        	message.setMsg_id(cursor.getInt(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_id)));
        	message.setMsg_description(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_description)));
        	message.setMsg_name(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_name)));
        	message.setMsg_caption(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_caption)));
        	message.setMsg_link(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_link)));
        	message.setMsg_picture(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_picture)));
        	
        }
        cursor.close();
        db.close(); // Closing database connection
        
        return message;
    }
 
    // Getting All Contacts
    public List<GuserMessage> getAllMessages() {
        
    	List<GuserMessage> messageList = new ArrayList<GuserMessage>();
    	
        // Select All Query
        String selectQuery = "SELECT  * FROM " + GlobalVariables.DB_TABLE_GUSER_MESSAGES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding message to list
            	GuserMessage message = new GuserMessage();
            	
             	message.setMsg_id(cursor.getInt(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_id)));
             	message.setMsg_description(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_description)));
             	message.setMsg_name(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_name)));
             	message.setMsg_caption(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_caption)));
             	message.setMsg_link(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_link)));
             	message.setMsg_picture(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_picture)));
             	//message.setMsg_call_dir(cursor.getInt(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_call_dir)));
             	
            	messageList.add(message);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close(); // Closing database connection
        
        return messageList;
    }
 
    public GuserMessage GetRandomMessage()
    {
    	 String countQuery = "SELECT * FROM " + GlobalVariables.DB_TABLE_GUSER_MESSAGES + " ORDER BY RANDOM() LIMIT 1";
    	 GuserMessage message = null;
    	 
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);
         
         if (cursor.moveToFirst()) {
         	message = new GuserMessage();
        	
         	message.setMsg_id(cursor.getInt(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_id)));
         	message.setMsg_description(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_description)));
         	message.setMsg_name(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_name)));
         	message.setMsg_caption(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_caption)));
         	message.setMsg_link(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_link)));
         	message.setMsg_picture(cursor.getString(cursor.getColumnIndex(GlobalVariables.DB_FIELD_msg_picture)));
         }
         cursor.close();
         db.close(); // Closing database connection
         
         return message;
    }
    // Updating single contact
    public int updateMessage(GuserMessage message, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(GlobalVariables.DB_FIELD_msg_description, message.getMsg_description());
        values.put(GlobalVariables.DB_FIELD_msg_name, message.getMsg_name());
        values.put(GlobalVariables.DB_FIELD_msg_caption, message.getMsg_caption());
        values.put(GlobalVariables.DB_FIELD_msg_link, message.getMsg_link());
        values.put(GlobalVariables.DB_FIELD_msg_picture, message.getMsg_picture());
        values.put(GlobalVariables.DB_FIELD_msg_call_dir, message.getMsg_call_dir());
        
        // updating row
        return db.update(GlobalVariables.DB_TABLE_GUSER_MESSAGES, values, GlobalVariables.DB_FIELD_msg_id + " = ?",
                new String[] { String.valueOf(id) });
        
    }
 
    // Deleting single contact
    public void deleteMessage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(GlobalVariables.DB_TABLE_GUSER_MESSAGES, GlobalVariables.DB_FIELD_msg_id + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
 
    // Getting contacts Count
    public int getMessagesCount() {
        String countQuery = "SELECT * FROM " + GlobalVariables.DB_TABLE_GUSER_MESSAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}