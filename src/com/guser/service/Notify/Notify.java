package com.guser.service.Notify;

import com.guser.service.common.*;
import com.guser.service.FBService;
import com.guser.service.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Notify {

	private Context context;
	
	public Notify(Context context)
	{
		this.context = context;
	}
	
	public void showNotify(GuserMessage message, int notifyId)
	{
		// Pending intent to be fired when notification is clicked
			Intent intentCancel = new Intent(this.context.getApplicationContext(), CancelNotifyService.class);
			Intent intentFB = new Intent(this.context.getApplicationContext(), FBService.class);
			
			//put notify id for close the notify after action
			intentCancel.putExtra(GlobalVariables.EXTRA_NotifyIdParamName, notifyId);
			intentFB.putExtra(GlobalVariables.EXTRA_NotifyIdParamName, notifyId);
			
			Bundle BundleMessage = new Bundle();
			//BundleMessage.putString("picture", message.getMsg_picture());

			//BundleMessage.putString("picture","http://pixabay.com/static/uploads/photo/2012/05/02/22/07/red-46477_640.png?i");
		
			if(String.valueOf("") != message.getMsg_link())
			{
				BundleMessage.putString("name", message.getMsg_name());
				BundleMessage.putString("link", message.getMsg_link());
				BundleMessage.putString("caption", message.getMsg_caption());
				BundleMessage.putString("description", message.getMsg_description());
				BundleMessage.putString("picture", message.getMsg_picture());
			}
			else
			{
				BundleMessage.putString("message", message.getMsg_description());
			}
	        
//			BundleMessage.putString("picture","http://pixabay.com/static/uploads/photo/2012/05/02/22/07/red-46477_640.png?i");
//			
//			BundleMessage.putString("name", "Pink Floyd");
//			BundleMessage.putString("link", "http://www.lyricsnmusic.com/pink-floyd/money-lyrics/24441721");
//			BundleMessage.putString("caption", "Money");
//			BundleMessage.putString("description", "Money, get away \n Get a good<center></center> job with good pay and you're okay \r\nMoney, it's a gas\r\nGrab that cash with both hands and make a stash\r\nNew car, caviar, ...");
//			
			
			
			
	        intentFB.putExtra(GlobalVariables.EXTRA_NotifyGuserMessage, BundleMessage);
			
	        
			PendingIntent pendingCancel = PendingIntent.getService(this.context.getApplicationContext(), notifyId, intentCancel, 0);
			PendingIntent pendingFB = PendingIntent.getService(this.context.getApplicationContext(), notifyId, intentFB, 0);
			
			// Get the builder to create notification.
			Builder builder = new Notification.Builder(this.context);
			
			// Set the first line of text in the platform notification template.
			builder.setContentTitle(BundleMessage.getString("name"));
			
			// Set the second line of text in the platform notification
			// template.
			builder.setContentText(BundleMessage.getString("caption"));
			
			// Set the third line of text in the platform notification template.
			// Don't use if you're also using setProgress(int, int, boolean);
			// they occupy the same location in the standard template.
			//builder.setSubText(subText);
			
			//set big text in android 4.1
			builder.setStyle(new Notification.BigTextStyle().bigText(BundleMessage.getString("description")));

			// Set the large number at the right-hand side of the notification.
			// This is equivalent to setContentInfo, although it might show the
			// number in a different font size for readability.
			builder.setNumber(150);
			
			// Set the "ticker" text which is displayed in the status bar when
			// the notification first arrives.
			builder.setTicker("Quack from Guser");
			
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
			//builder.setDeleteIntent(pendingIntent);
			
			// Make this notification automatically dismissed when the user
			// touches it. The PendingIntent set with
			// setDeleteIntent(PendingIntent) will be sent when this happens.
			builder.setAutoCancel(true);
			
			// Set the priority of this notification.
			builder.setPriority(1);

			//builder.setStyle(new Notification.InboxStyle().setSummaryText(subText));
			
			builder.addAction(R.drawable.ic_launcher, "close", pendingCancel);
			builder.addAction(R.drawable.com_facebook_icon, "facebook", pendingFB);
			
			// Combine all of the options that have been set and return a new
			// Notification object.
			Notification notification = builder.build();

			NotificationManager notificationManger = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
			
			// Post a notification to be shown in the status bar. If a
			// notification with the same id has already been posted by your
			// application and has not yet been canceled, it will be replaced by
			// the updated information.
			notificationManger.notify(notifyId, notification);
	}
}
