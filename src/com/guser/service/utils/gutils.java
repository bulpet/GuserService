package com.guser.service.utils;

import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.guser.service.quackers.command.IQuack;

public class gutils {

	public void res()
	{
//		Reflections reflections = new Reflections("com.guser.service.quacker.command");
//
//	     Set<Class<? extends IQuack>> subTypes = reflections.getSubTypesOf(IQuack.class);
//	     
//	     Long i = (long) subTypes.size();
//
//	     Log.i("Reflaction", i.toString());
	     //Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SomeAnnotation.class);
	}
	
	public int GetRandomInt(int min,int max)
	{
		int i1;
		Random r = new Random();
		i1 = r.nextInt(max - min + 1) + min;
		
		return i1;
	}
	
	public Boolean IsInternetConnected(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if ( 
				connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED || 
				connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED 
			)
			return false;
			
		return true;
		    
	}
	
	public String secondsToHms(Long d) {
		Long h = (long) Math.floor(d / 3600);
		Long m = (long) Math.floor(d % 3600 / 60);
		Long s = (long) Math.floor(d % 3600 % 60);
	
		return ((h > 0 ? h + ":" : "") + (m > 0 ? (h > 0 && m < 10 ? "0" : "") + m + ":" : "0:") + (s < 10 ? "0" : "") + s); 
		
	}
	
	public String secondsToHms(String d)
	{
		Long tmp = Long.getLong(d);
		
		if(tmp == null)
			return "";
		else
			return secondsToHms(tmp);
	}
}
