package com.guser.service.Notify;

//import java.io.BufferedInputStream;
import com.guser.service.R;
import com.guser.service.R.drawable;
import com.guser.service.R.layout;
import com.guser.service.R.menu;
import com.guser.service.activity.RegisterActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

public class NotifyEvent extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);


			/*buf = new BufferedInputStream(getAssets().open("me.jpeg"));

			// Create the bitmap to be set in notification.
			Bitmap bitmap = BitmapFactory.decodeStream(buf);
			buf.close();*/
			

			// Pending intent to be fired when notification is clicked
			Intent intent = new Intent(this, RegisterActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 01,
					intent, Intent.FLAG_ACTIVITY_CLEAR_TASK);

			// Get the builder to create notification.
			Builder builder = new Notification.Builder(getApplicationContext());
			
			// Set the first line of text in the platform notification template.
			builder.setContentTitle("Guser");
			
			// Set the second line of text in the platform notification
			// template.
			builder.setContentText("Content Text");
			
			// Set the third line of text in the platform notification template.
			// Don't use if you're also using setProgress(int, int, boolean);
			// they occupy the same location in the standard template.
			builder.setSubText("Sub Text");
			
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

			NotificationManager notificationManger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			
			// Post a notification to be shown in the status bar. If a
			// notification with the same id has already been posted by your
			// application and has not yet been canceled, it will be replaced by
			// the updated information.
			notificationManger.notify(01, notification);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_register, menu);
		return true;
	}

}