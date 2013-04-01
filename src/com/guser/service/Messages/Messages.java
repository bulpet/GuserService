package com.guser.service.Messages;

import android.content.Context;
import com.guser.service.R;
import com.guser.service.DB.DatabaseHandler;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserCallTime;
import com.guser.service.common.GuserMessage;

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
