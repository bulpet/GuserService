package com.guser.service.Messages;

import java.util.Random;

import android.content.Context;
import com.guser.service.R;

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

	public String getRandomMessage() {
		String[] msgArr = context.getResources().getStringArray(
				R.array.CuteMessages);
		Random r = new Random();

		int count = msgArr.length;
		int i = r.nextInt(count);

		Long tmp = this.getDuration();
		int hours = (int) (tmp / 3600);
		int minutes = (int) ((tmp % 3600) / 60);
		int seconds = (int) (tmp % 60);

		String message = msgArr[i].toString();
		String dateString = String.format("%02d:%02d:%02d", hours, minutes,
				seconds);
		String formatedMessage = message.replaceAll("##n", this.getName())
				.replaceAll("##d", dateString);

		return formatedMessage;
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
