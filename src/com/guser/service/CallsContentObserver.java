package com.guser.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

public class CallsContentObserver extends ContentObserver {

		private Context context;
		
        public CallsContentObserver(android.os.Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override public boolean deliverSelfNotifications() { 
            return false; 
        }

        public void logCallLog() {
            String columns[]=new String[] {
                    CallLog.Calls._ID, 
                    CallLog.Calls.NUMBER, 
                    CallLog.Calls.DATE, 
                    CallLog.Calls.DURATION, 
                    CallLog.Calls.TYPE};

            long dialed;
            String number;
            String duration;
            
            
            Cursor c;
            c =  this.context.getContentResolver().query(Uri.parse("content://call_log/calls"),
                    columns, null, null, "Calls._ID DESC"); //last record first
           
            c.moveToFirst();
            
            dialed = c.getLong(c.getColumnIndex(CallLog.Calls.DATE));  
            number = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
            duration = c.getString(c.getColumnIndex(CallLog.Calls.DURATION)); 
            
            Log.i("CallLog","type: " + c.getString(4) + ", Call to number: "+ number +", registered at: "+ new Date(dialed).toString() + ", duration:" + duration);
            
            c.close();
            
            
         // Pending intent to be fired when notification is clicked
         			Intent intent = new Intent(this.context, RegisterActivity.class);
         			PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 01,
         					intent, Intent.FLAG_ACTIVITY_CLEAR_TASK);

         			// Get the builder to create notification.
         			Builder builder = new Notification.Builder(this.context.getApplicationContext());
         			
         			// Set the first line of text in the platform notification template.
         			builder.setContentTitle("Guser");
         			
         			// Set the second line of text in the platform notification
         			// template.
         			builder.setContentText("Call to number: "+ number +", duration:" + duration);
         			
         			// Set the third line of text in the platform notification template.
         			// Don't use if you're also using setProgress(int, int, boolean);
         			// they occupy the same location in the standard template.
         			builder.setSubText("registered at: "+ new Date(dialed).toString());
         			
         			// Set the large number at the right-hand side of the notification.
         			// This is equivalent to setContentInfo, although it might show the
         			// number in a different font size for readability.
         			builder.setNumber(100);
         			
         			// Set the "ticker" text which is displayed in the status bar when
         			// the notification first arrives.
         			builder.setTicker("Call Notification by Guser");
         			
         			// Set the small icon resource, which will be used to represent the
         			// notification in the status bar. The platform template for the
         			// expanded view will draw this icon in the left, unless a large
         			// icon has also been specified, in which case the small icon will
         			// be moved to the right-hand side.
         			builder.setSmallIcon(R.drawable.ic_launcher);
         			
         			// Add a large icon to the notification (and the ticker on some
         			// devices). In the platform template, this image will be shown on
         			// the left of the notification view in place of the small icon
         			// (which will move to the right side).
         			
         			/*builder.setLargeIcon(bitmap);*/
         			
         			// Supply a PendingIntent to send when the notification is cleared
         			// explicitly by the user.
         			builder.setDeleteIntent(pendingIntent);
         			
         			// Make this notification automatically dismissed when the user
         			// touches it. The PendingIntent set with
         			// setDeleteIntent(PendingIntent) will be sent when this happens.
         			builder.setAutoCancel(true);
         			
         			// Set the priority of this notification.
         			builder.setPriority(0);

         			// Combine all of the options that have been set and return a new
         			// Notification object.
         			Notification notification = builder.build();

         			NotificationManager notificationManger = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
         			
         			// Post a notification to be shown in the status bar. If a
         			// notification with the same id has already been posted by your
         			// application and has not yet been canceled, it will be replaced by
         			// the updated information.
         			notificationManger.notify(01, notification);
            
        }

        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.d("PhoneService", "StringsContentObserver.onChange( " + selfChange + ")");
            logCallLog();
        }
        

}