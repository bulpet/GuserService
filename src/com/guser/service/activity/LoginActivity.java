package com.guser.service.activity;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.guser.service.CallsContentObserver;
import com.guser.service.R;
import com.guser.service.R.layout;
import com.guser.service.R.menu;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserMessage;
import com.guser.service.common.LastFM;
import com.guser.service.common.fillMessages;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.model.*;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;

public class LoginActivity extends Activity {

	 private static final String APP_ID = "443702672382079";
	 //CallsContentObserver GuserCallObserver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		
//		fillMessages msg = new fillMessages(this.getApplicationContext());
//		msg.fillOnce();
		
		
//        GlobalVariables GV = new GlobalVariables();
//        GV.callObserver = new CallsContentObserver(new Handler(), this.getApplicationContext());
        
//		Object tmp = new LastFM().execute("","");
//		GuserMessage msg = (GuserMessage)tmp;
		
//		JSONObject j = (JSONObject)json;
//		
//		try {
//			JSONArray jarr= j.getJSONArray("track");
//			for(int i=0;i<jarr.length();i++)
//			{
//				String tmp = jarr.getJSONObject(i).getString("name");
//				Log.i("LAST-FM", "name - " + tmp);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//"track"
		
		
		CallsContentObserver GuserCallObserver = new CallsContentObserver(new Handler(),this.getBaseContext());
		
		this.getApplicationContext()
		      	.getContentResolver()
		      		.registerContentObserver(android.provider.CallLog.Calls.CONTENT_URI, false, GuserCallObserver); 
	        
        Session session = Session.getActiveSession();
		
        if (session != null && session.isOpened())
		{
//			 Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(this,Arrays.asList(GlobalVariables.FB_Ppermissions));
//			 session.requestNewReadPermissions(newPermissionsRequest);
//			 session.requestNewPublishPermissions(newPermissionsRequest);
		}
		
        
		 
		  LoginButton authButton = (LoginButton) findViewById(R.id.authButton);
		  authButton.setOnErrorListener(new OnErrorListener() {
		   @Override
		   public void onError(FacebookException error) {
		    Log.i("PhoneService", "Error " + error.getMessage());
		   }
		  });
		  
		  // set permission list, Don't foeget to add email
		  //authButton.setReadPermissions(Arrays.asList(GlobalVariables.FB_Ppermissions));
		  authButton.setPublishPermissions(Arrays.asList(GlobalVariables.FB_Ppermissions));

		  //session state call back event
		  authButton.setSessionStatusCallback(new Session.StatusCallback() {
		   
		   @Override
		   public void call(Session session, SessionState state, Exception exception) {
		    
		    if (session.isOpened()) {
		              Log.i("PhoneService","Access Token"+ session.getAccessToken());
		              Request.executeMeRequestAsync(session,
		                      new Request.GraphUserCallback() {
		                          @Override
		                          public void onCompleted(GraphUser user,Response response) {
		                              if (user != null) { 
		                               Log.i("PhoneService","User ID "+ user.getId());
		                               Log.i("PhoneService","Email "+ user.asMap().get("email"));
		                               //welcom.setText(user.asMap().get("email").toString());
		                              }
		                          }
		                      });
		          }
		    
		   }
		  });
		 }

		 @Override
		 public void onActivityResult(int requestCode, int resultCode, Intent data) {
		     super.onActivityResult(requestCode, resultCode, data);
		     Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		 }
		 
		 @Override
		    public void onDestroy() {

		     	//getContentResolver().unregisterContentObserver(GuserCallObserver);
		     	super.onDestroy();
		    }
}
		 
//
//	  @Override
//	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
//	      super.onActivityResult(requestCode, resultCode, data);
//	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
//	      
//	      String permissions[] = {
//	  		    "user_about_me",
//	  		    "user_activities",
//	  		    "user_birthday",
//	  		    "user_checkins",
//	  		    "user_education_history",
//	  		    "user_events",
//	  		    "user_groups",
//	  		    "user_hometown",
//	  		    "user_interests",
//	  		    "user_likes",
//	  		    "user_location",
//	  		    "user_notes",
//	  		    "user_online_presence",
//	  		    "user_photo_video_tags",
//	  		    "user_photos",
//	  		    "user_relationships",
//	  		    "user_relationship_details",
//	  		    "user_religion_politics",
//	  		    "user_status",
//	  		    "user_videos",
//	  		    "user_website",
//	  		    "user_work_history",
//	  		    "email",
//
//	  		    "read_friendlists",
//	  		    "read_insights",
//	  		    "read_mailbox",
//	  		    "read_requests",
//	  		    "read_stream",
//	  		    "xmpp_login",
//	  		    "ads_management",
//	  		    "create_event",
//	  		    "manage_friendlists",
//	  		    "manage_notifications",
//	  		    "offline_access",
//	  		    "publish_checkins",
//	  		    "publish_stream",
//	  		    "rsvp_event",
//	  		    "sms",
//	  		    //"publish_actions",
//
//	  		    "manage_pages"
//
//	  		  };
//	  }
//
//
//	  
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_login, menu);
//		return true;
//	}
//
//}
