package com.guser.service;

import java.util.Set;

import com.guser.service.Messages.Messages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {

	@Override
    public void onReceive(Context context, Intent intent) {

		Log.i("CallReceiver", intent.getAction());
		
		Bundle bundle = intent.getExtras();
		Set<String> keys = bundle.keySet();
		
		for (String key : keys) {
	         Log.i("MYAPP##", key + "="+ bundle.getString(key));
		}     
		
		//get state when end of call 
		if(bundle.containsKey("state")==true && bundle.getString("state").equals("OFFHOOK"))
		{
//		     Intent myIntent = new Intent(context, CallListenerService.class);
//		     context.startService(myIntent);
		     
		     Messages guser = new Messages(context);
		     guser.Quack();
		     
		     Log.i("CallReceiver", "start CallListenerService !!!");
		}
	
		if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()))
		{
	     Intent myIntent = new Intent(context, CallListenerService.class);
	     context.startService(myIntent);
		}
	}
}
