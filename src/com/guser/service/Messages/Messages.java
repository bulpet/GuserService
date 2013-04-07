package com.guser.service.Messages;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.guser.service.R;
import com.guser.service.DB.DatabaseHandler;
import com.guser.service.Notify.Notify;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserCallTime;
import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.LastFM_TopChart;

//TODO: delete
public class Messages {

	private Context context;
	private Long dialed;
	private String number;
	private Long duration;
	private String name;
	private String label;
	private Long callType;

	public Messages(Context context) {
		this.context = context;
	}

	public void Quack() {
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
			GuserMessage message=null;
			try {
				message = new LastFM_TopChart().execute("").get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Log.i("Message", message.getMsg_name());

			Notify notify = new Notify(this.context);
			notify.showNotify(message, id);
		}
	}

	
	public GuserMessage getRandomMessage() {

		Long tmp = this.getDuration();
		DatabaseHandler db = new DatabaseHandler(this.context);
		
		GuserMessage message = db.GetRandomMessage();
		GuserCallTime call = new GuserCallTime(tmp);
				
		message.setMsg_caption(formatStringWithGuserPattern(message.getMsg_caption(),call));
		message.setMsg_description(formatStringWithGuserPattern(message.getMsg_description(),call));
		message.setMsg_name(formatStringWithGuserPattern(message.getMsg_name(),call));
		       
		return message;

	}

	public String formatStringWithGuserPattern(String text,GuserCallTime call)
	{
		return text.replaceAll(GlobalVariables.PATTERN_ContactNameMask, this.getName())
				.replaceAll(GlobalVariables.PATTERN_FullTimeMask, String.format("%02d:%02d:%02d", call.getHours(), call.getMinutes(), call.getSeconds()))
				.replaceAll(GlobalVariables.PATTERN_HourMask, String.format("%02d",call.getHours()))
				.replaceAll(GlobalVariables.PATTERN_MinuteMask, String.format("%02d",call.getMinutes()))
				.replaceAll(GlobalVariables.PATTERN_SecondeMask, String.format("%02d",call.getSeconds()));
	}
	
	public String getName() {

		return name == null ? "incognito" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDialed() {
		return dialed;
	}

	public void setDialed(Long dialed) {
		this.dialed = dialed;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getCallType() {
		return callType;
	}

	public void setCallType(Long callType) {
		this.callType = callType;
	}
}
