package com.guser.service.activity;

import com.guser.service.CallsContentObserver;
import com.guser.service.R;
import com.guser.service.DB.DatabaseHandler;
import com.guser.service.R.layout;
import com.guser.service.R.menu;
import com.guser.service.common.GlobalVariables;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        Button btn = (Button) findViewById(R.id.btn_H);
        btn.setOnClickListener(onClickListener);

        btn = (Button) findViewById(R.id.btn_M);
        btn.setOnClickListener(onClickListener);
        
        btn = (Button) findViewById(R.id.btn_S);
        btn.setOnClickListener(onClickListener);
        
        btn = (Button) findViewById(R.id.btn_C);
        btn.setOnClickListener(onClickListener);
        
        btn = (Button) findViewById(R.id.btn_FT);
        btn.setOnClickListener(onClickListener);
                
//        this.getApplicationContext()
//      	.getContentResolver()
//      		.registerContentObserver(
//      				android.provider.CallLog.Calls.CONTENT_URI, true,
//      					new CallsContentObserver(new Handler(), this.getApplicationContext())); 

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_register, menu);
        
        return true;
    }
    public void SaveClick(View view)
    {
    	EditText txt = (EditText)findViewById(R.id.editText1);
    	String message = txt.getText().toString();
        
    	//DatabaseHandler db = new DatabaseHandler(getBaseContext());
        //db.addMessage(message);
    }
    
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            
        	switch(v.getId()){
                case R.id.btn_H:
                	InsertTextPattern(GlobalVariables.PATTERN_HourMask);
                break;
                case R.id.btn_M:
                	InsertTextPattern(GlobalVariables.PATTERN_MinuteMask);
                break;
                case R.id.btn_S:
                	InsertTextPattern(GlobalVariables.PATTERN_SecondeMask);
                break;
                case R.id.btn_C:
                	InsertTextPattern(GlobalVariables.PATTERN_ContactNameMask);
                break;
                case R.id.btn_FT:
                	InsertTextPattern(GlobalVariables.PATTERN_FullTimeMask);
                break;
            }
        }
        
        private void InsertTextPattern(String pattern)
        {
        	if(!("").equals(pattern)){
	        	EditText txt = (EditText)findViewById(R.id.editText1);
	        	
	        	int start =txt.getSelectionStart(); //this is to get the the cursor position
	        	txt.getText().insert(start, pattern);
        	}
        }
    };
}
