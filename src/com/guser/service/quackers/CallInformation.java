package com.guser.service.quackers;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.guser.service.DB.DatabaseHandler;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserCallTime;
import com.guser.service.common.GuserMessage;

public class CallInformation {

	private Context context;
	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	protected void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the dialed
	 */
	public Long getDialed() {
		return dialed;
	}

	/**
	 * @param dialed the dialed to set
	 */
	protected void setDialed(Long dialed) {
		this.dialed = dialed;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	protected void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	protected void setDuration(Long duration) {
		this.duration = duration;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name == null ? "incognito" : name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	protected void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the callType
	 */
	public Long getCallType() {
		return callType;
	}

	/**
	 * @param callType the callType to set
	 */
	protected void setCallType(Long callType) {
		this.callType = callType;
	}

	/**
	 * @return the iD
	 */
	public Long getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	protected void setID(Long iD) {
		ID = iD;
	}

	private Long dialed;
	private String number;
	private Long duration;
	private String name;
	private String label;
	private Long callType;
	private Long ID;
	
	public CallInformation(Context context)
	{
		this.context = context;
	}
	
	public GuserMessage Quack()
	{
		String columns[] = new String[] { CallLog.Calls._ID,
				CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL,
				CallLog.Calls.NUMBER, CallLog.Calls.DATE,
				CallLog.Calls.DURATION, CallLog.Calls.TYPE };

		Cursor c = this.context.getContentResolver().query(
							CallLog.Calls.CONTENT_URI, columns, null, null,
							CallLog.Calls.DATE + " DESC"); // last record first
		c.moveToFirst();
		
		Long type = c.getLong(c.getColumnIndex(CallLog.Calls.TYPE));
		Log.i("CallLog.Calls.TYPE", type.toString());
		
		this.setDuration(c.getLong(c.getColumnIndex(CallLog.Calls.DURATION)));
		this.setDialed(c.getLong(c.getColumnIndex(CallLog.Calls.DATE)));
		this.setNumber(c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)));
		this.setName(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)));
		this.setLabel(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NUMBER_LABEL)));
		this.setCallType(c.getLong(c.getColumnIndex(CallLog.Calls.TYPE)));
		this.setID(c.getLong(c.getColumnIndex(CallLog.Calls._ID)));
		
		c.close();
		
		return this.getRandomMessage();
	}
	
	public GuserMessage getRandomMessage() {

		Long tmp = this.getDuration();
		DatabaseHandler db = new DatabaseHandler(this.context);
		
		GuserMessage message = db.GetRandomMessage();
		GuserCallTime call = new GuserCallTime(tmp);
				
		message.setMsg_caption(formatStringWithGuserPattern(message.getMsg_caption(),call));
		message.setMsg_description(formatStringWithGuserPattern(message.getMsg_description(),call)  + "<center>CENTER</center>\n after new line \\r\\n after RN %0A after encode");
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
}
