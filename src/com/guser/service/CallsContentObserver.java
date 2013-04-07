package com.guser.service;

import com.guser.service.Messages.Messages;
import com.guser.service.Notify.Notify;
import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.command.IQuack;
import com.guser.service.quackers.command.music_top_chart;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

//TODO: delete
public class CallsContentObserver extends ContentObserver {

	private Context context;

	public CallsContentObserver(android.os.Handler handler, Context context) {
		super(handler);
		this.context = context;
	}

	@Override
	public boolean deliverSelfNotifications() {
		return true;
	}

	public void logCallLog() {
		String columns[] = new String[] { CallLog.Calls._ID,
				CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL,
				CallLog.Calls.NUMBER, CallLog.Calls.DATE,
				CallLog.Calls.DURATION, CallLog.Calls.TYPE };

		Cursor c;
//		c = this.context.getContentResolver().query(
//				Uri.parse("content://call_log/calls"), columns, null, null,
//				"Calls._ID DESC"); // last record first

		c = this.context.getContentResolver().query(
				CallLog.Calls.CONTENT_URI, columns, null, null,
				CallLog.Calls.DATE + " DESC"); // last record first

		
		c.moveToFirst();

		Messages msg = new Messages(this.context);

		Long type = c.getLong(c.getColumnIndex(CallLog.Calls.TYPE));
		Log.i("CallLog.Calls.TYPE", type.toString());
		
		msg.setDuration(c.getLong(c.getColumnIndex(CallLog.Calls.DURATION)));
		msg.setDialed(c.getLong(c.getColumnIndex(CallLog.Calls.DATE)));
		msg.setNumber(c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)));
		msg.setName(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)));
		msg.setLabel(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NUMBER_LABEL)));
		msg.setCallType(c.getLong(c.getColumnIndex(CallLog.Calls.TYPE)));
		
		int id = c.getInt(c.getColumnIndex(CallLog.Calls._ID));
		

		c.close();
		
		if (msg.getName() != null) {

			//GuserMessage message = msg.getRandomMessage();
			IQuack quacker = new music_top_chart();
			GuserMessage message = quacker.execute(null,this.context);
			
			Notify notify = new Notify(this.context);
			notify.showNotify(message, id);
		}
	}

	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		logCallLog();
	}

}