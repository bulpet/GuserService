package com.guser.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallReceiver extends BroadcastReceiver {

	@Override
    public void onReceive(Context context, Intent intent) {

	     Intent myIntent = new Intent(context, CallListenerService.class);
	     context.startService(myIntent);
	}
}
