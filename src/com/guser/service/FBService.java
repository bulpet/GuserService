package com.guser.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.*;
import com.facebook.android.Facebook;
import com.facebook.model.*;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserMessage;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class FBService  extends Service {

	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;
	
	 @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		 
		 Bundle bind = intent.getExtras();
			
			if(bind != null){
				int notifyId = bind.getInt(GlobalVariables.EXTRA_NotifyIdParamName);
				Bundle BundleGuserMessage = bind.getBundle(GlobalVariables.EXTRA_NotifyGuserMessage);
				
//				Bundle BundleMessage = new Bundle();
//				BundleMessage.putString("name", bind.getString("name"));
//				BundleMessage.putString("caption", bind.getString("caption"));
//				BundleMessage.putString("description",bind.getString("description"));
//				BundleMessage.putString("link", bind.getString("link"));
//				BundleMessage.putString("link", "https://developers.facebook.com/android");
//				BundleMessage.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
				
				GuserMessage message = new GuserMessage(BundleGuserMessage);
				message.Log();
				
				publishStory(BundleGuserMessage);
				
				NotificationManager notificationManger = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManger.cancel(notifyId);
			}
	        			
			return START_STICKY;
	 }
	 
	 private void publishStory(Bundle BundleGuserMessage) {
		    Session session = Session.getActiveSession();

		    //Session session = Session.openActiveSession(this);

		    if (session != null){

		        // Check for publish permissions    
//		        List<String> permissions = session.getPermissions();
//		        isSubsetOf(PERMISSIONS, permissions);
//		        
		        
//		        if (!isSubsetOf(PERMISSIONS, permissions)) {
//		            pendingPublishReauthorization = true;
//		            Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(LoginActivity.class, PERMISSIONS);
//		            
//		        session.requestNewPublishPermissions(newPermissionsRequest);
//		            return;
//		        }

//		        Bundle postParams = new Bundle();
//		        postParams.putString("name", "Facebook SDK for Android");
//		        postParams.putString("caption", "Build great social apps and get more installs.");
//		        postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
//		        postParams.putString("link", "https://developers.facebook.com/android");
//		        postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

		        Request.Callback callback= new Request.Callback() {
		            public void onCompleted(Response response) {
		                
		            	Log.i("FB CallBack","Callback ! ");

		            	/*
		            	JSONObject graphResponse = response
		                                           .getGraphObject()
		                                           .getInnerJSONObject();
		                String postId = null;
		                try {
		                    postId = graphResponse.getString("id");
		                } catch (JSONException e) {
		                    Log.i("FB Error",
		                        "JSON error "+ e.getMessage());
		                }
		                */
		            	
		                FacebookRequestError error = response.getError();
		                
		                if (error != null) {
		                	Log.i("FB CallBack",error.getErrorMessage());
		                    } 
		                else {
		                    	Log.i("FB CallBack","no Error");
		                }
		                
//		                if (error != null) {
//		                    Toast.makeText(getActivity()
//		                         .getApplicationContext(),
//		                         error.getErrorMessage(),
//		                         Toast.LENGTH_SHORT).show();
//		                    } else {
//		                        Toast.makeText(getActivity()
//		                             .getApplicationContext(), 
//		                             postId,
//		                             Toast.LENGTH_LONG).show();
//		                }
		            }
		        };

		        Request request = new Request(session, "me/feed", BundleGuserMessage, 
		                              HttpMethod.POST, callback);

		        RequestAsyncTask task = new RequestAsyncTask(request);
		        task.execute();
		    }

		}
	 
	 private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
		    for (String string : subset) {
		        if (!superset.contains(string)) {
		            return false;
		        }
		    }
		    return true;
		}

	 
	@Override
	public IBinder onBind(Intent arg0) {
	
		return null;
	}
	
}
