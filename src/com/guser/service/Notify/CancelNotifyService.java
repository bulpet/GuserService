package com.guser.service.Notify;

import com.guser.service.common.GlobalVariables;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class CancelNotifyService extends Service {

	 @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		 
		 Bundle bind = intent.getExtras();
			
			if(bind != null){
				int notifyId = bind.getInt(GlobalVariables.EXTRA_NotifyIdParamName);
				
				NotificationManager notificationManger = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManger.cancel(notifyId);
			}
			
		 return START_STICKY;
		 
	 }
	 
	@Override
	public IBinder onBind(Intent intent) {
//		
//		Bundle bind = intent.getExtras();
//		
//		if(bind != null){
//			int notifyId = bind.getInt(GlobalVariables.EXTRA_NotifyIdParamName);
//			
//			NotificationManager notificationManger = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//			notificationManger.cancel(notifyId);
//		}
//		
		return null;
	}

}
