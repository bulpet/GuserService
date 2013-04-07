package com.guser.service;

import java.util.Set;

import com.guser.service.Messages.Messages;
import com.guser.service.Notify.Notify;
import com.guser.service.common.GuserApplication;
import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.CallInformation;
import com.guser.service.quackers.command.IQuack;
import com.guser.service.quackers.command.call_information;
import com.guser.service.quackers.command.music_top_chart;
import com.guser.service.utils.gutils;

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
		
		gutils utils = new gutils();
		utils.res();
		
		for (String key : keys) {
	         Log.i("CallReceiver", key + "="+ bundle.getString(key));
		}     
		
		
		if(bundle.containsKey("state") == false)
			return;
		
		GuserApplication app =  (GuserApplication)context.getApplicationContext();

		//IDLE
		//RINING
		//OFFHOOK
		if(bundle.getString("state").toUpperCase().equals("IDLE"))
		{
			if(app.getCallState().toUpperCase().equals("OFFHOOK"))
			{
				if(new gutils().IsInternetConnected(context))
				{
					GuserMessage message = FillGuserMessage(context);
				
					NotifyMessage(context,message);
				}
				else
				{
					Log.i("CallReceiver", "NO INTERNET CONNECTION");
				}
			}
		}
		
		app.setCallState(bundle.getString("state"));
	}

	private void NotifyMessage(Context context, GuserMessage message)
	{
		if(message!= null)
		{
			if(message.getMsg_id() == 0)
			{
				message.setMsg_id(new CallInformation(context).getID().intValue());
			}
			//notify message (must optional on prod)
			Notify notify = new Notify(context);
			notify.showNotify(message, message.getMsg_id());					
		}
	}
	private GuserMessage FillGuserMessage(Context context) {
		
		//load custom quacker (to be random)
		IQuack quacker = null;
		quacker = new music_top_chart();
		//quacker = new call_information();

		//execute quacker
		GuserMessage message = quacker.execute(null, context);
		
		if(message!= null)
			message.Log();
		
		return message;
		
	}
}
